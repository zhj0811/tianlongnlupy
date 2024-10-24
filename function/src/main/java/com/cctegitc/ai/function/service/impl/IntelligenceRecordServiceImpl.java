package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.IntelligenceRecordMapper;
import com.cctegitc.ai.function.db.pojo.IntelligenceRecord;
import com.cctegitc.ai.function.modules.intelligentduty.service.IntelligenceRecordService;
import com.cctegitc.ai.function.util.TimeUtils;
import com.cctegitc.ai.function.vo.pagefind.IntelligenceRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @Description:
 * @Author: wgd
 * @Date: 2023-06-05
 * @Time: 15:25
 */
@Service
@Slf4j
public class IntelligenceRecordServiceImpl implements IntelligenceRecordService {

    @Autowired
    private IntelligenceRecordMapper intelligenceRecordMapper;

    /**
     * 查询分页数据
     *
     * @param
     * @param current 当前页
     * @param size    大小
     * @return
     */
    @Override
    public IntelligenceRecordVo findByPage(IntelligenceRecord intelligenceRecord, Integer current, Integer size) {
        IntelligenceRecordVo intelligenceRecordVo = new IntelligenceRecordVo();
        Page<IntelligenceRecord> page = new Page<>(current, size);
        QueryWrapper<IntelligenceRecord> queryWrapper = new QueryWrapper<>();
        if (!"".equals(intelligenceRecord.getRecordContent()) && intelligenceRecord.getRecordContent() != null) {
            queryWrapper.like("record_content", intelligenceRecord.getRecordContent());
        }
        if (intelligenceRecord.getRecordDate() != null) {
            queryWrapper.eq("record_date", TimeUtils.getDate(intelligenceRecord.getRecordDate()));
        }
        if (!"".equals(intelligenceRecord.getRecordState()) && intelligenceRecord.getRecordState() != null) {
            queryWrapper.eq("record_state", intelligenceRecord.getRecordState());
        }
        if (!"".equals(intelligenceRecord.getRemind()) && intelligenceRecord.getRemind() != null) {
            queryWrapper.eq("remind", intelligenceRecord.getRemind());
        }
        Page<IntelligenceRecord> result = intelligenceRecordMapper.selectPage(page, queryWrapper);
        intelligenceRecordVo.setPage(current);
        intelligenceRecordVo.setLimit(size);
        intelligenceRecordVo.setTotal(result.getTotal());
        intelligenceRecordVo.setItems(result.getRecords());
        return intelligenceRecordVo;
    }

    /**
     * 添加
     *
     * @param intelligenceRecord
     * @return int
     */
    @Override
    public int add(IntelligenceRecord intelligenceRecord) {
        intelligenceRecord.setRecordDate(new Date());
        return intelligenceRecordMapper.insert(intelligenceRecord);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    @Override
    public int delete(Long id) {
        return intelligenceRecordMapper.deleteById(id);
    }

    /**
     * 修改
     *
     * @param intelligenceRecord
     * @return int
     */
    @Override
    public int updateData(IntelligenceRecord intelligenceRecord) {
        return intelligenceRecordMapper.updateById(intelligenceRecord);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return VideoLibrary
     */
    @Override
    public IntelligenceRecord findById(Long id) {
        return intelligenceRecordMapper.selectById(id);
    }
}
