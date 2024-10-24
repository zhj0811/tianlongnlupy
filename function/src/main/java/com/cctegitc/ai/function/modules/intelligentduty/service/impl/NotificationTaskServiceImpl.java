package com.cctegitc.ai.function.modules.intelligentduty.service.impl;


import com.cctegitc.ai.function.common.exception.job.TaskException;
import com.cctegitc.ai.function.db.pojo.Notification;
import com.cctegitc.ai.function.db.pojo.RecordTaskVo;
import com.cctegitc.ai.function.modules.intelligentduty.service.ITaskManageService;
import com.cctegitc.ai.function.modules.intelligentduty.utils.JobManageUtils;
import com.cctegitc.ai.function.scheduleTask.service.impl.SysJobServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationTaskServiceImpl implements ITaskManageService {
    /**
     * JobGroup:备忘录
     */
    public static final String NOTIFICATION = "notification";
    @Autowired
    private SysJobServiceImpl sysJobService;

    @Autowired
    private JobManageUtils jobManageUtils;

    /**
     * 实现语音备忘录创建任务的业务逻辑
     *
     */
    @Override
    public void createTask(Object taskDetail) {
        if (taskDetail instanceof Notification) {
            RecordTaskVo recordTaskVo = buildRecordTaskVo((Notification) taskDetail);
            String invokeTarget = "notificationHandler.executeNotificationJob(" + "'" + recordTaskVo.getId() + "'" + ")";
            jobManageUtils.createTask(recordTaskVo, NOTIFICATION, invokeTarget);
        }
    }

    @Override
    public void updateTask(Object taskDetail) {
        if (taskDetail instanceof Notification) {
            RecordTaskVo recordTaskVo = buildRecordTaskVo((Notification) taskDetail);
            jobManageUtils.updateTask(recordTaskVo, NOTIFICATION);
        }
    }


    private static RecordTaskVo buildRecordTaskVo(Notification notification) {
        RecordTaskVo recordTaskVo = new RecordTaskVo();
        recordTaskVo.setCreateCode(notification.getCreateCode());
        recordTaskVo.setModel(notification.getModel());
        recordTaskVo.setModelValue(notification.getModelValue());
        recordTaskVo.setDateAlarm(notification.getDateAlarm());
        recordTaskVo.setDuration(notification.getOutcallDuration());
        recordTaskVo.setOnOff(notification.getOnOff());
        recordTaskVo.setId(String.valueOf(notification.getId()));
        return recordTaskVo;
    }

    @Override
    public void deleteTask(Long notificationId) throws SchedulerException {
        String jobName = NOTIFICATION + "_" + notificationId;
        sysJobService.deleteJobByJobName(jobName, NOTIFICATION);
    }
}
