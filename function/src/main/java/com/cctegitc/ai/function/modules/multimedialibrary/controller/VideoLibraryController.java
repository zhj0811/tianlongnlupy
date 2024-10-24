package com.cctegitc.ai.function.modules.multimedialibrary.controller;

import com.cctegitc.ai.function.db.pojo.VideoLibrary;
import com.cctegitc.ai.function.dto.UploadFileDto;
import com.cctegitc.ai.function.service.impl.SpecialFileUploadService;
import com.cctegitc.ai.function.service.impl.VideoLibraryServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.VideoLibraryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author peip
 * @since 2022-11-18
 */
@Api(tags = {"视频文件库模块"})
@Slf4j
@RestController
@RequestMapping("/videoLibrary")
public class VideoLibraryController {

    @Autowired
    private VideoLibraryServiceImpl videoLibraryService;

    @Autowired
    private SpecialFileUploadService specialFileUploadService;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody VideoLibrary videoLibrary) {
        ResultResponse res = new ResultResponse();
        try {
            videoLibraryService.add(videoLibrary);
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
            videoLibraryService.delete(id);
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
    public ResultResponse update(@RequestBody VideoLibrary videoLibrary) {
        ResultResponse res = new ResultResponse();
        try {
            videoLibraryService.updateData(videoLibrary);
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
    public ResultResponse findByPage(@RequestBody VideoLibrary videoLibrary) {

        ResultResponse res = new ResultResponse();
        try {
            VideoLibraryVo pages = videoLibraryService.findByPage(videoLibrary, videoLibrary.getPage(), videoLibrary.getLimit());
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
            VideoLibrary entity = videoLibraryService.findById(id);
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

    @ApiOperation(value = "上传视频文件")
    @PostMapping("/upload")
    public ResultResponse upload(@RequestParam(value = "file", required = false) MultipartFile file
            , HttpServletRequest request) {
        ResultResponse res = new ResultResponse();
        try {
            List<UploadFileDto> uploadFileDtos = specialFileUploadService.uploadVideo(file);
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

}
