package com.cctegitc.ai.function.scheduleTask.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cctegitc.ai.function.db.dao.SysJobLogMapper;
import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import com.cctegitc.ai.function.scheduleTask.domain.SysJobLog;
import com.cctegitc.ai.function.scheduleTask.service.ISysJobLogService;
import com.cctegitc.ai.function.util.FindByPageHelper;
import com.cctegitc.ai.function.util.common.utils.DateUtils;
import com.cctegitc.ai.function.vo.pagefind.SysJobLogPageVO;
import com.cctegitc.ai.function.vo.pagefind.SysJobPageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时任务调度日志信息 服务层
 * 
 * 
 */
@Service
public class SysJobLogServiceImpl implements ISysJobLogService
{
    @Resource
    private SysJobLogMapper jobLogMapper;

    /**
     * 获取quartz调度器日志的计划任务
     * 
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLog> selectJobLogList()
    {
        return jobLogMapper.selectList(new QueryWrapper<SysJobLog>());
    }

    /**
     * 通过调度任务日志ID查询调度信息
     * 
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId)
    {
        return  jobLogMapper.selectById(jobLogId);
    }

    /**
     * 新增任务日志
     * 
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLog jobLog) {
        jobLog.setCreateTime(DateUtils.getNowDate());
        jobLogMapper.insert(jobLog);
    }

    /**
     * 批量删除调度日志信息
     * 
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(Long[] logIds)
    {
        return jobLogMapper.deleteBatchIds(Arrays.asList(logIds));
    }

    /**
     * 删除任务日志
     * 
     * @param jobId 调度日志ID
     */
    @Override
    public int deleteJobLogById(Long jobId)
    {
        return jobLogMapper.deleteById(jobId);
    }

    @Override
    public void cleanJobLog() {
        List<Long> jobLogIds = jobLogMapper.selectList(null)
                .stream()
                .map(SysJobLog::getId)
                .collect(Collectors.toList());

        if (!jobLogIds.isEmpty()) {
            jobLogMapper.deleteBatchIds(jobLogIds);
        }
    }

    @Override
    public SysJobLogPageVO findByPage(Integer page, Integer limit) {
        return FindByPageHelper.findByPage(SysJobLogPageVO.class, new QueryWrapper<SysJobLog>(), page, limit, jobLogMapper);
    }
}
