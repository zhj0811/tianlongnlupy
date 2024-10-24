package com.cctegitc.ai.function.feign.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 分页查询条件参数
 *
 * @author jiangyang
 * @date 2022-01-14 15:06:31
 */
@Data
public class PageAbleParam<T> implements Serializable {

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页数量
     */
    private Integer pageSize = 10;

    /**
     * 查询条件
     */
    private T condition;
}
