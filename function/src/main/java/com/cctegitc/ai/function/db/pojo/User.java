package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * 用户
 */
@Data
@TableName(value = "sys_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;
    @TableField(fill = FieldFill.INSERT)
    private String uuid;
    private Long did;
    private String name;
    private String department;
    private String role;
    @TableField(value = "user_name")
    private String username;
    private String password;
    private String tel;
    private String status;
    private String deptId;
    private String deptName;

}
