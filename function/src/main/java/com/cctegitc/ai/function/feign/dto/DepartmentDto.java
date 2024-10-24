package com.cctegitc.ai.function.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Department
 * @Description:
 * @Authour:yrc
 * @Data:2022/2/24 13:54
 * @Version:1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门ID
     */
    private Long parentId;

    /**
     * 父部门名称
     */
    private String parentName;

}
