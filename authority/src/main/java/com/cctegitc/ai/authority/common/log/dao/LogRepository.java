package com.cctegitc.ai.authority.common.log.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.authority.common.log.entity.OperationalLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yankee
 * @since 2017-09-05
 */
@Mapper
public interface LogRepository extends BaseMapper<OperationalLog> {

    /**
     * 查询分页
     * @param request
     * @param page
     * @return
     */
    //List<Log> page(@Param("re") LogRequest request, Pagination page);
}
