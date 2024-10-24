package com.cctegitc.ai.authority.common.core.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tbl_attachment")
public class Attachment {
    @TableId(type = IdType.ASSIGN_UUID)//自动增长
    private String id;
    private String aid;
    private String aname;
    private String businessNo;
    private String businessType;
}
