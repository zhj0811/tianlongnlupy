package com.cctegitc.ai.authority.system.service;

import com.cctegitc.ai.authority.system.domain.vo.CollectionVo;

public interface CollectionService {
    CollectionVo findByPage(CollectionVo collectionListVo);
}
