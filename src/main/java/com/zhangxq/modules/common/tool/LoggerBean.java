package com.zhangxq.modules.common.tool;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/3/1 15:28
 * @description:
 */
@Component
@Aspect
public class LoggerBean {
    public LoggerBean() {
        System.out.println("loggerBean加载");
    }
    @Before("execution(* com.zhangxq.modules.common.controller.*.list(..))")
    public void logController(){
        System.out.println("AOP功能注入");
    }
}
