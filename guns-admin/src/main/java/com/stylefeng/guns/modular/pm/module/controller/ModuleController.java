package com.stylefeng.guns.modular.pm.module.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.function.service.FunctionService;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.module.service.ModuleService;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import com.stylefeng.guns.modular.pm.project.service.ProjectService;
import com.stylefeng.guns.modular.pm.util.PmOfUtil;
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
@RequestMapping("/module")
public class ModuleController extends BaseController {
    private String PREFIX = "/system/pm/module/";

    private static Logger LOG = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProjectService projectService;

    @Autowired
    private FunctionService functionService;
    @RequestMapping("")
    public String index(){
        return PREFIX+"module.html";
    }
    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String moduleName) {
        //当前用户登录id
        String id = ShiroKit.getUser().getId().toString();
        //当前用户登录部门id
        String deptId = ShiroKit.getUser().getDeptId().toString();
        if(moduleName==null){
            List<Module> modules = moduleService.selectMyModuleList(id,deptId);
            LOG.info("获取我的模块列表-->{}",modules);
            return modules;
        }else {
            LOG.info("查询参数：{}",moduleName);
            List<Module> modules = moduleService.selectListCondition(moduleName);
            LOG.info("获取我的模块列表-->{}",modules);
            return modules;
        }

    }
    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/module_add")
    public String moduleAdd(Model model) {
        List<Project> projects = projectService.selectList(null);
        model.addAttribute("projectList", projects);
        return PREFIX + "module_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/module_update/{moduleId}")
    public String moduleUpdate(@PathVariable String moduleId, Model model) {
        Module module = moduleService.selectById(moduleId);
        model.addAttribute("item",module);
        model.addAttribute("projectList", projectService.selectList(null));
        LogObjectHolder.me().set(module);
        return PREFIX + "module_edit.html";
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Module module) {
        module.setId(UUID.randomUUID().toString().substring(0, 31).replaceAll("-","" ));
        module.setCreateUserId(ShiroKit.getUser().getId().toString());
        module.setCreateUserDeptId(ShiroKit.getUser().getDeptId().toString());
        Map<String,String> map = new HashMap<>();
        PmOfUtil.setDefaultValue(module);
        getProjectName(module);
        String startTime = module.getStartTime();
        String endTime = module.getEndTime();
        if (!DateUtil.compareDate(startTime, endTime)){
            long daySub = DateUtil.getDaySub(startTime, endTime);
            Project project = projectService.selectById(module.getProjectId());
            if (PmOfUtil.compareTime(project.getTime(),daySub+"")){
                if(PmOfUtil.compareStartTime(project.getStartTime(),module.getStartTime())){
                    module.setTime(daySub+"天");
                    String content= HtmlUtils.htmlUnescape(module.getMarks());
                    module.setMarks(content);
                    moduleService.insert(module);
                    map.put("msg", "200");
                }else {
                    map.put("msg", "201");
                }

            }else {
                map.put("msg", "202");
            }
        }else {
            map.put("msg", "203");
        }

        return map;
    }

    private void getProjectName(Module module) {
        String projectId = module.getProjectId();
        Project project = projectService.selectById(projectId);
        String projectName = project.getProjectName();
        module.setProjectName(projectName);
    }


    /**
     * 删除我的项目模块
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String moduleId) {
        List<Function> functionList= functionService.selectByModuleId(moduleId);
        if (functionList.size()>0){
            for (Function function:functionList) {
                if(function.getModuleId().equals(moduleId)){
                    functionService.deleteById(function.getModuleId());
                }else {
                    continue;
                }
            }
        }
        moduleService.deleteById(moduleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Module module) {
        Map<String,String> map = new HashMap<>();
        PmOfUtil.setDefaultValue(module);
        String startTime = module.getStartTime();
        String endTime = module.getEndTime();
        if (!DateUtil.compareDate(startTime, endTime)){
            long daySub = DateUtil.getDaySub(startTime, endTime);
            Project project = projectService.selectById(module.getProjectId());
            if (PmOfUtil.compareTime(project.getTime(),daySub+"")){
                if(PmOfUtil.compareStartTime(project.getStartTime(),module.getStartTime())){
                    module.setTime(daySub+"天");
                    String content= HtmlUtils.htmlUnescape(module.getMarks());
                    module.setMarks(content);
                    moduleService.updateById(module);
                    map.put("msg", "200");
                }else {
                    map.put("msg", "201");
                }

            }else {
                map.put("msg", "202");
            }
        }else {
            map.put("msg", "203");
        }
        return map;
    }




}