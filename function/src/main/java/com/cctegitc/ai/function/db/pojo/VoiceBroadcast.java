package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("broadcast")
@ApiModel(value = "语音播报")
public class VoiceBroadcast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty(value = "播报时间")
    @TableField("report_time")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    private Date reportTime;

    @ApiModelProperty(value = "轮次")
    @TableField("turn_count")
    private String turnCount;

    @ApiModelProperty(value = "间隔时间")
    @TableField("interval_time")
    private String intervalTime;

    @ApiModelProperty(value = "播报内容")
    @TableField("report_content")
    private String reportContent;

    @ApiModelProperty(value = "录音文件地址")
    @TableField("recording_filepath")
    private String recordingHome;

    @Column(name = "model")
    @ApiModelProperty(value = "模式")
    private String model;

    @Column(name = "model_value")
    @ApiModelProperty(value = "模式值")
    private String modelValue;

    @Column(name = "date_alarm")
    @ApiModelProperty(value = "年月日")
    private Date dateAlarm;

    @Column(name = "on_off", nullable = false)
    @ApiModelProperty(value = "开关")
    private String onOff;

    @Column(name = "create_code", nullable = false)
    @ApiModelProperty(value = "创建人")
    private String createCode;


    @Column(name = "status", nullable = false)
    @ApiModelProperty(value = "状态")
    private String status;

    @Column(name = "create_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Column(name = "status_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "状态更新时间")
    private Date statusTime;
}
