package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.ExpertLibraryMapper;
import com.cctegitc.ai.function.db.pojo.ExpertLibrary;
import com.cctegitc.ai.function.modules.contactsmodule.service.IExpertLibraryService;
import com.cctegitc.ai.function.vo.pagefind.ExpertLibraryVo;
import com.cctegitc.ai.function.vo.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peip
 * @since 2022-12-02
 */
@Service
public class ExpertLibraryServiceImpl implements IExpertLibraryService {

    @Resource
    private ExpertLibraryMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param expertLibrary 条件
     * @param
     * @param
     * @return
     */
    @Override
    public ExpertLibraryVo findByPage(ExpertLibrary expertLibrary, Integer page, Integer limit) {
        ExpertLibraryVo factoryVo = new ExpertLibraryVo();
        Page<ExpertLibrary> page1 = new Page<>(page, limit);
        QueryWrapper<ExpertLibrary> queryWrapper = new QueryWrapper<>();
        if (!"".equals(expertLibrary.getName())) {
            queryWrapper.like("name", expertLibrary.getName());
        }
        if (!"".equals(expertLibrary.getUnit())) {
            queryWrapper.like("unit", expertLibrary.getUnit());
        }
        if (!"".equals(expertLibrary.getDuties())) {
            queryWrapper.like("duties", expertLibrary.getDuties());
        }
        if (!"".equals(expertLibrary.getTitle())) {
            queryWrapper.like("title", expertLibrary.getTitle());
        }
        if (!"".equals(expertLibrary.getEmergencyDuties())) {
            queryWrapper.like("emergency_duties", expertLibrary.getEmergencyDuties());
        }
        if (!"".equals(expertLibrary.getPeopleType())) {
            queryWrapper.like("people_type", expertLibrary.getPeopleType());
        }
        if (!"".equals(expertLibrary.getExpertType())) {
            queryWrapper.like("expert_type", expertLibrary.getExpertType());
        }
        if (!"".equals(expertLibrary.getGroups())) {
            queryWrapper.like("groups", expertLibrary.getGroups());
        }
        if (!"".equals(expertLibrary.getTel())) {
            queryWrapper.like("tel", expertLibrary.getTel());
        }
        if (!"".equals(expertLibrary.getPhone())) {
            queryWrapper.like("phone", expertLibrary.getPhone());
        }
        if (!"".equals(expertLibrary.getAdeptDomain())) {
            queryWrapper.like("adept_domain", expertLibrary.getAdeptDomain());
        }
        Page<ExpertLibrary> result = baseMapper.selectPage(page1, queryWrapper);
        factoryVo.setPage(page);
        factoryVo.setLimit(limit);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param expertLibrary
     * @return int
     */
    @Override
    public int add(ExpertLibrary expertLibrary) {
        return baseMapper.insert(expertLibrary);
    }

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    @Override
    public int delete(Long id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 修改
     *
     * @param expertLibrary
     * @return int
     */
    @Override
    public int updateData(ExpertLibrary expertLibrary) {
        return baseMapper.updateById(expertLibrary);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return ExpertLibrary
     */
    @Override
    public ExpertLibrary findById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询所有数据
     *
     * @return List<ExpertLibrary>
     */
    @Override
    public List<ExpertLibrary> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public List<ResultVo> getPhone(String name, String dept) {
        QueryWrapper<ExpertLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        queryWrapper.eq("unit", dept);
        List<ExpertLibrary> otherContacts = baseMapper.selectList(queryWrapper);
        List<ResultVo> childrens = new ArrayList<>();

        for (int i = 0; i < otherContacts.size(); i++) {
            ResultVo children = new ResultVo();
            children.setLabel(otherContacts.get(i).getPhone());
            children.setValue(otherContacts.get(i).getPhone());
            children.setChildren(null);
            childrens.add(children);
            ResultVo children2 = new ResultVo();
            children2.setLabel(otherContacts.get(i).getTel());
            children2.setValue(otherContacts.get(i).getTel());
            children2.setChildren(null);
            childrens.add(children2);
        }
        return childrens;
    }

    @Override
    public List<ExpertLibrary> find() {
        return baseMapper.find();
    }

    @Override
    public List<ResultVo> getPeople(String dept) {
        QueryWrapper<ExpertLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unit", dept);
        List<ExpertLibrary> otherContacts = baseMapper.selectList(queryWrapper);
        List<ResultVo> childrens = new ArrayList<>();
        for (int i = 0; i < otherContacts.size(); i++) {
            ResultVo children = new ResultVo();
            children.setLabel(otherContacts.get(i).getName());
            children.setValue(otherContacts.get(i).getName());
            children.setChildren(null);
            childrens.add(children);
        }
        return childrens;
    }

    @Override
    public List<ResultVo> getDept() {
        List<ExpertLibrary> OtherContact = baseMapper.getDept();
        List<ResultVo> childrens = new ArrayList<>();
        for (int i = 0; i < OtherContact.size(); i++) {
            ResultVo children = new ResultVo();
            children.setLabel(OtherContact.get(i).getUnit());
            children.setValue(OtherContact.get(i).getUnit());
            children.setChildren(null);
            childrens.add(children);
        }
        return childrens;
    }
}
