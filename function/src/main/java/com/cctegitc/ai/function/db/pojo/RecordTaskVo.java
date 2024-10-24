package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class RecordTaskVo {
    @ApiModelProperty(value = "id")
    private String id;


    @ApiModelProperty(value = "时间")
    private String duration;


    @ApiModelProperty(value = "模式:模式:一次,每天,按星期,按月")
    private String model;


    @ApiModelProperty(value = "模式值:星期几;每月几号")
    private String modelValue;

    @ApiModelProperty(value = "日期:年月日,模式为一次时必填")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateAlarm;


    @ApiModelProperty(value = "启停:1是开启;0是关闭")
    @TableField("on_off")
    private String onOff;

    @ApiModelProperty(value = "创建人")
    private String createCode;
}
