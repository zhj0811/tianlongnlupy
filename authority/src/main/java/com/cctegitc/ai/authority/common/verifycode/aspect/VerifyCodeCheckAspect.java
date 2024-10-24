//package com.cctegitc.ai.authority.common.verifycode.aspect;
//
//import cn.hutool.core.util.StrUtil;
//import com.cctegitc.ai.authority.common.constant.Constants;
//import com.cctegitc.ai.authority.common.verifycode.exception.VerifyCodeCheckException;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 验证码验证切面
// *
// * @FileName VerifyCodeCheckAspect
// * @Author TianCheng
// * @Date 2021/7/8 15:15
// * @Modifier
// * @ModifiedDate
// * @Description 功能描述
// * @See
// **/
//@Aspect
//@Component
//public class VerifyCodeCheckAspect {
//
//    @Pointcut("@annotation(com.cctegitc.ai.authority.common.verifycode.annotation.VerifyCodeCheck)")
//    public void pointCut() {
//
//    }
//
//    @Before("pointCut()")
//    public void check(JoinPoint joinPoint) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String verifyCode = (String) request.getSession().getAttribute(Constants.RANDOMCODEKEY);
//        if (StrUtil.isEmpty(verifyCode)) {
//            //获取自动生成的图片验证码信息
//            verifyCode = (String) request.getServletContext().getAttribute(Constants.RANDOMCODEKEY);
//        }
//        //获取前段页面实际输入传回的验证码信息
//        String headerCode = request.getParameter("verifyCode");
//        if (!verifyCode.equalsIgnoreCase(headerCode)) {
//            throw new VerifyCodeCheckException("验证码错误");
//        }
//    }
//}
