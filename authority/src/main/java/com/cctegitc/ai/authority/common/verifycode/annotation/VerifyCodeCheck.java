package com.cctegitc.ai.authority.common.verifycode.annotation;

import java.lang.annotation.*;

/**
 * @author lenovo
 * 自定义注解：关于图片验证码的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerifyCodeCheck {
}
