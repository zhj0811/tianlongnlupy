package com.cctegitc.ai.function.feign.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jiangyang
 * @date 2021-12-30 15:54:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApproverOptionsDto {

    @ApiModelProperty(value = "岗位id")
    private Long postId;

    @ApiModelProperty(value = "岗位名称")
    private String postName;

    @ApiModelProperty("岗位对应审批人列表")
    private List<UserDto> userList;

    @ApiModelProperty("流程图中审批人对应的key")
    private String approverKey;

}
