package com.zhangxq.modules.common.service;

import com.zhangxq.modules.common.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

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
public class BaseRedisServiceImpl<E extends BaseEntity> implements BaseRedisService<E> {
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
        ValueOperations<String, E> ops = redisvalue.opsForValue();
        ops.set(key, e, timeOut, TimeUnit.SECONDS);
    }

    public E cacheGet(String key) {
        ValueOperations<String, E> ops = redisvalue.opsForValue();
        return ops.get(key);
    }

    public E cacheGet(E e) {
        return cacheGet(e.getId());
    }

    public void cacheListSet(String key, int timeOut, List<E> list) {
        ValueOperations<String, List<E>> ops = redisList.opsForValue();
        ops.set(key, list, timeOut, TimeUnit.SECONDS);
    }

    public List<E> cacheListGet(String key) {
        ValueOperations<String, List<E>> ops = redisList.opsForValue();
        return ops.get(key);
    }
}
