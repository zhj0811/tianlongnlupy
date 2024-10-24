package com.cctegitc.ai.function.service.impl;

import com.cctegitc.ai.function.dto.UploadFileDto;
import com.cctegitc.ai.function.util.common.constant.ContentTypeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 名称
 *
 * @FileName SpecialFileUploadServiceImpl
 * @Author TianCheng
 * @Date 2021/6/25 10:49
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Service
@Slf4j
public class SpecialFileUploadService {
    @Value("${upload.image-max-size}")
    private Long maxImage;

    @Value("${upload.doc-max-size}")
    private Long maxDoc;

    @Value("${upload.video-max-size}")
    private Long maxVideo;

    @Autowired
    private NormalFileUploadService iNormalFileUploadService;

    @Autowired
    private String recordFilesPath;

    @Autowired
    private String documentPath;

    @Autowired
    private String videoPath;

    @Autowired
    private String picturePath;

    @Autowired
    private String audioPath;

    /**
     * 图片上传
     */
    public List<UploadFileDto> uploadImages(MultipartFile file) throws FileUploadException, IOException {
        String errorWords = checkFileSpecification(file, maxImage, ContentTypeConstant.IMAGE_TYPES);
        if (errorWords != null) {
            throw new FileUploadException(errorWords);
        }
        return iNormalFileUploadService.uploadFile(file, picturePath);
    }


    /**
     * 文本文件上传
     *
     * @param file
     * @return
     */
    public List<UploadFileDto> uploadDocument(MultipartFile file) throws FileUploadException, IOException {

        String errorWords = checkFileSpecification(file, maxDoc, ContentTypeConstant.DOCUMENT_TYPES);
        if (errorWords != null) {
            throw new FileUploadException(errorWords);
        }
        return iNormalFileUploadService.uploadFile(file, documentPath);
    }

    /**
     * 文件合规检查
     *
     * @param file
     * @param maxSize
     * @param allowTypes
     * @return
     */
    public String checkFileSpecification(MultipartFile file, Long maxSize, List<String> allowTypes) {
        if (file.getSize() > maxSize) {
            log.error(file.getOriginalFilename() + "文件过大：" + file.getSize());
            return file.getOriginalFilename() + "文件过大：" + file.getSize();
        }
        String contentType = file.getContentType();
        if (!allowTypes.contains(contentType)) {
            log.error(file.getOriginalFilename() + "文件格式错误：");
            return file.getOriginalFilename() + "文件格式错误：";
        }
        return null;
    }

    public Boolean delete(String fileName) {
        return iNormalFileUploadService.delete(fileName);
    }

    /**
     * 音频文件上传
     *
     * @param file
     * @return
     */
    public List<UploadFileDto> uploadAudio(MultipartFile file) throws FileUploadException, IOException {

        String errorWords = checkFileSpecification(file, maxVideo, ContentTypeConstant.AUDIO_TYPES);
        if (errorWords != null) {
            throw new FileUploadException(errorWords);
        }
        return iNormalFileUploadService.uploadFile(file, audioPath);
    }


    /**
     * 视频文件上传
     *
     * @param file
     * @return
     */
    public List<UploadFileDto> uploadVideo(MultipartFile file) throws FileUploadException, IOException {

        String errorWords = checkFileSpecification(file, maxVideo, ContentTypeConstant.VIDEO_TYPES);
        if (errorWords != null) {
            throw new FileUploadException(errorWords);
        }
        return iNormalFileUploadService.uploadFile(file, videoPath);
    }
}
