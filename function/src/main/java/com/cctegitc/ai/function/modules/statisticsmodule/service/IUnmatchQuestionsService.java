package com.cctegitc.ai.function.modules.statisticsmodule.service;


import com.cctegitc.ai.function.db.pojo.UnmatchQuestions;
import com.cctegitc.ai.function.vo.pagefind.UnmatchQuestionsVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
public interface IUnmatchQuestionsService {

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    UnmatchQuestionsVo findByPage(UnmatchQuestionsVo tFactoryVo, Integer current, Integer size);

    /**
     * 添加
     *
     * @param unmatchQuestions
     * @return int
     */
    int add(UnmatchQuestions unmatchQuestions);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param unmatchQuestions
     * @return int
     */
    int updateData(UnmatchQuestions unmatchQuestions);

    /**
     * id查询数据
     *
     * @param id id
     * @return UnmatchQuestions
     */
    UnmatchQuestions findById(Long id);

    /**
     * 查询所有数据
     *
     * @return List<UnmatchQuestions>
     */
    List<UnmatchQuestions> findAll();
}
