package com.cctegitc.ai.function.modules.statisticsmodule.service;


import com.cctegitc.ai.function.db.pojo.QuestionJl;

/**
 * <p>
 * 智能调度统计 服务类
 * </p>
 *
 * @author
 * @since 2022-03-10
 */
public interface QuestionJlService {

    Integer insertOne(QuestionJl questionJl);

    void update(QuestionJl questionJl);
}
