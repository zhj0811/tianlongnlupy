package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.BusinessCase;

import java.util.List;

public interface BusinessCaseMapper extends BaseMapper<BusinessCase> {
    List<BusinessCase> selectList(QueryWrapper<BusinessCase> queryWrapper);
}
