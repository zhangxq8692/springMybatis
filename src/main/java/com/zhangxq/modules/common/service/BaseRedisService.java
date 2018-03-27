package com.zhangxq.modules.common.service;

import com.zhangxq.modules.common.entity.BaseEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/3/27 11:11
 * @description:
 */
public interface BaseRedisService<E extends BaseEntity> {
    /**
     * 集合对象缓存过期时间
     */
    int TIME_OUT_LIST = 10;
    /**
     * 单个对象缓存过期时间
     */
    int TIME_OUT_OBJECT = 5;

    /**
     * 存放数据
     * 对象必须继承BaseEntity
     * 对应的键为对象ID
     *
     * @param e 对象
     * @return
     */
    public void cacheSet(E e, int timeout);

    /**
     * 存放数据
     *
     * @param key 键
     * @param e   对象
     * @return
     */
    public void cacheSet(String key, E e, int timeout);

    /**
     * 通过键获取缓存对象
     *
     * @param key 键
     * @return
     */
    public E cacheGet(String key);

    /**
     * 通过对象获取完整的对象
     * 参数对象中只存在key
     *
     * @param e 对象
     * @return
     */
    public E cacheGet(E e);

    /**
     * 存放一条list数据
     *
     * @param key     键
     * @param timeOut 超时时间（秒）
     * @param list    集合
     */
    public void cacheListSet(String key, int timeOut, List<E> list);

    /**
     * 获取一个list集合
     *
     * @param key 键
     * @return 集合
     */
    public List<E> cacheListGet(String key);
}

