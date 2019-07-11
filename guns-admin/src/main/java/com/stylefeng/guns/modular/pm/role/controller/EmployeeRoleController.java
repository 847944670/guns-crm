package com.stylefeng.guns.modular.pm.role.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.pm.department.controller.DepartmentController;
import com.stylefeng.guns.modular.pm.role.entity.EmployeeRole;
import com.stylefeng.guns.modular.pm.role.service.EmployeeRoleService;
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
@RequestMapping("/employeeRole")
public class EmployeeRoleController extends BaseController {
    private String PREFIX = "/system/pm/employeeRole/";

    private static Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private EmployeeRoleService employeeRoleService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"employeeRole.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<EmployeeRole> employeeRole = employeeRoleService.selectList(null);
        LOG.info("获取我的项目列表-->{}",employeeRole);
        return employeeRole;
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/employeeRole_add")
    public String employeeRoleAdd() {

        return PREFIX + "employeeRole_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/employeeRole_update/{employeeRoleId}")
    public String employeeRoleUpdate(@PathVariable Integer employeeRoleId, Model model) {
        EmployeeRole employeeRole = employeeRoleService.selectById(employeeRoleId);
        model.addAttribute("item",employeeRole);
        LogObjectHolder.me().set(employeeRole);
        return PREFIX + "employeeRole_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EmployeeRole employeeRole) {

        employeeRoleService.insert(employeeRole);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer employeeRoleId) {
        employeeRoleService.deleteById(employeeRoleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EmployeeRole employeeRole) {
        employeeRoleService.updateById(employeeRole);
        return SUCCESS_TIP;
    }

}