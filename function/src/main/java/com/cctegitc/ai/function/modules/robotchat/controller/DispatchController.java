package com.cctegitc.ai.function.modules.robotchat.controller;

import com.cctegitc.ai.function.modules.robotchat.service.DispatchService;
import com.cctegitc.ai.function.modules.robotchat.service.RobotChatService;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@Slf4j
@RequestMapping("/dispatch")
public class DispatchController {
    
@Autowired
private RobotChatService robotChatService;


@Autowired
private DispatchService dispatchService;


@Autowired
private FileConversionService fileConversionService;

    @ApiOperation(value = "音频问题获取答案")
    @PostMapping("/audioanswer")
    public ResultResponse getAudioAnswer(@RequestPart MultipartFile file, String sid, HttpServletRequest request) {
        if (file.isEmpty()) {
            return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_FAIL, "请传语音文件！");
        }
        return robotChatService.getAudioResp(file, sid, request);
    }

    @ApiOperation(value = "文本问题获取答案")
    @GetMapping("/textanswer")
    public ResultResponse getTextAnswer(String text, String sid, String username, String userId, String controlFlag, HttpServletResponse response) {
        log.debug("textanswer text={},sid={},username={},userId={},controlFlag={}", text, sid, username, userId, controlFlag);
        ResultResponse res = new ResultResponse();
        if (userId == null || userId.isEmpty() || "undefined".equals(userId)) {
            log.debug("userId为空或者无效");
            return robotChatService.handleInvalidUserId(text, sid, userId, res);
        }
        // 请求语意服务
        return robotChatService.getTxtAnswerResp(text, sid, username, userId, controlFlag);
    }



    @ApiOperation(value = "播放录音")
    @GetMapping("/playAudio")
    public ResultResponse playAudio(String audioName, String sid, HttpServletResponse response, HttpServletRequest request) throws IOException {
        return robotChatService.playRecording(audioName, sid);
    }

    @ApiOperation(value = "文本问题转语音")
    @GetMapping("/texttoaudio")
    public ResultResponse getTextAudio(String text) {
       return fileConversionService.getTxtToWavResult(text);
    }


    @ApiOperation(value = "音频问题转文本")
    @PostMapping("/audiototext")
    public ResultResponse getAudioText(@RequestPart MultipartFile file, HttpServletRequest request) throws IOException {
        ResultResponse res = new ResultResponse();
        if (file.isEmpty()) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData("请传语音文件！");
            return res;
        }
        String text = dispatchService.getText(dispatchService.getBuffers(file));
        log.error("音频问题转文：" + text);
        if (text != null) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(text);
        } else {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("音频问题转文本失败！");
        }
        log.error(res.toString());
        return res;
    }

    //优化后----------
    @ApiOperation(value = "音频问题转文本")
    @PostMapping("/audiototexttest")
    public ResultResponse getAudioTextTest(@RequestPart MultipartFile file, HttpServletRequest request) throws IOException {
        log.debug("getAudioTextTest is begin");
        ResultResponse res = new ResultResponse();
        if (file == null) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("上传音频文件失败！");
            return res;
        }
        log.debug("getAudioTextTest file is {}", file);
        String text = dispatchService.getText(dispatchService.getBuffers(file));
        log.debug("getAudioTextTest text is {}", text);
        if (text != null) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(text);
        } else {
            log.debug("getAudioTextTest text is null");
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("语音转换文本失败！");
        }
        log.error(res.toString());
        return res;
    }
}