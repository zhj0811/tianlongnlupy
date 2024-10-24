package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 关联问题库
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_relevance_question")
@ApiModel(value = "关联问题库")
public class RelevanceQuestion implements Serializable {

    @ApiModelProperty(value = "主键")
    @TableId(value = "relevance_id", type = IdType.AUTO)
    private Integer relevanceId;

    @ApiModelProperty(value = "关联问题")
    @TableField("relevance_question")
    private String relevanceQuestion;

    @ApiModelProperty(value = "标准问题编号")
    @TableField("question_id")
    private Integer questionId;
}
