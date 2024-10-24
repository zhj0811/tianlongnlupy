package com.cctegitc.ai.function.modules.intelligentduty.service;


import com.cctegitc.ai.function.db.pojo.Memo;

import com.cctegitc.ai.function.vo.pagefind.VoiceMemoVo;

import java.util.List;

public interface IVoiceMemoService {

    /**
     * 查看所有语音备忘录信息
     *
     * @return
     */
    List<Memo> findAll();

    /**
     * 分页查看所有语音备忘录信息
     *
     * @return
     */
    VoiceMemoVo findByPage(Integer page, Integer limit);

    /**
     * 新增语音备忘录信息
     *
     * @param voiceMemo
     * @return
     */
    int add(Memo voiceMemo);

    /**
     * 更新语音备忘录信息
     *
     * @param voiceMemo
     * @return
     */
    int updateData(Memo voiceMemo);

    /**
     * 删除当前语音备忘录信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 通过id查询语音备忘录信息
     *
     * @param id
     * @return
     */
    Memo findById(Long id);
}
