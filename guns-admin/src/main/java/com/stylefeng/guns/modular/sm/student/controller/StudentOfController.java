package com.stylefeng.guns.modular.sm.student.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.sm.student.entity.StudentEntity;
import com.stylefeng.guns.modular.sm.student.service.StudentService;
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
@RequestMapping("/studentOf")
public class StudentOfController extends BaseController {
    private String PREFIX = "/system/sm/student/";

    private static Logger LOG = LoggerFactory.getLogger(StudentOfController.class);

    @Autowired
    private StudentService studentService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"student.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<StudentEntity> studentList = studentService.selectList(null);
        LOG.info("获取我的项目列表-->{}",studentList);
        return studentList;
    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/student_add")
    public String studentAdd() {

        return PREFIX + "student_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/student_update/{studentId}")
    public String Update(@PathVariable String studentId, Model model) {
        StudentEntity student = studentService.selectById(studentId);
        model.addAttribute("item", student);
        LogObjectHolder.me().set(student);
        return PREFIX + "student_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(StudentEntity student) {
        student.setId(UUID.randomUUID().toString().substring(0,32).replaceAll("-",""));

        studentService.insert(student);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
   @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String id) {
        studentService.deleteById(id);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(StudentEntity student) {
        studentService.updateById(student);
        return SUCCESS_TIP;
    }
}
