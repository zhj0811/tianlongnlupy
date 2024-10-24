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
 * 智能调度记录
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("que_jl")
@ApiModel(value = "智能调度记录")
public class QuestionJl implements Serializable {

    @ApiModelProperty(value = "主键，记录编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "问题")
    @TableField("question")
    private String question;

    @ApiModelProperty(value = "类型")
    @TableField("label")
    private String label;

    /*
     * 0失败1成功
     * */
    @ApiModelProperty(value = "是否成功")
    @TableField("success")
    private Integer success;

    @ApiModelProperty(value = "查询时间")
    @TableField("que_date")
    private String queDate;

    @ApiModelProperty(value = "查询用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String username;

    @ApiModelProperty(value = "up")
    @TableField("up")
    private Integer up;

    @ApiModelProperty(value = "down")
    @TableField("down")
    private Integer down;


    @ApiModelProperty(value = "desc")
    @TableField("desc")
    private Integer desc;
}
