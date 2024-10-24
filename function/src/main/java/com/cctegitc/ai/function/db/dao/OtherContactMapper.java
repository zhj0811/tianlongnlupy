package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.OtherContact;
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
public interface OtherContactMapper extends BaseMapper<OtherContact> {

    @Select("select name,unit,tel,phone from other_contact ")
    List<OtherContact> getPhone();

    @Select("select DISTINCT unit from other_contact ")
    List<OtherContact> getDept();
}
