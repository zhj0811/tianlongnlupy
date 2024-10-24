package com.cctegitc.ai.function.modules.statisticsmodule.service;


import com.cctegitc.ai.function.db.pojo.StatisticalChart;
import com.cctegitc.ai.function.vo.pagefind.StatisticalChartVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
public interface IStatisticalChartService {

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    StatisticalChartVo findByPage(StatisticalChartVo tFactoryVo, Integer current, Integer size);

    /**
     * 添加
     *
     * @param statisticalChart
     * @return int
     */
    int add(StatisticalChart statisticalChart);

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
     * @param statisticalChart
     * @return int
     */
    int updateData(StatisticalChart statisticalChart);

    /**
     * id查询数据
     *
     * @param id id
     * @return StatisticalChart
     */
    StatisticalChart findById(Long id);

    /**
     * 查询所有数据
     *
     * @return StatisticalChart
     */
    List<StatisticalChart> findAll();
}
