package com.zhangxq.modules.common.tool;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(LoggerBean.class);
    public LoggerBean() {
        logger.info("AOP功能注入初始化");
    }
    @Before("execution(* com.zhangxq.modules.common.controller.*.list(..))")
    public void logController(){
        logger.info("AOP功能注入");
    }
}
