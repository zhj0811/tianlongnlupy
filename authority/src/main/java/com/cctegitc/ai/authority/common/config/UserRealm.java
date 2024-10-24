package com.cctegitc.ai.authority.common.config;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.cctegitc.ai.authority.framework.web.service.SysPermissionService;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.common.utils.sign.JWTUtils;
import com.cctegitc.ai.authority.system.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
//自定义UserRealm
public class UserRealm extends AuthorizingRealm {
    private static final Logger LOG = LoggerFactory.getLogger(UserRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Autowired
    private SysUserServiceImpl userService;
    @Autowired
    private SysPermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOG.info("执行了=>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        String token = (String) subject.getPrincipal();
        String username = JWTUtils.getUsername(token);
        SysUser user = userService.selectUserByUserName(username);
        if (Objects.isNull(user)) {
            return info;
        }
        Set<String> lRole = permissionService.getRolePermission(user);
        if (lRole != null) {
            for (String role : lRole) {
                info.addRole(role);
            }
        }
        // 权限集合
        Set<String> lPermission = permissionService.getMenuPermission(user);
        if (lPermission != null) {
            for (String perm : lPermission) {
                info.addStringPermission(perm);
                LOG.info(perm);
            }
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        LOG.info("执行了=>DB1认证AuthenticationInfo");
        String tokenStr = (String) token.getCredentials();
        String username = Optional.ofNullable(JWTUtils.getUsername(tokenStr)).orElseThrow(() -> new AuthenticationException("非法token"));
        SysUser user = userService.selectUserByUserName(username);
        if (user == null) {
            //@DS注解失效，手动切换数据源
            DynamicDataSourceContextHolder.push("db2");
            user = userService.selectUserByDB2UserName(username);
            DynamicDataSourceContextHolder.poll();
            if (Objects.isNull(user)) {
                throw new UnknownAccountException();//抛出异常，UnknownAccountException
            }
        }
//        UserDto userDto=userService.findByName(username);
        //验证密码是否正确
//        if (JWTUtils.verify(tokenStr, username, userDto.getPassword())) {
//            log.info("登录成功");
//        } else {
//            throw new IncorrectCredentialsException("用户名密码错误");
//        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token.getCredentials(), token.getCredentials(), this.getName());
        return simpleAuthenticationInfo;
    }
}
