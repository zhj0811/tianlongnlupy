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

/**
 * <p>
 * 词库实体
 * </p>
 *
 * @author ShiGuangWei
 * @since 2022-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("intelligence_words")
@ApiModel(value = "词库对象")
public class WordsBase {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "标准词")
    @TableField("standard_words")
    private String standardWords;

    @ApiModelProperty(value = "同义词")
    @TableField("synonym_words")
    private String synonymWords;

    @ApiModelProperty(value = "词语类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "说明")
    @TableField("remark")
    private String remark;
}
