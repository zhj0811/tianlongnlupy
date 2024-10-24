package com.cctegitc.ai.authority.common.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.cctegitc.ai.authority.common.constant.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }
        Map<String, Object> map = new HashMap<>();
        //获取请求头中的令牌
        String token = request.getHeader("token");
        System.out.println("token: " + token);
        try {
            //DecodedJWT verify = JWTUtils.verify(token);
            //验证令牌
            JWT.decode(token);
            //放行请求
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("data", "无效签名!");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("data", "token过期!");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("data", "token算法不一致!");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", "token无效!");
        }
        //设置状态
        map.put("message", Constants.MESSAGE_FAIL);
        //map转json
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
