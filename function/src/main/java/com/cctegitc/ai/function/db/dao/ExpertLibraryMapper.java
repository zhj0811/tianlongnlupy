package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.ExpertLibrary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
public interface ExpertLibraryMapper extends BaseMapper<ExpertLibrary> {
    @Select("select DISTINCT unit from expert_library ")
    List<ExpertLibrary> getDept();

    @Select(" select t.name,t1.name as unit,t.tel,t.phone,null as duties,null as title,null as emergency_duties,null as expert_type,null as adept_domain from employee_library t,organization t1 where t.dept=t1.id " +
            " union all " +
            " select t2.`name`,t2.unit,t2.tel,t2.phone,t2.duties,t2.title,t2.expert_type,t2.emergency_duties,t2.adept_domain from expert_library t2 " +
            " union all " +
            " select t3.`name`,t3.unit,t3.tel,t3.phone,null as duties,null as title,null as emergency_duties,null as expert_type,null as adept_domain from other_contact t3 ")
    List<ExpertLibrary> find();
}
