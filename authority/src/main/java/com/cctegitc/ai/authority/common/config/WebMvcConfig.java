package com.cctegitc.ai.authority.common.config;

import com.cctegitc.ai.authority.common.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * 名称
 *
 * @FileName WebMvcConfig
 * @Author TianCheng
 * @Date 2021/6/28 14:23
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private String uploadPath;

    @Autowired
    private String wavFilesPath;

    @Autowired
    private String documentPath;

    @Autowired
    private String videoPath;

    @Autowired
    private String picturePath;

    @Autowired
    private String audioPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("外部文件路径：" + uploadPath);
        //file:///本地文件传输协议
        registry.addResourceHandler("/files/**").addResourceLocations("file:///" + uploadPath);
        registry.addResourceHandler("/app-icons/**").addResourceLocations("file:///" + uploadPath + Constants.APP_ICONS + File.separatorChar);//app图标
        registry.addResourceHandler("/equip-qr/**").addResourceLocations("file:///" + uploadPath + Constants.EQUIP_QR + File.separatorChar);//设备二维码路径
        registry.addResourceHandler("/indicator/**").addResourceLocations("file:///" + uploadPath + Constants.WEB_INDICATOR + File.separatorChar);//指标文件路径
        //映射到jar包内的静态资源资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // 音频文件路径映射
        registry.addResourceHandler("/wav/**").addResourceLocations("file:///" + wavFilesPath);
        // 文档文件库文件路径映射
        registry.addResourceHandler("/document/**").addResourceLocations("file:///" + documentPath);
        // 视频文件库文件路径映射
        registry.addResourceHandler("/video/**").addResourceLocations("file:///" + videoPath);
        // 视频文件库文件路径映射
        registry.addResourceHandler("/picture/**").addResourceLocations("file:///" + picturePath);
        // 图片文件库文件路径映射
        registry.addResourceHandler("/audio/**").addResourceLocations("file:///" + audioPath);

    }

}
