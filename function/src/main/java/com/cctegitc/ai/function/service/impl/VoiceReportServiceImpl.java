package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.VoiceReportMapper;
import com.cctegitc.ai.function.db.pojo.VoiceReport;
import com.cctegitc.ai.function.modules.intelligentcontrol.service.IVoiceReportService;
import com.cctegitc.ai.function.vo.pagefind.VoiceReportVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoiceReportServiceImpl implements IVoiceReportService {

    @Resource
    private VoiceReportMapper voiceReportMapper;

    /**
     * 查看所有语音播报管理信息
     *
     * @return
     */
    @Override
    public List<VoiceReport> findAll() {
        QueryWrapper<VoiceReport> queryWrapper = new QueryWrapper<>();
        return voiceReportMapper.selectList(queryWrapper);
    }

    /**
     * 分页查看所有语音播报管理信息
     *
     * @return
     */
    @Override
    public VoiceReportVo findByPage(List<VoiceReport> items, Integer page, Integer limit) {
        VoiceReportVo voiceReportVo = new VoiceReportVo();
        QueryWrapper<VoiceReport> queryWrapper = new QueryWrapper<>();
        Page<VoiceReport> objectPage = new Page<>(page, limit);
        if (items.size() > 0) {
            if (!"".equals(items.get(0).getCreatePcode())) {
                queryWrapper.eq("create_pcode", items.get(0).getCreatePcode());
            }
            if (!"".equals(items.get(0).getOnOff())) {
                queryWrapper.eq("on_off", items.get(0).getOnOff());
            }
        }
        Page<VoiceReport> voiceReportPage = voiceReportMapper.selectPage(objectPage, queryWrapper);
        voiceReportVo.setPage(page);
        voiceReportVo.setLimit(limit);
        voiceReportVo.setTotal(voiceReportPage.getTotal());
        voiceReportVo.setItems(voiceReportPage.getRecords());
        return voiceReportVo;
    }

    /**
     * 添加语音播报信息
     *
     * @param voiceReport
     * @return
     */
    @Override
    public int add(VoiceReport voiceReport) {
        return voiceReportMapper.insert(voiceReport);
    }

    /**
     * 更新语音播报信息
     *
     * @param voiceReport
     * @return
     */
    @Override
    public int updateData(VoiceReport voiceReport) {
        return voiceReportMapper.updateById(voiceReport);
    }

    /**
     * 删除当前语音播报信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return voiceReportMapper.deleteById(id);
    }

    /**
     * 通过id查询语音播报信息
     *
     * @param id
     * @return
     */
    @Override
    public VoiceReport findById(Long id) {
        return voiceReportMapper.selectById(id);
    }
}
