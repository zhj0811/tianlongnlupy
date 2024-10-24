package com.cctegitc.ai.function.feign.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiangyang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignApi {
    /**
     * 调用的服务地址
     *
     * @return String
     */
    String serviceUrl() default "";

    /**
     * 调用的服务名称
     *
     * @return String
     */
    String serviceName();
}
