package com.cctegitc.ai.authority.framework.web.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.system.service.ISysMenuService;
import com.cctegitc.ai.authority.system.service.ISysRoleService;
import com.cctegitc.ai.authority.system.service.impl.SysMenuServiceImpl;
import com.cctegitc.ai.authority.system.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 *
 */
@Component
public class SysPermissionService {
    @Autowired
    private SysRoleServiceImpl roleService;

    @Autowired
    private SysMenuServiceImpl menuService;

    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysUser user) {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            roles.add("sysadmin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getUserId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add("*:*:*");
        } else if ((user.getUserId() != null)) {
            perms.addAll(menuService.selectMenuPerms(user.getUserId(), null));
        } else {// 通过角色查询菜单权限
            perms.addAll(menuService.selectMenuPerms(null, "anbiao"));
        }
        return perms;
    }
}
