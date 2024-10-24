package com.cctegitc.ai.function.feign;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangyang
 * @date 2021-12-28 15:01:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestResult<T> {

    private static final String SUCCESS_MEG = "success";

    private Integer code;

    private String message;

    private T data;

    public static <T> RestResult success(T t) {
        return RestResult.builder().code(HttpStatus.HTTP_OK).data(t).message(SUCCESS_MEG).build();
    }

    public static RestResult success() {
        return RestResult.builder().code(HttpStatus.HTTP_OK).message(SUCCESS_MEG).build();
    }

    public static RestResult error(Integer code, String message) {
        return RestResult.builder().code(code).message(message).build();
    }

    public static RestResult error(String message) {
        return RestResult.builder().message(message).code(HttpStatus.HTTP_INTERNAL_ERROR).build();
    }

}
