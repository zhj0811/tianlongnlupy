package com.cctegitc.ai.function.service.impl;

import com.cctegitc.ai.function.dto.UploadFileDto;
import com.cctegitc.ai.function.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * 名称
 *
 * @FileName NomorlFileUploadServiceImpl
 * @Author TianCheng
 * @Date 2021/6/25 10:14
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Service
@Slf4j
public class NormalFileUploadService {

    @Autowired
    private String uploadPath;

    public List<UploadFileDto> uploadFile(MultipartFile file, String filePath) throws IOException {
        log.info("File upload starts..");
        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isEmpty()) {
            throw new FileUploadException("File name is empty..");
        }
        try {
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + fileExtension;
            String fullPath = filePath + File.separatorChar + newFileName;
            log.debug("Full path: {}", fullPath);
            Path path = Paths.get(fullPath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            long sizeInKb = Files.size(path) / 1024;
            String size = sizeInKb + " KB";
            List<UploadFileDto> uploadFileDtos = new ArrayList<>(1);
            uploadFileDtos.add(new UploadFileDto(fileName, file.getContentType(), size, path.toString(), newFileName));
            log.debug("uploadFileDtos : {}", uploadFileDtos);
            return uploadFileDtos;
        } catch (IOException e) {
            log.error("File upload exception:", e);
            throw new FileUploadException("File upload exception: " + e.getMessage(), e);
        }
    }

    /**
     * 获取文件上传路径
     *
     * @param fileType
     * @param newName
     * @return
     */
    private String getFullPath(String fileType, String newName) {
        String fullPath;
        if (Constants.APP_ICONS.equals(fileType)) {
            fullPath = uploadPath + Constants.APP_ICONS + File.separatorChar + newName;
        } else if (Constants.WEB_INDICATOR.equals(fileType)) {
            fullPath = uploadPath + Constants.WEB_INDICATOR + File.separatorChar + newName;
        } else if (Constants.EQUIP_QR.equals(fileType)) {
            fullPath = uploadPath + Constants.EQUIP_QR + File.separatorChar + newName;
        } else {
            fullPath = uploadPath + newName;
        }
        return fullPath;
    }

    public Boolean delete(String fileName) {
        File file = new File(uploadPath + File.separator + fileName);
        return file.exists() && file.isFile() && file.delete();
    }
}
