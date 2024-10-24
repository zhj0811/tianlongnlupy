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
@TableName("voice_memo")
@ApiModel(value = "语音备忘录")
public class VoiceMemo {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "语音内容")
    @TableField("voice_content")
    private String voiceContent;

    @ApiModelProperty(value = "模式")
    @TableField("model")
    private String model;

    @ApiModelProperty(value = "定时日期")
    @TableField("date_alarm")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateAlarm;

    @ApiModelProperty(value = "语音定时时间点")
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JsonFormat(pattern = "HH:mm:ss")
    @TableField("voice_duration")
    private Date voiceDuration;


    @ApiModelProperty(value = "星期")
    @TableField("week")
    private String week;


    @ApiModelProperty(value = "启停")
    @TableField("on_off")
    private String onOff;

    @ApiModelProperty(value = "创建人")
    @TableField("create_pcode")
    private String createPcode;
}
