package com.cctegitc.ai.authority.system.domain.vo;

import com.cctegitc.ai.authority.common.core.domain.entity.Collection;
import lombok.Data;

import java.util.List;

@Data
public class CollectionVo {
    private Integer page;
    private Integer limit;
    private String sort;
    private Long total;
    private List<Collection> items;
}
