package com.cctegitc.ai.function.scheduleTask.controller;

import com.cctegitc.ai.function.common.BaseController;
import com.cctegitc.ai.function.common.core.domain.AjaxResult;
import com.cctegitc.ai.function.common.core.page.TableDataInfo;
import com.cctegitc.ai.function.scheduleTask.domain.SysJobLog;
import com.cctegitc.ai.function.scheduleTask.service.impl.SysJobLogServiceImpl;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.SysJobLogPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 调度日志操作处理
 * 
 *
 */
@RestController
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController
{

    @Autowired
    private SysJobLogServiceImpl jobLogService;

    /**
     * 查询定时任务调度日志列表
     */
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList();
        return getDataTable(list);
    }

    @GetMapping("/findByPage")
    public ResultResponse findByPage(Integer page, Integer limit) {
        ResultResponse res = new ResultResponse();
        try {
            SysJobLogPageVO pages = jobLogService.findByPage(page, limit);
            if (Objects.nonNull(pages)) {
                res.setCode(com.cctegitc.ai.function.util.Constants.STATUS_OK);
                res.setMessage(com.cctegitc.ai.function.util.Constants.MESSAGE_OK);
                res.setData(pages);
            } else {
                res.setCode(com.cctegitc.ai.function.util.Constants.STATUS_FAIL);
                res.setMessage(com.cctegitc.ai.function.util.Constants.MESSAGE_FAIL);
                res.setData(com.cctegitc.ai.function.util.Constants.FAIL_TO_GET);
            }
        } catch (Exception e) {
            res.setCode(com.cctegitc.ai.function.util.Constants.STATUS_FAIL);
            res.setMessage(com.cctegitc.ai.function.util.Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + com.cctegitc.ai.function.util.Constants.FAIL_TO_GET);
        }
        return res;
    }


    /**
     * 删除定时任务调度日志
     */
    @DeleteMapping("/{jobLogIds}")
    public AjaxResult remove(@PathVariable Long[] jobLogIds)
    {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }

    /**
     * 清空定时任务调度日志
     */
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}
