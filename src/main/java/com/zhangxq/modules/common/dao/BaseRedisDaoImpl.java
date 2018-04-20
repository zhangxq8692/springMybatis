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

    /**
     * 缓存一个对象
     *
     * @param e       对象
     * @param timeOut 过期失效时间
     */
    public void cacheSet(E e, int timeOut) {
        cacheSet(e.getId(), e, timeOut);
    }

    /**
     * 缓存一个字符串
     *
     * @param e       字符串
     * @param timeOut 过期失效时间
     */
    public void cacheSet(String key, E e, int timeOut) {
        redisvalue.opsForValue().set(key, e, timeOut, TimeUnit.SECONDS);
    }

    public E cacheGet(String key) {
        return redisvalue.opsForValue().get(key);
    }

    /**
     * 通过一个对象获取一个缓存对象
     * 对象必须是继承于BaseEntity
     *
     * @param e 对象
     * @return
     */
    public E cacheGet(E e) {
        return cacheGet(e.getId());
    }

    /**
     * 缓存一个集合
     *
     * @param key     键
     * @param timeOut BaseRedisDao接口TIME_常量值
     * @param list    集合
     */
    public void cacheListSet(String key, int timeOut, List<E> list) {
        redisList.opsForValue().set(key, list, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 获取一个集合
     *
     * @param key 键
     * @return 集合
     */
    public List<E> cacheListGet(String key) {
        return redisList.opsForValue().get(key);
    }

}
