package com.cctegitc.ai.function.modules.contactsmodule.service;


import com.cctegitc.ai.function.db.pojo.OtherContact;
import com.cctegitc.ai.function.vo.pagefind.OtherContactVo;
import com.cctegitc.ai.function.vo.ResultVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
public interface IOtherContactService {

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    OtherContactVo findByPage(OtherContactVo tFactoryVo, Integer current, Integer size);

    /**
     * 添加
     *
     * @param otherContact
     * @return int
     */
    int add(OtherContact otherContact);

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
     * @param otherContact
     * @return int
     */
    int updateData(OtherContact otherContact);

    /**
     * id查询数据
     *
     * @param id id
     * @return OtherContact
     */
    OtherContact findById(Long id);

    /**
     * 查询所有数据
     *
     * @return List<OtherContact>
     */
    List<OtherContact> findAll();

    List<ResultVo> getPhone(String name, String dept);

    List<ResultVo> getPeople(String dept);
}
