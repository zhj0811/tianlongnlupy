package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cctegitc.ai.authority.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 标准问题库
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("knowledge_answer_library")
@ApiModel(value = "标准问题库")
public class KnowledgeLibrary implements Serializable {
    @ApiModelProperty(value = "主键，问题编号")
    @TableId(value = "question_id", type = IdType.AUTO)
    @Excel(name = "姓名")
    private Integer questionId;

    @ApiModelProperty(value = "问题")
    @TableField("question")
    @Excel(name = "问题")
    private String question;

    @Excel(name = "备注")
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "类别  公司简介，文化理念，应急预案")
    @TableField("question_type")
    @Excel(name = "问题类别")
    private String questionType;

    @ApiModelProperty(value = "答案")
    @TableField("answer")
    @Excel(name = "答案")
    private String answer;

    @ApiModelProperty(value = "生效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("effect_time")
    @Excel(name = "生效时间")
    private Date effectTime;

    @ApiModelProperty(value = "失效时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("efficacy_time")
    @Excel(name = "失效时间")
    private Date efficacyTime;

    @TableField(exist = false)
    List<RelevanceQuestion> relevanceQuestion;

    @TableField(exist = false)
    @Excel(name = "关联问题")
    String relevanceQuestionFlag;
}
