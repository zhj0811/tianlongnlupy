package com.cctegitc.ai.function.modules.voiceconversion.controller;

import com.cctegitc.ai.function.modules.robotchat.service.DispatchService;
import com.cctegitc.ai.function.modules.voiceconversion.service.FileConversionServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/conversion")
public class ConversionController {

    @Autowired
    private FileConversionServiceImpl fileConversionService;

    @Autowired
    private DispatchService dispatchService;

    @ApiOperation(value = "语音合成-上传文件转语音")
    @PostMapping("/textFileToAudio")
    public ResultResponse textFileToAudio(@RequestPart MultipartFile file, HttpServletRequest request) throws IOException {
        log.debug("wordToAudio 上传文件：" + file.getOriginalFilename());
        ResultResponse res = fileConversionService.checkTextFileType(file);
        if (res != null) {
            return res;
        }
        String text = fileConversionService.wordToAudio(file);
        log.debug("text:{}", text);
        return fileConversionService.getTxtToWavResult(text);
    }


    @ApiOperation(value = "语音合成-输入文字转语音")
    @PostMapping("/textToAudio")
    public ResultResponse textToAudio(String text) {
        log.debug("text:{}", text);
        return fileConversionService.getTxtToWavResult(text);
    }

    @ApiOperation(value = "语音识别")
    @PostMapping("/audioTotext")
    public ResultResponse convertAudioToText(@RequestPart MultipartFile file, HttpServletRequest request) throws IOException {
        log.debug("getAudioText is begin");
        ResultResponse res = fileConversionService.checkAudioFileType(file);
        if (res != null) {
            return res;
        }
        log.debug("getAudioText file is {}", file);
        String text = dispatchService.getText(dispatchService.getBuffers(file));
        log.debug("getAudioText text is {}", text);
        if (text != null) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(text);
        } else {
            log.debug("getAudioText text is null");
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData("语音转换文本失败");
        }
        log.error(res.toString());
        return res;
    }
}
