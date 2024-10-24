package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.PictureLibraryMapper;
import com.cctegitc.ai.function.db.pojo.PictureLibrary;
import com.cctegitc.ai.function.modules.multimedialibrary.service.IPictureLibraryService;
import com.cctegitc.ai.function.vo.pagefind.PictureLibraryVo;
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
public class PictureLibraryServiceImpl implements IPictureLibraryService {

    @Resource
    private PictureLibraryMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param pictureLibrary 条件
     * @param current        当前页
     * @param size           大小
     * @return
     */
    @Override
    public PictureLibraryVo findByPage(PictureLibrary pictureLibrary, Integer current, Integer size) {
        PictureLibraryVo factoryVo = new PictureLibraryVo();
        Page<PictureLibrary> page = new Page<>(current, size);
        QueryWrapper<PictureLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("picture_name", pictureLibrary.getPictureName());
        Page<PictureLibrary> result = baseMapper.selectPage(page, queryWrapper);
        factoryVo.setPage(current);
        factoryVo.setLimit(size);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param pictureLibrary
     * @return int
     */
    @Override
    public int add(PictureLibrary pictureLibrary) {
        pictureLibrary.setCreatTime(new Date());
        pictureLibrary.setUploadTime(new Date());
        return baseMapper.insert(pictureLibrary);
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
     * @param pictureLibrary
     * @return int
     */
    @Override
    public int updateData(PictureLibrary pictureLibrary) {
        pictureLibrary.setUploadTime(new Date());
        return baseMapper.updateById(pictureLibrary);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return PictureLibrary
     */
    @Override
    public PictureLibrary findById(Long id) {
        return baseMapper.selectById(id);
    }
}
