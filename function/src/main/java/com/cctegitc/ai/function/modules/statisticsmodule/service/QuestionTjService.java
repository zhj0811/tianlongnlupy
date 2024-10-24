package com.cctegitc.ai.function.modules.statisticsmodule.service;


import com.cctegitc.ai.function.db.pojo.QuestionTj;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 智能调度统计 服务类
 * </p>
 *
 * @author
 * @since 2022-03-10
 */
public interface QuestionTjService {

    List<QuestionTj> selectHot();

    List<QuestionTj> selectHotIndex(Integer userId);

    Map<String, Object> selectCountTj();

    Integer selectExist(String substring);

    void updateNum(String substring, int success);

    void insert(QuestionTj questionTj);
}
