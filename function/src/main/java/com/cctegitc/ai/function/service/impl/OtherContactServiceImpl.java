package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.OtherContactMapper;
import com.cctegitc.ai.function.db.pojo.OtherContact;
import com.cctegitc.ai.function.modules.contactsmodule.service.IOtherContactService;
import com.cctegitc.ai.function.vo.pagefind.OtherContactVo;
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
public class OtherContactServiceImpl implements IOtherContactService {

    @Resource
    private OtherContactMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param tFactoryVo 条件
     * @param current    当前页
     * @param size       大小
     * @return
     */
    @Override
    public OtherContactVo findByPage(OtherContactVo tFactoryVo, Integer current, Integer size) {
        OtherContactVo factoryVo = new OtherContactVo();
        Page<OtherContact> page = new Page<>(current, size);
        QueryWrapper<OtherContact> queryWrapper = new QueryWrapper<>();
        Page<OtherContact> result = baseMapper.selectPage(page, queryWrapper);
        factoryVo.setPage(current);
        factoryVo.setLimit(size);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param otherContact
     * @return int
     */
    @Override
    public int add(OtherContact otherContact) {
        otherContact.setId(null);
        return baseMapper.insert(otherContact);
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
     * @param otherContact
     * @return int
     */
    @Override
    public int updateData(OtherContact otherContact) {
        return baseMapper.updateById(otherContact);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return OtherContact
     */
    @Override
    public OtherContact findById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询所有数据
     *
     * @return List<OtherContact>
     */
    @Override
    public List<OtherContact> findAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public List<ResultVo> getPhone(String name, String dept) {
        QueryWrapper<OtherContact> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        queryWrapper.eq("unit", dept);
        List<OtherContact> otherContacts = baseMapper.selectList(queryWrapper);
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
    public List<ResultVo> getPeople(String dept) {
        QueryWrapper<OtherContact> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unit", dept);
        List<OtherContact> otherContacts = baseMapper.selectList(queryWrapper);
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

    public List<ResultVo> getDept() {
        List<OtherContact> OtherContact = baseMapper.getDept();
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
