package com.cctegitc.ai.function.scheduleTask.util;

import com.cctegitc.ai.authority.common.utils.spring.SpringUtils;
import com.cctegitc.ai.function.common.constant.Constants;
import com.cctegitc.ai.function.common.constant.ScheduleConstants;
import com.cctegitc.ai.function.scheduleTask.service.impl.SysJobLogServiceImpl;
import com.cctegitc.ai.function.util.StringUtils;
import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import com.cctegitc.ai.function.scheduleTask.domain.SysJobLog;
import com.cctegitc.ai.function.scheduleTask.service.ISysJobLogService;
import com.cctegitc.ai.function.util.common.utils.DateUtils;
import com.cctegitc.ai.function.util.common.utils.ExceptionUtil;
import com.cctegitc.ai.function.util.common.utils.bean.BeanUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 抽象quartz调用
 */
public abstract class AbstractQuartzJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SysJob sysJob = new SysJob();
        BeanUtils.copyBeanProp(sysJob, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));
        try {
            before(context, sysJob);
            doExecute(context, sysJob);
            after(context, sysJob, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, sysJob, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     */
    protected void before(JobExecutionContext context, SysJob sysJob) {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     */
    protected void after(JobExecutionContext context, SysJob sysJob, Exception e) {
        threadLocal.remove();
        final SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobName(sysJob.getJobName());
        sysJobLog.setJobGroup(sysJob.getJobGroup());
        sysJobLog.setInvokeTarget(sysJob.getInvokeTarget());
        sysJobLog.setJobMessage(sysJobLog.getJobName() + " 定时任务执行时间: " + DateUtils.dateTimeNow());
        if (e != null) {
            sysJobLog.setStatus(Constants.FAIL);
            String errorMsg = StringUtils.substring(ExceptionUtil.getExceptionMessage(e), 0, 2000);
            sysJobLog.setExceptionInfo(errorMsg);
        } else {
            sysJobLog.setStatus(Constants.SUCCESS);
        }
        // 写入数据库当中
        SpringUtils.getBean(SysJobLogServiceImpl.class).addJobLog(sysJobLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param sysJob  系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception;
}
