package com.cctegitc.ai.function.modules.intelligentduty.service.impl;

import com.cctegitc.ai.function.db.pojo.Memo;
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
public class VoiceMemoTaskServiceImpl implements ITaskManageService {
    /**
     * JobGroup:备忘录
     */
    public static final String VOICE_MEMO = "voiceMemo";
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
        if (taskDetail instanceof Memo) {
            RecordTaskVo recordTaskVo = buildRecordTaskVo((Memo) taskDetail);
            String invokeTarget = "voiceMemoJobHandler.executeVoiceMemoJob(" + "'" + recordTaskVo.getId() + "'" + ")";
            jobManageUtils.createTask(recordTaskVo, VOICE_MEMO, invokeTarget);
        }
    }

    @Override
    public void updateTask(Object taskDetail) {
        if (taskDetail instanceof Memo) {
            RecordTaskVo recordTaskVo = buildRecordTaskVo((Memo) taskDetail);
            jobManageUtils.updateTask(recordTaskVo, VOICE_MEMO);
        }
    }

    private RecordTaskVo buildRecordTaskVo(Memo voiceMemo) {
        RecordTaskVo recordTaskVo = new RecordTaskVo();
        recordTaskVo.setCreateCode(voiceMemo.getCreateCode());
        recordTaskVo.setModel(voiceMemo.getModel());
        recordTaskVo.setModelValue(voiceMemo.getModelValue());
        recordTaskVo.setDateAlarm(voiceMemo.getDateAlarm());
        recordTaskVo.setDuration(voiceMemo.getVoiceDuration());
        recordTaskVo.setOnOff(voiceMemo.getOnOff());
        recordTaskVo.setId(String.valueOf(voiceMemo.getId()));
        return recordTaskVo;
    }

    @Override
    public void deleteTask(Long voiceMemoId) throws SchedulerException {
        String jobName = VOICE_MEMO + "_" + voiceMemoId;
        sysJobService.deleteJobByJobName(jobName, VOICE_MEMO);
    }
}
