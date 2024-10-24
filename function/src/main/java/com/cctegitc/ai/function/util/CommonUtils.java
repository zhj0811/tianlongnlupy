package com.cctegitc.ai.function.util;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cctegitc.ai.function.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 工具类
 *
 * @author jiangyang
 * @date 2022-04-01 10:22:15
 */
@Slf4j
public class CommonUtils {

    /**
     * 根据正则获取第一个匹配组
     *
     * @param resource 源字符串
     * @param regx     正则
     * @return String
     * @author jiangyang
     * @date 2022-04-01
     */
    public static String getMatchFirstGroupStr(String resource, String regx) {
        Pattern pattern = PatternPool.get(regx);
        Matcher matcher = pattern.matcher(resource);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return Constant.BLANK_STR;
    }

    /**
     * 生成uuid
     *
     * @return String
     * @author jiangyang
     * @date 2022-04-06
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Integer convertStr2Integer(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                return Integer.parseInt(str.trim());
            } catch (Exception e) {
                log.error("convert {} to number failed", str);
            }
        }
        return null;

    }

    public static String splitString(String resource, String delimiter, int index) {
        if (StringUtils.isBlank(resource)) {
            return Constant.BLANK_STR;
        }
        String[] split = resource.split(delimiter);
        // "aaa bbbb"; length =2 index =1
        if (split.length < index + 1) {
            return Constant.BLANK_STR;
        }
        return split[index];
    }

    public static Double convertToDouble(String str) {
        if (StringUtils.isNotBlank(str)) {
            try {
                return Double.parseDouble(str.trim());
            } catch (Exception e) {
                log.error("convert {} to number failed", str);
            }
        }
        return null;
    }

    public static String roundStr(String src, int scale) {
        Double doubleNum = convertToDouble(src);
        if (doubleNum != null) {
            return NumberUtil.roundStr(doubleNum, scale);
        }
        return src;
    }

    /**
     * 用字节数组和文件名生成 ResponseEntity对象
     *
     * @param bytes    字节数组
     * @param fileName 文件名
     * @return ResponseEntity<byte>
     * @author jiangyang
     * @date 2022-04-08
     */
    public static ResponseEntity<byte[]> createResponseEntity(byte[] bytes, String fileName) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
                URLEncoder.encode(fileName, Charset.defaultCharset().name()));
        headers.setContentLength(bytes.length);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setAccessControlExposeHeaders(Arrays.asList(HttpHeaders.CONTENT_DISPOSITION, HttpHeaders.CONTENT_LENGTH,
                HttpHeaders.CONTENT_TYPE));
        return ResponseEntity.status(HttpStatus.SC_OK).headers(headers).body(bytes);
    }

    public static ResponseEntity<byte[]> createResponseEntity(Workbook workbook, String fileName) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return CommonUtils.createResponseEntity(bytes, fileName);
    }

}
