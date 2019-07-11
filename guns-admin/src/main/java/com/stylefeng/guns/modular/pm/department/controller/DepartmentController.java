package com.stylefeng.guns.modular.pm.department.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.pm.department.entity.DepartmentEntity;
import com.stylefeng.guns.modular.pm.department.service.DepartmentService;
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

/**
 * @author sunwei
 * @date 2019/5/28
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    private String PREFIX = "/system/pm/department/";

    private static Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"departmentInfo.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<DepartmentEntity> departments = departmentService.selectList(null);
        LOG.info("获取我的项目列表-->{}",departments);
        return departments;
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/department_add")
    public String departmentAdd() {

        return PREFIX + "department_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/department_update/{departmentId}")
    public String departmentUpdate(@PathVariable Integer departmentId, Model model) {
        DepartmentEntity department = departmentService.selectById(departmentId);
        model.addAttribute("item",department);
        LogObjectHolder.me().set(department);
        return PREFIX + "department_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DepartmentEntity department) {

        departmentService.insert(department);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer departmentId) {
        departmentService.deleteById(departmentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DepartmentEntity departmentEntity) {
        departmentService.updateById(departmentEntity);
        return SUCCESS_TIP;
    }
}