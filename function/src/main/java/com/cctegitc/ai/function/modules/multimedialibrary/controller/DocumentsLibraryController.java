package com.cctegitc.ai.function.modules.multimedialibrary.controller;

import cn.hutool.http.HttpStatus;
import com.cctegitc.ai.function.db.pojo.DocumentsLibrary;
import com.cctegitc.ai.function.dto.UploadFileDto;
import com.cctegitc.ai.function.service.impl.DocumentsLibraryServiceImpl;
import com.cctegitc.ai.function.service.impl.SpecialFileUploadService;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.DocumentsLibraryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author peip
 * @since 2022-11-18
 */
@Api(tags = {"文档文件库模块"})
@Slf4j
@RestController
@RequestMapping("/documentsLibrary")
public class DocumentsLibraryController {

    @Autowired
    private DocumentsLibraryServiceImpl documentsLibraryService;

    @Autowired
    private SpecialFileUploadService specialFileUploadService;

    private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";

    @Autowired
    private String documentPath;
    @Autowired
    private String videoPath;
    @Autowired
    private String picturePath;
    @Autowired
    private String audioPath;
    @Autowired
    private String pcmFilesPath;
    @Autowired
    private String wavFilesPath;
    @Autowired
    private String recordFilesPath;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody DocumentsLibrary documentsLibrary) {
        ResultResponse res = new ResultResponse();
        try {
            documentsLibraryService.add(documentsLibrary);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("保存成功!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + ", 保存失败!");
        }
        return res;
    }

    @ApiOperation(value = "删除")
    @PostMapping("delete/{id}")
    public ResultResponse delete(@PathVariable("id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            documentsLibraryService.delete(id);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("删除成功!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + ", 删除失败!");
        }
        return res;
    }

    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResultResponse update(@RequestBody DocumentsLibrary documentsLibrary) {
        ResultResponse res = new ResultResponse();
        try {
            documentsLibraryService.updateData(documentsLibrary);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("修改成功!");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + ", 修改失败!");
        }
        return res;
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/findByPage")
    public ResultResponse findByPage(@RequestBody DocumentsLibrary documentsLibrary) {

        ResultResponse res = new ResultResponse();
        try {
            DocumentsLibraryVo pages = documentsLibraryService.findByPage(documentsLibrary, documentsLibrary.getPage(), documentsLibrary.getLimit());
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(pages);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，查询失败！");
        }
        return res;
    }

    @ApiOperation(value = "id查询")
    @GetMapping("/findById/{id}")
    public ResultResponse findById(@PathVariable Long id) {
        ResultResponse res = new ResultResponse();
        try {
            DocumentsLibrary entity = documentsLibraryService.findById(id);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(entity);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，查询失败！");
        }
        return res;
    }

    @ApiOperation(value = "上传文档文件")
    @PostMapping("/upload")
    public ResultResponse upload(@RequestParam(value = "file", required = false) MultipartFile file
            , HttpServletRequest request) {
        ResultResponse res = new ResultResponse();
        try {
            List<UploadFileDto> uploadFileDtos = specialFileUploadService.uploadDocument(file);
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(uploadFileDtos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + ", 保存失败!");
        }
        return res;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> fileDownload(String fileName, String fileType) {
        String filePath = "";
        try {
            if ("recordFilesPath".equals(fileType)) {
                filePath = recordFilesPath;
            } else if ("documentPath".equals(fileType)) {
                filePath = documentPath;
            } else if ("videoPath".equals(fileType)) {
                filePath = videoPath;
            } else if ("picturePath".equals(fileType)) {
                filePath = picturePath;
            } else if ("audioPath".equals(fileType) || "wavFilesPath".equals(fileType)) {
                filePath = audioPath;
            } else if ("pcmFilesPath".equals(fileType)) {
                filePath = pcmFilesPath;
            } else {
                filePath = wavFilesPath;
            }
            File file = new File(filePath + fileName);

            // 路径为文件且不为空
            if (file.isFile() && file.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(DEFAULT_CONTENT_TYPE))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .body(org.apache.commons.io.FileUtils.readFileToByteArray(org.apache.commons.io.FileUtils.getFile(filePath, fileName)));
            } else {
                log.error("下载文件失败，文件不存在");
                return ResponseEntity.status(HttpStatus.HTTP_INTERNAL_ERROR).build();
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
            return ResponseEntity.status(HttpStatus.HTTP_INTERNAL_ERROR).build();
        }

    }

}
