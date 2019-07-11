package com.stylefeng.guns.modular.sm.grade.controller;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.sm.grade.entity.GradeEntity;
import com.stylefeng.guns.modular.sm.grade.service.GradeService;
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
@RequestMapping("/grade")
public class GradeController extends BaseController {
    private String PREFIX = "/system/sm/grade/";

    private static Logger LOG = LoggerFactory.getLogger(GradeController.class);

    @Autowired
    private GradeService gradeService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"grade.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<GradeEntity> gradeEntityList = gradeService.selectList(null);
        LOG.info("获取我的项目列表-->{}",gradeEntityList);
        return gradeEntityList;
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/grade_add")
    public String gradeAdd() {

        return PREFIX + "grade_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/grade_update/{gradeId}")
    public String departmentUpdate(@PathVariable String gradeId, Model model) {
        GradeEntity gradeEntity = gradeService.selectById(gradeId);
        model.addAttribute("item", gradeEntity);
        LogObjectHolder.me().set(gradeEntity);
        return PREFIX + "grade_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(GradeEntity gradeEntity) {
        gradeEntity.setId(UUID.randomUUID().toString().substring(0,32).replaceAll("-",""));
        gradeService.insert(gradeEntity);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
   @RequestMapping(value = "/delete")
   // @RequestMapping("/grade_delete/{gradeId}")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        gradeService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(GradeEntity gradeEntity) {
        gradeService.updateById(gradeEntity);
        return SUCCESS_TIP;
    }
}
