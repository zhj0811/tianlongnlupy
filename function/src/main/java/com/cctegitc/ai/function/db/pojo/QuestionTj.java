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
 * 智能调度统计
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("que_tj")
@ApiModel(value = "智能调度统计")
public class QuestionTj implements Serializable {

    @ApiModelProperty(value = "主键，问题编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "问题")
    @TableField("question")
    private String question;

    @ApiModelProperty(value = "类型")
    @TableField("label")
    private String label;
    /*
     * 0失败1成功
     * */
    @ApiModelProperty(value = "是否成功")
    @TableField("success")
    private Integer success;

    @ApiModelProperty(value = "查询次数统计")
    @TableField("num")
    private Integer num;

    @ApiModelProperty(value = "点赞")
    @TableField("up")
    private Integer up;

    @ApiModelProperty(value = "踩")
    @TableField("down")
    private Integer down;

    @ApiModelProperty(value = "评论描述")
    @TableField("desc")
    private String desc;


}
