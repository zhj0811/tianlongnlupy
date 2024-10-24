package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.RelevanceQuestion;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RelevanceQuestionMapper extends BaseMapper<RelevanceQuestion> {
    @Select("select max(relevance_id) from tb_relevance_question")
    Integer selectIdMax();
}
