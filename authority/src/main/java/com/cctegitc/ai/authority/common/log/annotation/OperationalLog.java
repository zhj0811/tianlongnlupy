package com.cctegitc.ai.authority.common.log.annotation;

import java.lang.annotation.*;

/**
 * @author Hao
 * @create 2017-03-29
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationalLog {
    /**
     * 模块
     */
    String module();

    /**
     * 描述
     */
    String description();
}
