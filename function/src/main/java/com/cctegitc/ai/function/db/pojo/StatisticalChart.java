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
 * <p>
 *
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("statistical_chart")
@ApiModel(value = "StatisticalChart对象", description = "统计分析")
public class StatisticalChart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "提问数量")
    @TableField("question_num")
    private String questionNum;

    @ApiModelProperty(value = "回答正确数")
    @TableField("answer_correctly_num")
    private String answerCorrectlyNum;

    @ApiModelProperty(value = "回答正确率")
    @TableField("answer_accuracy")
    private String answerAccuracy;


}
