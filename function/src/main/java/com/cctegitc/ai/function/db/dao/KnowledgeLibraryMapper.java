package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.KnowledgeLibrary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface KnowledgeLibraryMapper extends BaseMapper<KnowledgeLibrary> {

    @Select("select max(question_id) from knowledge_answer_library")
    Integer selectIdMax();

    @Select(" SELECT  t.question as question,t.question_type as type,t.answer as answer  FROM knowledge_answer_library t " +
            " union all " +
            " select t1.relevance_question as question,t2.question_type as type,t2.answer as answer from tb_relevance_question t1,knowledge_answer_library t2 where t1.question_id=t2.question_id ")
    List<KnowledgeLibrary> findAll();
}
