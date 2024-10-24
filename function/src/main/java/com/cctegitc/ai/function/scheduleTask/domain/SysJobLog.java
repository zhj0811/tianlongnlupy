package com.cctegitc.ai.function.scheduleTask.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务调度日志表 sys_job_log
 * 
 * 
 */
@Data
@ApiModel(value = "定时任务调度日志表")
@Entity
@Table(name = "sys_job_log")
public class SysJobLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "job_name", nullable = false, length = 64)
    private String jobName;

    @Column(name = "job_group", nullable = false, length = 64)
    private String jobGroup;

    @Column(name = "invoke_target", nullable = false, length = 500)
    private String invokeTarget;

    @Column(name = "job_message", length = 500)
    private String jobMessage;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "exception_info", length = 2000)
    private String exceptionInfo;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
