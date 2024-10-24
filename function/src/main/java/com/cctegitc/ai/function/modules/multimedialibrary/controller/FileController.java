package com.cctegitc.ai.function.modules.multimedialibrary.controller;

import com.cctegitc.ai.function.service.FileSaveService;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.util.file.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileSaveService fileService;

    @PostMapping("/upload")
    public ResultResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 保存文件到服务器，计算MD5值
        String originalFileName = file.getOriginalFilename();
        String md5FileName = MD5Util.generateMD5WithTimestamp(file.getBytes());
        return fileService.saveFile(originalFileName, md5FileName);
    }

    @GetMapping("/download")
    public ResultResponse downloadFile(@RequestParam("fileName") String fileName) {
        String md5FileName = fileService.findByOriginalFileName(fileName);
        return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, "下载成功");
    }
}
