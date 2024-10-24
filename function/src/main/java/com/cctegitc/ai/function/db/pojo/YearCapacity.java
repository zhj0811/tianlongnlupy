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

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 智能分析产量预测年产量
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("year_capacity")
@ApiModel(value = "YearCapacity对象", description = "智能分析产量预测年产量")
public class YearCapacity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "采煤队")
    @TableField("team")
    private String team;

    @ApiModelProperty(value = "当年产量")
    @TableField("current_capacity")
    private String currentCapacity;

    @ApiModelProperty(value = "日期")
    @DateTimeFormat(pattern = "yyyy")
    @JsonFormat(pattern = "yyyy")
    @TableField("year_time")
    private Date yearTime;

    @ApiModelProperty(value = "预计明年产量")
    @TableField("expect_capacity")
    private String expectCapacity;


}
