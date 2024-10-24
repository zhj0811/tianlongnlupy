package com.cctegitc.ai.function.modules.intelligentcontrol.controller;

import com.cctegitc.ai.function.db.pojo.InstructConfigg;
import com.cctegitc.ai.function.service.impl.InstructConfiggServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.InstructConfiggVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(tags = {"指令配置管理模块"})
@RestController
@RequestMapping("/instructconfigg")
public class InstructConfiggController {

    @Autowired
    private InstructConfiggServiceImpl instructConfiggService;

    @ApiOperation(value = "所有表单")
    @GetMapping("/findAll")
    public ResultResponse findAll() {
        ResultResponse res = new ResultResponse();
        try {
            List<InstructConfigg> instructConfiggList = instructConfiggService.findAll();
            if (instructConfiggList != null && !instructConfiggList.isEmpty()) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(instructConfiggList);
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
    public ResultResponse findByPage(@RequestBody InstructConfiggVo instructConfiggVo) {
        ResultResponse res = new ResultResponse();
        try {
            InstructConfiggVo pages = instructConfiggService.findByPage(instructConfiggVo.getPage(), instructConfiggVo.getLimit());
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
    public ResultResponse add(@RequestBody InstructConfigg instructConfigg) {
        ResultResponse res = new ResultResponse();
        try {
            int i = instructConfiggService.add(instructConfigg);
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
    public ResultResponse update(@RequestBody InstructConfigg instructConfigg) {
        ResultResponse res = new ResultResponse();
        try {
            int i = instructConfiggService.updateData(instructConfigg);
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
            int i = instructConfiggService.delete(id);
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
            InstructConfigg instructConfigg = instructConfiggService.findById(id);
            if (Objects.nonNull(instructConfigg)) {
                res.setCode(Constants.STATUS_OK);
                res.setMessage(Constants.MESSAGE_OK);
                res.setData(instructConfigg);
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
