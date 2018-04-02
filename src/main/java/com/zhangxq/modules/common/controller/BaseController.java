package com.zhangxq.modules.common.controller;

import com.zhangxq.modules.common.entity.BaseEntity;
import com.zhangxq.modules.common.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Quentin
 * @version: 1.0
 * @date: 2018/2/9 10:47
 * @description 基本控制器类
 */
public abstract class BaseController<E extends BaseEntity> {
    @Autowired
    private BaseService<E> baseService;

    @ModelAttribute(value = "e")
    public E get(E e) {

        if (StringUtils.isNotEmpty(e.getId())) {
            return baseService.get(e);
        }
        return e;
    }

    /**
     * 查询单条记录
     *
     * @param e     实体类
     * @param model
     * @return
     */
    @RequestMapping("/form")
    @ResponseBody
    public E form(@ModelAttribute("e") E e, Model model) {
        return e;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<E> list() {
        return baseService.findAllList();
    }

    /**
     * 更新一条记录
     *
     * @return
     */
    @RequestMapping("/save")
    public String save(@ModelAttribute(value = "e") E e, Model model) {
        boolean result = baseService.save(e);
        if (!result) {
            model.addAttribute(e);
            model.addAttribute("message", "更新数据失败");
            return getPresentPath() + "/form";
        }
        return "redirect:" + getPresentPath() + "/list";
    }

    /**
     * 删除一条数据
     *
     * @param e 要删除对象
     * @return
     */
    @RequestMapping("/delete")
    public String delete(@ModelAttribute("e")E e, Model model) {
        boolean result = baseService.delete(e);
        if (!result) {
            model.addAttribute("message","删除数据失败");
        }else {
            model.addAttribute("message","删除数据成功");
        }
        return "redirect:" + getPresentPath() + "/list";
    }

    /**
     * 获取当前类路径
     * @return
     */
    public abstract String getPresentPath();
}
