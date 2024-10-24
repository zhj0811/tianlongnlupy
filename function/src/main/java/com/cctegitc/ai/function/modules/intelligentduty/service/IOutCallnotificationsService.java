package com.cctegitc.ai.function.modules.intelligentduty.service;


import com.cctegitc.ai.function.db.pojo.OutCallnotifications;
import com.cctegitc.ai.function.vo.pagefind.OutCallnotificationsVo;

import java.util.List;

public interface IOutCallnotificationsService {

    /**
     * 查看所有外呼信息
     *
     * @return
     */
    List<OutCallnotifications> findAll();

    /**
     * 分页查看所有外呼信息
     *
     * @param page
     * @param limit
     * @return
     */
    OutCallnotificationsVo findByPage(OutCallnotifications outCallnotifications, Integer page, Integer limit);

    /**
     * 添加外呼
     *
     * @param outCallnotifications
     * @return
     */
    int add(OutCallnotifications outCallnotifications);

    /**
     * 更新外呼信息
     *
     * @param outCallnotifications
     * @return
     */
    int updateData(OutCallnotifications outCallnotifications);

    /**
     * 删除当前外呼信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 通过id查询外呼信息
     *
     * @param id
     * @return
     */
    OutCallnotifications findById(Long id);


    /**
     * 定时外呼功能
     *
     * @param
     * @return
     */
    void outCallScheduled();
}
