package com.cctegitc.ai.function.scheduleTask.controller;

import com.cctegitc.ai.function.common.BaseController;
import com.cctegitc.ai.function.common.core.domain.AjaxResult;
import com.cctegitc.ai.function.common.core.page.TableDataInfo;
import com.cctegitc.ai.function.common.exception.job.TaskException;
import com.cctegitc.ai.function.scheduleTask.service.impl.SysJobServiceImpl;
import com.cctegitc.ai.function.util.Constants;
import com.cctegitc.ai.function.util.ExcelUtil;
import com.cctegitc.ai.function.util.StringUtils;
import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import com.cctegitc.ai.function.scheduleTask.util.CronUtils;
import com.cctegitc.ai.function.util.common.res.ResultResponse;
import com.cctegitc.ai.function.vo.pagefind.SysJobPageVO;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * 调度任务信息操作处理
 * 
 *
 */
@RestController
@RequestMapping("/monitor/job")
public class SysJobController extends BaseController
{
    @Autowired
    private SysJobServiceImpl jobService;

    /**
     * 查询定时任务列表
     */
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<SysJob> list = jobService.selectJobList();
        return getDataTable(list);
    }

    @GetMapping("/info/{jobId}")
    public ResultResponse info(@PathVariable("jobId") Long jobId) {
        ResultResponse res = new ResultResponse();
        res.setCode(Constants.STATUS_OK);
        res.setMessage(Constants.MESSAGE_OK);
        SysJob sysJob = jobService.selectJobById(jobId);
        res.setData(sysJob);
        return res;
    }

    @GetMapping("/findByPage")
    public ResultResponse findByPage(Integer page, Integer limit) {
        ResultResponse res = new ResultResponse();
        try {
            SysJobPageVO jobPageVO = jobService.findByPage(page, limit);
            if (Objects.nonNull(jobPageVO)) {
                return new ResultResponse(Constants.STATUS_OK, Constants.MESSAGE_OK, jobPageVO);
            } else {
                return new ResultResponse(Constants.STATUS_FAIL, Constants.MESSAGE_FAIL, "未查询到数据");
            }
        } catch (Exception e) {
            res.setCode(Constants.STATUS_FAIL);
            res.setMessage(Constants.MESSAGE_FAIL);
            res.setData(e.getMessage() + "，" + Constants.FAIL_TO_GET);
        }
        return res;
    }


    /**
     * 导出定时任务列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJob sysJob)
    {
        List<SysJob> list = jobService.selectJobList();
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        util.exportExcel(list, "定时任务");
    }

    /**
     * 新增定时任务
     */
    @PostMapping("/add") public AjaxResult add(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        }

        return toAjax(jobService.insertJob(job));
    }

    /**
     * 修改定时任务
     */
    @PostMapping("/update") public AjaxResult update(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return error("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        }
        return toAjax(jobService.updateJob(job));
    }

    /**
     * 定时任务状态修改
     */
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(Long jobId, String status) throws SchedulerException
    {
        SysJob newJob = jobService.selectJobById(jobId);
        newJob.setStatus(status);
        return toAjax(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException
    {
        boolean result = jobService.run(job.getId(), job.getJobGroup());
        return result ? success() : error("任务不存在或已过期！");
    }

    /**
     * 删除定时任务
     */
    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable List<Long> jobIds, String jobGroupName) throws SchedulerException, TaskException
    {
        jobService.deleteJobByIds(jobGroupName, jobIds);
        return success();
    }
}
