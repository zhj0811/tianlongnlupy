package com.cctegitc.ai.function.modules.intelligentduty.controller;

import com.cctegitc.ai.function.db.pojo.Memo;

import com.cctegitc.ai.function.modules.intelligentduty.service.impl.VoiceMemoTaskServiceImpl;
import com.cctegitc.ai.function.service.impl.VoiceMemoServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.VoiceMemoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Api(tags = "语音备忘录模块")
@RequestMapping("/voicememo")
@Slf4j
public class VoiceMemoController {

    @Autowired
    private VoiceMemoServiceImpl voiceMemoService;

    @Autowired
    private VoiceMemoTaskServiceImpl voiceMemoCreateTaskService;

    @ApiOperation(value = "所有表单")
    @GetMapping("/findAll")
    public ResultResponse findAll() {
        ResultResponse res = new ResultResponse();
        try {
            List<Memo> voiceMemoList = voiceMemoService.findAll();
            if (voiceMemoList != null && !voiceMemoList.isEmpty()) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(voiceMemoList);
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
    public ResultResponse findByPage(Integer page, Integer limit) {
        try {
            VoiceMemoVo voiceMemoVo = voiceMemoService.findByPage(page, limit);
            if (Objects.nonNull(voiceMemoVo)) {
                return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, voiceMemoVo);
            } else {
                return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody Memo voiceMemo) {
        ResultResponse res = new ResultResponse();
        try {
            int i = voiceMemoService.add(voiceMemo);
            if (i == 1) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(Constants.SAVE_SUCESS);
                try {
                    voiceMemoCreateTaskService.createTask(voiceMemo);
                } catch (Exception e) {
                    log.error("创建语音备忘录任务失败", e);
                }

            } else {
                res.setCode(Constants.STATUS_FAIL);
                res.setMessage(Constants.MESSAGE_FAIL);
                res.setData(Constants.OPT_FAILED);
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.toString() + "," + Constants.SAVE_FAILED);
        }
        return res;
    }

    @ApiOperation(value = "更新")
    @PostMapping("/update")
    public ResultResponse update(@RequestBody Memo voiceMemo) {
        ResultResponse res = new ResultResponse();
        try {
            int i = voiceMemoService.updateData(voiceMemo);
            if (i == 1) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(Constants.UPDATE_SUCESS);
                try {
                    voiceMemoCreateTaskService.updateTask(voiceMemo);
                } catch (Exception e) {
                    log.error("更新语音备忘录任务失败", e);
                }
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
            int i = voiceMemoService.delete(id);
            if (i == 1) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(Constants.DELETE_SUCESS);
                voiceMemoCreateTaskService.deleteTask(id);
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
            Memo voiceMemo = voiceMemoService.findById(id);
            if (Objects.nonNull(voiceMemo)) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(voiceMemo);
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
