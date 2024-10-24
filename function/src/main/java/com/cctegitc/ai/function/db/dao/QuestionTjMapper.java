package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.QuestionTj;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface QuestionTjMapper extends BaseMapper<QuestionTj> {


    @Select("select t.id,t.question from que_tj t  order by t.num DESC limit 10")
    List<QuestionTj> selectHot();

    @Select("select count(t.question) as count ,t.question from que_jl t where t.user_id=#{userId} GROUP BY t.question order by count desc limit 10")
    List<QuestionTj> selectHotIndex(Integer userId);

    @Select("select sum(t.num) from que_tj t")
    Integer selectAllCount();

    @Select("select sum(t.success) from que_tj t")
    Integer selectSuccess();

    @Select("select t.id,t.question from que_tj t  order by t.num-t.success DESC limit 10")
    List<QuestionTj> selectFail();

    @Select("select count(*) from que_tj t where t.label='db'")
    Integer selectDb();

    @Select("select count(*) from que_tj t where t.label='others'")
    Integer selectOther();

    @Select("select count(*) from que_tj t where t.label='kb'")
    Integer selectkb();

    @Select("select * from que_tj t where t.question  like concat('%', #{substring}, '%')")
    QuestionTj selectExist(String substring);

    @Update("update que_tj t set t.num=t.num+1 where t.question like concat('%', #{substring}, '%')")
    void updateNum(String substring);

    @Update("update que_tj t set t.num=t.num+1,t.success=t.success+1  where t.question like concat('%', #{substring}, '%')")
    void updateNumSuccess(String substring);
}
