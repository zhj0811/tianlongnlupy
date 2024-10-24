package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.VoiceMemoMapper;
import com.cctegitc.ai.function.db.pojo.Memo;

import com.cctegitc.ai.function.modules.intelligentduty.service.IVoiceMemoService;
import com.cctegitc.ai.function.util.FindByPageHelper;
import com.cctegitc.ai.function.vo.pagefind.VoiceMemoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VoiceMemoServiceImpl implements IVoiceMemoService {

    @Resource
    private VoiceMemoMapper voiceMemoMapper;

    /**
     * 查看所有语音备忘录信息
     *
     * @return
     */
    @Override public List<Memo> findAll() {
        QueryWrapper<Memo> queryWrapper = new QueryWrapper<>();
        return voiceMemoMapper.selectList(queryWrapper);
    }

    /**
     * 分页查看所有语音备忘录信息
     *
     * @return
     */
    @Override
    public VoiceMemoVo findByPage(Integer page, Integer limit) {
        return FindByPageHelper.findByPage(VoiceMemoVo.class, new QueryWrapper<Memo>(), page, limit, voiceMemoMapper);
    }

    /**
     * 新增语音备忘录信息
     *
     * @param voiceMemo
     * @return
     */
    @Override
    public int add(Memo voiceMemo) {
        return voiceMemoMapper.insert(voiceMemo);
    }

    /**
     * 更新语音备忘录信息
     *
     * @param voiceMemo
     * @return
     */
    @Override
    public int updateData(Memo voiceMemo) {
        return voiceMemoMapper.updateById(voiceMemo);
    }

    /**
     * 删除当前语音备忘录信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return voiceMemoMapper.deleteById(id);
    }

    /**
     * 通过id查询语音备忘录信息
     *
     * @param id
     * @return
     */
    @Override
    public Memo findById(Long id) {
        return voiceMemoMapper.selectById(id);
    }

}
