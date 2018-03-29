package com.zhangxq.modules.common.dao;

import com.zhangxq.modules.common.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/3/27 11:18
 * @description:
 */
@Component
public class BaseRedisDaoImpl<E extends BaseEntity> implements BaseRedisDao<E> {
    /**
     * Redis模板对象
     */
    @Autowired
    private RedisTemplate<String, E> redisvalue;

    @Autowired
    private RedisTemplate<String, List<E>> redisList;

    public void cacheSet(E e, int timeOut) {
        cacheSet(e.getId(), e, timeOut);
    }

    public void cacheSet(String key, E e, int timeOut) {
        redisvalue.opsForValue().set(key, e, timeOut, TimeUnit.SECONDS);
    }

    public E cacheGet(String key) {
        return redisvalue.opsForValue().get(key);
    }

    public E cacheGet(E e) {
        return cacheGet(e.getId());
    }

    public void cacheListSet(String key, int timeOut, List<E> list) {
        redisList.opsForValue().set(key, list, timeOut, TimeUnit.SECONDS);
    }

    public List<E> cacheListGet(String key) {
        return redisList.opsForValue().get(key);
    }

}
