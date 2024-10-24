package com.cctegitc.ai.function.service;

import com.cctegitc.ai.function.db.dao.FileSaveMapper;
import com.cctegitc.ai.function.db.pojo.FileEntity;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileSaveService {

    @Resource
    private FileSaveMapper fileRepository;

    public ResultResponse saveFile(String originalFileName, String md5FileName) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setOriginalFileName(originalFileName);
        fileEntity.setMd5FileName(md5FileName);
        int insert = fileRepository.insert(fileEntity);
        if (insert> 0){
            return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, "保存成功");
        }
        return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, "保存失败");
    }

    public String findByOriginalFileName(String originalFileName) {
        return fileRepository.findByOriginalFileName(originalFileName);
    }
}