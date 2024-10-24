package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.scheduleTask.domain.SysJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 调度任务信息 数据层
 */

@Mapper
public interface SysJobMapper extends  BaseMapper<SysJob> {
    @Select("select * from sys_job")
    List<SysJob> selectList(QueryWrapper<SysJob> queryWrapper);

    @Select("select * from sys_job where job_name = #{jobName} and job_group = #{jobGroup}")
    List<SysJob> selectByName(String jobName, String jobGroup);
}
