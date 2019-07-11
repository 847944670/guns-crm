package com.stylefeng.guns.modular.pm.function.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.function.service.FunctionService;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.module.service.ModuleService;
import com.stylefeng.guns.modular.pm.project.controller.ProjectController;
import com.stylefeng.guns.modular.pm.util.PmOfUtil;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

/**
 * @author sunwei
 * @date 2019/5/29
 */
@Controller
@RequestMapping("/funOfModule")
public class FunctionController extends BaseController {
    private String PREFIX = "/system/pm/function/";

    private static Logger LOG = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private FunctionService functionService;
    @Autowired
    private ModuleService moduleService;

    @RequestMapping("")
    public String index(){
        return PREFIX+"function.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String functionName) {
        if(functionName==null){

            List<Function> functions = functionService.selectList(null);
            LOG.info("获取我的模块功能列表-->{}",functions);
            return functions;
        }else {
            LOG.info("查询参数：{}",functionName);
            List<Function> functions = functionService.selectListCondition(functionName);
            LOG.info("获取我的模块功能列表-->{}",functions);
            return functions;
        }

    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/funOfModule_add")
    public String functionAdd( Model model) {
        model.addAttribute("moduleList", moduleService.selectList(null));
        return PREFIX + "function_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/funOfModule_update/{funOfModuleId}")
    public String projectUpdate(@PathVariable String funOfModuleId, Model model) {
        Function function = functionService.selectById(funOfModuleId);
        model.addAttribute("item",function);
        model.addAttribute("moduleList", moduleService.selectList(null));
        LogObjectHolder.me().set(function);
        return PREFIX + "function_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Function function) {
        function.setId(UUID.randomUUID().toString().substring(0, 31).replaceAll("-","" ));
        Map<String,String> map=new HashMap<>();
        PmOfUtil.setDefaultValue(function);
        getModuleOfProjectInfo(function);
        String startTime = function.getStartTime();
        String endTime = function.getEndTime();
        if(!DateUtil.compareDate(startTime, endTime)){
            long daySub = DateUtil.getDaySub(startTime, endTime);
            Module module = moduleService.selectById(function.getModuleId());
            if(PmOfUtil.compareTime(module.getTime(),daySub+"")){
                if(PmOfUtil.compareStartTime(module.getStartTime(), function.getStartTime())){
                    function.setTime(daySub+"天");
                    String content= HtmlUtils.htmlUnescape(function.getMarks());
                    function.setMarks(content);
                    functionService.insert(function);
                    map.put("msg", "200");
                }else {
                    map.put("msg", "201");
                }

            }else {
                map.put("msg","202" );
            }
        }else {
            map.put("msg", "203");
        }
        return map;
    }

    private void getModuleOfProjectInfo(Function function) {
        Assert.notNull(function, "[Function]类为null");
        String moduleId = function.getModuleId();
        Module module = moduleService.selectById(moduleId);
        //获取到的项目名称
        String projectName = module.getProjectName();
        //获取到的模块名称
        String moduleName = module.getModuleName();
        //获取到的项目ID
        String projectId = module.getProjectId();
        function.setProjectId(projectId);
        function.setProjectName(projectName);
        function.setModuleName(moduleName);

    }

    /**
     * 删除我的项目模块
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String funOfModuleId) {
        functionService.deleteById(funOfModuleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Function function) {
        Map<String,String> map=new HashMap<>();
        PmOfUtil.setDefaultValue(function);
        getModuleOfProjectInfo(function);
        String startTime = function.getStartTime();
        String endTime = function.getEndTime();
        if(!DateUtil.compareDate(startTime, endTime)){
            long daySub = DateUtil.getDaySub(startTime, endTime);
            Module module = moduleService.selectById(function.getModuleId());
            if(PmOfUtil.compareTime(module.getTime(),daySub+"")){
                if(PmOfUtil.compareStartTime(module.getStartTime(), function.getStartTime())){
                    function.setTime(daySub+"天");
                    String content= HtmlUtils.htmlUnescape(function.getMarks());
                    function.setMarks(content);
                    functionService.insert(function);
                    map.put("msg", "200");
                }else {
                    map.put("msg", "201");
                }

            }else {
                map.put("msg","202" );
            }
        }else {
            map.put("msg", "203");
        }
        return map;
    }


}