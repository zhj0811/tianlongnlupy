package com.cctegitc.ai.function.modules.intelligentcontrol.service;


import com.cctegitc.ai.function.db.pojo.VoiceReport;
import com.cctegitc.ai.function.vo.pagefind.VoiceReportVo;

import java.util.List;

public interface IVoiceReportService {

    /**
     * 查看所有语音播报管理信息
     *
     * @return
     */
    List<VoiceReport> findAll();

    /**
     * 分页查看所有语音播报管理信息
     *
     * @return
     */
    VoiceReportVo findByPage(List<VoiceReport> items, Integer page, Integer limit);

    /**
     * 添加语音播报信息
     *
     * @param voiceReport
     * @return
     */
    int add(VoiceReport voiceReport);

    /**
     * 更新语音播报信息
     *
     * @param voiceReport
     * @return
     */
    int updateData(VoiceReport voiceReport);

    /**
     * 删除当前语音播报信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 通过id查询语音播报信息
     *
     * @param id
     * @return
     */
    VoiceReport findById(Long id);
}
