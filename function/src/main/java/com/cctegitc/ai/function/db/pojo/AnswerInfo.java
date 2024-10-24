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
 * 答案库
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_answer")
@ApiModel(value = "答案库")
public class AnswerInfo implements Serializable {

    @ApiModelProperty(value = "主键，答案编号")
    @TableId(value = "answer_id", type = IdType.AUTO)
    private Integer answerId;

    @ApiModelProperty(value = "答案")
    @TableField("answer")
    private String answer;
}
