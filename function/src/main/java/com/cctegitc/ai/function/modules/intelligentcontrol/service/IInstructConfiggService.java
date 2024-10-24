package com.cctegitc.ai.function.modules.intelligentcontrol.service;


import com.cctegitc.ai.function.db.pojo.InstructConfigg;
import com.cctegitc.ai.function.vo.pagefind.InstructConfiggVo;

import java.util.List;

public interface IInstructConfiggService {

    /**
     * 查看所有指令配置管理信息
     *
     * @return
     */
    List<InstructConfigg> findAll();

    /**
     * 分页查看所有指令配置管理信息
     *
     * @param page
     * @param limit
     * @return
     */
    InstructConfiggVo findByPage(Integer page, Integer limit);

    /**
     * 添加指令配置
     *
     * @param instructConfigg
     * @return
     */
    int add(InstructConfigg instructConfigg);

    /**
     * 更新指令配置信息
     *
     * @param instructConfigg
     * @return
     */
    int updateData(InstructConfigg instructConfigg);

    /**
     * 删除当前指令配置信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 通过id查询指令配置信息
     *
     * @param id
     * @return
     */
    InstructConfigg findById(Long id);
}
