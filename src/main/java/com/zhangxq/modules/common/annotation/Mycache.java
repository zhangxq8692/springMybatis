package com.zhangxq.modules.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/4/2 15:18
 * @description: 自定义缓存注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mycache {
    /**
     * 存取key值
     */
    String key() default "";

    /**
     * 是否设置数据过期时间（秒）
     */
    int timeout() default 0;
}
