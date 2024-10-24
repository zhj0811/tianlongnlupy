package com.cctegitc.ai.function.modules.robotchat.service;

import com.cctegitc.ai.function.dto.UploadFileDto;
import com.cctegitc.ai.function.service.impl.NormalFileUploadService;
import com.cctegitc.ai.function.service.impl.WebSocketServer1;
import com.cctegitc.ai.function.util.StringUtils;
import com.cctegitc.ai.function.util.file.MD5Util;
import com.google.gson.Gson;
import com.cctegitc.ai.function.db.pojo.Attribute;
import com.iflytek.mt_scylla.mt_scylla;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 名称
 *
 * @FileName DispatchServiceImp
 * @Author ShiGuangWei
 * @Date 2022/9/20 15:40
 * @Modifier
 * @ModifiedDate
 * @Description 智能调度 服务实现类
 * @See
 **/
@Service
@Slf4j
public class DispatchService {

    public static final int SCY_SUCCESS = 0;
    public static final int CHUNK_SIZE = 2048;
    @Value("${dispatch.iatparam}")
    private String iatparam; //识别参数

    @Value("${dispatch.ttsparam}")
    private String ttsparam; //合成参数

    private byte[] audioBuffGet = new byte[1024 * 50];

    private int count = 1;

    private Attribute product = new Attribute();

    private Gson gson = new Gson();

    private static mt_scylla mt;

    @Autowired
    private String pcmFilesPath;

    @Autowired
    private String wavFilesPath;

    @Autowired
    private NormalFileUploadService iNormalFileUploadService;

    @Autowired
    private WebSocketServer1 webSocketServer;

    private  static final int AUDIO_STATUS_FIRST = 1;
    private static final int AUDIO_STATUS_MIDDLE = 2;
    private static final int AUDIO_STATUS_LAST = 4;

    @Autowired
    private String audioPath;

    /**
     * 音频识别
     *
     * @return String
     * @param bufferList
     */

    public String getText(ArrayList<byte[]> bufferList) {
        long startTime = System.currentTimeMillis();
        String textResult="";
        try {
            mt = new mt_scylla();
            // 初始化语音识别引擎
            int initret = mt.SCYMTInitializeEx(null);
            if (initret != 0) {
                log.error("请检查IP地址是否正确、网络是否正常开启,错误码是:{}", initret);
                return null;
            }
            textResult = convertAudioToText(bufferList);
        } catch (IOException e) {
            log.error("发送音频文件失败,错误信息: {}", e.getMessage(), e);
        } finally {
            // 逆初始化
            int uniret = mt.SCYMTUninitializeEx(null);
            if (uniret != 0) {
                log.error("逆初始化失败,错误码是:{}", uniret);
            }
            log.debug("Speech recognition took:{} ms", System.currentTimeMillis() - startTime);
        }
        log.debug("textResult:{}", textResult);
        return textResult;
    }

