package com.cctegitc.ai.function.modules.knowledgemanagement.service;


import com.cctegitc.ai.function.db.pojo.WordsBase;
import com.cctegitc.ai.function.vo.pagefind.WordsBaseVo;

import java.util.List;

public interface IWordsBaseService {

    /**
     * 查看所有词库信息
     *
     * @return
     */
    List<WordsBase> findAll();

    /**
     * 分页查看所有词库信息
     *
     * @param page
     * @param limit
     * @return
     */
    WordsBaseVo findByPage(Integer page, Integer limit);

    /**
     * 添加词库库信息
     *
     * @param wordsBase
     * @return
     */
    int add(WordsBase wordsBase);

    /**
     * 更新词库信息
     *
     * @param wordsBase
     * @return
     */
    int updateData(WordsBase wordsBase);

    /**
     * 删除当前词库信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 通过id查询词库信息
     *
     * @param id
     * @return
     */
    WordsBase findById(Long id);
}
