package com.cctegitc.ai.function.db.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.authority.common.core.domain.entity.SysDept;
import com.cctegitc.ai.function.db.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@DS(value = "db2")
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_user where user_name=#{name}")
    User selectByName(String name);

    @Select("select distinct m.perms from sys_user u left join sys_user_role ur on u.user_id=ur.user_id  left join sys_role_menu rm on rm.role_id=ur.role_id left join sys_menu m on rm.menu_id=m.menu_id where u.username=#{name} and m.perms!=''")
    List<String> selectPermsByName(String name);

    @Select("select u.role from sys_user u where  u.username=#{name}")
    List<String> selectRolesByName(String name);

    @Select("select * from sys_user where name=#{name}")
    User selectByRealName(String name);

    @Select("SELECT distinct name  FROM sys_user where role=#{name}")
    List<String> selectUsersByRole(String role);

    @Select("select sr.role_id, sr.data_scope from coalding.sys_user_role sur " +
            "LEFT JOIN coalding.sys_user su on sur.user_id = su.user_id " +
            "LEFT JOIN coalding.sys_role sr on sur.role_id = sr.role_id " +
            "where su.user_name = #{userName}")
    List<SysDept> getDataScope(String userName);

    @Select("SELECT sd.dept_id FROM coalding.sys_dept sd LEFT JOIN coalding.sys_role_dept srd " +
            "ON sd.dept_id = srd.dept_id WHERE srd.role_id in (#{roleId})")
    List<String> getDeptId(String roleId);

    @Select("SELECT sd.dept_id FROM coalding.sys_dept sd where sd.parent_id in (" +
            " select sd.dept_id from coalding.sys_user su LEFT JOIN coalding.sys_dept sd on su.dept_id = sd.parent_id" +
            " where su.user_name = #{userName})" +
            " UNION all" +
            " select sd.dept_id from coalding.sys_user su LEFT JOIN coalding.sys_dept sd on su.dept_id = sd.parent_id" +
            " where su.user_name = #{userName}" +
            " UNION all" +
            " SELECT sd.dept_id FROM coalding.sys_user su LEFT JOIN coalding.sys_dept sd on su.dept_id = sd.dept_id " +
            " WHERE su.user_name = #{userName}")
    List<String> getDeptList4(String userName);

    @Select("SELECT su.dept_id FROM coalding.sys_user su" +
            " where su.user_name = #{userName}")
    List<String> getDeptList(String userName);

    @Select("SELECT sd.dept_id,sd.dept_name FROM coalding.sys_dept sd LEFT JOIN " +
            "coalding.sys_user su ON sd.dept_id = su.dept_id WHERE su.user_name = #{userName}")
    User getDeptInfo(String userName);

}
