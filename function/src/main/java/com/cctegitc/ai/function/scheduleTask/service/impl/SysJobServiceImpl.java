package com.cctegitc.ai.function.scheduleTask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cctegitc.ai.function.common.constant.ScheduleConstants;
import com.cctegitc.ai.function.common.exception.job.TaskException;
import com.cctegitc.ai.function.db.dao.SysJobMapper;
import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import com.cctegitc.ai.function.scheduleTask.service.ISysJobService;
import com.cctegitc.ai.function.scheduleTask.util.CronUtils;
import com.cctegitc.ai.function.scheduleTask.util.ScheduleUtils;
import com.cctegitc.ai.function.util.FindByPageHelper;
import com.cctegitc.ai.function.vo.pagefind.SysJobPageVO;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 */

@Service
public class SysJobServiceImpl implements ISysJobService {
    private Scheduler scheduler;

    @Resource
    private SysJobMapper sysJobMapper;

    public SysJobServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * 项目启动时，初始化定时器 主要是防止手动修改数据库导致未同步到定时任务处理（注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException {
        scheduler.clear();
        List<SysJob> jobList = sysJobMapper.selectList(new QueryWrapper<SysJob>());
        for (SysJob job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }

    /**
     * 获取quartz调度器的计划任务列表
     *
     * @return
     */
    @Override
    public List<SysJob> selectJobList() {
        return sysJobMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public SysJobPageVO findByPage(Integer page, Integer limit) {
        return FindByPageHelper.findByPage(SysJobPageVO.class, new QueryWrapper<SysJob>(), page, limit, sysJobMapper);
    }


    @Override
    @Transactional
    public void deleteJobByJobName(String jobName, String jobGroup) throws SchedulerException {
        List<SysJob> sysJobs = selectJobByJobName(jobName, jobGroup);
        for (SysJob sysJob : sysJobs) {
            deleteJob(sysJob);
        }
    }

    public List<SysJob> selectJobByJobName(String jobName, String jobGroup) {
        return sysJobMapper.selectByName(jobName, jobGroup);
    }

    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public SysJob selectJobById(Long jobId) {
        return sysJobMapper.selectById(jobId);
    }

    /**
     * 暂停任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int pauseJob(SysJob job) throws SchedulerException {
        Long jobId = job.getId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        int rows = sysJobMapper.update(job, new UpdateWrapper<SysJob>().eq("id", jobId).eq("job_group", jobGroup));
        if (rows > 0) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * 恢复任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resumeJob(SysJob job) throws SchedulerException {
        Long jobId = job.getId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = sysJobMapper.update(job, new UpdateWrapper<SysJob>().eq("id", jobId).eq("job_group", jobGroup));
        if (rows > 0) {
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteJob(SysJob job) throws SchedulerException {
        Long jobId = job.getId();
        String jobGroup = job.getJobGroup();
        int rows = sysJobMapper.deleteById(jobId);
        if (rows > 0) {
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return rows;
    }

    /**
     * 批量删除调度信息
     *
     * @param jobGroupName
     * @param idList
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJobByIds(String jobGroupName, List<Long> idList) throws SchedulerException {
        sysJobMapper.deleteBatchIds(idList);
        for (Long jobId : idList) {
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroupName));
        }
    }

    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeStatus(SysJob job) throws SchedulerException {
        int rows = 0;
        String status = job.getStatus();
        if (ScheduleConstants.Status.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }

    /**
     * 立即运行任务
     *
     * @param jobGroup
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean run(Long jobId, String jobGroup) throws SchedulerException {
        boolean result = false;

        SysJob properties = selectJobById(jobId);
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            result = true;
            scheduler.triggerJob(jobKey, dataMap);
        }
        return result;
    }

    /**
     * 新增任务
     *
     * @param job 调度信息 调度信息
     */
    @Override
    public int insertJob(SysJob job) throws SchedulerException, TaskException {
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        int rows = sysJobMapper.insert(job);
        System.out.println(rows);
        if (rows > 0) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }

    /**
     * 更新任务的时间表达式
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateJob(SysJob job) throws SchedulerException, TaskException {
        UpdateWrapper<SysJob> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", job.getId());
        updateWrapper.eq("job_group", job.getJobGroup());
        updateWrapper.eq("job_name", job.getJobName());
        int rows = sysJobMapper.update(job, updateWrapper);
        if (rows > 0) {
            updateSchedulerJob(job);
        }
        return rows;
    }

    /**
     * 更新任务
     *
     * @param job 任务对象
     */
    public void updateSchedulerJob(SysJob job) throws SchedulerException, TaskException {
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(job.getId(), job.getJobGroup());
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, job);
    }

    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}
