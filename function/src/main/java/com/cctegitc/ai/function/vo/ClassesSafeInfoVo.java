package com.cctegitc.ai.function.vo;

import com.cctegitc.ai.function.db.pojo.ClassesSafeInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author: zhen
 * @Date: 2022/3/11 15:04
 * @Description:
 */
@Data
public class ClassesSafeInfoVo {

    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private Long status;
    private Long classesId;
    private List<ClassesSafeInfo> items;
}
