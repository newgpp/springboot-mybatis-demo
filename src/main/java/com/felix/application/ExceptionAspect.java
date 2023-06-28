package com.felix.application;

import com.felix.domain.base.RestCode;
import com.felix.domain.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger log = LoggerFactory.getLogger(ExceptionAspect.class);

    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public Result paramErrorHandler(Exception e) {
        log.error("paramError: ", e);
        return Result.fail(RestCode.PARAM_ERROR, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {NullPointerException.class})
    public Result nullErrorHandler(Exception e) {
        log.error("systemErrorError: ", e);
        return Result.fail(RestCode.SYSTEM_ERROR, "空指针异常");
    }


    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public Result systemErrorHandler(Exception e) {
        log.error("systemError: ", e);
        return Result.fail(RestCode.SYSTEM_ERROR, "系统异常");
    }
}
