package com.cctegitc.ai.function.modules.robotchat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.function.db.pojo.QuestionJl;
import com.cctegitc.ai.function.db.pojo.QuestionTj;
import com.cctegitc.ai.function.modules.voiceconversion.service.FileConversionServiceImpl;
import com.cctegitc.ai.function.service.impl.QuestionJlServiceImpl;
import com.cctegitc.ai.function.service.impl.QuestionTjServiceImpl;
import com.cctegitc.ai.function.util.AudioPcmToWaveUtil;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.HttpURLConnectionUtil;
import com.cctegitc.ai.function.util.HttpUtils;
import com.cctegitc.ai.function.util.TimeUtils;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.util.common.utils.UserUtils;
import com.cctegitc.ai.function.util.file.MD5Util;
import com.cctegitc.ai.function.vo.DispatchChatVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class RobotChatService {

    public static final String NO_ANSWER_MSG = "非常抱歉，目前无法给您答案，我还在学习中。";

    /**
     * "非常抱歉，目前无法给您答案，我还在学习中"的音频文件
     */
    public static final String NO_ANSWER_WAV = "noanswer.wav";
    //合成参数
    @Value("${dispatch.nwpu-http-url}")
    private String httpUrl;

    @Value("${dispatch.audio-login-url}")
    private String loginUrl;
    //语音外呼接口
    @Value("${dispatch.nuas-http-url}")
    private String nuasUrl;

    //录音通知接口
    @Value("${dispatch.audio-notice-url}")
    private String noticeUrl;

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private String wavFilesPath;

    @Autowired
    private String audioPath;

    @Autowired
    private String pcmFilesPath;

    @Autowired
    private HttpURLConnectionUtil httpURLConnectionUtil;

    @Autowired
    private QuestionTjServiceImpl questionTjService;

    @Autowired
    private QuestionJlServiceImpl questionJlService;

    @Autowired
    private FileConversionServiceImpl fileConversionService;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private DispatchChatVo dispatchChatVo;

    public ResultResponse getTxtAnswerResp(String text, String sid, String username, String userId, String controlFlag) {
        String answer = httpURLConnectionUtil.doGetAnswer(httpUrl, text, userId, controlFlag);
        if (answer == null || answer.isEmpty()) {
            return buildNoResultResp();
        }
        log.debug("语义服务返回结果：{}", answer);
        JSONObject object = parseJsonString(answer);
        if (object == null || object.getString("answer") == null){
            return buildNoResultResp();
        }

        String answerType = object.getString("answer_type");
        log.debug("answerType:{}", answerType);
        if ("tail".equals(answerType)) {
            // 外呼电话
            return getTailTypeResultResp(object);
        } else if ("message".equals(answerType)) {
            // 语音通知
            return getMsgTypeResp(object);
        } else if ("file".equals(answerType)) {
            // 富文档答案
            return getFileTypeResultResp(text, sid, username, userId, object);
        } else {
            // 普通问答
            return getNormalResp(text, username, userId, object, sid);
        }
    }

    private ResultResponse buildNoResultResp() {
        DispatchChatVo dispatchChatVo = new DispatchChatVo();
        dispatchChatVo.setTextAnswer(NO_ANSWER_MSG);
        dispatchChatVo.setAudioPath(NO_ANSWER_WAV);
        return createSuccessResponse(dispatchChatVo);
    }


    public ResultResponse getAudioResp(MultipartFile file, String sid, HttpServletRequest request) {
        try {
            String text = dispatchService.getText(dispatchService.getBuffers(file));
            log.debug("科大返回文本问题：" + text);
            if (text.isEmpty()) {
                return buildNoResultResp();
            }
            SysUser userInfo = userUtils.getSysUser(request);
            String textAnswerJson = httpURLConnectionUtil.doGetAnswer(httpUrl, text, String.valueOf(userInfo.getUserId()), userInfo.getControlFlag());
            JSONObject object = parseJsonString(textAnswerJson);
            String textAnswer = object.getString("answer");
            if (textAnswer.isEmpty()) {
                return buildNoResultResp();
            }
            String pcmPath = dispatchService.getAudio(textAnswer, textAnswer, sid);
            if (pcmPath.isEmpty()) {
                log.debug("科大返回音频问题：" + textAnswer);
                dispatchChatVo.setTextAnswer(textAnswer);
                return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, dispatchChatVo);
            }
            String wavFileName = MD5Util.generateMD5(text) + ".wav";
            String wavPath = wavFilesPath + wavFileName;
            Boolean isSuccess = AudioPcmToWaveUtil.audioPcmToWave(pcmPath, wavPath);
            if (isSuccess) {
                log.debug("语音合成成功！");
                dispatchChatVo.setTextAnswer(textAnswer);
                dispatchChatVo.setAudioPath(wavFileName);
                return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, dispatchChatVo);
            }
        } catch (Exception e) {
            log.error("处理音频答案时发生异常", e);
            return buildNoResultResp();
        }
        return buildNoResultResp();
    }


    public JSONObject parseJsonString(String answer) {
        if (answer == null) {
            return null;
        }
        JSONObject object = null;
        try {
            return JSON.parseObject(answer);
        } catch (Exception e) {
            log.error("Parsing JSON string failed: {}", e.getMessage());
        }
        return object;
    }

    public ResultResponse handleInvalidUserId(String text, String sid, String userId, ResultResponse res) {
        String pcm = dispatchService.getAudio(text, text, sid);
        String wavFileName = MD5Util.generateMD5(text) + ".wav";
        String wav = wavFilesPath + wavFileName;
        Boolean flag = AudioPcmToWaveUtil.audioPcmToWave(pcm, wav);
        if (flag) {
            dispatchChatVo.setTextAnswer(text);
            dispatchChatVo.setAudioPath(wavFileName);
            dispatchChatVo.setUserId(userId);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(dispatchChatVo);
        } else {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("pcm转wav格式失败！");
        }
        return res;
    }

    /**
     * 外呼电话
     *
     * @param object
     * @return
     */
    private ResultResponse getTailTypeResultResp(JSONObject object) {
        String token = null;
        try {
            // 登录请求获取token
            log.debug("Starting to fetch token---------------------");
            token = fetchToken();
            log.debug("Token fetch completed---------------------");

            // 请求外呼接口
            log.debug("Starting call API request-----------------");
            boolean callSuccess = attemptCall(object, token);
            if (callSuccess) {
                log.debug("Call succeeded");
                return createSuccessResponse("呼叫成功");
            } else {
                log.debug("Call failed");
                return createFailResponse("呼叫失败");
            }
        } catch (Exception e) {
            log.error("An exception occurred during processing:error message={}", e.getMessage(), e);
            return createFailResponse("呼叫失败");
        }
    }

    private String fetchToken() throws Exception {
        JSONObject loginResponse = HttpUtils.httpPostLoginRequest(loginUrl);
        if (loginResponse == null) {
            return "";
        }
        String tokenStr = loginResponse.getString("data");
        if (tokenStr == null) {
            return "";
        }
        JSONObject data = parseJsonString(tokenStr);
        return data.getString("token");
    }


    private boolean attemptCall(JSONObject object, String token) throws Exception {
        // 检查输入参数是否为空
        if (object == null || token == null || token.isEmpty()) {
            log.debug("Parameters 'object' or 'token' are null, cannot proceed with the call attempt.");
            throw new IllegalArgumentException("Parameters 'object' and 'token' cannot be null or empty.");
        }

        // 初始化请求参数
        Map<String, Object> map = new HashMap<>();
        String phoneNumber = object.getString("phonenumber");
        if (phoneNumber.isEmpty()) {
            log.debug("Phone number is empty, cannot proceed with the call attempt.");
            return false;
        }
        map.put("called", phoneNumber);
        map.put("caller", 8000);

        // 尝试进行第一次呼叫
        log.debug("Initiating the first call attempt with phone number: {}", phoneNumber);
        JSONObject response = HttpUtils.sendPost(nuasUrl, map, token);
        if (isCallSuccessful(response)) {
            log.debug("First call attempt was successful.");
            return true;
        }

        // 使用备用caller再次尝试呼叫
        map.put("caller", 8001);
        log.debug("First call attempt failed, trying with an alternate caller");
        response = HttpUtils.sendPost(nuasUrl, map, token);
        if (isCallSuccessful(response)) {
            log.debug("Call was successful with the alternate caller.");
            return true;
        } else {
            log.debug("Call failed with the alternate caller.");
            return false;
        }
    }

    // 检查呼叫是否成功的辅助方法
    private boolean isCallSuccessful(JSONObject response) {
        if (response != null && response.containsKey("code")) {
            return response.getInteger("code") == 0;
        } else {
            log.debug("Response JSON object is missing 'code' field or the response is null.");
            return false;
        }
    }

    /**
     * 语音通知
     *
     * @param object
     * @return
     */
    private ResultResponse getMsgTypeResp(JSONObject object) {
        ResultResponse res = new ResultResponse();
        String token = null;
        try {
            // 获取Token
            log.debug("开始请求登录以获取token...");
            token = fetchToken();

            // 构造请求参数
            Map<String, Object> map = buildNoticeRequestParams(object);

            // 发送通知请求
            log.debug("请求录音通知接口...");
            JSONObject json = HttpUtils.sendPost(noticeUrl, map, token);
            log.debug("请求录音通知接口结束: {}", json.toString());

            return processNoticeResponse(json, res);
        } catch (Exception e) {
            log.error("处理通知请求时出错, token: {}, 错误信息: {}, Date: {}, 内容: {}, 电话: {}", token
                    , e.getMessage(), new Date(), object.getString("message"), object.getString("phonenumber"), e);
            return createFailResponse("通知失败！");
        }
    }

    private Map<String, Object> buildNoticeRequestParams(JSONObject object) {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", "601000100016");
        map.put("message", object.getString("message"));

        List<JSONObject> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "");
        jsonObject.put("phoneNums", object.getString("phonenumber"));
        list.add(jsonObject);

        map.put("contact2NumberInfos", list);
        return map;
    }

    private ResultResponse processNoticeResponse(JSONObject json, ResultResponse res) {
        JSONObject status = parseJsonString(json.getString("status"));
        if (status == null) {
            return createFailResponse("通知失败！");
        }
        if ("0".equals(status.getString("code"))) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("通知成功");
        } else {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData("通知失败！");
        }
        return res;
    }


    private ResultResponse getNormalResp(String text, String username, String userId, JSONObject object, String sid) {
        String answer = object.getString("answer");
        if (answer.isEmpty()) {
            return createFailResponse(NO_ANSWER_MSG);
        }

        String label = object.getString("label");
        int success = object.getIntValue("success");

        // 插入问题记录
        QuestionJl questionJl = insertQuestionJl(text, label, success, userId, username);

        // 判断问题是否已经查询过，并更新或插入统计信息
        updateOrInsertQuestionTj(text, label);
        if (NO_ANSWER_MSG.equals(answer)) {
            return buildNoResultResp();
        }

        dispatchChatVo.setTextAnswer(answer);
        dispatchChatVo.setUserId(userId);
        dispatchChatVo.setJlId(questionJl.getId());
        String pcm = dispatchService.getAudio(answer, answer, sid);
        if (pcm == null || pcm.isEmpty()) {
            log.error("dispatchService.getNormalResp  getAudio is fail");
            return createSuccessResponse(dispatchChatVo);
        }
        // 尝试生成音频文件，如果失败则不影响答案返回
        String audioPath = tryGenerateAudioFile(pcm, answer);
        log.debug("audioPath: {}", audioPath);
        dispatchChatVo.setAudioPath(audioPath);
        log.debug("dispatchChatVo: {}", dispatchChatVo);
        return createSuccessResponse(dispatchChatVo);
    }



    private QuestionJl insertQuestionJl(String text, String label, int success, String userId, String username) {
        QuestionJl questionJl = new QuestionJl();
        questionJl.setQuestion(text);
        questionJl.setLabel(label);
        questionJl.setSuccess(success);
        questionJl.setQueDate(TimeUtils.getDateTime(new Date()));
        questionJl.setUserId(Integer.parseInt(userId));
        questionJl.setUsername(username);
        questionJlService.insertOne(questionJl);
        return questionJl;
    }

    private void updateOrInsertQuestionTj(String text, String label) {
        log.debug("updateOrInsertQuestionTj called with text: {}, label: {}, hasAnswer: {}", text, label, true);
        // 检查问题是否已存在
        int result = questionTjService.selectExist(text);
        log.debug("Result of selectExist for processedText: {}", result);

        if (result > 0) {
            // 如果问题已存在，则更新其统计数字
            log.debug("Updating num for existing question with processedText: {}", text);
            questionTjService.updateNum(text, 1);
        } else {
            // 如果问题不存在，则创建并插入一个新的记录
            log.debug("Inserting new questionTj with text: {}, label: {}", text, label);
            QuestionTj questionTj = new QuestionTj();
            questionTj.setLabel(label);
            // 使用完整的文本
            questionTj.setQuestion(text);
            questionTj.setSuccess(1);
            questionTj.setNum(1);
            questionTjService.insert(questionTj);
        }
    }

    private String tryGenerateAudioFile(String pcm, String text) {
        String wavFileName = MD5Util.generateMD5(text) + ".wav";
        String wav = wavFilesPath + wavFileName;
        log.debug("Generating WAV file at: {}", wav);
        boolean flag = AudioPcmToWaveUtil.audioPcmToWave(pcm, wav);
        return flag ? wavFileName : "";
    }

    private ResultResponse createFailResponse(String message) {
        ResultResponse res = new ResultResponse();
        res.setCode(Constants.STATUS_OK);
        res.setMessage(Constants.MESSAGE_FAIL);
        res.setData(message);
        return res;
    }

    private ResultResponse createSuccessResponse(Object data) {
        ResultResponse res = new ResultResponse();
        res.setCode(Constants.STATUS_OK);
        res.setMessage(Constants.MESSAGE_OK);
        res.setData(data);
        return res;
    }

    private ResultResponse getFileTypeResultResp(String text, String sid, String username, String userId, JSONObject object) {
        log.debug("Starting to process file type result response");
        ResultResponse res = new ResultResponse();

        // 插入问题记录
        insertQuestionJl(text, username, userId, object);

        // 处理问题统计
        handleQuestionTj(text, object);

        // 处理答案
        String answer = object.getString("answer");
        if (answer.isEmpty()) {
            log.debug("Answer is empty, creating fail response");
            return buildNoResultResp();
        } else {
            log.debug("Answer is not empty, handling answer");
            return handleAnswer(sid, answer, object, userId, res);
        }
    }

    // 插入问题记录的方法
    private void insertQuestionJl(String text, String username, String userId, JSONObject object) {
        log.debug("Inserting question record");
        QuestionJl questionJl = new QuestionJl();
        questionJl.setQuestion(text);
        questionJl.setLabel(object.getString("label"));
        questionJl.setSuccess(Integer.parseInt(object.getString("success")));
        questionJl.setQueDate(TimeUtils.getDateTime(new Date()));
        questionJl.setUserId(Integer.parseInt(userId));
        questionJl.setUsername(username);
        Integer jlId = questionJlService.insertOne(questionJl);
        // 保存记录ID供后续使用
        object.put("jlId", jlId);
    }

    /**
     * 处理问题统计的方法
     *
     * @param text
     * @param object
     */
    private void handleQuestionTj(String text, JSONObject object) {
        log.debug("Handling question statistics");
        int success = object.getString("answer").isEmpty() ? 0 : 1;
        int result = questionTjService.selectExist(text.substring(0, text.length() - 1));
        if (result > 0) {
            questionTjService.updateNum(text.substring(0, text.length() - 1), success);
        } else {
            QuestionTj questionTj = new QuestionTj();
            questionTj.setLabel(object.getString("label"));
            questionTj.setQuestion(text);
            questionTj.setSuccess(success);
            questionTj.setNum(1);
            questionTjService.insert(questionTj);
        }
    }

    /**
     * 处理答案的方法
     */
    private ResultResponse handleAnswer(String sid, String answer, JSONObject object, String userId, ResultResponse res) {
        log.debug("Handling answer for sid: {}", sid);
        // 提取方法，构造带链接的答案文本
        String textNewAnswer = constructAnswerWithLink(answer, object);
        object.put("answer", textNewAnswer);
        dispatchChatVo.setTextAnswer(object.toJSONString());
        String wavFileName =  "audio_" + sid + "_" + MD5Util.generateMD5(answer) + ".wav";
        String wavFullPath = wavFilesPath + wavFileName;
        String pcmPath = dispatchService.getAudio(answer, textNewAnswer, sid);
        dispatchChatVo.setAudioPath(wavFileName);
        dispatchChatVo.setUserId(userId);
        boolean conversionSuccess = AudioPcmToWaveUtil.audioPcmToWave(pcmPath, wavFullPath);
        // 设置返回结果
        if (!conversionSuccess) {
            log.debug("Audio conversion failed for sid: {}", sid);
            // 转换失败时考虑返回不同的状态码或信息
            res.setCode(Constants.STATUS_OK);
            res.setMessage("Audio conversion failed");
        } else {
            log.debug("Audio conversion was successful for sid: {}", sid);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
        }
        res.setData(dispatchChatVo);
        return res;
    }

    // 提取构造带链接答案的方法
    private String constructAnswerWithLink(String answer, JSONObject object) {
        String fileName = object.getString("file_name");
        String fileNewName = object.getString("file_new_name");
        if (!fileName.isEmpty() && !fileNewName.isEmpty()) {
            return answer + String.format("<br><a style=\"color:blue;\" onclick=\"preview('%s')\">%s</a>", fileNewName, fileName);
        }
        return answer;
    }

    public  ResultResponse playRecording(String audioName, String sid) throws IOException {
        ResultResponse res = new ResultResponse();
        log.error("playAudio is begin ,audioName {}", audioName);
        String audio = audioPath + audioName;
        log.error("DispatchController.playAudio audio= {}", audio);
        File file = new File(audio);
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
        String text = dispatchService.getText(dispatchService.getBuffers(multipartFile));
        String wavFileName = MD5Util.generateMD5(text) + ".wav";
        String wav = wavFilesPath  + wavFileName;
        String pcm = dispatchService.getAudio(text, text, sid);
        Boolean flag = AudioPcmToWaveUtil.audioPcmToWave(pcm, wav);
        if (flag) {
            log.error("DispatchController.playAudio flag={}", flag);
            DispatchChatVo dispatchChatVo = new DispatchChatVo();
            dispatchChatVo.setTextAnswer(text);
            dispatchChatVo.setAudioPath(wavFileName);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(dispatchChatVo);
        } else {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData("pcm转wav格式失败！");
        }
        return res;
    }

}
