package com.cctegitc.ai.function.modules.intelligentduty.controller;


import com.cctegitc.ai.function.db.pojo.IntelligenceRecord;
import com.cctegitc.ai.function.service.impl.IntelligenceRecordServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.IntelligenceRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author peip
 * @since 2022-11-18
 */
@Api(tags = {"电子台账"})
@Slf4j
@RestController
@RequestMapping("/record")
public class IntelligenceRecordController {

    @Autowired
    private IntelligenceRecordServiceImpl intelligenceRecordService;


    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody IntelligenceRecord intelligenceRecord) {
        ResultResponse res = new ResultResponse();
        try {
            intelligenceRecordService.add(intelligenceRecord);
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
            intelligenceRecordService.delete(id);
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
    public ResultResponse update(@RequestBody IntelligenceRecord intelligenceRecord) {
        ResultResponse res = new ResultResponse();
        try {
            intelligenceRecordService.updateData(intelligenceRecord);
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
    public ResultResponse findByPage(@RequestBody IntelligenceRecord intelligenceRecord) {

        ResultResponse res = new ResultResponse();
        try {
            IntelligenceRecordVo pages = intelligenceRecordService.findByPage(intelligenceRecord, intelligenceRecord.getPage(), intelligenceRecord.getLimit());
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
            IntelligenceRecord entity = intelligenceRecordService.findById(id);
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


}
