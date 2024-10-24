package com.cctegitc.ai.function.modules.contactsmodule.service;


import com.cctegitc.ai.function.db.pojo.ExpertLibrary;
import com.cctegitc.ai.function.vo.pagefind.ExpertLibraryVo;
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
public interface IExpertLibraryService {

    /**
     * 查询分页数据
     *
     * @param expertLibrary 条件
     * @param current       当前页
     * @param size          大小
     * @return
     */
    ExpertLibraryVo findByPage(ExpertLibrary expertLibrary, Integer current, Integer size);

    /**
     * 添加
     *
     * @param expertLibrary
     * @return int
     */
    int add(ExpertLibrary expertLibrary);

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
     * @param expertLibrary
     * @return int
     */
    int updateData(ExpertLibrary expertLibrary);

    /**
     * id查询数据
     *
     * @param id id
     * @return ExpertLibrary
     */
    ExpertLibrary findById(Long id);

    /**
     * 查询所有数据
     *
     * @return List<ExpertLibrary>
     */
    List<ExpertLibrary> findAll();

    List<ResultVo> getDept();

    List<ResultVo> getPeople(String dept);

    List<ResultVo> getPhone(String name, String dept);

    List<ExpertLibrary> find();
}
