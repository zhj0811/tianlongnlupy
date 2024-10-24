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
 * 定时任务调度表 sys_job
 * 
 * 
 */
@Data
@Entity
@Table(name = "sys_job")
@ApiModel(value = "定时任务调度表")
public class SysJob implements Serializable {

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

    @Column(name = "cron_expression", length = 255)
    private String cronExpression;

    @Column(name = "misfire_policy", length = 20)
    private String misfirePolicy;

    @Column(name = "concurrent", length = 1)
    private String concurrent;

    @Column(name = "status", length = 1)
    private String status;

    @Column(name = "create_by", length = 64)
    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_by", length = 64)
    private String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "remark", length = 500)
    private String remark;
}
