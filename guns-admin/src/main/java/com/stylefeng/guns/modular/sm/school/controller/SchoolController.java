package com.stylefeng.guns.modular.sm.school.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.sm.school.entity.SchoolEntity;
import com.stylefeng.guns.modular.sm.school.service.SchoolService;
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
@RequestMapping("/school")
public class SchoolController extends BaseController {
    private String PREFIX = "/system/sm/school/";

    private static Logger LOG = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"school.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<SchoolEntity> schoolList = schoolService.selectList(null);
        LOG.info("获取我的大学校园列表-->{}",schoolList);
        return schoolList;
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/school_add")
    public String schoolAdd() {

        return PREFIX + "school_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/school_update/{schoolId}")
    public String Update(@PathVariable String schoolId, Model model) {
        SchoolEntity school = schoolService.selectById(schoolId);
        model.addAttribute("item", school);
        LogObjectHolder.me().set(school);
        return PREFIX + "school_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(SchoolEntity school) {
        //随机插入Id,并截取
        school.setId(UUID.randomUUID().toString().substring(0,32).replaceAll("-",""));
        schoolService.insert(school);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
   @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        schoolService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(SchoolEntity school) {
        schoolService.updateById(school);
        return SUCCESS_TIP;
    }
}
