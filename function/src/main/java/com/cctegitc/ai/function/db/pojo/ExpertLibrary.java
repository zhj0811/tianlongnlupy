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
@TableName("expert_library")
@ApiModel(value = "ExpertLibrary对象", description = "专家库")
public class ExpertLibrary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "部门")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "职务")
    @TableField("duties")
    private String duties;

    @ApiModelProperty(value = "职称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "应急救援职务")
    @TableField("emergency_duties")
    private String emergencyDuties;

    @ApiModelProperty(value = "人员类型")
    @TableField("people_type")
    private String peopleType;

    @ApiModelProperty(value = "专家类型")
    @TableField("expert_type")
    private String expertType;

    @ApiModelProperty(value = "擅长领域")
    @TableField("adept_domain")
    private String adeptDomain;

    @ApiModelProperty(value = "组别")
    @TableField("groups")
    private String groups;

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
    private Integer limit;
    @TableField(exist = false)
    private Integer page;

}
