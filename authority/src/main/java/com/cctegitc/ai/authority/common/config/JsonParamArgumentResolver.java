package com.cctegitc.ai.authority.common.config;

import com.alibaba.fastjson.JSONPath;
import com.cctegitc.ai.authority.common.annotation.JsonParam;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * 解析带自定义注解JsonParam的参数
 *
 * @author jiangyang
 * @date 2022-04-27 09:39:52
 */
public class JsonParamArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) (webRequest.getNativeRequest());
        BufferedReader br = request.getReader();
        String str, reqStr = "";
        while ((str = br.readLine()) != null) {
            reqStr += str;
        }
        return JSONPath.read(reqStr, "$." + parameter.getParameterAnnotation(JsonParam.class).value());
    }
}
