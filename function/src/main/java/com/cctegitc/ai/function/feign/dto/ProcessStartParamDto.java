package com.cctegitc.ai.function.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 名称
 *
 * @FileName ProcessStartParamDto
 * @Author TianCheng
 * @Date 2021/8/9 11:01
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Data
@AllArgsConstructor
public class ProcessStartParamDto {
    private String processDefinitionKey;
    private String businessKey;

    private Map<String, Object> variables;

}
