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
@TableName("voice_report")
@ApiModel(value = "语音播报管理")
public class VoiceReport {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
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
    @TableField("recording_home")
    private String recordingHome;

    @ApiModelProperty(value = "启停")
    @TableField("on_off")
    private String onOff;

    @ApiModelProperty(value = "创建人")
    @TableField("create_pcode")
    private String createPcode;
}
