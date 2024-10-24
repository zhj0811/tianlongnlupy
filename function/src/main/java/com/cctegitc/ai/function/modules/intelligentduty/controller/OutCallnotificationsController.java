package com.cctegitc.ai.function.modules.intelligentduty.controller;


import com.cctegitc.ai.function.db.pojo.OutCallnotifications;
import com.cctegitc.ai.function.service.impl.OutCallnotificationsServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.OutCallnotificationsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Api(tags = "外呼通知模块")
@RequestMapping("/outcall")
public class OutCallnotificationsController {

    @Autowired
    private OutCallnotificationsServiceImpl outCallnotificationsService;

    @ApiOperation(value = "所有表单")
    @GetMapping("/findAll")
    public ResultResponse findAll() {
        ResultResponse res = new ResultResponse();
        try {
            List<OutCallnotifications> callnotificationsList = outCallnotificationsService.findAll();
            if (callnotificationsList != null && !callnotificationsList.isEmpty()) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(callnotificationsList);
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
    public ResultResponse findByPage(@RequestBody OutCallnotifications outCallnotifications) {
        ResultResponse res = new ResultResponse();
        try {
            OutCallnotificationsVo pages = outCallnotificationsService.findByPage(outCallnotifications, outCallnotifications.getPage(), outCallnotifications.getLimit());
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
    public ResultResponse add(@RequestBody OutCallnotifications outCallnotifications) {
        ResultResponse res = new ResultResponse();
        try {
            int i = outCallnotificationsService.add(outCallnotifications);
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
    public ResultResponse update(@RequestBody OutCallnotifications outCallnotifications) {
        ResultResponse res = new ResultResponse();
        try {
            int i = outCallnotificationsService.updateData(outCallnotifications);
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
            int i = outCallnotificationsService.delete(id);
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
            OutCallnotifications outCallnotifications = outCallnotificationsService.findById(id);
            if (Objects.nonNull(outCallnotifications)) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(outCallnotifications);
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
