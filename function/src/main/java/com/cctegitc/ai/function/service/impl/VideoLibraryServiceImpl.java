package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.VideoLibraryMapper;
import com.cctegitc.ai.function.db.pojo.VideoLibrary;
import com.cctegitc.ai.function.modules.multimedialibrary.service.IVideoLibraryService;
import com.cctegitc.ai.function.vo.pagefind.VideoLibraryVo;
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
public class VideoLibraryServiceImpl implements IVideoLibraryService {

    @Resource
    private VideoLibraryMapper baseMapper;

    /**
     * 查询分页数据
     *
     * @param
     * @param current 当前页
     * @param size    大小
     * @return
     */
    @Override
    public VideoLibraryVo findByPage(VideoLibrary videoLibrary, Integer current, Integer size) {
        VideoLibraryVo factoryVo = new VideoLibraryVo();
        Page<VideoLibrary> page = new Page<>(current, size);
        QueryWrapper<VideoLibrary> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("video_name", videoLibrary.getVideoName());
        Page<VideoLibrary> result = baseMapper.selectPage(page, queryWrapper);
        factoryVo.setPage(current);
        factoryVo.setLimit(size);
        factoryVo.setTotal(result.getTotal());
        factoryVo.setItems(result.getRecords());
        return factoryVo;
    }

    /**
     * 添加
     *
     * @param videoLibrary
     * @return int
     */
    @Override
    public int add(VideoLibrary videoLibrary) {
        videoLibrary.setCreatTime(new Date());
        videoLibrary.setUploadTime(new Date());
        return baseMapper.insert(videoLibrary);
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
     * @param videoLibrary
     * @return int
     */
    @Override
    public int updateData(VideoLibrary videoLibrary) {
        videoLibrary.setUploadTime(new Date());
        return baseMapper.updateById(videoLibrary);
    }

    /**
     * id查询数据
     *
     * @param id id
     * @return VideoLibrary
     */
    @Override
    public VideoLibrary findById(Long id) {
        return baseMapper.selectById(id);
    }
}
