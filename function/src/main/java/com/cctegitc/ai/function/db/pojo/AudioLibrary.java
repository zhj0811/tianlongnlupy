package com.cctegitc.ai.function.db.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("audio_library")
@ApiModel(value = "AudioLibrary对象", description = "音频文件库")
public class AudioLibrary implements Serializable {

    @ApiModelProperty(value = "主键，音频编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "音频名称")
    @TableField("audio_name")
    private String audioName;

    @ApiModelProperty(value = "音频名称")
    @TableField("audio_new_name")
    private String audioNewName;

    @ApiModelProperty(value = "音频大小")
    @TableField("audio_size")
    private String audioSize;

    @ApiModelProperty(value = "上传时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("upload_time")
    private Date uploadTime;

    @ApiModelProperty(value = "上传账号")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty(value = "说明")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("creat_time")
    private Date creatTime;


    @TableField(exist = false)
    private Integer limit;
    @TableField(exist = false)
    private Integer page;

    public AudioLibrary(String wavFileName, String wavSize, String displayFileName) {
        this.audioName = wavFileName;
        this.audioSize = wavSize;
        this.audioNewName = displayFileName;
    }
}

