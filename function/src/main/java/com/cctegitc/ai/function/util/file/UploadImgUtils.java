package com.cctegitc.ai.function.util.file;

import cn.hutool.core.lang.UUID;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadImgUtils {

    /**
     * 默认大小 50M
     */
    public static final long DEFAULT_MAX_SIZE = 100 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * @Description: 根据文件路径上传
     * @Author: yrc
     * @Date: 2021/11/3 14:30
     * @Param: [baseDir:上传文件路径;即文件上传后要存放的地址路径, file:上传的文件]
     * @Return: java.lang.String
     * @Throws:
     */
    public static final String upload(String baseDir, MultipartFile file, String id) throws Exception {
        try {
            return uploadFile(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, id);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * @Description:
     * @Author: yrc
     * @Date: 2021/11/3 14:32
     * @Param: [baseDir:上传文件路径;即文件上传后要存放的地址路径, file:上传的文件, allowedExtension:上传文件类型]
     * @Return: java.lang.String
     * @Throws: FileUploadException:文件名太长
     * @Throws: IOException:其他异常；比如读写文件出错时
     */
    public static final String uploadFile(String baseDir, MultipartFile file, String[] allowedExtension, String id) throws IOException {

        //获取上传图片文件名的长度
        int fileNamelength = file.getOriginalFilename().length();
        //判断上传的图片文件名是否超出定义的长度
        if (fileNamelength > UploadImgUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileUploadException("文件名太长");
        }
        //调用assertAllowed;对上传文件的大小、格式进行校验
        assertAllowed(file, allowedExtension);
        //对上传文件重命名
        String fileName = extractFilename(file, id);
        //调用getAbsoluteFile判断该文件夹是否存在
        File desc = getAbsoluteFile(baseDir, fileName);
        //将文件写入磁盘
        file.transferTo(desc);
        //获取文件自动生成的路径+命名
        String pathFileName = getPathFileName(baseDir, fileName);
        return pathFileName;

    }

    /**
     * 编码文件名
     */
    public static final String extractFilename(MultipartFile file, String id) {
        //获取上传的图片文件名
        String fileName = file.getOriginalFilename();
        //获取上传图片文件的格式：getExtension();
        String extension = getExtension(file);
        //对上传的文件名加密；即重命名
        fileName = id + "/" + UUID.fastUUID().toString() + "." + extension;
        return fileName;
    }

    /**
     * 文件大小校验
     *
     * @param file 上传的文件
     * @return
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension) throws FileUploadException {
        long size = file.getSize();
        if (DEFAULT_MAX_SIZE != -1 && size > DEFAULT_MAX_SIZE) {
            throw new FileUploadException(String.valueOf(DEFAULT_MAX_SIZE / 1024 / 1024));
        }
        String fileName = file.getOriginalFilename();
        //获取上传图片文件的格式：getExtension();
        String extension = getExtension(file);
        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == MimeTypeUtils.IMAGE_EXTENSION || allowedExtension == MimeTypeUtils.FLASH_EXTENSION ||
                    allowedExtension == MimeTypeUtils.MEDIA_EXTENSION || allowedExtension == MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION) {
                throw new FileUploadException("文件格式不正确");
            } else {
                throw new FileUploadException("未知错误");
            }
        }

    }

    /**
     * 获取文件名的后缀
     *
     * @param file 表单文件
     * @return 后缀名
     */
    public static final String getExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) {
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    private static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException {

        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    private static final String getPathFileName(String uploadDir, String fileName) throws IOException {
        //linux "/"   windows"\"
        if ("/".equals(File.separator)) {
            //linux系统文件上传
            String substring = uploadDir.substring(uploadDir.indexOf("/uploadFile"));
            String pathFileName = substring + "/" + fileName;
            return pathFileName;
        } else {
            //windows系统文件上传
            String substring = uploadDir.substring(uploadDir.indexOf(":") + 2, uploadDir.length());
            String pathFileName = substring + "/" + fileName;
            return pathFileName;
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------

    public static final String uploads(String baseDir, MultipartFile file, String id) throws Exception {
        try {
            return uploadFiles(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, id);
        } catch (Exception e) {
            throw new IOException(e.getMessage(), e);
        }
    }

    /**
     * 对文件进行校验
     *
     * @param baseDir
     * @param file
     * @param allowedExtension
     * @param id
     * @return
     * @throws IOException
     */
    public static final String uploadFiles(String baseDir, MultipartFile file, String[] allowedExtension, String id) throws IOException {

        //获取上传图片文件名的长度
        int fileNamelength = file.getOriginalFilename().length();
        //判断上传的图片文件名是否超出定义的长度
        if (fileNamelength > UploadImgUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileUploadException("文件名太长");
        }
        //调用assertAllowed;对上传文件的大小、格式进行校验
        assertAllowed(file, allowedExtension);
        //对上传文件重命名
        //String fileName = extractFileName(file, id);
        //调用getAbsoluteFile判断该文件夹是否存在
        //File desc = getAbsoluteFile(baseDir, id);
        String uploadPath = baseDir + File.separator + id;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String desc1 = uploadPath + File.separator + file.getOriginalFilename();
        File desc = new File(desc1);
        //将文件写入磁盘
        Path path;
        try {
            path = Paths.get(desc1);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new FileUploadException("File upload exception..");
        }
        //获取文件自动生成的路径+命名
        return desc1;
    }

    public static final String uploadFiles1(String baseDir, MultipartFile file, String[] allowedExtension, String id) throws IOException {

        //获取上传图片文件名的长度
        int fileNamelength = file.getOriginalFilename().length();
        //判断上传的图片文件名是否超出定义的长度
        if (fileNamelength > UploadImgUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileUploadException("文件名太长");
        }
        //调用assertAllowed;对上传文件的大小、格式进行校验
        assertAllowed(file, allowedExtension);
        String uploadPath = baseDir + File.separator + id;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String desc1 = uploadPath + File.separator + file.getOriginalFilename();
        //将文件写入磁盘
        Path path;
        try {
            path = Paths.get(desc1);
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new FileUploadException("File upload exception..");
        }
        //获取文件自动生成的路径+命名
        String pathFileName = getPathFileName(baseDir, id) + File.separator + file.getOriginalFilename();
        return pathFileName;
    }
}
