package com.cctegitc.ai.function.modules.robotchat.service;


import com.cctegitc.ai.function.db.pojo.HotQuestions;
import com.cctegitc.ai.function.vo.pagefind.HotQuestionsVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
public interface IHotQuestionsService {

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    HotQuestionsVo findByPage(HotQuestionsVo tFactoryVo, Integer current, Integer size);

    /**
     * 添加
     *
     * @param hotQuestions
     * @return int
     */
    int add(HotQuestions hotQuestions);

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
     * @param hotQuestions
     * @return int
     */
    int updateData(HotQuestions hotQuestions);

    /**
     * id查询数据
     *
     * @param id id
     * @return HotQuestions
     */
    HotQuestions findById(Long id);

    /**
     * 查询所有数据
     *
     * @return HotQuestions
     */
    List<HotQuestions> findAll();
}
