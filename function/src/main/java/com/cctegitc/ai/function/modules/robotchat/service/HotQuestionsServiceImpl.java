package com.cctegitc.ai.function.modules.robotchat.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.HotQuestionsMapper;
import com.cctegitc.ai.function.db.pojo.HotQuestions;
import com.cctegitc.ai.function.modules.robotchat.service.IHotQuestionsService;
import com.cctegitc.ai.function.vo.pagefind.HotQuestionsVo;
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
public class HotQuestionsServiceImpl implements IHotQuestionsService {

    @Resource
    private HotQuestionsMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    @Override
    public HotQuestionsVo findByPage(HotQuestionsVo tFactoryVo, Integer current, Integer size) {
        HotQuestionsVo factoryVo = new HotQuestionsVo();
        Page<HotQuestions> page = new Page<>(current, size);
        QueryWrapper<HotQuestions> queryWrapper = new QueryWrapper<>();
        Page<HotQuestions> result = baseMapper.selectPage(page, queryWrapper);
        factoryVo.setPage(current);
        factoryVo.setLimit(size);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param hotQuestions
     * @return int
     */
    @Override
    public int add(HotQuestions hotQuestions) {
        return baseMapper.insert(hotQuestions);
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
     * @param hotQuestions
     * @return int
     */
    @Override
    public int updateData(HotQuestions hotQuestions) {
        return baseMapper.updateById(hotQuestions);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return HotQuestions
     */
    @Override
    public HotQuestions findById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询所有数据
     *
     * @return HotQuestions
     */
    @Override
    public List<HotQuestions> findAll() {
        return baseMapper.selectList(null);
    }
}
