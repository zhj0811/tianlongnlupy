package com.cctegitc.ai.function.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-07-17
 * @Time: 15:08
 */
@Data
public class ResultVo {
    private String label;
    private String value;
    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ResultVo> children;

}
