package com.zhangxq.modules.common.service;

import com.zhangxq.modules.common.entity.BaseEntity;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:48
 * @description:
 */

public interface BaseService<E extends BaseEntity> {
    /**
     * 获取单条数据
     * @param e
     * @return
     */
    E get(E e);

    /**
     * 查询所有记录
     * @return
     */
    List<E> findAllList();

    /**
     * 更新或添加一条记录
     * @return 返回结果
     */
    boolean save(E e);

    /**
     * 删除一条数据
     * @param e 要删除对象
     * @return  返回结果
     */
    boolean delete(E e);

}
