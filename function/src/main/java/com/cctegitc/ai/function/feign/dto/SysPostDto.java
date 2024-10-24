package com.cctegitc.ai.function.feign.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysPostDto
 * @Description:
 * @Authour:yrc
 * @Data:2022/1/26 9:50
 * @Version:1.0
 */
@Data
public class SysPostDto implements Serializable {

    private Long postId;

    private String postCode;

    private String postName;

    private String postSort;

}
