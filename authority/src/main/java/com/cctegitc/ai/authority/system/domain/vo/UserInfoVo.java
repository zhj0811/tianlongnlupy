package com.cctegitc.ai.authority.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoVo {
    private Set<String> roles;
    private Set<String> permissions;
    private String introduction;
    private String avatar;
    private String name;
    private String nickName;
    private Long deptId;
    private Long userId;
    private String deptName;
    private String leader;
    private String chargeLeader;
    private String controlFlag;
}
