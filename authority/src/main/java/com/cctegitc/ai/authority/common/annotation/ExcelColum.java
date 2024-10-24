package com.cctegitc.ai.authority.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColum {

    /**
     * 在excel中列的位置
     */
    int col();

    /**
     * 日期格式化
     */
    String format() default "";

    boolean forceString() default false;

    int scale() default 0;


}
