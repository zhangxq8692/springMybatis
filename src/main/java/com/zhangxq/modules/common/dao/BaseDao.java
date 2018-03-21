package com.zhangxq.modules.common.dao;

import com.zhangxq.modules.common.entity.BaseEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:49
 * @description:
 */
public interface BaseDao<E extends BaseEntity> {
    E get(E e);

    List<E> findAll();

    int update(E e);

    int insert(E e);

    int delete(E e);
}
