package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.scheduleTask.domain.SysJobLog;
import org.mapstruct.Mapper;

/**
 * 调度任务日志信息 数据层
 */

@Mapper
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {
}
