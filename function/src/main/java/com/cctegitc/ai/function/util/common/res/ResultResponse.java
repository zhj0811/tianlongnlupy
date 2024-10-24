package com.cctegitc.ai.function.util.common.res;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class ResultResponse {
    private int code;
    private String message;
    private Object data;
}
