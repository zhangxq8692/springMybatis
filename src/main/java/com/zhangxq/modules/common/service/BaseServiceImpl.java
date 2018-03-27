package com.zhangxq.modules.common.service;

import com.zhangxq.modules.common.dao.BaseDao;
import com.zhangxq.modules.common.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/10 9:50
 * @description:
 */
public class BaseServiceImpl<E extends BaseEntity> extends BaseRedisServiceImpl<E> implements BaseService<E> {

    /**
     * Dao层对象
     */
    @Autowired
    private BaseDao<E> dao;

    /**
     * 获取单条数据
     *
     * @param e
     * @return
     */
    public E get(E e) {
        // 从缓存中获取数据
        E t = cacheGet(e);
        // 数据不存在时处理
        if (t == null) {
            // 进入同步块
            synchronized (e.getId()) {
                // 在次判断是否有另一线程进入同步块
                if ((t = cacheGet(e)) == null) {
                    t = dao.get(e);
                    if (t != null) {
                        cacheSet(t, TIME_OUT_OBJECT);
                    }
                }
            }
        }
        return t;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<E> findAllList() {
        // 获取一个key
        String name = getKey("findAllList");
        // 通过key在缓存中查询
        List<E> list = cacheListGet(name);
        if (list == null) {
            synchronized (this) {
                if (list == null) {
                    list = dao.findAll();
                    if (list != null) {
                        cacheListSet(name, TIME_OUT_LIST, list);
                    }
                }
            }
        }

        if (!list.isEmpty()) {
            return list;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 更新或添加一条记录
     *
     * @return 返回结果数量
     */
    public boolean save(E e) {
        // 确保对象不能为NULL
        if (e != null) {
            // 判断对象是否为新数据，分别进行处理
            if (e.isNewRecord()) {      // 新对象
                // 创建并设置ID
                e.preInset();
                return (dao.insert(e)) != 0;
            } else {                    // 做更新处理
                e.preUpdate();
                return (dao.update(e)) != 0;
            }
        }
        return false;

    }

    /**
     * 删除一条数据
     *
     * @param e 要删除对象
     * @return 返回结果数量
     */
    public boolean delete(E e) {
        return (dao.delete(e)) != 0;
    }


    /**
     * 获取一个key
     *
     * @param suffix 追加的key字符串，如:方法名
     * @return key
     */
    private String getKey(String suffix) {
        // 获取当前类的类名
        String name = getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1) + "::" + suffix;
    }

}
