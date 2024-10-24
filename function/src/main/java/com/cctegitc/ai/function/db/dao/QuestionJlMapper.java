package com.cctegitc.ai.function.db.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.QuestionJl;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionJlMapper extends BaseMapper<QuestionJl> {

    @Select(" select t1.question from(select t.question,count(t.up) as upSize from que_jl t group by t.question) t1 order by t1.upSize desc limit 10;")
    List<QuestionJl> selectUp();

    @Select(" select t1.question from(select t.question,count(t.down) as downSize from que_jl t group by t.question) t1 order by t1.downSize desc limit 10;")
    List<QuestionJl> selectDown();

    @Select("select count(t.up)  from que_jl t where t.up=1")
    Integer selectUpSize();

    @Select("select count(t.down)  from que_jl t where t.down=1")
    Integer selectDownSize();
}
