package com.cctegitc.ai.function.modules.knowledgemanagement.controller;


import com.cctegitc.ai.function.db.pojo.KnowledgeLibrary;
import com.cctegitc.ai.function.db.pojo.PageBasics;
import com.cctegitc.ai.function.db.pojo.R;
import com.cctegitc.ai.function.modules.knowledgemanagement.service.impl.QuestionlibServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.ExcelUtil;
import com.cctegitc.ai.function.util.HttpURLConnectionUtil;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.util.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Api(tags = {"知识问答库模块"})
@RestController
@RequestMapping("/knowledgelibrary")
@Slf4j
public class QuestionLibController {

    @Autowired
    private QuestionlibServiceImpl iQuestionlibService;

    /**
     * 分页查询所有问答知识
     *
     * @return
     */
    @ApiOperation(value = "分页查询所有问答知识")
    @PostMapping("/findByPage")
    public ResultResponse findByPage(@RequestBody PageBasics pageBasics) {
        ResultResponse res = new ResultResponse();
        try {
            R pages = iQuestionlibService.findByPage(pageBasics.getQuestion(), pageBasics.getPage(), pageBasics.getLimit());
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

    @ApiOperation(value = "新增问答知识")
    @PostMapping("/save")
    public ResultResponse save(@RequestBody KnowledgeLibrary knowledgeLibrary) {
        log.debug("Saving knowledgeLibrary: {}", knowledgeLibrary);
        if (knowledgeLibrary == null || StringUtils.isEmpty(knowledgeLibrary.getQuestion()) || StringUtils.isEmpty(knowledgeLibrary.getAnswer())) {
            return  new ResultResponse(Constants.STATUS_FAIL,Constants.MESSAGE_FAIL,"问题或答案不能为空");
        }
        return iQuestionlibService.save(knowledgeLibrary);
    }

    /**
     * 修改当前该问答知识
     *
     * @param knowledgeLibrary
     * @return
     */
    @ApiOperation("修改当前该问答知识")
    @PostMapping("/update")
    public ResultResponse update(@RequestBody KnowledgeLibrary knowledgeLibrary) {
        if (knowledgeLibrary == null || StringUtils.isEmpty(knowledgeLibrary.getQuestion()) || StringUtils.isEmpty(knowledgeLibrary.getAnswer())) {
            return  new ResultResponse(Constants.STATUS_FAIL,Constants.MESSAGE_FAIL,"问题或答案不能为空");
        }
        log.debug("Updating knowledgeLibrary: {}",knowledgeLibrary);
        return iQuestionlibService.update(knowledgeLibrary);
    }

    /**
     * 删除当前该问答知识
     */
    @ApiOperation("删除当前该问答知识")
    @PostMapping("/delete/{questionId}")
    public ResultResponse delete(@PathVariable("questionId") Integer questionId) {
        if (questionId == null) {
            return  new ResultResponse(Constants.STATUS_FAIL,Constants.MESSAGE_FAIL,"问题或答案不能为空");
        }
        return iQuestionlibService.delete(questionId);
    }

    @ApiOperation("导入问题")
    @PostMapping("/importData")
    public R importData(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        ExcelUtil<KnowledgeLibrary> util = new ExcelUtil<KnowledgeLibrary>(KnowledgeLibrary.class);
        List<KnowledgeLibrary> knowledgeLibraries = util.importExcel(file.getInputStream());
        String message = iQuestionlibService.importData(knowledgeLibraries);
        return R.success(message);
    }


    @ApiOperation("查询当前该问答知识关联问题id最大值")
    @GetMapping("/selectIdMax")
    public ResultResponse selectIdMax() {
        ResultResponse res = new ResultResponse();
        try {
            Integer ids = iQuestionlibService.selectIdMax();
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(ids);
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage());
        }
        return res;
    }


    /**
     * 分页查询所有问答知识
     *
     * @return
     */
    @ApiOperation(value = "查询所有问题包括关联问题")
    @PostMapping("/findAll")
    public ResultResponse findAll() {
        ResultResponse res = new ResultResponse();
        try {
            res.setCode(Constants.STATUS_OK);
            res.setMessage(Constants.MESSAGE_OK);
            res.setData(iQuestionlibService.findAll());

        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
        return res;
    }
}
