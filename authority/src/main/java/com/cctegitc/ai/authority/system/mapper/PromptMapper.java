package com.cctegitc.ai.authority.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.authority.common.core.domain.entity.Prompt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromptMapper extends BaseMapper<Prompt> {
    /**
     * 查询提示信息
     *
     * @param path 路由地址
     * @return
     */
    Prompt getPrompt(String path);
}
