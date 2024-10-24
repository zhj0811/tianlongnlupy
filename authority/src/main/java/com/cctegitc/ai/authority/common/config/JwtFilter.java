package com.cctegitc.ai.authority.common.config;

import com.cctegitc.ai.authority.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Boolean isNew = ((HttpServletRequest) request).getSession().isNew();
        String token = ((HttpServletRequest) request).getHeader("token");
        if (((HttpServletRequest) request).getMethod().equalsIgnoreCase(RequestMethod.OPTIONS.name())) {
            return true;
        }
//        if(isNew){
//            throw new CustomException("会话过期，请重新登录");
//        }
//        if (StringUtils.isEmpty(token)) {
//            throw new CustomException("token不能为空");
//        }
        executeLogin(request, response);
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        if ("".equals(token) || token == null) {
           // dispatchErrorMsg("请登录后访问", request, response);
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e) {
          //  dispatchErrorMsg("token失效，请重新登录", request, response);
        }
        //  如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("登录失败");
        return super.onAccessDenied(request, response);
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
//        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
//        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
//        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
//            httpServletResponse.setStatus(HttpStatus.OK.value());
//            return false;
//        }
        return super.preHandle(request, response);
    }

    /**
     * 请求转发到错误处理controller
     *
     * @param msg      错误信息
     * @param request  ServletRequest
     * @param response ServletResponse
     * @author jiangyang
     * @date 2022-04-12
     */
    private void dispatchErrorMsg(String msg, ServletRequest request, ServletResponse response) {
        try {
//            request.getRequestDispatcher("/authenticationError/50008/" + msg).forward(request, response);
        } catch (Exception e) {
            log.error("dispatchErrorMsg error", e);
        }
    }

}
