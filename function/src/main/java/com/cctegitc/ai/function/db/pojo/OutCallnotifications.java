package com.cctegitc.ai.function.db.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("outcall_notifications")
@ApiModel(value = "外呼通知")
public class OutCallnotifications {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "外呼内容")
    @TableField("call_content")
    private String callContent;

    @ApiModelProperty(value = "外呼时间")
    @TableField("call_time")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private Date callTime;

    @ApiModelProperty(value = "模式")
    @TableField("model")
    private String model;

    @ApiModelProperty(value = "启停")
    @TableField("on_off")
    private String onOff;

    @ApiModelProperty(value = "外呼日期")
    @TableField("call_date")
    private String callDate;

    @ApiModelProperty(value = "外呼电话")
    @TableField("phone")
    private String phone;

    @TableField(exist = false)
    private Integer limit;
    @TableField(exist = false)
    private Integer page;
}
