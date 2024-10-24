package com.cctegitc.ai.function.util.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户搜索下拉选项
 *
 * @author jiangyang
 * @date 2022-02-22 10:17:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserItem implements Serializable {

    @ApiModelProperty("用户名（登录id）")
    private String loginName;

    @ApiModelProperty("用户显示名(用于下拉选择项显示)")
    private String displayName;

    @ApiModelProperty("用户姓名（真实名字）")
    private String userName;

}
