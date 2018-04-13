package com.zhangxq.modules.common.tool;


import com.zhangxq.modules.common.annotation.Mycache;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.ClassSignature;

import java.util.Collection;
import java.util.concurrent.TimeUnit;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/4/2 14:36
 * @description: 缓存注解拦截处理器
 */
@Component
@Aspect
public class CacheProcess {
    /**
     * 此处应该弄成接口对象，以方便扩展
     */
    @Autowired
    private RedisTemplate<String, Object> objectBaseRedisDao;

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.zhangxq.modules.common.annotation.Mycache)")
    public void pointcut() {
    }

    /**
     * 处理切入点方法
     *
     * @param joinPoint 切入点
     * @return 切入点方法返回的数据
     */
    @Around("pointcut()")
    public Object before(ProceedingJoinPoint joinPoint) {

        // 获取对象签名，并转化为方法签名，
        MethodSignature m = (MethodSignature) joinPoint.getSignature();

        // 通过方法签名，获取方法注解
        Mycache annotation = m.getMethod().getAnnotation(Mycache.class);

        // 获取注解的key值
        String key = annotation.key();
        if (!StringUtils.isNoneBlank(key)) {
            //创建一个唯一key键
            key = joinPoint.getTarget().getClass()      //获取目标对象class对象
                    .getSimpleName()                    //获取class类名称，不是全路径名称
                    .concat("::").concat(m.getMethod().getName());      //追加目标方法名称
        }

        // 获取注解的超时时间
        int timeout = annotation.timeout();

        // 获取redis模板操作对象
        ValueOperations<String, Object> ops = objectBaseRedisDao.opsForValue();

        // 从redis中获取key键对应的数据
        Object result = ops.get(key);

        /*
        判断缓存数据是否存在，存在直接返回数据，不存在调用目标方法从数据库获取数据
        从缓存拿数据存在就存在，不需要做其它判断
        */
        if (result == null) {
            synchronized (this) {
                if (result == null) {
                    try {
                        // 执行拦截的方法来获取数据
                        result = joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    // 返回的对象是否为有效对象
                    if (result instanceof Collection && result != null
                            && ((Collection) result).size() != 0 || result != null) {
                        if (timeout == 0) {
                            ops.set(key, result);  // 不设置过期时间
                        } else {
                            ops.set(key, result, timeout, TimeUnit.SECONDS); // 设置过期时间(单位：秒)
                        }
                    }
                }
            }
        }
        return result;
    }

}
