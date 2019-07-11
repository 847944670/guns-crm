package com.stylefeng.guns.modular.sm.departmentInfo.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.sm.departmentInfo.entity.DepartmentInfoEntity;
import com.stylefeng.guns.modular.sm.departmentInfo.service.DepartmentInfoService;
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
@RequestMapping("/departmentInfo")
public class DepartmentInfoController extends BaseController {
    private String PREFIX = "/system/sm/departmentInfo/";

    private static Logger LOG = LoggerFactory.getLogger(DepartmentInfoController.class);

    @Autowired
    private DepartmentInfoService departmentInfoService;

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
        List<DepartmentInfoEntity> departmentInfoList = departmentInfoService.selectList(null);
        LOG.info("获取我的系别列表-->{}",departmentInfoList);
        return departmentInfoList;
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/departmentInfo_add")
    public String departmentInfoAdd() {
        return PREFIX + "departmentInfo_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/departmentInfo_update/{departmentInfoId}")
    public String Update(@PathVariable String departmentInfoId, Model model) {
        DepartmentInfoEntity departmentInfo = departmentInfoService.selectById(departmentInfoId);
        model.addAttribute("item", departmentInfo);
        LogObjectHolder.me().set(departmentInfo);
        return PREFIX + "departmentInfo_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(DepartmentInfoEntity departmentInfo) {
        //随机插入Id,并截取
        departmentInfo.setId(UUID.randomUUID().toString().substring(0,32).replaceAll("-",""));
        departmentInfoService.insert(departmentInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
   @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        departmentInfoService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(DepartmentInfoEntity departmentInfo) {
        departmentInfoService.updateById(departmentInfo);
        return SUCCESS_TIP;
    }
}
