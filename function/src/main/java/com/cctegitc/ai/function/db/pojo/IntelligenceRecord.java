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

/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-06-08
 * @Time: 13:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("intelligence_record")
@ApiModel(value = "智能值班-电子台账")
public class IntelligenceRecord {

    @ApiModelProperty(value = "主键，台账编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "台账内容")
    @TableField("record_content")
    private String recordContent;

    @ApiModelProperty(value = "台账创建日期")
    @TableField("record_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordDate;

    @ApiModelProperty(value = "台账状态")
    @TableField("record_state")
    private String recordState;

    @ApiModelProperty(value = "是否提醒")
    @TableField("remind")
    private String remind;

    @ApiModelProperty(value = "提醒时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("remind_date")
    private String remindDate;

    @ApiModelProperty(value = "创建人code")
    @TableField("create_pcode")
    private String createPcode;

    @TableField(exist = false)
    private Integer limit;
    @TableField(exist = false)
    private Integer page;

}
