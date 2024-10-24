package com.cctegitc.ai.function.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author: ShiGuangWei
 * @Date: 2022-09-28 16:31
 * @Description: 智能调度机器人聊天返回对象
 */
@Data
@Component
public class DispatchChatVo {

    private String textAnswer;

    private String audioPath;

    private String userId;

    private Integer jlId;

}
