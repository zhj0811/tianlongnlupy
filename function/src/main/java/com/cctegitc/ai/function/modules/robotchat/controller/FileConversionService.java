package com.cctegitc.ai.function.modules.robotchat.controller;

import com.cctegitc.ai.function.util.common.res.ResultResponse;
import org.springframework.web.multipart.MultipartFile;


public interface FileConversionService {

    String wordToAudio(MultipartFile file);

    ResultResponse getTxtToWavResult(String text);

    ResultResponse checkTextFileType(MultipartFile file);

    ResultResponse checkAudioFileType(MultipartFile file);
}
