package com.cctegitc.ai.function.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cctegitc.ai.function.db.dao.AudioLibraryMapper;
import com.cctegitc.ai.function.db.pojo.AudioLibrary;
import com.cctegitc.ai.function.modules.multimedialibrary.service.IAudioLibraryService;
import com.cctegitc.ai.function.vo.pagefind.AudioLibraryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AudioLibraryServiceImpl implements IAudioLibraryService {

    /**音频文件名*/
    public static final String AUDIO_NAME = "audio_name";

    /**备注*/
    public static final String REMARK = "remark";

    /**用户ID*/
    public static final String USER_ID = "user_id";

    @Resource
    private AudioLibraryMapper audioLibraryMapper;

    /**
     * 查看所有语音备忘录信息
     *
     * @return
     */
    @Override
    public List<AudioLibrary> findAll() {
        // 查询所有语音备忘录信息
        return audioLibraryMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 分页查看所有语音备忘录信息
     *
     * @return
     */
    @Override
    public AudioLibraryVo findByPage(AudioLibrary audioLibrary, Integer page, Integer limit) {
        // 创建 AudioLibraryVo 对象
        AudioLibraryVo audioLibraryVo = new AudioLibraryVo();
        // 创建 Page 对象，传入页码和每页显示数量
        Page<AudioLibrary> audioLibraryPage = new Page<>(page, limit);
        // 创建 QueryWrapper 对象
        QueryWrapper<AudioLibrary> queryWrapper = buildAudioLibraryQueryWrapper(audioLibrary);

        // 执行查询
        Page<AudioLibrary> result = audioLibraryMapper.selectPage(audioLibraryPage, queryWrapper);

        // 设置返回对象的值
        audioLibraryVo.setPage(page);
        audioLibraryVo.setLimit(limit);
        audioLibraryVo.setTotal(result.getTotal());
        audioLibraryVo.setItems(result.getRecords());
        return audioLibraryVo;
        // return FindByPageHelper.findByPage(AudioLibraryVo.class, queryWrapper, page, limit, audioLibraryMapper);
    }



    /**
     * 组装 QueryWrapper 对象
     *
     * @param audioLibrary
     * @return
     */
    private static QueryWrapper<AudioLibrary> buildAudioLibraryQueryWrapper(AudioLibrary audioLibrary) {
        QueryWrapper<AudioLibrary> queryWrapper = new QueryWrapper<>();

        // 如果音频名称不为空，则添加到 QueryWrapper 对象中
        if (StringUtils.isNotEmpty(audioLibrary.getAudioName())) {
            queryWrapper.like(AUDIO_NAME, audioLibrary.getAudioName());
        }
        // 如果备注不为空，则添加到 QueryWrapper 对象中
        if (StringUtils.isNotEmpty(audioLibrary.getRemark())) {
            queryWrapper.eq(REMARK, audioLibrary.getRemark());
        }
        // 如果用户 id 不为空，则添加到 QueryWrapper 对象中
        if (StringUtils.isNotEmpty(audioLibrary.getUserId())) {
            queryWrapper.eq(USER_ID, audioLibrary.getUserId());
        }
        return queryWrapper;
    }

    /**
     * 新增语音备忘录信息
     *
     * @param audioLibrary
     * @return
     */
    @Override
    public int add(AudioLibrary audioLibrary) {
        audioLibrary.setCreatTime(new Date());
        audioLibrary.setUploadTime(new Date());
        // 调用 Mapper 层的 insert 方法插入数据,返回插入的行数
        return audioLibraryMapper.insert(audioLibrary);
    }

    /**
     * 更新语音备忘录信息
     *
     * @param audioLibrary
     * @return
     */
    @Override
    public int updateData(AudioLibrary audioLibrary) {
        audioLibrary.setUploadTime(new Date());
        //调用 Mapper 层的 updateById 方法更新数据,返回更新的行数
        return audioLibraryMapper.updateById(audioLibrary);
    }

    /**
     * 删除当前语音备忘录信息
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        // 调用 Mapper 层的 deleteById 方法删除数据,返回删除的行数
        return audioLibraryMapper.deleteById(id);
    }

    /**
     * 通过id查询语音备忘录信息
     *
     * @param id
     * @return
     */
    @Override
    public AudioLibrary findById(Long id) {
        // 调用 Mapper 层的 selectById 方法查询数据
        return audioLibraryMapper.selectById(id);
    }
}
