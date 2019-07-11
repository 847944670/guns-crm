package com.stylefeng.guns.modular.sm.classes.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.sm.classes.entity.ClassesEntity;
import com.stylefeng.guns.modular.sm.classes.service.ClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/classes")
public class ClassesController extends BaseController {
    private String PREFIX = "/system/sm/classes/";

    private static Logger LOG = LoggerFactory.getLogger(ClassesController.class);

    @Autowired
    private ClassesService classesService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"classes.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<ClassesEntity> classesList = classesService.selectList(null);
        LOG.info("获取我的大学校园列表-->{}",classesList);
        return classesList;
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/classes_add")
    public String classesAdd() {

        return PREFIX + "classes_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/classes_update/{classesId}")
    public String Update(@PathVariable String classesId, Model model) {
        ClassesEntity classes = classesService.selectById(classesId);
        model.addAttribute("item", classes);
        LogObjectHolder.me().set(classes);
        return PREFIX + "classes_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ClassesEntity classes) {
        //随机插入Id,并截取
        classes.setId(UUID.randomUUID().toString().substring(0,32).replaceAll("-",""));
        classesService.insert(classes);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
   @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        classesService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ClassesEntity classes) {
        classesService.updateById(classes);
        return SUCCESS_TIP;
    }
}
