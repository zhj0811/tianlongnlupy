package com.cctegitc.ai.function.modules.contactsmodule.controller;

import com.cctegitc.ai.authority.common.res.ResultResponse;
import com.cctegitc.ai.function.db.pojo.ExpertLibrary;
import com.cctegitc.ai.function.service.impl.ExpertLibraryServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.vo.pagefind.ExpertLibraryVo;
import com.cctegitc.ai.function.vo.ResultVo;
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
@Api(tags = {"专家库"})
@Slf4j
@RestController
@RequestMapping("/expertLibrary")
public class ExpertLibraryController {

    @Autowired
    private ExpertLibraryServiceImpl expertLibraryService;

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultResponse add(@RequestBody ExpertLibrary expertLibrary) {
        ResultResponse res = new ResultResponse();
        try {
            expertLibraryService.add(expertLibrary);
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
            expertLibraryService.delete(id);
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
    public ResultResponse update(@RequestBody ExpertLibrary expertLibrary) {
        ResultResponse res = new ResultResponse();
        try {
            expertLibraryService.updateData(expertLibrary);
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
    public ResultResponse findByPage(@RequestBody ExpertLibrary factory) {
        ResultResponse res = new ResultResponse();
        try {
            ExpertLibraryVo pages = expertLibraryService.findByPage(factory, factory.getPage(), factory.getLimit());
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
            ExpertLibrary entity = expertLibraryService.findById(id);
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
            List<ExpertLibrary> list = expertLibraryService.findAll();
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

    @ApiOperation(value = "查询所有数据提供语义")
    @GetMapping("/find")
    public ResultResponse find() {
        ResultResponse res = new ResultResponse();
        try {
            List<ExpertLibrary> list = expertLibraryService.find();
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

    @ApiOperation(value = "查询所有其他联系人部门")
    @GetMapping("/getDept")
    public ResultResponse getDept() {
        ResultResponse res = new ResultResponse();
        try {
            List<ResultVo> entity = expertLibraryService.getDept();
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

    @ApiOperation(value = "查询所有其他联系人部门所有人")
    @GetMapping("/getPeople")
    public ResultResponse getPeople(String dept) {
        ResultResponse res = new ResultResponse();
        try {
            List<ResultVo> entity = expertLibraryService.getPeople(dept);
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

    @ApiOperation(value = "查询所有其他联系人部门所有人")
    @GetMapping("/getPhone")
    public ResultResponse getPhone(String name, String dept) {
        ResultResponse res = new ResultResponse();
        try {
            List<ResultVo> entity = expertLibraryService.getPhone(name, dept);
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
