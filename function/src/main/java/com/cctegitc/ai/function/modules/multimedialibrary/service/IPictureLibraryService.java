package com.cctegitc.ai.function.modules.multimedialibrary.service;


import com.cctegitc.ai.function.db.pojo.PictureLibrary;
import com.cctegitc.ai.function.vo.pagefind.PictureLibraryVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-11-18
 */
public interface IPictureLibraryService {

    /**
     * 查询分页数据
     *
     * @param pictureLibrary 条件
     * @param current        当前页
     * @param size           大小
     * @return
     */
    PictureLibraryVo findByPage(PictureLibrary pictureLibrary, Integer current, Integer size);

    /**
     * 添加
     *
     * @param pictureLibrary
     * @return int
     */
    int add(PictureLibrary pictureLibrary);

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
     * @param pictureLibrary
     * @return int
     */
    int updateData(PictureLibrary pictureLibrary);

    /**
     * id查询数据
     *
     * @param id id
     * @return PictureLibrary
     */
    PictureLibrary findById(Long id);
}
