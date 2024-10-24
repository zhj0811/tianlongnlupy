package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.DocumentsLibraryMapper;
import com.cctegitc.ai.function.db.pojo.DocumentsLibrary;
import com.cctegitc.ai.function.modules.multimedialibrary.service.IDocumentsLibraryService;
import com.cctegitc.ai.function.vo.pagefind.DocumentsLibraryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author peip
 * @since 2022-11-18
 */
@Service
@Slf4j
public class DocumentsLibraryServiceImpl implements IDocumentsLibraryService {

    @Resource
    private DocumentsLibraryMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param
     * @param current 当前页
     * @param size    大小
     * @return
     */
    @Override
    public DocumentsLibraryVo findByPage(DocumentsLibrary documentsLibraryVo, Integer current, Integer size) {
        DocumentsLibraryVo factoryVo = new DocumentsLibraryVo();
        Page<DocumentsLibrary> page = new Page<>(current, size);
        QueryWrapper<DocumentsLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("documents_name", documentsLibraryVo.getDocumentsName());
        Page<DocumentsLibrary> result = baseMapper.selectPage(page, queryWrapper);
        factoryVo.setPage(current);
        factoryVo.setLimit(size);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param dol
     * @return int
     */
    @Override
    public int add(DocumentsLibrary dol) {
        dol.setCreatTime(new Date());
        dol.setUploadTime(new Date());
        return baseMapper.insert(dol);
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
     * @param documentsLibrary
     * @return int
     */
    @Override
    public int updateData(DocumentsLibrary documentsLibrary) {
        documentsLibrary.setUploadTime(new Date());
        return baseMapper.updateById(documentsLibrary);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return DocumentsLibrary
     */
    @Override
    public DocumentsLibrary findById(Long id) {
        return baseMapper.selectById(id);
    }


}
