//package com.cctegitc.ai.authority.common.config;
//
//import com.cctegitc.ai.authority.common.interceptor.JWTInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////拦截器
////@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JWTInterceptor())
//                .addPathPatterns("/abgk/deleteById/**")
//                .addPathPatterns("/user/deleteByIds/**")
//                .excludePathPatterns("/user/findAll")
//                .excludePathPatterns("/user/login");
//    }
//}
