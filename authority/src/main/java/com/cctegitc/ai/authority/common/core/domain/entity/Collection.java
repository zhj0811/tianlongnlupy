package com.cctegitc.ai.authority.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "collection")
public class Collection implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    //菜单ID
    @TableField(value = "mid")
    private Long mid;
    //人员id
    @TableField(value = "uid")
    private Long uid;
    //路由地址
    @TableField(exist = false)
    private String path;
    //菜单名称
    @TableField(exist = false)
    private String menuName;
}
