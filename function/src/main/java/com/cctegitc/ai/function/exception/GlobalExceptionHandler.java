package com.cctegitc.ai.function.exception;

import com.cctegitc.ai.authority.common.verifycode.exception.VerifyCodeCheckException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cctegitc.ai.function.feign.RestResult;

import javax.validation.ConstraintViolationException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public RestResult customException(CustomException e) {
        log.error("自定义异常：", e);
        Integer code = e.getCode();
        if (code != null) {
            return RestResult.error(e.getCode(), e.getMessage());
        } else {
            return RestResult.error(e.getMessage());
        }
    }

    @ExceptionHandler(com.cctegitc.ai.authority.common.exception.CustomException.class)
    public RestResult authorityException(com.cctegitc.ai.authority.common.exception.CustomException e) {
        log.error("权限服务自定义异常：", e);
        Integer code = e.getCode();
        if (code != null) {
            return RestResult.error(e.getCode(), e.getMessage());
        } else {
            return RestResult.error(e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public RestResult otherException(Exception e) {
        log.error("捕获到全局异常：", e);
        return RestResult.error("系统异常，请联系管理员");
    }

    @ExceptionHandler(VerifyCodeCheckException.class)
    public RestResult VerifyCodeCheckException(VerifyCodeCheckException e) {
        log.error("捕获到全局异常：", e);
        return RestResult.error("验证码输入有误");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestResult validationException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        log.error("validationException error:", exception);
        return RestResult.error(getErrorMsgs(result));
    }

    @ExceptionHandler(value = BindException.class)
    public RestResult bindExceptionHandle(BindException exception) {
        log.error("bindExceptionHandle error:", exception);
        BindingResult result = exception.getBindingResult();
        return RestResult.error(getErrorMsgs(result));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public RestResult constraintViolationExceptionHandle(ConstraintViolationException exception) {
        log.error("constraintViolationExceptionHandle error:", exception);
        return RestResult.error(exception.getMessage());
    }

    public String getErrorMsgs(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(error -> {
            log.error("field: " + error.getField() + ", msg:" + error.getDefaultMessage());
            errorMsg.append(error.getDefaultMessage()).append("! ");
        });
        return errorMsg.toString();
    }
}
