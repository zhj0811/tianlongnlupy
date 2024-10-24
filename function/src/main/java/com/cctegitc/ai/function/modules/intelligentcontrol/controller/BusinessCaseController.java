package com.cctegitc.ai.function.modules.intelligentcontrol.controller;


import com.cctegitc.ai.authority.common.res.ResultResponse;
import com.cctegitc.ai.function.db.pojo.BusinessCase;
import com.cctegitc.ai.function.service.impl.BusinessCaseServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.ResponseUtils;
import com.cctegitc.ai.function.vo.pagefind.BusinessCaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"业务场景管理模块"})
@RestController
@RequestMapping("/businesscase")
public class BusinessCaseController {
    // 操作成功
    private static final int OPTION_SUCCESS = 1;

    private static final String ERROR_MESSAGE = "系统繁忙，请稍后再试";

    @Autowired
    private BusinessCaseServiceImpl businessCaseService;

    @ApiOperation(value = "所有表单")
    @GetMapping("/findAll")
    public ResultResponse findAll() {
        ResultResponse response = new ResultResponse();
        try {
            List<BusinessCase> businessCaseList = businessCaseService.findAll();
            ResponseUtils.buildResponsePutList(response, businessCaseList, ERROR_MESSAGE);
        } catch (Exception e) {
            ResponseUtils.buildExceptionResp(e, response);
        }
        return response;
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/findByPage")
    public ResultResponse findByPage(@RequestBody BusinessCaseVo businessCaseVo) {
        ResultResponse res = new ResultResponse();
        try {
            BusinessCaseVo pages = businessCaseService.findByPage(businessCaseVo.getPage(), businessCaseVo.getLimit());
            ResponseUtils.buildResponsePutObj(res, pages, ERROR_MESSAGE);
        } catch (Exception e) {
            ResponseUtils.buildExceptionResp(e, res);
        }
        return res;
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody BusinessCase businessCase) {
        ResultResponse res = new ResultResponse();
        try {
            buildPostOptionResp(businessCaseService.add(businessCase), res);
        } catch (Exception e) {
            ResponseUtils.buildExceptionResp(e, res);
        }
        return res;
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public ResultResponse update(@RequestBody BusinessCase businessCase) {
        ResultResponse res = new ResultResponse();
        try {
            buildPostOptionResp(businessCaseService.updateData(businessCase), res);
        } catch (Exception e) {
            ResponseUtils.buildExceptionResp(e, res);
        }
        return res;
    }

    @ApiOperation(value = "删除")
    @PostMapping("delete/{id}")
    public ResultResponse delete(@PathVariable(value = "id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            buildPostOptionResp(businessCaseService.delete(id), res);
        } catch (Exception e) {
            ResponseUtils.buildExceptionResp(e, res);
        }
        return res;
    }

    @ApiOperation(value = "id查询")
    @GetMapping("/findById/{id}")
    public ResultResponse findById(@PathVariable(value = "id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            BusinessCase businessCase = businessCaseService.findById(id);
            ResponseUtils.buildResponsePutObj(res, businessCase, ERROR_MESSAGE);
        } catch (Exception e) {
            ResponseUtils.buildExceptionResp(e, res);
        }
        return res;
    }

    private void buildPostOptionResp(int businessCaseService, ResultResponse res) {
        if (businessCaseService == OPTION_SUCCESS) {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData("操作成功");
        } else {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(ERROR_MESSAGE);
        }
    }
}
