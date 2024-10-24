package com.cctegitc.ai.function.modules.intelligentduty.service;


import com.cctegitc.ai.function.db.pojo.IntelligenceRecord;
import com.cctegitc.ai.function.vo.pagefind.IntelligenceRecordVo;

/**
 * <p>
 * 电子台账 服务类
 * </p>
 *
 * @author
 * @since 2022-03-10
 */
public interface IntelligenceRecordService {
    /**
     * 查询分页数据
     *
     * @param intelligenceRecord 条件
     * @param current            当前页
     * @param size               大小
     * @return
     */
    IntelligenceRecordVo findByPage(IntelligenceRecord intelligenceRecord, Integer current, Integer size);

    /**
     * 添加
     *
     * @param intelligenceRecord
     * @return int
     */
    int add(IntelligenceRecord intelligenceRecord);

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
     * @param intelligenceRecord
     * @return int
     */
    int updateData(IntelligenceRecord intelligenceRecord);

    /**
     * id查询数据
     *
     * @param id id
     * @return IntelligenceRecord
     */
    IntelligenceRecord findById(Long id);
}
