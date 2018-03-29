package com.zhangxq.modules.test.service;

import com.zhangxq.modules.common.service.BaseActivitiService;
import com.zhangxq.modules.common.service.BaseServiceImpl;
import com.zhangxq.modules.test.entity.Tentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:48
 * @description:
 */
@Service
@Transactional
public class TService extends BaseServiceImpl<Tentity> implements AbService{
    @Autowired
    private BaseActivitiService act;
    public String startProcess(String proDefId){
        return act.startProcess(proDefId);
    }

}
