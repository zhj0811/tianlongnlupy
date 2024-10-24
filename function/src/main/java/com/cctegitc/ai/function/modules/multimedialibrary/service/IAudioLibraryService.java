package com.cctegitc.ai.function.modules.multimedialibrary.service;


import com.cctegitc.ai.function.db.pojo.AudioLibrary;
import com.cctegitc.ai.function.vo.pagefind.AudioLibraryVo;

import java.util.List;

public interface IAudioLibraryService {
    /**
     * 查看所有音频文件
     *
     * @return
     */
    List<AudioLibrary> findAll();

    /**
     * 分页查看所有音频文件信息
     *
     * @return
     */
    AudioLibraryVo findByPage(AudioLibrary audioLibrary, Integer page, Integer limit);

    /**
     * 新增音频文件信息
     *
     * @param audioLibrary
     * @return
     */
    int add(AudioLibrary audioLibrary);

    /**
     * 更新音频文件信息
     *
     * @param audioLibrary
     * @return
     */
    int updateData(AudioLibrary audioLibrary);

    /**
     * 删除当前音频文件信息
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 通过id查询音频文件信息
     *
     * @param id
     * @return
     */
    AudioLibrary findById(Long id);
}
