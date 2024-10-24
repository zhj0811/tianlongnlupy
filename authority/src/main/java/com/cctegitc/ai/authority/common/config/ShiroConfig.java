package com.cctegitc.ai.authority.common.config;

import com.cctegitc.ai.authority.common.matcher.RetryLimitHashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {


    /**
     * 配置密码比较器
     *
     * @return
     */
    @Bean("credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher(getCacheManager());
        return retryLimitHashedCredentialsMatcher;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    //DefaultWebSecurityManager
    //安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // userRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher);
        securityManager.setRealm(userRealm);
        //设置Ehcache缓存
        securityManager.setCacheManager(getCacheManager());
        //关闭session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    //Realm
    @Bean
    public UserRealm userRealm() {
        UserRealm UserRealm = new UserRealm();
        //配置自定义密码比较器
        UserRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());
        return UserRealm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    //配置Shiro整合Ehcache的CacheManager
    @Bean
    public EhCacheManager getCacheManager() {
        //创建shiro的cacheManager对象
        EhCacheManager ehCacheManager = new EhCacheManager();
        //创建Ehcache的cacheManager对象
        InputStream is = null;
        try {
            is = ResourceUtils.getInputStreamForPath("classpath:ehcache-shiro.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        net.sf.ehcache.CacheManager cacheManager = new net.sf.ehcache.CacheManager(is);
        //将Ehcache的cacheManager对象存入shiro的cacheManager对象中
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        LinkedHashMap<String, String> filterChainDefinitions = new LinkedHashMap<>();
        filterChainDefinitions.put("/websocket/**", "anon");
        filterChainDefinitions.put("/documentsLibrary/upload", "anon");
        filterChainDefinitions.put("/audio/upload", "anon");
        filterChainDefinitions.put("/videoLibrary/upload", "anon");
        filterChainDefinitions.put("/pictureLibrary/upload", "anon");
        filterChainDefinitions.put("/documentsLibrary/download", "anon");
        filterChainDefinitions.put("/user/login", "anon");
        filterChainDefinitions.put("/voicememo/add", "anon");
        filterChainDefinitions.put("/record/add", "anon");
        filterChainDefinitions.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitions);
        // 其他请求均要经过jwt过滤器
        HashMap<String, Filter> myFIleter = new HashMap<>();
        myFIleter.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(myFIleter);
        return shiroFilterFactoryBean;
    }
}
