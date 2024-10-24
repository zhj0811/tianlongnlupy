package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.UnmatchQuestionsMapper;
import com.cctegitc.ai.function.db.pojo.UnmatchQuestions;
import com.cctegitc.ai.function.modules.statisticsmodule.service.IUnmatchQuestionsService;
import com.cctegitc.ai.function.vo.pagefind.UnmatchQuestionsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Service
public class UnmatchQuestionsServiceImpl implements IUnmatchQuestionsService {

    @Resource
    private UnmatchQuestionsMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    @Override
    public UnmatchQuestionsVo findByPage(UnmatchQuestionsVo tFactoryVo, Integer current, Integer size) {
        UnmatchQuestionsVo factoryVo = new UnmatchQuestionsVo();
        Page<UnmatchQuestions> page = new Page<>(current, size);
        QueryWrapper<UnmatchQuestions> queryWrapper = new QueryWrapper<>();
        Page<UnmatchQuestions> result = baseMapper.selectPage(page, queryWrapper);
        factoryVo.setPage(current);
        factoryVo.setLimit(size);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param unmatchQuestions
     * @return int
     */
    @Override
    public int add(UnmatchQuestions unmatchQuestions) {
        return baseMapper.insert(unmatchQuestions);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    @Override
    public int delete(Long id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改
     *
     * @param unmatchQuestions
     * @return int
     */
    @Override
    public int updateData(UnmatchQuestions unmatchQuestions) {
        return baseMapper.updateById(unmatchQuestions);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return UnmatchQuestions
     */
    @Override
    public UnmatchQuestions findById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询所有数据
     *
     * @return List<UnmatchQuestions>
     */
    public List<UnmatchQuestions> findAll() {
        return baseMapper.selectList(null);
    }
}
