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

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("instruct_configg")
@ApiModel(value = "指令配置管理")
public class InstructConfigg {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "指令名称")
    @TableField("instruct_name")
    private String instructName;

    @ApiModelProperty(value = "关键词")
    @TableField("main_word")
    private String mainWord;

    @ApiModelProperty(value = "启停")
    @TableField("on_off")
    private String onOff;

    @ApiModelProperty(value = "说明")
    @TableField("remark")
    private String remark;
}
