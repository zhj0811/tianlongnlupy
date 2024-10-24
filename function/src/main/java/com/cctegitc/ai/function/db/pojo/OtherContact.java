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
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("other_contact")
@ApiModel(value = "OtherContact对象", description = "其他联络人")
public class OtherContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "联络人姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "联络人单位")
    @TableField("unit")
    private String unit;

    @ApiModelProperty(value = "联络人职能")
    @TableField("function")
    private String function;

    @ApiModelProperty(value = "联络人方式")
    @TableField("tel")
    private String tel;

    @ApiModelProperty(value = "联络人手机号")
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
