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
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 综采工作面班组安全评估
 * </p>
 *
 * @author maqh
 * @since 2022-03-10
 */
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@TableName("classes_safe_assess")
@ApiModel(value = "ClassesSafeAssess对象", description = "综采工作面班组安全评估")
public class ClassesSafeAssess implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "工作面名称")
    @TableField("coal_name")
    private String coalName;

    @ApiModelProperty(value = "日期")
    @TableField("date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @ApiModelProperty(value = "班次 早中晚班")
    @TableField("classes")
    private String classes;

    @ApiModelProperty(value = "评估人")
    @TableField("assess_person")
    private String assessPerson;

    @ApiModelProperty(value = "班组负责人")
    @TableField("responsible_person")
    private String responsiblePerson;


}
