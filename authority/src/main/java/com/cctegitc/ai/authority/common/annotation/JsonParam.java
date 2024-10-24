package com.cctegitc.ai.authority.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于获取单个参数值
 *
 * @author jiangyang
 * @date 2022-04-27 09:28:57
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface JsonParam {
    String value();
}
