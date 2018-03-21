package com.zhangxq.modules.test.controller;

import com.zhangxq.modules.common.controller.BaseController;
import com.zhangxq.modules.test.entity.Tentity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getPresentPath() {
        return "/test";
    }
}
