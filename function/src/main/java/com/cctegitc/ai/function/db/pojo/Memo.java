package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("memo")
@ApiModel(value = "语音备忘录")
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "voice_duration", nullable = false)
    private String voiceDuration;

    @Column(name = "voice_content", nullable = false)
    private String voiceContent;

    @Column(name = "model")
    private String model;

    @Column(name = "model_value")
    private String modelValue;

    @Column(name = "date_alarm")
    private Date dateAlarm;

    @Column(name = "on_off", nullable = false)
    private String onOff;

    @Column(name = "create_code", nullable = false)
    private String createCode;

    @Column(name = "create_code_phone")
    private String createCodePhone;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "create_time", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "status_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date statusTime;
}
