package com.cctegitc.ai.function.db.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;


@Data
@Entity
@Table(name = "file_entity")
@Accessors(chain = true)
@ApiModel(value = "多媒体文件名称映射")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 文件中文名
     */
    @Column(name = "original_FileName", nullable = false, length = 128)
    private String originalFileName;

    /**
     * md5命名
     */
    @Column(name = "md5_FileName", nullable = false, length = 128)
    private String md5FileName;

    /**
     * 文件路径
     */
    @Column(name = "file_path",nullable = false, length = 256)
    private String filePath;

    /**
     * 文件创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 文件创建人
     */
    @Column(name = "create_user_id",nullable = false)
    private String createUserId;
}