    /**
     * 文本转音频
     *
     * @param text
     * @param text
     * @return String
     */
    public String getAudio(String text, String newAnswer, String sid) {
        if (text == null || text.isEmpty()) {
            log.debug("text is null");
            return "";
        }
        long startTime = System.currentTimeMillis();
        String filePath = "";
        try {
            mt = new mt_scylla();
            // 初始化语音识别引擎
            int initret = mt.SCYMTInitializeEx(null);
            if (initret != 0) {
                log.error("请检查IP地址是否正确、网络是否正常开启,错误码是:{}", initret);
                return null;
            }
            filePath = localTts(text, newAnswer, sid);
            // 逆初始化,断开所有的连接，并释放所有分配的全局资源，否则会造成系统资源泄漏
            int uniret = mt.SCYMTUninitializeEx(null);
            if (uniret != 0) {
                log.error("逆初始化失败,错误码是:{}", uniret);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("获取文本的字节数组异常，异常信息:{}", e.getMessage(), e);
        }
        long endTime = System.currentTimeMillis();
        log.debug("语音合成总耗时: " + (endTime - startTime) + "ms , filePath:{}", filePath);
        return filePath;
    }

    //语音合成
    public String localTts(String text,String newAnswer, String sid) throws UnsupportedEncodingException {
        long startTime = System.currentTimeMillis();
        log.debug("语音合成开始！");
        int[] errorCode = new int[1];
        // 获取本次合成的sessionId
        String session_id = mt.SCYMTSessionBeginEx(ttsparam, errorCode, null);
        if (errorCode[0] != SCY_SUCCESS) {
            log.error("请检查IP地址是否正确、网络是否正常开启,错误码是:{}", errorCode[0]);
            return null;
        }
        String pathPcm = pcmFilesPath + File.separatorChar + MD5Util.generateMD5(text) + ".pcm";
        log.debug("语音合成生成的pcm文件路径是:{}", pathPcm);
        int textlen = text.getBytes(StandardCharsets.UTF_8).length;
        int ret = mt.SCYMTTextPutEx(session_id, text, textlen, null);

        if (ret != 0) {
            log.error("SCYMTTextPutEx error,错误码是:{}", errorCode[0]);
        }
        webSocketServer.sendInfo1(newAnswer, sid);
        int[] errorCod = new int[1];
        errorCod[0] = ret;
        int[] recStatus = new int[1];
        int[] len = new int[1];
        recStatus[0] = 1;
        int i = 1;
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(pathPcm)));
            while (recStatus[0] != 0 && errorCod[0] == 0) {
                len[0] = 0;
                mt.SCYMTAudioGetEx(session_id, audioBuffGet, len, recStatus, errorCod, null);
                if (errorCod[0] != 0) {
                    String error = "SCYMTAudioGetEx error,错误码是" + errorCod[0];
                    log.debug(error);
                    break;
                }
                if (len[0] != 0) {
                    if (out != null) {
                        out.write(audioBuffGet, 0, len[0]);
                        StringBuilder builder = toStringMethod(audioBuffGet, len[0]);
                        webSocketServer.sendInfo1(String.valueOf(builder), sid);
                    }
                }
                i++;
            }
            out.close();
        } catch (Exception e) {
            log.error("获取音频数据失败！错误信息: {}", e.getMessage(), e);
        }
        // 结束一路会话
        int endret = mt.SCYMTSessionEndEx(session_id);
        if (endret != 0) {
            log.error("会话关闭失败,错误码是: {}", endret);
        }
        long endTime = System.currentTimeMillis();
        log.debug("语音合成" + i + "次，耗时总: " + (endTime - startTime) + "ms");
        return pathPcm;
    }

    //byte[]转换字符串
    private static StringBuilder toStringMethod(byte[] arr, int len) {
        // 自定义一个字符缓冲区
        StringBuilder sb = new StringBuilder();

        sb.append("[ ");
        //遍历int数组，并将int数组中的元素转换成字符串储存到字符缓冲区中去
        for (int i = 0; i < len; i++) {
            if (i != len - 1) {
                sb.append(arr[i] + " ,");
            } else {
                sb.append(arr[i] + " ]");
            }
        }
        return sb;
    }

    /**
     * 发送音频获取文本
     *
     * @return String
     * @param buffers
     */
    public String convertAudioToText(ArrayList<byte[]> buffers) throws IOException {
        int[] errorCode = new int[1];
        //第一步 SessionBegin
        String sid = mt.SCYMTSessionBeginEx(iatparam, errorCode, null);
        if (errorCode[0] != 0) {
            log.error("请检查IP地址是否正确、网络是否正常开启,错误码是:{}", errorCode[0]);
            return null;
        }
        String result = getTextResult(sid, buffers);
        // 第四步 结束一路会话
        int endret = mt.SCYMTSessionEndEx(sid);
        if (endret != 0) {
            log.error("会话关闭失败,错误码是:{}", endret);
            return null;
        }

        log.debug("语音识别结果:result{} ", result);
        return result;
    }

    private String getTextResult(String sid, ArrayList<byte[]> buffers) throws IOException {
        List<String> resultList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < buffers.size(); ++i) {
            byte[] wave = buffers.get(i);
            // 音频缓存区位置信息
            int audioStatus;
            // 第一段音频
            if (i == 0) {
                audioStatus = 1;
                // 最后一段音频
            } else if (i == buffers.size() - 1) {
                audioStatus = 4;
            } else {// 中间音频
                audioStatus = 2;
            }
            int[] epStatus = new int[1];
            int[] recogStatus = new int[1];
            int[] ret = new int[1];

            //第二步 AudioWrite写入音频
            String scymtAudioWriteEx = mt.SCYMTAudioWriteEx(sid, wave, wave.length, audioStatus, epStatus, recogStatus, ret, null);
            if (ret[0] != 0) {
                log.error("上传音频出错，错误码为:{}", ret[0]);
                break;
            }
            if (scymtAudioWriteEx != null && !scymtAudioWriteEx.isEmpty()) {
                resultList.add(scymtAudioWriteEx);
            }
            // 语音很短时,resultList是空的
            if (i == buffers.size() - 1) {
                processShortFile(sid, recogStatus, stringBuilder);
            }

            // 每次间隔80ms
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (resultList.isEmpty()) {
            return stringBuilder.toString();
        }

        StringBuilder result = new StringBuilder();
        for (String string : resultList) {
            Attribute attribute = gson.fromJson(string, Attribute.class);
            // 输出
            if (attribute.getPgs() == 1) {
                // 获取识别结果
                result.append(attribute.getResult());
            }
        }
        return result.toString();
    }

    private void processShortFile(String sid, int[] recogStatus, StringBuilder stringBuilder) {
        int[] errCode = new int[1];
        int timeout = 3;
        while (recogStatus[0] != 5 && 0 == errCode[0]) {
            // GetResult获取结果
            String scymtGetResultEx = mt.SCYMTGetResultEx(sid, recogStatus, timeout, errCode, null);
            if (scymtGetResultEx != null && !scymtGetResultEx.isEmpty()) {
                product = gson.fromJson(scymtGetResultEx, Attribute.class);
                // 输出
                if (product.getPgs() == 1) {
                    log.debug(scymtGetResultEx);
                    // 获取识别结果
                    String result = product.getResult();
                    if (result != null && !result.isEmpty()) {
                        stringBuilder.append(result);
                    }

                }
            }
        }
    }

    /**
     * 将音频文件转化成字节数组集合
     *
     * @param fileObj
     * @return ArrayList<byte [ ]>
     */
    public ArrayList<byte[]> getBuffers(MultipartFile fileObj) throws IOException {
        ArrayList<byte[]> buffers = new ArrayList<>();
        InputStream stream = null;
        int length;
        try {
            // 保存音频文件
            List<UploadFileDto> list = iNormalFileUploadService.uploadFile(fileObj, audioPath);
            log.debug(list.get(0).getPath());
            stream = fileObj.getInputStream();
            while (true) {
                // 在线模式，一次限制大小16k16bit的音频，需要sleep 80ms
                byte[] bts = new byte[2560];
                length = stream.read(bts);
                if (length == -1) {
                    break;
                }
                // 最后一段的 length < bts.length
                if (length != bts.length) {
                    byte[] tmp = new byte[length];
                    System.arraycopy(bts, 0, tmp, 0, length);
                    buffers.add(tmp);
                } else {
                    buffers.add(bts);
                }
            }
            if (buffers.size() == 1) {
                byte[] bts = new byte[1];
                buffers.add(bts);
            }
        } catch (Exception e) {
            log.error("读取音频文件失败,错误信息: {}", e.getMessage(), e);
            return buffers;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        log.debug("buffers size {}" ,buffers.size());
        return buffers;
    }

    /**
     * 文本转音频
     *
     * @param text
     * @return String
     */
    public String getAudio2(String text) {
        long startTime = System.currentTimeMillis();
        String filePath = null;
        try {
            mt = new mt_scylla();
            // 初始化语音识别引擎
            int initret = mt.SCYMTInitializeEx(null);
            if (initret != 0) {
                log.error("请检查IP地址是否正确、网络是否正常开启,错误码是:{}", initret);
                return null;
            }
            filePath = localTts2(text);
            log.debug("filePath:{}", filePath);
            // 逆初始化
            int uniret = mt.SCYMTUninitializeEx(null);
            if (uniret != 0) {
                log.error("逆初始化失败,错误码是:{}", uniret);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("获取文本的字节数组异常，异常信息:{}", e.getMessage(), e);
        }
        long endTime = System.currentTimeMillis();
        log.debug("合成语音结束，耗时:{}ms",endTime - startTime);
        return filePath;
    }

    //语音合成
    public String localTts2(String text) throws UnsupportedEncodingException {
        long startTime = System.currentTimeMillis();
        log.debug("开始合成语音");
        int[] errorCode = new int[1];
        String session_id = mt.SCYMTSessionBeginEx(ttsparam, errorCode, null);
        if (errorCode[0] != 0) {
            log.error("请检查IP地址是否正确、网络是否正常开启,错误码是:{}", errorCode[0]);
            return null;
        }
        String pathPcm = pcmFilesPath + MD5Util.generateMD5(text) + ".pcm";
        int textlen = text.getBytes(StandardCharsets.UTF_8).length;
        int ret = mt.SCYMTTextPutEx(session_id, text, textlen, null);

        if (ret != 0) {
            log.error("SCYMTTextPutEx error,错误码是:{}", errorCode[0]);
        }

        int[] errorCod = new int[1];
        errorCod[0] = ret;
        int[] recStatus = new int[1];
        int[] len = new int[1];
        recStatus[0] = 1;
        int i = 1;
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(pathPcm)));

            while (recStatus[0] != 0 && errorCod[0] == 0) {
                len[0] = 0;
                mt.SCYMTAudioGetEx(session_id, audioBuffGet, len, recStatus, errorCod, null);
                if (errorCod[0] != 0) {
                    String error = "SCYMTAudioGetEx error,错误码是" + errorCod[0];
                    log.debug(error);
                    break;
                }
                if (len[0] != 0) {
                    if (out != null) {
                        out.write(audioBuffGet, 0, len[0]);
                    }
                }
                i++;
            }

            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            log.error("获取音频数据失败！错误信息: {}", e.getMessage(), e);
        }
        // 结束一路会话
        int endret = mt.SCYMTSessionEndEx(session_id);
        if (endret != 0) {
            log.error("会话关闭失败,错误码是: {}", endret);
        }
        long endTime = System.currentTimeMillis();
        log.debug("localTts2 is end,time is: {}", endTime - startTime);
        return pathPcm;
    }
}
