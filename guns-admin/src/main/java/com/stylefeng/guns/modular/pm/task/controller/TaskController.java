package com.stylefeng.guns.modular.pm.task.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.pm.attachment.entity.Attachment;
import com.stylefeng.guns.modular.pm.attachment.service.AttachmentService;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.function.service.FunctionService;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.module.service.ModuleService;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import com.stylefeng.guns.modular.pm.project.service.ProjectService;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;
import com.stylefeng.guns.modular.pm.task.service.TaskService;
import com.stylefeng.guns.modular.pm.taskofuser.service.EmailService;
import com.stylefeng.guns.modular.pm.taskofuser.service.TaskOfUserService;
import com.stylefeng.guns.modular.pm.util.PmOfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunwei
 * @date 2019/5/28
 * 任务管理
 */
@Controller
@RequestMapping("/myTask")
public class TaskController extends BaseController {

    private String PREFIX = "/system/pm/task/";

    private static Logger LOG= LoggerFactory.getLogger(TaskController.class);

    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private TaskOfUserService taskOfUserService;
    /**
     * 返回我的任务页面
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(){

        return PREFIX+"task.html";
    }
    @RequestMapping(value = "/myTestTask",method = RequestMethod.GET)
    public String testIndex(){

        return PREFIX+"taskTest.html";
    }

    /**
     * 返回已完成的测试任务页面
     * @return
     */
    @RequestMapping(value = "/taskTestEnd",method = RequestMethod.GET)
    public String indexTaskTestIndex(){

        return PREFIX+"taskTestEnd.html";
    }
    /**
     * 返回已完成的测试任务页面
     * @return
     */
    @RequestMapping(value = "/operationTask",method = RequestMethod.GET)
    public String operationTaskIndex(){

        return PREFIX+"taskOperation.html";
    }

    /**
     * 查看测试任务详情页面
     * @return
     */
    @RequestMapping(value = "/taskTest_detail/{id}",method = RequestMethod.GET)
    public String indexTaskTestDetail(@PathVariable String id,Model model){
        TaskEntity taskEntity = taskService.selectById(id);
        model.addAttribute("item", taskEntity);
        LogObjectHolder.me().set(taskEntity);
        return PREFIX+"taskTest_edit.html";
    }
    /**
     * 返回正在进行中的任务页面
     * @return
     */
    @RequestMapping(value = "/taskProgress",method = RequestMethod.GET)
    public String indexProgress(){

        return PREFIX+"taskProgress.html";
    }
    @RequestMapping(value = "/taskTestOfDevelop",method = RequestMethod.GET)
    public String taskTestOfDevelopIndex(){

        return PREFIX+"taskTestOfDevelop.html";
    }

    @RequestMapping("/taskTest_develop")
    @ResponseBody
    public Object taskTestOfManager(){
        //存放提示信息的map
        HashMap<String, String> map = new HashMap<>();

        //获取当前登录用户id
        String userId = ShiroKit.getUser().getId().toString();
        //通过当前用户登录id获取当前用户任务
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        String deptId = ShiroKit.getUser().getDeptId().toString();
        ArrayList<TaskEntity> taskEntitieList=new ArrayList<>();

        for (Integer roleId:roleList) {
            //如果角色ID为项目经理(研发部)
            if(roleId==2 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目经理");
                List<TaskEntity> taskEntities = taskService.selectAllTestTask(userId,deptId);
                taskEntitieList.addAll(taskEntities);
                //如果角色ID为项目组长(研发组)
            }else if(roleId==3 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目组长");

                //项目组组长获取项目经理派送的任务和自己创建的任务
                List<TaskEntity> taskEntities= taskService.selectTestTaskGroup(userId,userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(研发组)
            }else if(roleId==6 && deptId.equals("25")||deptId.equals("30")) {
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(), "普通员工(研发)");
                String userid = ShiroKit.getUser().getId().toString();
                List<TaskEntity> taskEntities = taskService.selectDevelopTestTask(userid);
                if (taskEntities != null && taskEntities.size() > 0) {
                    LOG.info("获取我的项目任务列表-->{}", taskEntities);
                    taskEntitieList.addAll(taskEntities);

                } else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }

            }
        }

