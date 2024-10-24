package com.cctegitc.ai.function.util.common.utils;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.common.utils.sign.JWTUtils;
import com.cctegitc.ai.authority.system.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class UserUtils {
    @Autowired
    private SysUserServiceImpl userService;

    public SysUser getSysUser(HttpServletRequest request) {
        String username = JWTUtils.getUsername(request.getHeader("token"));
        log.debug("username:{}", username);
        DynamicDataSourceContextHolder.push("db1");
        return userService.selectUserByUserName(username);
    }

}
