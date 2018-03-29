package com.zhangxq.modules.common.service;

import org.activiti.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Quentin
 * @version: 1.0
 * @date: 2018/03/28 00:06
 * @description
 */
@Service
public class BaseActivitiServiceImpl implements BaseActivitiService{
    Logger logger = LoggerFactory.getLogger(BaseActivitiService.class);
    @Autowired
    private ManagementService managementService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private FormService formService;

    public String startProcess(String proDefId){
        logger.info("---------------------------->>启动流程11111->>>>>>");
        String processInstanceId = runtimeService.startProcessInstanceById(proDefId).getProcessInstanceId();
        return processInstanceId;
    }

}
