package com.cctegitc.ai.function.scheduleTask.task;

import com.cctegitc.ai.function.db.pojo.Memo;

import com.cctegitc.ai.function.service.impl.VoiceMemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("voiceMemoJobHandler")
public class VoiceMemoJobHandler {

    @Autowired
    private VoiceMemoServiceImpl voiceMemoService;

    /**
     * 语音备忘录任务
     *
     */
    public void executeVoiceMemoJob(String voiceMemoId) {
        Memo voiceMemo = voiceMemoService.findById(Long.valueOf(voiceMemoId));
        String voiceContent = voiceMemo.getVoiceContent();
        System.out.println("语音备忘录任务执行，语音内容：" + voiceContent);

        // 调用外呼接口进行语音电话提醒

        // 调用websocket在页面进行语音播报
    }
}
