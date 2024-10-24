package com.cctegitc.ai.function.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 名称
 *
 * @FileName ProcessApproveDto
 * @Author TianCheng
 * @Date 2021/8/9 16:26
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Data
@AllArgsConstructor
public class ProcessApproveDto {
    private String taskId;
    private Map<String, Object> variables;
    private Map<String, String> comments;
}
