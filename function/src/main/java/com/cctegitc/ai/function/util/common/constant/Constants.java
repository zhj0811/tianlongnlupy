package com.cctegitc.ai.function.util.common.constant;

/**
 * 通用常量信息
 */
public class Constants {
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 防重提交 redis key
     */
    public static final String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 验证码有效期（分钟）
     */
    public static final Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌
     */
    public static final String TOKEN = "token";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    public static final String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = "sub";

    /**
     * 用户头像
     */
    public static final String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    public static final String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi://";

    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";
    public static final String LOCAL_IP = "http://192.168.20.176:";
    public static final String HOST = "8282";
    public final static int STATUS_OK = 200;
    public final static int STATUS_FAIL = 500;
    public final static String MESSAGE_OK = "success";
    public final static String MESSAGE_FAIL = "fail";
    public static final String password = "Swcpt@123";
    public static final String FILE_URL = LOCAL_IP + HOST + "/files/";
    public static final String MESSAGE_REPEAT = "保存失败,请检查数据是否重复";
    public static final String PASSWORD = "kd123456!";

    public static final String SAVE_SUCESS = "保存成功!";
    public static final String SAVE_FAILED = "保存失败!";

    public static final String SUCESS = "操作成功!";
    public static final String OPT_FAILED = "操作失败!";

    public static final String UPDATE_SUCESS = ":修改成功!";
    public static final String UPDATE_FAILED = ":修改失败!";

    public static final String DELETE_SUCESS = "删除成功!";
    public static final String DELETE_FAILED = "删除失败!";

    public static final String FAIL_TO_GET = "获取失败!";

    public static final String GLOBAL_EXCEPTION_MESSAGE = "系统异常，请联系系统管理员";
    public static final String GLOBAL_CUSTOM_EXCEPTION_MESSAGE = "系统自定义异常\n";
    /**
     * 岗位停用状态
     */
    public static final String POST_STATUS_DISABLE = "1";

    /**
     * 岗位正常状态
     */
    public static final String POST_STATUS_ENABLE = "0";
}
