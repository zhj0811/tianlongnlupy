package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.WordsBaseMapper;
import com.cctegitc.ai.function.db.pojo.WordsBase;
import com.cctegitc.ai.function.modules.knowledgemanagement.service.IWordsBaseService;
import com.cctegitc.ai.function.vo.pagefind.WordsBaseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WordsBaseServiceImpl implements IWordsBaseService {

    @Resource
    private WordsBaseMapper wordsBaseMapper;

    /**
     * 查看所有测库信息
     *
     * @return
     */
    public List<WordsBase> findAll() {
        QueryWrapper<WordsBase> queryWrapper = new QueryWrapper<>();
        return wordsBaseMapper.selectList(queryWrapper);
    }

    /**
     * 分页查看所有测库信息
     *
     * @param page
     * @param limit
     * @return
     */
    public WordsBaseVo findByPage(Integer page, Integer limit) {
        WordsBaseVo wordsBaseVo = new WordsBaseVo();
        QueryWrapper<WordsBase> queryWrapper = new QueryWrapper<>();
        Page<WordsBase> objectPage = new Page<>(page, limit);
        Page<WordsBase> wordsBasePage = wordsBaseMapper.selectPage(objectPage, queryWrapper);
        wordsBaseVo.setPage(page);
        wordsBaseVo.setLimit(limit);
        wordsBaseVo.setTotal(wordsBasePage.getTotal());
        wordsBaseVo.setItems(wordsBasePage.getRecords());
        return wordsBaseVo;
    }

    /**
     * 添加词库库信息
     *
     * @param wordsBase
     * @return
     */
    public int add(WordsBase wordsBase) {
        return wordsBaseMapper.insert(wordsBase);
    }

    /**
     * 更新词库信息
     *
     * @param wordsBase
     * @return
     */
    public int updateData(WordsBase wordsBase) {
        return wordsBaseMapper.updateById(wordsBase);
    }

    /**
     * 删除当前词库信息
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return wordsBaseMapper.deleteById(id);
    }

    /**
     * 通过id查询词库信息
     *
     * @param id
     * @return
     */
    public WordsBase findById(Long id) {
        return wordsBaseMapper.selectById(id);
    }
}
