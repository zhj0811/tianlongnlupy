package com.cctegitc.ai.function.modules.intelligentcontrol.controller;

import com.cctegitc.ai.function.db.pojo.VoiceReport;
import com.cctegitc.ai.function.service.impl.VoiceReportServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.VoiceReportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(tags = {"语音播报管理模块"})
@RestController
@RequestMapping("/voicereport")
public class VoiceReportController {

    @Autowired
    private VoiceReportServiceImpl voiceReportService;

    @ApiOperation(value = "所有表单")
    @GetMapping("/findAll")
    public ResultResponse findAll() {
        ResultResponse res = new ResultResponse();
        try {
            List<VoiceReport> voiceReportList = voiceReportService.findAll();
            if (voiceReportList != null && !voiceReportList.isEmpty()) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(voiceReportList);
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
        return res;
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/findByPage")
    public ResultResponse findByPage(@RequestBody VoiceReportVo voiceReportVo) {
        ResultResponse res = new ResultResponse();
        try {
            VoiceReportVo pages = voiceReportService.findByPage(voiceReportVo.getItems(), voiceReportVo.getPage(), voiceReportVo.getLimit());
            if (Objects.nonNull(pages)) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(pages);
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
        return res;
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody VoiceReport voiceReport) {
        ResultResponse res = new ResultResponse();
        try {
            int i = voiceReportService.add(voiceReport);
            if (i == 1) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(Constants.SAVE_SUCESS);
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.OPT_FAILED);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.SAVE_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public ResultResponse update(@RequestBody VoiceReport voiceReport) {
        ResultResponse res = new ResultResponse();
        try {
            int i = voiceReportService.updateData(voiceReport);
            if (i == 1) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(Constants.UPDATE_SUCESS);
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.OPT_FAILED);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.UPDATE_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "删除")
    @PostMapping("delete/{id}")
    public ResultResponse delete(@PathVariable(value = "id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            int i = voiceReportService.delete(id);
            if (i == 1) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(Constants.DELETE_SUCESS);
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.OPT_FAILED);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.DELETE_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "id查询")
    @GetMapping("/findById/{id}")
    public ResultResponse findById(@PathVariable(value = "id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            VoiceReport voiceReport = voiceReportService.findById(id);
            if (Objects.nonNull(voiceReport)) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(voiceReport);
            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
        return res;
    }
}
