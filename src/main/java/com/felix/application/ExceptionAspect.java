package com.felix.application;

import com.felix.domain.base.RestCode;
import com.felix.domain.base.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author felix
 * @description 全局异常处理
 * @date 2023/6/27 16:57
 */
@ControllerAdvice
public class ExceptionAspect {

    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Result argumentExceptionHandler(Exception e) {
        System.out.println("参数错误:" + e);
        return Result.fail(RestCode.PARAM_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public Result exceptionHandler(Exception e) {
        System.out.println("系统错误:" + e);
        return Result.fail(RestCode.SYSTEM_ERROR, e.getMessage());
    }
}
