package com.cctegitc.ai.function.scheduleTask.task;


import com.cctegitc.ai.function.db.pojo.Memo;
import com.cctegitc.ai.function.service.impl.VoiceMemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("notificationHandler")
public class NotificationJobHandler {

    @Autowired
    private VoiceMemoServiceImpl voiceMemoService;

    /**
     * 语音通知任务
     *
     */
    public void executeNotificationJob(String voiceMemoId) {
        Memo voiceMemo = voiceMemoService.findById(Long.valueOf(voiceMemoId));
        String voiceContent = voiceMemo.getVoiceContent();
        System.out.println("语音通知任务执行，语音内容：" + voiceContent);
        // 调用外呼接口进行语音电话提醒
    }
}
