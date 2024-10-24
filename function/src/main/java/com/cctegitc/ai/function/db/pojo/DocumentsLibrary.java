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

/**
 * <p>
 *
 * </p>
 *
 * @author peip
 * @since 2022-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("documents_library")
@ApiModel(value = "DocumentsLibrary对象", description = "文档文件库")
public class DocumentsLibrary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文档文件名")
    @TableField("documents_name")
    private String documentsName;

    @ApiModelProperty(value = "服务器文档文件名")
    @TableField("documents_new_name")
    private String documentsNewName;

    @ApiModelProperty(value = "文档文件存储路径")
    @TableField("documents_path")
    private String documentsPath;

    @ApiModelProperty(value = "文档文件存储类型")
    @TableField("documents_type")
    private String documentsType;

    @ApiModelProperty(value = "文档大小")
    @TableField("documents_size")
    private String documentsSize;

    @ApiModelProperty(value = "上传时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("upload_time")
    private Date uploadTime;

    @ApiModelProperty(value = "上传账号")
    @TableField("user_id")
    private Integer userId;

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


}
