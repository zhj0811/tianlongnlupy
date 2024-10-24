package com.cctegitc.ai.authority.system.service.impl;

import com.cctegitc.ai.authority.common.constant.UserConstants;
import com.cctegitc.ai.authority.common.core.domain.entity.SysDictData;
import com.cctegitc.ai.authority.common.core.domain.entity.SysDictType;
import com.cctegitc.ai.authority.common.exception.CustomException;
import com.cctegitc.ai.authority.common.utils.DictUtils;
import com.cctegitc.ai.authority.common.utils.StringUtils;
import com.cctegitc.ai.authority.system.mapper.SysDictDataMapper;
import com.cctegitc.ai.authority.system.mapper.SysDictTypeMapper;
import com.cctegitc.ai.authority.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 字典 业务层处理
 *
 *
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {
    @Autowired
    private SysDictTypeMapper _dictTypeMapper;

    @Autowired
    private SysDictDataMapper _dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeList(SysDictType dictType) {
        return _dictTypeMapper.selectDictTypeList(dictType);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return _dictTypeMapper.selectDictTypeAll();
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return _dictDataMapper.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return _dictTypeMapper.selectDictTypeById(dictId);
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeByType(String dictType) {
        return _dictTypeMapper.selectDictTypeByType(dictType);
    }

    /**
     * 批量删除字典类型信息
     *
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    @Override
    public void deleteDictTypeByIds(Long[] dictIds) {
        for (Long dictId : dictIds) {
            SysDictType dictType = selectDictTypeById(dictId);
            if (_dictDataMapper.countDictDataByType(dictType.getDictType()) > 0) {
                throw new CustomException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            _dictTypeMapper.deleteDictTypeById(dictId);
            DictUtils.removeDictCache(dictType.getDictType());
        }
    }

    /**
     * 加载字典缓存数据
     */
    public void loadingDictCache() {
//        List<SysDictType> dictTypeList = dictTypeMapper.selectDictTypeAll();
//        for (SysDictType dictType : dictTypeList)
//        {
//            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(dictType.getDictType());
//            DictUtils.setDictCache(dictType.getDictType(), dictDatas);
//        }
    }

    /**
     * 清空字典缓存数据
     */
    public void clearDictCache() {
        DictUtils.clearDictCache();
    }

    /**
     * 重置字典缓存数据
     */
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dict) {
        int row = _dictTypeMapper.insertDictType(dict);
//        if (row > 0)
//        {
//            DictUtils.setDictCache(dict.getDictType(), null);
//        }
        return row;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dict 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dict) {
        SysDictType oldDict = _dictTypeMapper.selectDictTypeById(dict.getDictId());
        //修改字典类型
        _dictDataMapper.updateDictDataType(oldDict.getDictType(), dict.getDictType());
        //修改字典状态
        _dictDataMapper.updateDictDatastatus(dict.getStatus(), dict.getDictType());
        //修改字典类型表中的信息
        int row = _dictTypeMapper.updateDictType(dict);
//        if (row > 0)
//        {
//            List<SysDictData> dictDatas = _dictDataMapper.selectDictDataByType(dict.getDictType());
//            DictUtils.setDictCache(dict.getDictType(), dictDatas);
//        }
        return row;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = _dictTypeMapper.checkDictTypeUnique(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
