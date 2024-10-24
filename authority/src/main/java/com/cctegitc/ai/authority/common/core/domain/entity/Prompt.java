package com.cctegitc.ai.authority.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "prompt")
public class Prompt implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    //文件id
    @TableField(value = "fid")
    private String fid;
    //路由地址
    @TableField(value = "path")
    private String path;
    //提示信息
    @TableField(value = "prompt_information")
    private String promptInformation;
    //收藏id
    @TableField(value = "cid", updateStrategy = FieldStrategy.IGNORED)
    private Long cid;
    //菜单名称
    @TableField(exist = false)
    private String menuName;
    //菜单Id
    @TableField(exist = false)
    private Long mid;
}