        return taskEntitieList;
    }
    @RequestMapping("/endOfTestTask")
    @ResponseBody
    public Object taskTestEnd(){
        //存放提示信息的map
        HashMap<String, String> map = new HashMap<>();
        //获取当前登录用户id
        String userId = ShiroKit.getUser().getId().toString();
        //通过当前用户登录id获取当前用户任务
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        String deptId = ShiroKit.getUser().getDeptId().toString();
        ArrayList<TaskEntity> taskEntitieList=new ArrayList<>();

        for (Integer roleId:roleList) {
            //如果角色ID为项目经理(研发部)
            if (roleId == 2 && deptId.equals("25")) {
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(), "项目经理");
                List<TaskEntity> taskEntities = taskService.selectTaskTestEndManager(deptId, userId);
                taskEntitieList.addAll(taskEntities);
                //如果角色ID为项目组长(研发组)
            } else if (roleId == 3 && deptId.equals("25")) {
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(), "项目组长");
                //项目组组长获取项目经理派送的任务和自己创建的任务
                List<TaskEntity> taskEntities = taskService.selectTaskTestEndGroup(userId, deptId, userId);
                if (taskEntities != null && taskEntities.size() > 0) {
                    LOG.info("获取我的项目任务列表-->{}", taskEntities);
                    taskEntitieList.addAll(taskEntities);

                } else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(研发组)
            } else if (roleId == 6 && deptId.equals("25") || deptId.equals("30")) {
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(), "普通员工(研发)");

                List<TaskEntity> taskEntities = taskService.selectTaskEndEmployee(userId);
                if (taskEntities != null && taskEntities.size() > 0) {
                    LOG.info("获取我的项目任务列表-->{}", taskEntities);
                    taskEntitieList.addAll(taskEntities);

                } else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }

                //如果角色ID为测试组组长(测试组)
            } else if (roleId == 7) {
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(), "测试组组长");
                //通过任务的测试状态来(已完成或未完成)和当前登录的用户ID来查询所有测试任务
                List<TaskEntity> taskEntities = taskService.selectAllTestTaskEnd(userId, deptId);
                if (taskEntities != null && taskEntities.size() > 0) {
                    LOG.info("获取我的项目任务列表-->{}", taskEntities);
                    taskEntitieList.addAll(taskEntities);

                } else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(测试组)
            } else if (roleId == 8) {
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(), "普通员工(测试)");
                //通过任务的指派人(研发组成员)所属的部门和指派人的id、测试状态来(已完成或未完成)来查询出当前已完成的测试任务和进行中的测试任务
                List<TaskEntity> taskEntities = taskService.selectMyTestTaskEnd(userId);
                if (taskEntities != null && taskEntities.size() > 0) {
                    LOG.info("获取我的项目任务列表-->{}", taskEntities);
                    taskEntitieList.addAll(taskEntities);

                } else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
            }
        }
        return taskEntitieList;
    }
    @RequestMapping("/progress")
    @ResponseBody
    public Object taskProgress(){

        //存放提示信息的map
        HashMap<String, String> map = new HashMap<>();

        //获取当前登录用户id
        String userId = ShiroKit.getUser().getId().toString();
        //通过当前用户登录id获取当前用户任务
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        String deptId = ShiroKit.getUser().getDeptId().toString();
        ArrayList<TaskEntity> taskEntitieList=new ArrayList<>();

        for (Integer roleId:roleList) {
            //如果角色ID为项目经理(研发部)
            if(roleId==2 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目经理");
                List<TaskEntity> taskEntities = taskService.selectTaskProgressManager(deptId,userId);
                taskEntitieList.addAll(taskEntities);
                //如果角色ID为项目组长(研发组)
            }else if(roleId==3 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目组长");

                //项目组组长获取项目经理派送的任务和自己创建的任务
                List<TaskEntity> taskEntities= taskService.selectTaskProgressGroup(userId,deptId,userId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(研发组)
            }else if(roleId==6 && deptId.equals("25")||deptId.equals("30")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(研发)");

                List<TaskEntity> taskEntities = taskService.selectTaskProgress(userId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }

                //如果角色ID为测试组组长(测试组)
            }else if (roleId==7){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"测试组组长");
                //通过任务的测试状态来(已完成或未完成)和当前登录的用户ID来查询所有测试任务
                List<TaskEntity> taskEntities = taskService.selectAllTestTask(userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(测试组)
            }else if(roleId==8){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(测试)");
                //通过任务的指派人(研发组成员)所属的部门和指派人的id、测试状态来(已完成或未完成)来查询出当前已完成的测试任务和进行中的测试任务
                List<TaskEntity> taskEntities = taskService.selectMyTestTask (userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为运维组组长(运维组)
            }else if(roleId==9){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"运维组组长");
                //通过任务的运维状态来(已完成或未完成)和当前登录的用户ID、测试状态来(已完成或未完成)来查询所有运维任务
                List<TaskEntity> taskEntities = taskService.selectAllOperationTask(userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(运维组)
            }else if(roleId==10){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(运维)");
                //通过任务的指派人(测试组成员)所属的部门和指派人的id、测试状态来(已完成或未完成)来查询出当前已完成的运维任务和进行中的运维任务
                List<TaskEntity> taskEntities = taskService.selectMyOperationTask (userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");

                }
            }else {
                map.put("msg", "当前用户未被指派任务");

            }
        }

        return taskEntitieList;
    }
    @RequestMapping("/end")
    @ResponseBody
    public Object endTask(){
        //存放提示信息的map
        HashMap<String, String> map = new HashMap<>();

        //获取当前登录用户id
        String userId = ShiroKit.getUser().getId().toString();
        //通过当前用户登录id获取当前用户任务
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        String deptId = ShiroKit.getUser().getDeptId().toString();
        ArrayList<TaskEntity> taskEntitieList=new ArrayList<>();

        for (Integer roleId:roleList) {
            //如果角色ID为项目经理(研发部)
            if(roleId==2 && deptId.equals("25") ){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目经理");
                List<TaskEntity> taskEntities = taskService.selectTaskEndManager(deptId,userId);
                taskEntitieList.addAll(taskEntities);
                //如果角色ID为项目组长(研发组)
            }else if(roleId==3 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目组长");

                //项目组组长获取项目经理派送的任务和自己创建的任务
                List<TaskEntity> taskEntities= taskService.selectTaskEndGroup(userId,userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(研发组)
            }else if(roleId==6 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(研发)");

                List<TaskEntity> taskEntities = taskService.selectTaskEnd(userId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }

                //如果角色ID为测试组组长(测试组)
            }else if (roleId==7){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"测试组组长");
                //通过任务的测试状态来(已完成或未完成)和当前登录的用户ID来查询所有测试任务
                List<TaskEntity> taskEntities = taskService.selectAllTestTask(userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(测试组)
            }else if(roleId==8){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(测试)");
                //通过任务的指派人(研发组成员)所属的部门和指派人的id、测试状态来(已完成或未完成)来查询出当前已完成的测试任务和进行中的测试任务
                List<TaskEntity> taskEntities = taskService.selectMyTestTask (userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为运维组组长(运维组)
            }else if(roleId==9){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"运维组组长");
                //通过任务的运维状态来(已完成或未完成)和当前登录的用户ID、测试状态来(已完成或未完成)来查询所有运维任务
                List<TaskEntity> taskEntities = taskService.selectAllOperationTask(userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(运维组)
            }else if(roleId==10){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(运维)");
                //通过任务的指派人(测试组成员)所属的部门和指派人的id、测试状态来(已完成或未完成)来查询出当前已完成的运维任务和进行中的运维任务
                List<TaskEntity> taskEntities = taskService.selectMyOperationTask (userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");

                }
            }else {
                map.put("msg", "当前用户未被指派任务");

            }
        }
        return taskEntitieList;
    }
    /**
     * 返回我的任务的派送页面
     * @return
     */
    @RequestMapping(value = "/redeploy",method = RequestMethod.GET)
    public String indexRedeploy(){

        return PREFIX+"taskRedeploy.html";
    }

    /**
     * 返回结束的任务页面
     * @return
     */
    @RequestMapping(value = "/taskEnd",method = RequestMethod.GET)
    public String indexEnd(){

        return PREFIX+"taskEnd.html";
    }

    /**
     * 获取我的任务列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        //存放提示信息的map
        HashMap<String, String> map = new HashMap<>();

        //获取当前登录用户id
        String userId = ShiroKit.getUser().getId().toString();
        //通过当前用户登录id获取当前用户任务
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        String deptId = ShiroKit.getUser().getDeptId().toString();
        ArrayList<TaskEntity> taskEntitieList=new ArrayList<>();

        for (Integer roleId:roleList) {
            //如果角色ID为项目经理(研发部)
            if(roleId==2 && deptId.equals("25") ){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目经理");
                List<TaskEntity> taskEntities = taskService.selectList(null);
                taskEntitieList.addAll(taskEntities);
                //如果角色ID为项目组长(研发组)
            }else if(roleId==3 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"项目组长");

                //项目组组长获取项目经理派送的任务和自己创建的任务
                List<TaskEntity> taskEntities= taskService.selectAllTask(userId,userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(研发组)
            }else if(roleId==6 && deptId.equals("25")){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(研发)");

                List<TaskEntity> taskEntities = taskService.selectMyTask(userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }

                //如果角色ID为测试组组长(测试组)
            }else if (roleId==7){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"测试组组长");
                //通过任务的测试状态来(已完成或未完成)和当前登录的用户ID来查询所有测试任务
                List<TaskEntity> taskEntities = taskService.selectAllTestTask(userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(测试组)
            }else if(roleId==8){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(测试)");
                //通过任务的指派人(研发组成员)所属的部门和指派人的id、测试状态来(已完成或未完成)来查询出当前已完成的测试任务和进行中的测试任务
                List<TaskEntity> taskEntities = taskService.selectMyTestTask (userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为运维组组长(运维组)
            }else if(roleId==9){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"运维组组长");
                //通过任务的运维状态来(已完成或未完成)和当前登录的用户ID、测试状态来(已完成或未完成)来查询所有运维任务
                List<TaskEntity> taskEntities = taskService.selectAllOperationTask(userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");
                    return map;
                }
                //如果角色ID为普通员工(运维组)
            }else if(roleId==10){
                LOG.info("当前登录人={},角色={}", ShiroKit.getUser().getName(),"普通员工(运维)");
                //通过任务的指派人(测试组成员)所属的部门和指派人的id、测试状态来(已完成或未完成)来查询出当前已完成的运维任务和进行中的运维任务
                List<TaskEntity> taskEntities = taskService.selectMyOperationTask (userId,deptId);
                if(taskEntities!=null && taskEntities.size()>0){
                    LOG.info("获取我的项目任务列表-->{}",taskEntities);
                    taskEntitieList.addAll(taskEntities);

                }else {
                    map.put("msg", "当前用户未被指派任务");

                }
            }else {
                map.put("msg", "当前用户未被指派任务");

            }
        }

        return taskEntitieList;
    }


    /**
     *
     */
    @RequestMapping("/showTask/{taskId}")
    public String showMyTask(@PathVariable String taskId, Model model) {
        TaskEntity taskEntity = taskService.selectById(taskId);
        Project project = projectService.selectById(taskEntity.getProjectId());
        Module module = moduleService.selectById(taskEntity.getModuleId());
        Function function = functionService.selectById(taskEntity.getFunId());
        PmOfUtil.getDelayTime(taskEntity, project, module,function);
        taskService.updateById(taskEntity);
        TaskEntity taskEntityUpdate = taskService.selectById(taskId);
        model.addAttribute("item",taskEntityUpdate);
        //查询项目是否存在附件
        Map<String,Object> param = new HashMap<>();
        param.put("projectId",taskEntityUpdate.getProjectId());
        List<Attachment> attachments = attachmentService.selectByMap(param);
        StringBuilder sb=new StringBuilder();
        String li="<li class='list-group-item'>";
        String h4="<h4>任务附件:</h4>";
        sb.append(li).append(h4);
        if(attachments.size()>0){
            StringBuilder htmlSb=new StringBuilder();
            for (Attachment attachment:attachments) {

                String html="<div> <div >"+
                        "<span>&nbsp;&nbsp;"+attachment.getSourceAttachmentName()+"&nbsp;&nbsp;&nbsp;&nbsp;</span>"+
                        "<span><a  href='javascript:void(0);'  style='text-decoration:underline;' " +
                        " onclick=\"downloadFile('"+ attachment.getAttachmentName() +"','"+attachment.getSourceAttachmentName()+"')\">" +
                        "[附件下载] " +
                        "</a></span><div>" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;<span id='"+attachment.getAttachmentName().substring(0, 14)+"'></span>"+
                        "</div>"
                        ;

                htmlSb.append(html);
            }
            sb.append(htmlSb+"</li>");
        }

        model.addAttribute("html",sb);
        LogObjectHolder.me().set(taskEntityUpdate);
        return PREFIX + "task_edit.html";
    }

    /**
     * 跳转到添加我的项目
     */
    @RequestMapping("/task_add")
    public String projectAdd() {

        return PREFIX + "task_add.html";
    }

    /**
     * 跳转到修改我的项目
     */
    @RequestMapping("/show/{taskId}")
    public String taskUpdate(@PathVariable String taskId, Model model) {
        TaskEntity taskEntity = taskService.selectById(taskId);
        Project project = projectService.selectById(taskEntity.getProjectId());
        Module module = moduleService.selectById(taskEntity.getModuleId());
        Function function = functionService.selectById(taskEntity.getFunId());
        PmOfUtil.getDelayTime(taskEntity, project, module,function);
        taskService.updateById(taskEntity);
        TaskEntity taskEntityUpdate = taskService.selectById(taskId);
        model.addAttribute("item",taskEntityUpdate);
        LogObjectHolder.me().set(taskEntityUpdate);
        return PREFIX + "task_edit.html";
    }
    /**
     * 任务转派
     */
    @RequestMapping(value = "/redeployTaskList")
    @ResponseBody
    public Object redeployTask() {

        List<TaskEntity> taskEntities = taskService.selectTaskRedeploy(ShiroKit.getUser().getId().toString());
        return taskEntities;
    }


    /**
     *
     * @param taskId 任务ID
     * @param type 研发类型（develop）、测试类型(test)、运维类型(operation)
     * @return
     */
    @RequestMapping(value = "/changeStatus")
    @ResponseBody
    public Object changeStatus(@RequestParam String taskId , @RequestParam String type) {
        TaskEntity taskEntity = taskService.selectById(taskId);
        //如果类型为研发
        if(type.equals("develop")){
            if(taskEntity.getStatus().equals("0")){
                taskEntity.setStatus("1");
                taskEntity.setStatusName("已完成");
                taskEntity.setStatusOfTest("1");
                taskEntity.setTestBug("");
                taskService.updateById(taskEntity);
            }else {
                taskEntity.setStatus("0");
                taskEntity.setStatusName("研发任务进行中..");
                taskEntity.setStatusOfTest("0");
                taskService.updateById(taskEntity);
            }
        }else if (type.equals("test")){
            if (taskEntity.getStatusOfTest().equals("1")){
                taskEntity.setStatusOfTest("2");
                taskEntity.setStatusOfTestName("已完成");
                taskEntity.setStatusOfOperation("1");
            }else if (taskEntity.getStatusOfTest().equals("2")){
                taskEntity.setStatusOfTest("1");
                taskEntity.setStatusOfTestName("测试任务进行中..");
                taskEntity.setStatusOfOperation("0");
            }
            taskService.updateById(taskEntity);
        }else if (type.equals("operation")){
            if (taskEntity.getStatusOfOperation().equals("1")){
                taskEntity.setStatusOfOperation("2");
                taskEntity.setStatusOfOperationName("已完成");
            }else if (taskEntity.getStatusOfOperation().equals("2")){
                taskEntity.setStatusOfOperation("1");
                taskEntity.setStatusOfOperationName("运维任务进行中..");
            }
        }

        return SUCCESS_TIP;
    }


    /**
     * 测试者提交问题页面
     */
    @RequestMapping(value = "/problemFeedback/{id}")
    public String problemFeedback(@PathVariable String id,Model model) {
        TaskEntity taskEntity = taskService.selectById(id);
        model.addAttribute("item", taskEntity);
        LogObjectHolder.me().set(taskEntity);
        return PREFIX + "task_problemFeedback.html";
    }

    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/problem")
    @ResponseBody
    public Object problemFeedback(@RequestParam("id")String id,@RequestParam("testBug")String testBug) {
        TaskEntity taskEntity = taskService.selectById(id);
        taskEntity.setTestBug(testBug);
        taskEntity.setStatus("0");
        taskEntity.setStatusName("研发任务进行中..");
        taskEntity.setStatusOfTest("0");
        taskEntity.setStatusOfTestName("");
        taskService.updateById(taskEntity);
        return SUCCESS_TIP;
    }


    /**
     * 新增我的项目
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TaskEntity taskEntity) {
        taskEntity.setCreateUser(ShiroKit.getUser().getId().toString());
        taskService.insert(taskEntity);
        return SUCCESS_TIP;
    }

    /**
     * 删除我的项目
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer taskId) {
        taskService.deleteById(taskId);
        return SUCCESS_TIP;
    }

    /**
     * 修改我的项目
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TaskEntity taskEntity) {
        taskService.updateById(taskEntity);
        return SUCCESS_TIP;
    }


    /**
     * 下载附件
     */
    @RequestMapping(value = "/downLoad")
    @ResponseBody
    public Object downLoad(String fileName,String sourceFileName) {
        String filePath = "";
        String operationSystem = PmOfUtil.getOperationSystem();
        if(operationSystem.toLowerCase().startsWith("windows")){
            filePath="D:\\attachment";
        }else {
            filePath=PmOfUtil.dir()+"/spider/service-crm/file";
        }
        Map<String, String> map = PmOfUtil.downLoad(filePath +"\\"+ fileName, sourceFileName, fileName);
        return map;
    }




}