package com.cctegitc.ai.function.modules.multimedialibrary.service;


import com.cctegitc.ai.function.db.pojo.VideoLibrary;
import com.cctegitc.ai.function.vo.pagefind.VideoLibraryVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-11-18
 */
public interface IVideoLibraryService {

    /**
     * 查询分页数据
     *
     * @param videoLibrary 条件
     * @param current      当前页
     * @param size         大小
     * @return
     */
    VideoLibraryVo findByPage(VideoLibrary videoLibrary, Integer current, Integer size);

    /**
     * 添加
     *
     * @param videoLibrary
     * @return int
     */
    int add(VideoLibrary videoLibrary);

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
     * @param videoLibrary
     * @return int
     */
    int updateData(VideoLibrary videoLibrary);

    /**
     * id查询数据
     *
     * @param id id
     * @return VideoLibrary
     */
    VideoLibrary findById(Long id);
}
