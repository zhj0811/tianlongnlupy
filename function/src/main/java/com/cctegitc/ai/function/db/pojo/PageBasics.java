package com.cctegitc.ai.function.db.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页")
public class PageBasics {

    @ApiModelProperty(value = "查询参数")
    String question;

    @ApiModelProperty(value = "当前页")
    private Integer page;

    @ApiModelProperty(value = "页条目数")
    private Integer limit;
}
