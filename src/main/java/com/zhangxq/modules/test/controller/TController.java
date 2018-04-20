package com.zhangxq.modules.test.controller;

import com.zhangxq.modules.common.controller.BaseController;
import com.zhangxq.modules.test.entity.Tentity;
import com.zhangxq.modules.test.service.AbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:47
 * @description:
 */
@Controller
@RequestMapping("/test")
public class TController extends BaseController<Tentity> {
    @Autowired
    private AbService abService;

    @ResponseBody
    @RequestMapping("/start/{id}")
    public String start(@PathVariable("id") String proDefId){
        return abService.startProcess(proDefId);
    }
}
