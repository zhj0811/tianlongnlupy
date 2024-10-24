package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cctegitc.ai.function.db.dao.BusinessCaseMapper;
import com.cctegitc.ai.function.db.pojo.BusinessCase;
import com.cctegitc.ai.function.modules.intelligentcontrol.service.IBusinessCaseService;
import com.cctegitc.ai.function.util.FindByPageHelper;
import com.cctegitc.ai.function.vo.pagefind.BusinessCaseVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusinessCaseServiceImpl implements IBusinessCaseService {

    @Resource
    private BusinessCaseMapper businessCaseMapper;

    /**
     * 查看所有业务场景管理信息
     *
     * @return
     */
    @Override
    public List<BusinessCase> findAll() {
        // 查询所有业务场景管理信息
        return businessCaseMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 分页查看所有业务场景管理信息
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public BusinessCaseVo findByPage(Integer page, Integer limit) {
        return FindByPageHelper.findByPage(BusinessCaseVo.class, new QueryWrapper<>(), page, limit, businessCaseMapper);
    }

    /**
     * 添加业务场景
     *
     * @param businessCase
     * @return
     */
    @Override
    public int add(BusinessCase businessCase) {
        // 调用mapper层的insert方法，添加业务场景返回受影响的行数
        return businessCaseMapper.insert(businessCase);
    }

    /**
     * 更新业务场景信息
     *
     * @param businessCase
     * @return
     */
    @Override
    public int updateData(BusinessCase businessCase) {
        return businessCaseMapper.updateById(businessCase);
    }

    /**
     * 删除当前业务场景信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        // 调用mapper层的deleteById方法，根据id删除业务场景信息返回受影响的行数
        return businessCaseMapper.deleteById(id);
    }

    /**
     * 通过id查询业务场景信息
     *
     * @param id
     * @return
     */
    @Override
    public BusinessCase findById(Long id) {
        // 调用mapper层的selectById方法，根据id查询业务场景信息返回BusinessCase对象
        return businessCaseMapper.selectById(id);
    }
}
