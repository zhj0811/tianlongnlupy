package com.cctegitc.ai.function.db.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.ClassesSafeInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: zhen
 * @Date: 2022/3/11 11:04
 * @Description:
 */
@DS("db4")
public interface ClassesSafeInfoMapper extends BaseMapper<ClassesSafeInfo> {


    @Select("SELECT `csi`.id,`csi`.classes_id," +
            "`as`.assess_standard,`csi`.problem,`csi`.assess_result\n" +
            "FROM " +
            "`classes_safe_info` AS `csi` " +
            "LEFT JOIN " +
            "`assess_standard` AS `as` " +
            "ON " +
            "`csi`.assess_standard_id = `as`.id " +
            "WHERE  " +
            "csi.classes_id = #{classesId}")
    List<ClassesSafeInfo> allStandardById(Long classesId);
}
