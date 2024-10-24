package com.cctegitc.ai.authority.common.log.dto;

import com.cctegitc.ai.authority.common.dto.BaseDto;
import lombok.Data;

/**
 * 名称
 *
 * @FileName OperationalLogDto
 * @Author TianCheng
 * @Date 2021/7/14 16:55
 * @Modifier
 * @ModifiedDate
 * @Description 功能描述
 * @See
 **/
@Data
public class OperationalLogDto extends BaseDto {
    private String module;
    private String sysModule;
}
