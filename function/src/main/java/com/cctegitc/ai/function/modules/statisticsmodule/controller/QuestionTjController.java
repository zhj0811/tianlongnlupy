package com.cctegitc.ai.function.modules.statisticsmodule.controller;

import com.cctegitc.ai.function.db.pojo.QuestionJl;
import com.cctegitc.ai.function.service.impl.QuestionJlServiceImpl;
import com.cctegitc.ai.function.service.impl.QuestionTjServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"智能调度统计"})
@RestController
@Slf4j
@RequestMapping("/questiontj")
public class QuestionTjController {

    @Autowired
    private QuestionTjServiceImpl questionTjService;

    @Autowired
    private QuestionJlServiceImpl questionJlService;

    @ApiOperation(value = "查询热点问题")
    @GetMapping("/selectHot/{userId}")
    public ResultResponse selectHot(@PathVariable String userId) {
        ResultResponse res = new ResultResponse();
        try {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            if (userId == null || "undefined".equals(userId)){
                userId= "1";
            }
            res.setData(questionTjService.selectHotIndex(Integer.valueOf(userId)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，查询热点问题失败！");
        }
        return res;
    }

    @ApiOperation(value = "查询热点统计页面数据")
    @GetMapping("/selectCount")
    public ResultResponse findCount() {
        ResultResponse res = new ResultResponse();
        try {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(questionTjService.selectCountTj());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，查询热点统计页面数据失败！");
        }
        return res;
    }
}
