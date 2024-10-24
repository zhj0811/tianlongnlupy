package com.cctegitc.ai.function.modules.multimedialibrary.service;


import com.cctegitc.ai.function.db.pojo.DocumentsLibrary;
import com.cctegitc.ai.function.vo.pagefind.DocumentsLibraryVo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author peip
 * @since 2022-11-18
 */
public interface IDocumentsLibraryService {

    /**
     * 查询分页数据
     *
     * @param documentsLibraryVo 条件
     * @param current            当前页
     * @param size               大小
     * @return
     */
    DocumentsLibraryVo findByPage(DocumentsLibrary documentsLibraryVo, Integer current, Integer size);

    /**
     * 添加
     *
     * @param documentsLibrary
     * @return int
     */
    int add(DocumentsLibrary documentsLibrary);

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
     * @param documentsLibrary
     * @return int
     */
    int updateData(DocumentsLibrary documentsLibrary);

    /**
     * id查询数据
     *
     * @param id id
     * @return DocumentsLibrary
     */
    DocumentsLibrary findById(Long id);
}
