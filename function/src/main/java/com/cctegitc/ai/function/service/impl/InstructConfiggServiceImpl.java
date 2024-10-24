package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.InstructConfiggMapper;
import com.cctegitc.ai.function.db.pojo.InstructConfigg;
import com.cctegitc.ai.function.modules.intelligentcontrol.service.IInstructConfiggService;
import com.cctegitc.ai.function.vo.pagefind.InstructConfiggVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InstructConfiggServiceImpl implements IInstructConfiggService {

    @Resource
    private InstructConfiggMapper instructConfiggMapper;

    /**
     * 查看所有指令配置管理信息
     *
     * @return
     */
    public List<InstructConfigg> findAll() {
        QueryWrapper<InstructConfigg> queryWrapper = new QueryWrapper<>();
        return instructConfiggMapper.selectList(queryWrapper);
    }

    /**
     * 分页查看所有指令配置管理信息
     *
     * @param page
     * @param limit
     * @return
     */
    public InstructConfiggVo findByPage(Integer page, Integer limit) {
        InstructConfiggVo instructConfiggVo = new InstructConfiggVo();
        QueryWrapper<InstructConfigg> queryWrapper = new QueryWrapper<>();
        Page<InstructConfigg> objectPage = new Page<>(page, limit);
        Page<InstructConfigg> instructConfiggPage = instructConfiggMapper.selectPage(objectPage, queryWrapper);
        instructConfiggVo.setPage(page);
        instructConfiggVo.setLimit(limit);
        instructConfiggVo.setTotal(instructConfiggPage.getTotal());
        instructConfiggVo.setItems(instructConfiggPage.getRecords());
        return instructConfiggVo;
    }

    /**
     * 添加指令配置
     *
     * @param instructConfigg
     * @return
     */
    public int add(InstructConfigg instructConfigg) {
        return instructConfiggMapper.insert(instructConfigg);
    }

    /**
     * 更新指令配置信息
     *
     * @param instructConfigg
     * @return
     */
    public int updateData(InstructConfigg instructConfigg) {
        return instructConfiggMapper.updateById(instructConfigg);
    }

    /**
     * 删除当前指令配置信息
     *
     * @param id
     * @return
     */
    public int delete(Long id) {
        return instructConfiggMapper.deleteById(id);
    }

    /**
     * 通过id查询指令配置信息
     *
     * @param id
     * @return
     */
    public InstructConfigg findById(Long id) {
        return instructConfiggMapper.selectById(id);
    }
}
