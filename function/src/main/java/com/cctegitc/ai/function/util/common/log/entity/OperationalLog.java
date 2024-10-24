package com.cctegitc.ai.function.util.common.log.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 名称
 *
 * @FileName OperationalLog
 * @Author TianCheng
 * @Date 2021/7/14 14:52
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@TableName("sys_operational_log")
public class OperationalLog {

    @TableId(type = IdType.AUTO)
    private String id;
    private String module;
    private String description;
    private String httpMethod;
    private String username;
    private String ip;
    private String status;
    private String routing;
    private String controllerMethod;
    private String params;
    private String returning;
    private String sysModule;

    public OperationalLog(String id, String module, String description, String httpMethod, String username, String ip, String status, String routing, String controllerMethod, String params, String returning, String sysModule) {
        this.id = id;
        this.module = module;
        this.description = description;
        this.httpMethod = httpMethod;
        this.username = username;
        this.ip = ip;
        this.status = status;
        this.routing = routing;
        this.controllerMethod = controllerMethod;
        this.params = params;
        this.returning = returning;
        this.sysModule = sysModule;
    }

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
