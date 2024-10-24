package com.cctegitc.ai.function.modules.contactsmodule.service;

import com.alibaba.fastjson.JSONObject;
import com.cctegitc.ai.function.db.pojo.EmployeeLibrary;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
public interface IEmployeeLibraryService {

    /**
     * 查询分页数据
     *
     * @param tFactory 条件
     * @param
     * @param
     * @return
     */
    JSONObject findByPage(EmployeeLibrary tFactory) throws Exception;

    /**
     * 添加
     *
     * @param json
     * @return int
     */
    JSONObject add(JSONObject json);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    JSONObject delete(Long id);

    /**
     * 修改
     *
     * @param json
     * @return int
     */
    JSONObject updateData(JSONObject json);

    /**
     * id查询数据
     *
     * @param id id
     * @return EmployeeLibrary
     */
    EmployeeLibrary findById(Long id);

    /**
     * 查询所有数据
     *
     * @return List<EmployeeLibrary>
     */
    List<EmployeeLibrary> findAll();

    int updateAll();

    String phoneAdd(String number);

    JSONObject getDeptData();

    String getPhoneByName(String name);
}
