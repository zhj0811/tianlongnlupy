package com.cctegitc.ai.function.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.vo.pagefind.IPageVo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class FindByPageHelper {
    public static <E, V> V findByPage(Class<V> voClass, QueryWrapper<E> queryWrapper, Integer current, Integer size, BaseMapper<E> mapper) {
        try {
            // 创建分页对象
            Page<E> page = new Page<>(current, size);
            // 执行分页查询
            Page<E> result = mapper.selectPage(page, queryWrapper);
            // 实例化VO对象
            V voInstance = voClass.getDeclaredConstructor().newInstance();
            // 假设有一个通用的接口或者基类PageVo，所有VO类都实现这个接口
            if (voInstance instanceof IPageVo) {
                IPageVo pageVo = (IPageVo) voInstance;
                pageVo.setPage(current);
                pageVo.setLimit(size);
                pageVo.setTotal(result.getTotal());
                pageVo.setItems(result.getRecords());
            }
            return voInstance;
        } catch (Exception e) {
            log.error("分页查询失败", e);
            // 根据实际情况处理异常
            return null;
        }
    }
}
