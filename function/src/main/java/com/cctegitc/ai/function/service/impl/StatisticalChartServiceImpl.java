package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.StatisticalChartMapper;
import com.cctegitc.ai.function.db.pojo.StatisticalChart;
import com.cctegitc.ai.function.modules.statisticsmodule.service.IStatisticalChartService;
import com.cctegitc.ai.function.vo.pagefind.StatisticalChartVo;
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
public class StatisticalChartServiceImpl implements IStatisticalChartService {

    @Resource
    private StatisticalChartMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    @Override
    public StatisticalChartVo findByPage(StatisticalChartVo tFactoryVo, Integer current, Integer size) {
        StatisticalChartVo factoryVo = new StatisticalChartVo();
        Page<StatisticalChart> page = new Page<>(current, size);
        QueryWrapper<StatisticalChart> queryWrapper = new QueryWrapper<>();
        Page<StatisticalChart> result = baseMapper.selectPage(page, queryWrapper);
        factoryVo.setPage(current);
        factoryVo.setLimit(size);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param statisticalChart
     * @return int
     */
    @Override
    public int add(StatisticalChart statisticalChart) {
        return baseMapper.insert(statisticalChart);
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
     * @param statisticalChart
     * @return int
     */
    @Override
    public int updateData(StatisticalChart statisticalChart) {
        return baseMapper.updateById(statisticalChart);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return StatisticalChart
     */
    @Override
    public StatisticalChart findById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询所有数据
     *
     * @return StatisticalChart
     */
    @Override
    public List<StatisticalChart> findAll() {
        return baseMapper.selectList(null);
    }
}
