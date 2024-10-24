package com.cctegitc.ai.function.modules.intelligentduty.utils;

import cn.hutool.core.date.DateUtil;
import com.cctegitc.ai.authority.common.utils.StringUtils;
import com.cctegitc.ai.function.common.constant.ScheduleConstants;
import com.cctegitc.ai.function.common.exception.job.TaskException;
import com.cctegitc.ai.function.db.pojo.RecordTaskVo;
import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import com.cctegitc.ai.function.scheduleTask.service.impl.SysJobServiceImpl;
import com.cctegitc.ai.function.scheduleTask.util.CronGeneratorUtil;
import com.cctegitc.ai.function.util.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class JobManageUtils {
    /**
     * 禁止并发
     */
    public static final String NO_CONCURRENT = "1";

    // 一次
    public static final String MODEL_ONCE = "1";
    // 按天
    private static final String MODEL_DAY = "2";
    // 按星期
    public static final String MODEL_WEEK = "3";
    // 按月
    private static final String MODEL_MONTH = "4";



    @Autowired
    private SysJobServiceImpl sysJobService;


    public  void createTask(RecordTaskVo recordTaskVo, String jobType, String invokeTarget) {
        // 创建语音备忘录创建任务的逻辑
        SysJob sysJob = new SysJob();
        sysJob.setJobName(jobType + "_" + recordTaskVo.getId());
        sysJob.setJobGroup(jobType);
        sysJob.setCreateTime(DateUtils.getNowDate());
        sysJob.setCronExpression(buildCronExpression(recordTaskVo));
        sysJob.setStatus(recordTaskVo.getOnOff());
        sysJob.setCreateBy(recordTaskVo.getCreateCode());
        if (MODEL_DAY.equals(recordTaskVo.getModel())) {
            sysJob.setMisfirePolicy(ScheduleConstants.MISFIRE_IGNORE_MISFIRES);
        } else {
            sysJob.setMisfirePolicy(ScheduleConstants.MISFIRE_DEFAULT);
        }
        sysJob.setInvokeTarget(invokeTarget);
        sysJob.setConcurrent(NO_CONCURRENT);
        try {
            sysJobService.insertJob(sysJob);
        } catch (Exception e) {
            log.error("创建语音备忘录创建任务失败", e);
        }
    }

    public  void updateTask(RecordTaskVo recordTaskVo, String jobType) {
        String jobName = jobType + "_" + recordTaskVo.getId();
        List<SysJob> sysJobs = sysJobService.selectJobByJobName(jobName, jobType);
        for (SysJob sysJob : sysJobs) {
            sysJob.setCronExpression(buildCronExpression(recordTaskVo));
            sysJob.setStatus(recordTaskVo.getOnOff());
            sysJob.setUpdateBy(recordTaskVo.getCreateCode());
            sysJob.setUpdateTime(DateUtils.getNowDate());
            if (MODEL_DAY.equals(recordTaskVo.getModel())) {
                sysJob.setMisfirePolicy(ScheduleConstants.MISFIRE_IGNORE_MISFIRES);
            } else {
                sysJob.setMisfirePolicy(ScheduleConstants.MISFIRE_DEFAULT);
            }
            try {
                sysJobService.updateJob(sysJob);
            } catch (Exception e) {
                log.error("更新语音备忘录创建任务失败", e);
            }
        }
    }


    public  String buildCronExpression(RecordTaskVo recordTaskVo) {
        // 时分秒
        String duration = recordTaskVo.getDuration();
        System.out.println("duration:" + duration);


        // 模式:一次,每天,按星期,按月
        String model = recordTaskVo.getModel();

        // 每天执行一次，00:00 -23:59任意时刻
        if (model.equals(MODEL_DAY)) {
            System.out.println("每天执行一次，00:00 -23:59任意时刻");
            return CronGeneratorUtil.generateCronEveryDay(duration);
        }


        String modelValue = recordTaskVo.getModelValue();
        // 按星期执行:周一到周日不固定,可以指定一周多天执行,00:00 -23:59任意时刻
        if (model.equals(MODEL_WEEK)) {
            String weekDays = CronGeneratorUtil.mapWeekDays(modelValue);
            return CronGeneratorUtil.generateCronByWeek(weekDays, duration);
        }

        // 每个月执行一次，1号到31号任意一天,00:00 -23:59任意时刻
        if (model.equals(MODEL_MONTH)) {
            return CronGeneratorUtil.generateCronByMonth(modelValue, duration);
        }

        // 年月日
        Date dateAlarm = recordTaskVo.getDateAlarm();

        // 指定具体的年月日时分秒且只执行一次定时任务
        if (model.equals(MODEL_ONCE)) {
            String dateToStr = DateUtils.parseDateToStr("yyyy-MM-dd", dateAlarm);
            String dateTime =  dateToStr + " " + duration;
            return CronGeneratorUtil.generateCronByOnce(dateTime);
        }

        // 默认值: 2099年 12月31日0分0秒
        return "0 0 0 31 12 ? 2099";
    }
}
