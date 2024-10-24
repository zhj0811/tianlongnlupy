package com.cctegitc.ai.function.service.impl;

import com.cctegitc.ai.function.db.dao.QuestionJlMapper;
import com.cctegitc.ai.function.db.pojo.QuestionJl;
import com.cctegitc.ai.function.modules.statisticsmodule.service.QuestionJlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-06-05
 * @Time: 15:25
 */
@Service
@Slf4j
public class QuestionJlServiceImpl implements QuestionJlService {

    @Autowired
    private QuestionJlMapper questionJlMapper;


    @Override
    public Integer insertOne(QuestionJl questionJl) {
        questionJlMapper.insert(questionJl);
        return questionJl.getId();
    }

    @Override
    public void update(QuestionJl questionJl) {
        questionJlMapper.updateById(questionJl);
    }


}
