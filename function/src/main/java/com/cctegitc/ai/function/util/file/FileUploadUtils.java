package com.cctegitc.ai.function.util.file;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ArrayUtil;
import com.cctegitc.ai.function.constant.Constant;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


public class FileUploadUtils {

    /**
     * 默认大小 100M
     */
    public static final long DEFAULT_MAX_SIZE = 100 * 1024 * 1024;

    /**
     * 默认的文件名最大长度 100
     */
    public static final int DEFAULT_FILE_NAME_LENGTH = 100;

    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

//    资源映射路径 前缀
//    public static final String RESOURCE_PREFIX = "/profile";
//   读取yml文件上传的存放的根目录和Hp1Config方法的功能一样
//    @Value("${swcpt.profile}")
//    private String profilePath;

    /**
     * @Description: 根据文件路径上传
     * @Author: yrc
     * @Date: 2021/11/3 14:30
     * @Param: [baseDir:上传文件路径;即文件上传后要存放的地址路径, file:上传的文件]
     * @Return: java.lang.String
     * @Throws:
     */
    public static final String upload(String baseDir, MultipartFile file) throws Exception {
        try {
            return uploadFile(baseDir, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
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
    public static final String uploadFile(String baseDir, MultipartFile file, String[] allowedExtension) throws IOException {

        //获取上传图片文件名的长度
        int fileNamelength = file.getOriginalFilename().length();
        //判断上传的图片文件名是否超出定义的长度
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileUploadException("文件名太长");
        }
        //调用assertAllowed;对上传文件的大小、格式进行校验
        assertAllowed(file, allowedExtension);
        //对上传文件重命名
        String fileName = extractFilename(file);
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
    public static final String extractFilename(MultipartFile file) {
        //获取上传的图片文件名
        String fileName = file.getOriginalFilename();
        //获取上传图片文件的格式：getExtension();
        String extension = getExtension(file);
        //对上传的文件名加密；即重命名
        fileName = UUID.fastUUID().toString() + "." + extension;
        return fileName;
    }


    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
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

    private static final String getPathFileName(String uploadDir, String fileName) {
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

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULLSTR;
        }
        if (start < 0) {
            start = str.length() + start;
        }
        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }
        return str.substring(start);
    }

    public static String getFilePath(String parent, MultipartFile file, String businessType) throws Exception {
        String originalFilename = file.getOriginalFilename();
        LocalDate today = LocalDate.now();
        StringBuilder path = new StringBuilder(parent);
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        if (!originalFilename.contains(Constant.POINT)) {
            throw new Exception("文件扩展名不正确");
        }
        String extension =
                originalFilename.substring(originalFilename.lastIndexOf(Constant.POINT)).replace(Constant.POINT, "");
        if (!ArrayUtil.contains(MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION, extension.toLowerCase(Locale.ENGLISH))) {
            throw new Exception("不允许的文件类型");
        }
        String uuid = UUID.randomUUID(true).toString().replace(Constant.DASH_LINE, Constant.BLANK_STR);
        path.append(year);
        path.append(File.separator);
        path.append(month);
        path.append(File.separator);
        path.append(day);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(businessType)) {
            path.append(File.separator);
            path.append(businessType);
        }
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        path.append(File.separator);
        path.append(uuid);
        path.append(Constant.POINT);
        path.append(extension);
        return path.toString();
    }
}
