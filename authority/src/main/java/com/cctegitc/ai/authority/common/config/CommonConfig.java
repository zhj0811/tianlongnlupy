package com.cctegitc.ai.authority.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * 名称
 *
 * @FileName UploadFileConfig
 * @Author TianCheng
 * @Date 2021/6/24 16:25
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Configuration
@Slf4j
public class CommonConfig {

    @Value("${upload.path.windows}")
    private String windowsPath;

    @Value("${upload.path.linux}")
    private String linuxPath;

    @Value("${dispatch.record-path.windows}")
    private String recordWindowsPath;

    @Value("${dispatch.record-path.linux}")
    private String recordLinuxPath;

    @Value("${dispatch.pcm-path.windows}")
    private String pcmWindowsPath;

    @Value("${dispatch.pcm-path.linux}")
    private String pcmLinuxPath;

    @Value("${dispatch.wav-path.windows}")
    private String wavWindowsPath;

    @Value("${dispatch.wav-path.linux}")
    private String wavLinuxPath;

    @Value("${upload.document-path.windows}")
    private String documentWindowsPath;

    @Value("${upload.document-path.linux}")
    private String documentLinuxPath;

    @Value("${upload.video-path.windows}")
    private String videoWindowsPath;

    @Value("${upload.video-path.linux}")
    private String videoLinuxPath;

    @Value("${upload.picture-path.windows}")
    private String pictureWindowsPath;

    @Value("${upload.picture-path.linux}")
    private String pictureLinuxPath;

    @Value("${upload.audio-path.windows}")
    private String audioWindowsPath;

    @Value("${upload.audio-path.linux}")
    private String audioLinuxPath;

    @Value("${server.port}")
    public String port;

    @Bean("uploadPath")
    public String getUploadPath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return linuxPath;
        } else {
            return windowsPath;
        }
    }

    @Bean("recordFilesPath")
    public String getRecordFilesPath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return recordLinuxPath;
        } else {
            return recordWindowsPath;
        }
    }

    @Bean("documentPath")
    public String getDocumentPath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return documentLinuxPath;
        } else {
            return documentWindowsPath;
        }
    }

    @Bean("videoPath")
    public String getVideoPath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return videoLinuxPath;
        } else {
            return videoWindowsPath;
        }
    }

    @Bean("picturePath")
    public String getPicturePath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return pictureLinuxPath;
        } else {
            return pictureWindowsPath;
        }
    }

    @Bean("audioPath")
    public String getAudioPath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return audioLinuxPath;
        } else {
            return audioWindowsPath;
        }
    }

    @Bean("pcmFilesPath")
    public String getPcmFilesPath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return pcmLinuxPath;
        } else {
            return pcmWindowsPath;
        }
    }

    @Bean("wavFilesPath")
    public String getWavFilesPath() {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            return wavLinuxPath;
        } else {
            return wavWindowsPath;
        }
    }

    @Bean("port")
    public String getPort() {
        return port;
    }
}


