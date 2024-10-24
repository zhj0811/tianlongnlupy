package com.cctegitc.ai.function.db.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: zhen
 * @Date: 2022/3/11 11:00
 * @Description:
 */
@Data
@TableName("classes_safe_info")
public class ClassesSafeInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // classes_safe_assess_id
    private Long classesId;

    // 评估项目标准id
    private Long assessStandardId;

    // 评估项目标准
    @TableField(exist = false)
    private String assessStandard;

    // 存在问题
    private String problem;

    // 评估结果
    private String assessResult;
}
