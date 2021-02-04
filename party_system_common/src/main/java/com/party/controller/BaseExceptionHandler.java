package com.party.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理..");
    }
    //自定义异常
    @ExceptionHandler(PartyException.class)
    @ResponseBody //为了返回数据
    public R error(PartyException e) {
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
