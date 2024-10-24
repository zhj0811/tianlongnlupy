package com.cctegitc.ai.authority.system.domain.vo;

import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jiangyang
 * @date 2021-12-30 16:24:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostUserVo {

    private Long postId;

    private String postName;

    private List<SysUser> userList;
}
