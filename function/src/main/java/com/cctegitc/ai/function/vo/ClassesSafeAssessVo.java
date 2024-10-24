package com.cctegitc.ai.function.vo;

import com.cctegitc.ai.function.db.pojo.ClassesSafeAssess;
import lombok.Data;

import java.util.List;

/**
 * @Author: zhen
 * @Date: 2022/3/10 14:54
 * @Description:
 */
@Data
public class ClassesSafeAssessVo {

    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;
    private List<ClassesSafeAssess> items;
}
