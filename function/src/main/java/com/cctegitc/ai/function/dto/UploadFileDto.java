package com.cctegitc.ai.function.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 名称
 *
 * @FileName UploadFileDto
 * @Author TianCheng
 * @Date 2021/6/25 15:36
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Data
@AllArgsConstructor
public class UploadFileDto {
    private String name;
    private String type;
    private String size;
    private String path;
    private String newName;
}
