package com.cctegitc.ai.function.feign.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 名称
 *
 * @FileName ProcessDefinition
 * @Author TianCheng
 * @Date 2021/7/29 8:58
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessDefinitionDto {
    /**
     * 流程部署id
     */
    @ApiModelProperty(value = "流程部署id")
    private String id;
    /**
     * 流程定义名称
     */
    @ApiModelProperty(value = "流程名称")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty(value = "流程描述")
    private String des;
    /**
     * 版本
     */
    @ApiModelProperty(value = "流程部署版本")
    private Integer version;
    /**
     * 流程定义key
     */
    @ApiModelProperty(value = "流程定义Key")
    private String key;
    /**
     * 流程定义id
     */
    private String processDefinitionId;
}
