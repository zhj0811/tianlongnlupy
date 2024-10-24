package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cctegitc.ai.function.db.dao.QuestionJlMapper;
import com.cctegitc.ai.function.db.dao.QuestionTjMapper;
import com.cctegitc.ai.function.db.pojo.QuestionTj;
import com.cctegitc.ai.function.modules.statisticsmodule.service.QuestionTjService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-06-05
 * @Time: 15:25
 */
@Slf4j
@Service
public class QuestionTjServiceImpl implements QuestionTjService {

    @Autowired
    private QuestionTjMapper questionTjMapper;

    @Autowired
    private QuestionJlMapper questionJlMapper;

    @Override
    public List<QuestionTj> selectHot() {
        return questionTjMapper.selectHot();
    }

    @Override
    public List<QuestionTj> selectHotIndex(Integer userId) {
        List<QuestionTj> list = questionTjMapper.selectHotIndex(userId);
        List<QuestionTj> hotList = questionTjMapper.selectHot();
        List<QuestionTj> objects = new ArrayList<>();
        if (list.size() > 5) {
            for (int i = 0; i < list.size(); i++) {
                if (objects.size() <= 5) {
                    objects.add(list.get(i));
                } else if (objects.size() > 5 && objects.size() <= 10) {
                    for (int i1 = 0; i1 < hotList.size(); i1++) {
                        if (objects.size() >= 10) {
                            return objects;
                        } else {
                            objects.add(hotList.get(i1));
                        }
                    }
                }
            }
        }
        int flag = 10 - list.size();
        if (list.size() <= 5) {
            for (int i = 0; i < flag; i++) {
                list.add(hotList.get(i));
            }
            return list;
        }
        return null;
    }

    @Override
    public Map<String, Object> selectCountTj() {
        Map<String, Object> map = new HashMap<>();
        try {
            //提问数
            map.put("askCount", questionTjMapper.selectAllCount());
            //成功数
            map.put("successCount", questionTjMapper.selectSuccess());
            //热点问题
            map.put("hot", questionTjMapper.selectHot());
            //未匹配问题
            map.put("fail", questionTjMapper.selectFail());
            //实时数据应答
            map.put("dbCount", questionTjMapper.selectDb());
            //知识应答
            map.put("kbCount", questionTjMapper.selectkb());
            //其他
            map.put("otherCount", questionTjMapper.selectOther());
            //点赞数据
            map.put("up", questionJlMapper.selectUp());
            //踩数据
            map.put("down", questionJlMapper.selectDown());
            //点赞数
            map.put("upSize", questionJlMapper.selectUpSize());
            //踩数
            map.put("downSize", questionJlMapper.selectDownSize());
        } catch (Exception e) {
            return null;
        }
        return map;
    }

    @Override
    public Integer selectExist(String substring) {
        QueryWrapper<QuestionTj> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("question", substring);
        return questionTjMapper.selectCount(queryWrapper);
    }

    @Override
    public void updateNum(String substring, int success) {
        try {
            if (success > 0) {
                questionTjMapper.updateNumSuccess(substring);
            } else {
                questionTjMapper.updateNum(substring);
            }
        } catch (Exception e) {
            log.info(e.getMessage() + "统计表修改失败");
        }
    }

    @Override
    public void insert(QuestionTj questionTj) {
        try {
            questionTjMapper.insert(questionTj);
        } catch (Exception e) {
            log.info(e.getMessage() + "统计表插入失败");
        }

    }
}
