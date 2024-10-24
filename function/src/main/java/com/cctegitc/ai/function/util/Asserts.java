package com.cctegitc.ai.function.util;


import com.cctegitc.ai.function.exception.CustomException;
import org.apache.commons.lang3.StringUtils;

/**
 * 自定义断言
 *
 * @author jiangyang
 * @date 2022-04-18 16:09:03
 */
public class Asserts {

    /**
     * 判断condition是否为真，不为真时抛出异常
     *
     * @param condition 判断条件
     * @param msg       msg 错误信息
     * @return void
     * @author jiangyang
     * @date 2022-04-18
     */
    public static void assertTrue(boolean condition, String msg) {
        if (!condition) {
            throw new CustomException(msg);
        }
    }

    /**
     * 判断condition是否为真，为真时抛出异常
     *
     * @param condition 判断条件
     * @param msg       msg 错误信息
     * @return void
     * @author jiangyang
     * @date 2022-04-18
     */
    public static void assertFalse(boolean condition, String msg) {
        if (condition) {
            throw new CustomException(msg);
        }
    }

    /**
     * 判断obj是否为空，为空时抛出异常
     *
     * @param obj 判断条件
     * @param msg msg 错误信息
     * @return void
     * @author jiangyang
     * @date 2022-04-18
     */
    public static void assertNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new CustomException(msg);
        }
    }

    /**
     * 判断str是为null或空字符串，为空时抛出异常
     *
     * @param str 判断条件
     * @param msg msg 错误信息
     * @return void
     * @author jiangyang
     * @date 2022-04-18
     */
    public static void assertNotBlank(String str, String msg) {
        if (StringUtils.isBlank(str)) {
            throw new CustomException(msg);
        }
    }
}
