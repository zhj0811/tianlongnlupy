package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("employee_library")
@ApiModel(value = "EmployeeLibrary对象", description = "员工库")
public class EmployeeLibrary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "员工语音系统登录名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "员工姓名")
    @TableField("display_name")
    private String displayName;

    @ApiModelProperty(value = "员工单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "员工部门")
    @TableField("dept")
    private String dept;

    @ApiModelProperty(value = "员工职位")
    @TableField("job")
    private String job;

    @ApiModelProperty(value = "员工职能")
    @TableField("function")
    private String function;

    @ApiModelProperty(value = "办公电话")
    @TableField("tel")
    private String tel;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "办公地点")
    @TableField("office_location")
    private String officeLocation;

    @TableField(exist = false)
    private String keyword;

    @TableField(exist = false)
    private Integer limit;

    @TableField(exist = false)
    private Integer page;
}
