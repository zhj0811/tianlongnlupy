package com.cctegitc.ai.function.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cctegitc.ai.function.db.pojo.FileEntity;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

@Mapper
public interface FileSaveMapper extends BaseMapper<FileEntity> {

    @Select("select md5FileName from file_entity where original_file_name = #{originalFileName}")
    String findByOriginalFileName(String originalFileName);

    @Select("select md5FileName from file_entity where original_file_name = #{originalFileName}")
    String findByMd5Name(String originalFileName);

}
