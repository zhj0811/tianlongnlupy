package com.cctegitc.ai.function.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jiangyang
 * @date 2021-12-30 16:31:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {

    private Long userId;

    private String userName;

    private String password;

    private String nickName;

    private String empNumber;

    private String email;

    private String phoneNumber;

    private String sex;

    private Long deptId;

    private Long[] postIds;


}
