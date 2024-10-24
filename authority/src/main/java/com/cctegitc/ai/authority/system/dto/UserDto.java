package com.cctegitc.ai.authority.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserDto
 * @Description:
 * @Authour:yrc
 * @Data:2022/2/24 16:32
 * @Version:1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long userId;

    private String userName;

    private String nickName;

    private String empNumber;

    private String email;

    private String phoneNumber;

    private String sex;

    private Long deptId;

    private Long[] postIds;

}
