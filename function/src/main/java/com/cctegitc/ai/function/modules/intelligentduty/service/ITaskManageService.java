package com.cctegitc.ai.function.modules.intelligentduty.service;

import com.cctegitc.ai.function.common.exception.job.TaskException;
import com.cctegitc.ai.function.db.pojo.Notification;
import org.quartz.SchedulerException;

public interface ITaskManageService {

    void createTask(Object taskDetail);

    void updateTask(Object taskDetail) throws SchedulerException, TaskException;


    void deleteTask(Long Id) throws SchedulerException;
}
