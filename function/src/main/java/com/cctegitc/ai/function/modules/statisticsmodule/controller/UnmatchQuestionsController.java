package com.cctegitc.ai.function.modules.statisticsmodule.controller;

import com.cctegitc.ai.function.db.pojo.UnmatchQuestions;
import com.cctegitc.ai.function.service.impl.UnmatchQuestionsServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.UnmatchQuestionsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Api(tags = {"未匹配问题"})
@Slf4j
@RestController
@RequestMapping("/unmatchQuestions")
public class UnmatchQuestionsController {

    @Autowired
    private UnmatchQuestionsServiceImpl unmatchQuestionsService;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody UnmatchQuestions unmatchQuestions) {
        ResultResponse res = new ResultResponse();
        try {
            unmatchQuestionsService.add(unmatchQuestions);
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
    @DeleteMapping("delete/{id}")
    public ResultResponse delete(@PathVariable("id") Long id) {
        ResultResponse res = new ResultResponse();
        try {
            unmatchQuestionsService.delete(id);
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
    public ResultResponse update(@RequestBody UnmatchQuestions unmatchQuestions) {
        ResultResponse res = new ResultResponse();
        try {
            unmatchQuestionsService.updateData(unmatchQuestions);
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
    public ResultResponse findByPage(@RequestBody UnmatchQuestionsVo factoryVo) {
        ResultResponse res = new ResultResponse();
        try {
            UnmatchQuestionsVo pages = unmatchQuestionsService.findByPage(factoryVo, factoryVo.getPage(), factoryVo.getLimit());
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
            UnmatchQuestions entity = unmatchQuestionsService.findById(id);
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

    @ApiOperation(value = "查询所有数据")
    @GetMapping("/findAll")
    public ResultResponse findAll() {
        ResultResponse res = new ResultResponse();
        try {
            List<UnmatchQuestions> list = unmatchQuestionsService.findAll();
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(list);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，查询失败！");
        }
        return res;
    }

}
