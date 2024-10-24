package com.cctegitc.ai.function.feign.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cctegitc.swcpt.function.feign.encode.CustomEncoder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cctegitc.ai.function.feign.annotation.FeignApi;

import cn.hutool.core.util.StrUtil;
import feign.Feign;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyang
 * @date 2022-01-12 16:26:11
 */
@Slf4j
@Configuration
@Order(-1)
public class FeignConfiguration implements ApplicationContextAware, BeanFactoryPostProcessor,
        RequestInterceptor {

    public static final String FEIGN_CONFIG_PREFIX = "feign.urls.";

    private String scanPath = "com.cctegitc.swcpt.function.feign.api";

    private ApplicationContext applicationContext;


    private String getUrlByServiceName(String serviceName) {
        Environment environment = applicationContext.getEnvironment();
        return environment.getProperty(FEIGN_CONFIG_PREFIX + serviceName);
    }


    public Feign.Builder getFeignBuilder() {
        Feign.Builder builder = Feign.builder().encoder(new CustomEncoder()).decoder(new JacksonDecoder())
                .options(new Request.Options(10000, 10000)).retryer(new Retryer.Default(5000, 5000 * 2, 1))
                .requestInterceptor(this);
        return builder;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request =
                    ((ServletRequestAttributes) requestAttributes).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isEmpty(token)) {
                token = request.getHeader("X-Token");
            }
            if (StrUtil.isNotEmpty(token)) {
                requestTemplate.header("token", token);
            }
        }
    }

    public List<String> scan(String path) {
        ScanResult result =
                new FastClasspathScanner(path).matchClassesWithAnnotation(FeignApi.class, (Class<?> aClass) -> {
                }).scan();
        if (result != null) {
            return result.getNamesOfAllInterfaceClasses();
        }
        return new ArrayList<>();
    }

    /**
     * 注册Feign Client
     * 优先获取FeignApi注解上的srviceUrl作为远程调用的地址
     * 如果未指定，则根据注解上的serviceName属性从配置文件中获取
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 扫描带有FeignApi注解的类
        List<String> classes = scan(scanPath);
        Feign.Builder feignBuilder = getFeignBuilder();
        // 创建并注册bean
        if (CollectionUtils.isNotEmpty(classes)) {
            for (String clazzName : classes) {
                Class<?> targetClass;
                try {
                    targetClass = Class.forName(clazzName);
                    FeignApi feignApi = targetClass.getAnnotation(FeignApi.class);
                    String serviceName = feignApi.serviceName();
                    // 根据服务名获取配置中服务地址
                    String url = getUrlByServiceName(serviceName);
                    // 如果注解中指定了url,以指定的url为准
                    if (StringUtils.isNotBlank(feignApi.serviceUrl())) {
                        url = feignApi.serviceUrl();
                    }
                    Object target = feignBuilder.target(targetClass, url);
                    beanFactory.registerSingleton(targetClass.getName(), target);
                } catch (Exception e) {
                    log.error("注册FeignClient:{} 失败", clazzName, e);
                }
            }
        }
    }
}
