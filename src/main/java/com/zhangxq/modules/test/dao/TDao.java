package com.zhangxq.modules.test.dao;

import com.zhangxq.modules.common.annotation.Mybatis;
import com.zhangxq.modules.common.dao.BaseDao;
import com.zhangxq.modules.test.entity.Tentity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:49
 * @description:
 */
@Mybatis
public interface TDao extends BaseDao<Tentity> {}
