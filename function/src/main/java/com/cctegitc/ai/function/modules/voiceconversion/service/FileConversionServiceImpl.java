package com.cctegitc.ai.function.modules.voiceconversion.service;

import com.cctegitc.ai.function.db.pojo.AudioLibrary;
import com.cctegitc.ai.function.modules.robotchat.controller.FileConversionService;
import com.cctegitc.ai.function.modules.robotchat.service.DispatchService;
import com.cctegitc.ai.function.util.AudioPcmToWaveUtil;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.StringUtils;
import com.cctegitc.ai.function.util.common.constant.ContentTypeConstant;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.util.file.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Slf4j
@Service
public class FileConversionServiceImpl implements FileConversionService {
    public static final String WAV_FILE = ".wav";
    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private String wavFilesPath;

    @Override
    public String wordToAudio(MultipartFile file) {
        String contentType = file.getContentType();
        log.debug("contentType: {}", contentType);
        String result = "";
        try {
            if (ContentTypeConstant.TXT_CONTENTTYPE.equals(contentType)) {
                result = readTextFile(file.getInputStream());
            } else if (ContentTypeConstant.DOC_CONTENTTYPE.equals(contentType)) {
                // doc文档
                result = readDocFile(file.getInputStream());
            } else if(ContentTypeConstant.DOCX_CONTENTTYPE.equals(contentType)) {
                // docx文档
                result = readDocxFile(file.getInputStream());
            }
        } catch (IOException e) {
            log.error("Error processing file: {}", e.getMessage(), e);
        }
        log.debug("result: {}", result);
        return result;
    }

    public String readTextFile(InputStream inputStream)  throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (Exception e) {
            log.error("Error reading text file", e);
            throw e;
        }
        return stringBuilder.toString();
    }

    public String readDocxFile(InputStream inputStream) throws IOException {
        try (XWPFDocument docx = new XWPFDocument(inputStream)) {
            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);
            return extractor.getText();
        } catch (IOException e) {
            log.error("Error reading DOCX file", e);
            throw e;
        }
    }

    public String readDocFile(InputStream inputStream) throws IOException {
        try (HWPFDocument doc = new HWPFDocument(inputStream);
             WordExtractor extractor = new WordExtractor(doc)) {
            return extractor.getText();
        } catch (IOException e){
            log.error("Error reading DOC file", e);
            throw e;
        }
    }

    @Override public ResultResponse getTxtToWavResult(String text) {
        String pcm = dispatchService.getAudio2(text);
        log.error("pcm:{}", pcm);
        // 存到服务器的文件名称
        String wavFileName = MD5Util.generateMD5(text) + ".wav";
        String wavFullPath = wavFilesPath + wavFileName;
        log.error("wavFullPath:{}", wavFullPath);
        // 取文本内容前十个字符作为前台显示名字
        String displayFileName = StringUtils.truncateText(text) + WAV_FILE;
        Boolean flag = AudioPcmToWaveUtil.audioPcmToWave(pcm, wavFullPath);
        ResultResponse res;
        if (flag) {
            try {
                File wavFile = new File(wavFullPath);
                Files.write(wavFile.toPath(), new byte[0]);
                AudioLibrary audioLibrary = new AudioLibrary(wavFileName, Long.toString(wavFile.length()), displayFileName);
                res = new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, audioLibrary);
            } catch (IOException e) {
                res = new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, "文字文件转语音失败");
            }
        } else {
            res = new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, "文字文件转语音失败");
        }
        return res;
    }

    @Override
    public ResultResponse checkTextFileType(MultipartFile file) {
        if (!ContentTypeConstant.TEXT_TYPES.contains(file.getContentType())) {
            return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, "只支持上传TXT,DOC,DOX格式的文件");
        }
        return null;
    }

    @Override
    public ResultResponse checkAudioFileType(MultipartFile file) {
        if (!ContentTypeConstant.AUDIO_TYPES.contains(file.getContentType())) {
            return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, "只支持上传MP3、WAV、FLAC、OGG、AMRMP3、WAV、FLAC、OGG、AMR格式的文件");
        }
        return null;
    }

}
