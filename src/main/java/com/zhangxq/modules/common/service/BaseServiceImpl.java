package com.zhangxq.modules.common.service;

import com.zhangxq.modules.common.dao.BaseDao;
import com.zhangxq.modules.common.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

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
public class BaseServiceImpl<E extends BaseEntity> implements BaseService<E> {

    /**
     * Dao层对象
     */
    @Autowired
    private BaseDao<E> dao;
    @Autowired
    private Jedis jedis;

    /**
     * 获取单条数据
     *
     * @param e
     * @return
     */
    public E get(E e) {
        E t = null;

        byte[] bytes = jedis.get(e.getKeyHash());
        if (bytes != null) {
            System.out.println("到Redis获取到数据__________________:" + e.byteToObject(bytes).toString());
            t = (E) e.byteToObject(bytes);
        } else {
            synchronized (BaseServiceImpl.class) {
                bytes = jedis.get(e.getKeyHash());
                if (bytes != null) {
                    System.out.println("到Redis获取到数据__________________:" + e.byteToObject(bytes).toString());
                    t = (E) e.byteToObject(bytes);
                } else {
                    t = dao.get(e);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    if (t != null) {
                        String set = jedis.setex(t.getKeyHash(), 1, t.objectToBytes());
                        System.out.println("保存到Redis__________________:" + set);
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
        List<E> list = dao.findAll();
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
                //创建并设置ID
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
}
