package com.cctegitc.ai.function.modules.intelligentcontrol.service;


import com.cctegitc.ai.function.db.pojo.BusinessCase;
import com.cctegitc.ai.function.vo.pagefind.BusinessCaseVo;

import java.util.List;

public interface IBusinessCaseService {

    /**
     * 查看所有业务场景管理信息
     *
     * @return
     */
    List<BusinessCase> findAll();

    /**
     * 分页查看所有业务场景管理信息
     *
     * @param page
     * @param limit
     * @return
     */
    BusinessCaseVo findByPage(Integer page, Integer limit);

    /**
     * 添加业务场景
     *
     * @param businessCase
     * @return
     */
    int add(BusinessCase businessCase);

    /**
     * 更新业务场景信息
     *
     * @param businessCase
     * @return
     */
    int updateData(BusinessCase businessCase);

    /**
     * 删除当前业务场景信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 通过id查询业务场景信息
     *
     * @param id
     * @return
     */
    BusinessCase findById(Long id);
}

