package com.cctegitc.ai.authority.common.matcher;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.cctegitc.ai.authority.common.core.domain.entity.SysUser;
import com.cctegitc.ai.authority.common.utils.sign.JWTUtils;
import com.cctegitc.ai.authority.system.service.impl.SysUserServiceImpl;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

//登陆次数限制
@Component
public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher {

    private static final Logger LOG = LoggerFactory.getLogger(RetryLimitHashedCredentialsMatcher.class);

    @Autowired
    private SysUserServiceImpl userService;
    //声明缓存对象
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

/*
        @Override
        public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
            LOG.info("==============================");
            //记录错误次数
            int i=0;
            // 获取登录用户的用户名
            String username = JWTUtils.getUsername(token.getPrincipal().toString());
            LOG.info("username:{}",username);
            SysUser user = userService.selectUserByUserName(username);
            if(!("admin".equals(username))){
                //获取缓存对象
                AtomicInteger retryCount = passwordRetryCache.get(username);
                LOG.info("retryCount:{}",retryCount);
                if (retryCount == null) {
                    // 如果用户没有登陆过,登陆次数加1 并放入缓存
                    retryCount = new AtomicInteger(0);
                    passwordRetryCache.put(username, retryCount);
                }
                if (retryCount.incrementAndGet() > 3) {
                    // 如果用户登陆失败次数大于3次 抛出锁定用户异常  并修改数据库字段

                    if (user != null){
                        // 数据库字段 默认为 0  就是正常状态 所以 要改为1
                        user.setStatus("1");
                        userService.updateUser(user);
                        LOG.info("锁定用户" + user.getUserName());
                    }
                    // 抛出用户锁定异常
                    throw new LockedAccountException();
                }
            }
            // 判断用户账号和密码是否正确
//            UserDto userDto=userService.findByName(username);
//            final boolean matches = JWTUtils.verify(token.getPrincipal().toString(), username, userDto.getPassword());

            final boolean matches = JWTUtils.verify(token.getPrincipal().toString(), username, user.getPassword());
            if (matches) {
                // 如果正确,从缓存中将用户登录计数 清除
                passwordRetryCache.remove(username);
            }
            return matches;
        }*/


    //复写父类的登录认证方法，该方法由shiro的login方法在底层调用
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        LOG.info("==============================");
        //记录错误次数
        int i = 0;
        // 获取登录用户的用户名
        DynamicDataSourceContextHolder.push("db1");
        String username = JWTUtils.getUsername(token.getPrincipal().toString());
        LOG.info("username:{}", username);
        SysUser user = userService.selectUserByUserName(username);
        String password = "";
        if (Objects.isNull(user)) {
            DynamicDataSourceContextHolder.push("db2");
            user = userService.selectUserByDB2UserName(username);
            DynamicDataSourceContextHolder.poll();
            if (Objects.nonNull(user)) {
                password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            }
        } else {
            password = user.getPassword();
            if (!("sysadmin".equals(username))) {
                //获取缓存对象
                AtomicInteger retryCount = passwordRetryCache.get(username);
                LOG.info("retryCount:{}", retryCount);
                if (retryCount == null) {
                    // 如果用户没有登陆过,登陆次数加1 并放入缓存
                    retryCount = new AtomicInteger(0);
                    passwordRetryCache.put(username, retryCount);
                }
                if (retryCount.incrementAndGet() > 3) {
                    // 如果用户登陆失败次数大于3次 抛出锁定用户异常  并修改数据库字段

                    if (user != null) {
                        // 数据库字段 默认为 0  就是正常状态 所以 要改为1
                        user.setStatus("1");
                        userService.updateUser(user);
                        LOG.info("锁定用户" + user.getUserName());
                    }
                    // 抛出用户锁定异常
                    throw new LockedAccountException();
                }
            }
        }

        // 判断用户账号和密码是否正确
//            UserDto userDto=userService.findByName(username);
//            final boolean matches = JWTUtils.verify(token.getPrincipal().toString(), username, userDto.getPassword());

        final boolean matches = JWTUtils.verify(token.getPrincipal().toString(), username, password);
        if (matches) {
            // 如果正确,从缓存中将用户登录计数 清除
            passwordRetryCache.remove(username);
        }
        return matches;
    }

    /**
     * 根据用户名 解锁用户
     *
     * @param username
     * @return
     */
    public void unlockAccount(String username) {
        SysUser user = userService.selectUserByUserName(username);

        if (user != null) {
            // 修改数据库的状态字段为解锁
            user.setStatus("0");
            userService.updateUser(user);
            passwordRetryCache.remove(username);
        }
    }
}
