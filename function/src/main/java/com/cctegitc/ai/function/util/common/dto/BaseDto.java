package com.cctegitc.ai.function.util.common.dto;

import lombok.Data;

/**
 * 名称
 *
 * @FileName BaseDto
 * @Author TianCheng
 * @Date 2021/7/9 11:17
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Data
public class BaseDto {
    private Integer limit = 10;
    private Integer page = 1;
    private String sort;
}
