package com.cctegitc.ai.function.feign.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author jiangyang
 * @date 2021-12-31 10:11:04
 */
@Data
public class ApproveDto {


    /**
     * 流程所关联的业务表中的主键id
     */
    @NotBlank
    @ApiModelProperty(value = "任务id", required = true)

    private String businessKey;
    /**
     * 审核结果（true:审核通过，false:审核驳回）
     */
    @NotNull
    @ApiModelProperty(value = "审批是否通过", required = true)
    private Boolean approveFlag;

    /**
     * 提交信息类别
     */
    @ApiModelProperty(value = "提交信息类别（暂时先不用）", required = false)
    private String commentType;
    /**
     * 提交信息(审核填写时的审核意见)
     */
    @ApiModelProperty(value = "审批意见", required = false)
    private String comment;

}
