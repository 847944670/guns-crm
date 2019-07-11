package com.stylefeng.guns.modular.pm.taskofuser.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.system.model.Dept;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IDeptService;
import com.stylefeng.guns.modular.system.service.IRoleService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.function.service.FunctionService;
import com.stylefeng.guns.modular.pm.project.entity.Project;
import com.stylefeng.guns.modular.pm.project.service.ProjectService;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;
import com.stylefeng.guns.modular.pm.task.service.TaskService;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.module.service.ModuleService;
import com.stylefeng.guns.modular.pm.taskofuser.entity.TaskOfUser;
import com.stylefeng.guns.modular.pm.taskofuser.service.EmailService;
import com.stylefeng.guns.modular.pm.taskofuser.service.TaskOfUserService;
import com.stylefeng.guns.modular.pm.util.PmOfUtil;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author sunwei
 * @date 2019/5/31
 */
@Controller
@RequestMapping("/taskofuser")
public class TaskOfUserController extends BaseController {
    private static Logger LOG = LoggerFactory.getLogger(TaskOfUserController.class);
    @Value("${spring.mail.username}")
    private  String username;
    private String PREFIX = "/system/pm/taskofuser/";
    @Autowired
    private TaskOfUserService taskOfUserService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private FunctionService functionService;
    @Autowired
    private EmailService emailService;
    @RequestMapping("/index")
    public String indexMoudle(@RequestParam("id") String id, @RequestParam("type")String type, Model model){
        LOG.info("模块ID为={}",id );
        if(type.equals("moudle")){
            model.addAttribute("type","moudleId" );
        }
        if (type.equals("fun")){
            model.addAttribute("type","funId");
        }
        if (type.equals("redeploy")){
            model.addAttribute("type","redeploy");
        }
        if (type.equals("test")){
            model.addAttribute("type","test");
        }
        if (type.equals("operation")){
            model.addAttribute("type","operation");
        }

        model.addAttribute("id",id );
        return PREFIX+"taskofuser.html";
    }



    /**
     * 获取我的项目列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        taskOfUserService.deleteAll();
        List<User>  users = userService.selectList(null);
        if(users.size()>0){
            List<TaskOfUser> taskOfUserList =new ArrayList<>();
            for (User user: users) {
                //获取系统用户角色ID
                String roleid = user.getRoleid();
                Role role = roleService.selectById(roleid);

                //获取系统用户ID
                Integer id = user.getId();

                //获取系统用户姓名
                String name = user.getName();
                //获取系统用户部门ID
                Integer deptid = user.getDeptid();
                //获取系统用户的email
                String email = user.getEmail();
                Dept dept = deptService.selectById(deptid);
                TaskOfUser taskOfUser = new TaskOfUser();
                taskOfUser.setId(id.toString());
                taskOfUser.setName(user.getName());
                taskOfUser.setDeptName(dept.getSimplename());
                taskOfUser.setRoleName(role.getName());
                taskOfUser.setEmail(email);
                taskOfUserList.add(taskOfUser);
            }

            taskOfUserService.insertBatch(taskOfUserList);
            return taskOfUserList;
        }
        else {
            String msg = "无系统用户，请添加";

            return msg;
        }
    }
    @RequestMapping(value = "/taskdown")
    @ResponseBody
    public Object taskdown(@RequestParam  String taskOfUserId,@RequestParam String id,@RequestParam("type")String type) {
        Map<String, String> map = new HashMap<>();
        Assert.notNull(id, "模块ID为空");
        //获取TaskOfUser对象
        TaskOfUser taskOfUser = taskOfUserService.selectById(taskOfUserId);

        if (taskOfUser.getRoleName().equals("项目经理") && ShiroKit.getUser().roleNames.contains("项目组组长")) {
            map.put("msg", "无权限派送任务");
        } else if (taskOfUser.getRoleName().equals("项目经理") && ShiroKit.getUser().roleNames.contains("测试组组长")) {
            map.put("msg", "无权限派送任务");
        } else if (taskOfUser.getRoleName().equals("项目经理") && ShiroKit.getUser().roleNames.contains("普通员工(测试)")) {
            map.put("msg", "无权限派送任务");
        } else if (taskOfUser.getRoleName().equals("项目经理") && ShiroKit.getUser().roleNames.contains("普通员工(研发)")) {
            map.put("msg", "无权限派送任务");
        }  else if (taskOfUser.getRoleName().equals("测试组组长") && ShiroKit.getUser().roleNames.contains("项目组组长")) {
            map.put("msg", "无权限派送任务");
        } else if (taskOfUser.getRoleName().equals("项目组组长") && ShiroKit.getUser().roleNames.contains("普通员工(测试)")) {
            map.put("msg", "无权限派送任务");
        } else if (taskOfUser.getRoleName().equals("普通员工(测试)") && ShiroKit.getUser().roleNames.contains("项目组组长")) {
            map.put("msg", "无权限派送任务");
        }else {

            taskOfUser.setDown(true);
            //新建任务
            TaskEntity taskEntity = new TaskEntity();
            if (type.equals("moudleId")) {
                LOG.info("当前下放任务类型为【模块任务下放】");
                /**
                 * 任务信息部分
                 */
                //根据模块id查询出模块
                Module module = moduleService.selectById(id);
                //设置默认值
                PmOfUtil.setDefaultValue(taskEntity, module, null);
                //id
                taskEntity.setId(UUID.randomUUID().toString().substring(0, 31).replaceAll("-", ""));
                //任务名称
                taskEntity.setName(module.getProjectName() + "--" + module.getModuleName() + "");
                //领取任务的人的角色
                taskEntity.setEmployeeRole(taskOfUser.getRoleName());
                //领取任务的人的所属部门
                taskEntity.setDeptName(taskOfUser.getDeptName());
                //所在项目
                taskEntity.setProjectName(module.getProjectName());
                taskEntity.setProjectId(module.getProjectId());
                //所在模块
                taskEntity.setModuleName(module.getModuleName());
                taskEntity.setModuleId(module.getId());
                //获取到任务所在项目
                Project project = projectService.selectById(module.getProjectId());
                //项目研发周期(单位:天)
                taskEntity.setProjectTime(project.getTime());
                //模块研发周期(单位:天)
                taskEntity.setModuleTime(module.getTime());
//                //项目延时(项目结束时间小于当前之间)
//                taskEntity.setDelay(DateUtil.compareDate(DateUtil.format(new Date(), "yyyy-MM-dd"), project.getEndTime()));

                //任务是否开启:0为开启，1为关闭
                taskEntity.setStatus("0");
                //测试状态：0为未开启，1为进行中，2为已完成
                taskEntity.setStatusOfTest("0");
                //运维状态：0为未开启，1为进行中，2为已完成
                taskEntity.setStatusOfOperation("0");
                taskEntity.setStatusName("研发任务进行中..");
                taskEntity.setTaskType("下放记录");
                //打印任务信息
                LOG.info("任务ID={},任务名称={},任务所在项目={},任务所在模块={}", taskEntity.getId(), taskEntity.getName(), taskEntity.getProjectName(), taskEntity.getModuleName());

                /**
                 * 任务下放者信息
                 */
                //当前任务为当前登陆者下放的部门
                taskEntity.setCreateTaskUserDeptId(ShiroKit.getUser().getDeptId().toString());
                taskEntity.setCreateTaskUserId(ShiroKit.getUser().getId().toString());
                LOG.info("当前任务下放者ID={},当前任务下放者姓名={},当前任务下放者所在部门ID={},当前任务下放者所在部门名称={}", taskEntity.getCreateTaskUserId(), ShiroKit.getUser().getName(), taskEntity.getCreateTaskUserDeptId(), ShiroKit.getUser().getDeptName());
                /**
                 * 任务分配部分
                 */
                //领取任务的人
                taskEntity.setGetTaskUserName(taskOfUser.getName());
                //领取任务的人的id
                taskEntity.setUserId(taskOfUser.getId());
                //领取任务的员工(table里面的回显)
                module.setAccepterEmployee(taskOfUser.getName());
                moduleService.updateById(module);
                LOG.info("当前领取任务的人的ID={},当前领取任务的人的姓名={}", taskEntity.getUserId(), taskEntity.getGetTaskUserName());
                //任务功能详情
                taskEntity.setMarks("<font color=\"#FF0000\">项目信息：<br/></font>"+project.getMarks()+"<br/><font color=\"#FF0000\">模块信息：<br/></font>"+module.getMarks()+"<br/>");
                taskEntity.setType("module");
                //保存任务
                taskService.insert(taskEntity);
                map.put("msg", "success");
                map.put("id", taskEntity.getId());
                taskOfUser.setSubject(taskEntity.getName());
                taskEntity.getMarks();
                LOG.info("任务下放成功");


            } else if (type.equals("funId")) {
                LOG.info("当前下放任务类型为【模块功能任务下放】");
                /**
                 * 任务信息部分
                 */
                //根据功能id查询出模块
                Function function = functionService.selectById(id);
                //项目信息
                Project project = projectService.selectById(function.getProjectId());
                //模块信息
                Module module = moduleService.selectById(function.getModuleId());
                //设置默认值
                PmOfUtil.setDefaultValue(taskEntity, null, function);
                //id
                taskEntity.setId(UUID.randomUUID().toString().substring(0, 31).replaceAll("-", ""));
                //任务名称
                taskEntity.setName(function.getProjectName() + "--" + function.getModuleName() + ":" + function.getFunctionName());
                //角色
                taskEntity.setEmployeeRole(taskOfUser.getRoleName());
                //所属部门
                taskEntity.setDeptName(taskOfUser.getDeptName());
                //所在项目
                taskEntity.setProjectName(function.getProjectName());
                taskEntity.setProjectId(function.getProjectId());
                //所在模块
                taskEntity.setModuleName(function.getModuleName());
                taskEntity.setModuleId(function.getModuleId());
                //功能
                taskEntity.setFunName(function.getFunctionName() + ":" + function.getFunctionDescription());
                taskEntity.setFunId(function.getId());
                //项目研发周期
                taskEntity.setProjectTime(project.getTime());
                //模块研发周期(单位:天)
                taskEntity.setModuleTime(module.getTime());
                //功能研发周期
                taskEntity.setFunctionTime(function.getTime());

//                //项目延时(项目结束时间小于当前之间)
//                taskEntity.setDelay(DateUtil.compareDate(DateUtil.format(new Date(), "yyyy-MM-dd"), function.getEndTime()));
                //转派状态：0为已转派，1为未转派
                taskEntity.setRedeployStatus("1");
                //任务是否开启:0为开启，1为关闭
                taskEntity.setStatus("0");
                //测试状态：0为未开启，1为进行中，2为已完成
                taskEntity.setStatusOfTest("0");
                //运维状态：0为未开启，1为进行中，2为已完成
                taskEntity.setStatusOfOperation("0");
                taskEntity.setStatusName("研发任务进行中..");
                taskEntity.setTaskType("下放记录");

                LOG.info("任务ID={},任务名称={},任务所在项目={},任务所在模块={},任务所在功能={}", taskEntity.getId(), taskEntity.getName(), taskEntity.getProjectName(), taskEntity.getModuleName(), taskEntity.getFunName());
                /**
                 * 下放任务的人的信息
                 */
                //下放任务的人的id
                taskEntity.setCreateTaskUserId(ShiroKit.getUser().getId().toString());
                taskEntity.setCreateTaskUserDeptId(ShiroKit.getUser().getDeptId().toString());
                LOG.info("当前下放任务的人的ID={},当前下放任务的人的姓名={}", taskEntity.getCreateTaskUserId(), ShiroKit.getUser().getName(), taskEntity.getCreateTaskUserDeptId(), ShiroKit.getUser().getDeptName());
                /**
                 * 领取任务的人的信息
                 */

                //领取任务的人的id
                taskEntity.setUserId(taskOfUser.getId());
                //当前任务领取人的姓名
                taskEntity.setGetTaskUserName(taskOfUser.getName());
                //列表回显
                function.setAccepterEmployee(taskOfUser.getName());
                functionService.updateById(function);
                LOG.info("当前领取任务的人的ID={},当前领取任务的人的姓名={}", taskEntity.getUserId(), taskEntity.getGetTaskUserName());
                //任务详情
                taskEntity.setMarks( "<font color=\"#FF0000\">项目信息：<br/></font>"+ project.getMarks()+
                        "<font color=\"#FF0000\"><br/>模块信息：<br/></font>"+ module.getMarks()+
                        "<font color=\"#FF0000\"><br/>功能描述：<br/></font>"+function.getMarks()+"<br/>");

                taskEntity.setType("function");
                //保存任务
                taskService.insert(taskEntity);

                map.put("msg", "success");
                map.put("id", taskEntity.getId());
                LOG.info("任务下放成功");
                taskOfUser.setSubject(taskEntity.getName());

            } else if (type.equals("redeploy")) {
                LOG.info("当前下放任务类型为【任务转派】");
                TaskEntity task = taskService.selectById(id);
                task.setRedeployStatus("0");
                task.setTaskType("转派记录");
                task.setStatusName("研发任务进行中..");
                task.setUserId(taskOfUser.getId());
                task.setGetTaskUserName(taskOfUser.getName());
                task.setRedeployUser(ShiroKit.getUser().getName());
                task.setCreateTaskUserId(ShiroKit.getUser().getId().toString());
                task.setCreateTaskUserDeptId(ShiroKit.getUser().getDeptId().toString());
                task.setType("redeploy");
                taskService.updateById(task);
                map.put("msg", "success");
                map.put("id", task.getId());
                LOG.info("当前转派任务名称={},当前转派任务转派者={},当前转派任务领取者ID={}", task.getName(), task.getRedeployUser(), task.getUserId());
                taskOfUser.setSubject(taskEntity.getName());

            } else if (type.equals("test")) {
                TaskEntity task = taskService.selectById(id);
                task.setStatusOfTest("1");
                task.setTestOfUserId(taskOfUser.getId());
                task.setTestOfUserName(taskOfUser.getName());
                task.setStatusOfTestName("测试任务进行中..");
                task.setType("test");
                taskService.updateById(task);
                map.put("msg", "success");
                map.put("id", task.getId());
                LOG.info("当前测试任务名称={},当前测试任务执行者={},当前测试任务移交者={}", task.getName(), task.getTestOfUserName(), ShiroKit.getUser().getName());
                taskOfUser.setSubject(taskEntity.getName());

            } else if (type.equals("operation")) {
                TaskEntity task = taskService.selectById(id);
                task.setStatusOfOperation("1");
                task.setOperationOfUserId(taskOfUser.getId());
                task.setOperationOfUserName(taskOfUser.getName());
                task.setStatusOfOperationName("运维任务进行中..");
                task.setType("operation");
                taskService.updateById(task);
                map.put("msg", "success");
                map.put("id", task.getId());
                LOG.info("当前测试任务名称={},当前运维任务执行者={},当前运维任务移交者={}", task.getName(), task.getOperationOfUserName(), ShiroKit.getUser().getName());
                taskOfUser.setSubject(taskEntity.getName());


            }

        }
        return map;

    }
    @RequestMapping(value = "/sendMessage")
    @ResponseBody
    public Object sendMsg(String taskId,String type){
        Map<String,String> map = new HashMap<>();
        if (taskId!=null && !taskId.equals("")){
            TaskEntity taskEntity = taskService.selectById(taskId);
            TaskOfUser taskOfUser = taskOfUserService.selectById(taskEntity.getUserId());
            taskOfUser.setSubject(taskEntity.getName());
            if (taskOfUser.getEmail()!=null){
                if (type.equals("moudleId")){
                    emailService.sendHtmlMail(username, taskOfUser.getEmail(),taskOfUser.getSubject()+"[研发任务(模块)]", taskEntity.getMarks());
                    map.put("msg", "moudleId");
                }else if (type.equals("funId")){
                    emailService.sendHtmlMail(username, taskOfUser.getEmail(),taskOfUser.getSubject()+"[研发任务(功能)]", taskEntity.getMarks());
                    map.put("msg", "funId");

                }
                else if (type.equals("redeploy")){
                    emailService.sendHtmlMail(username, taskOfUser.getEmail(),taskOfUser.getSubject()+"[转派任务]", taskEntity.getMarks());
                    map.put("msg", "redeploy");

                }
                else if (type.equals("test")){
                    emailService.sendHtmlMail(username, taskOfUser.getEmail(),taskOfUser.getSubject()+"[测试任务]", taskEntity.getMarks());
                    map.put("msg", "test");

                }
                else if (type.equals("operation")){
                    emailService.sendHtmlMail(username, taskOfUser.getEmail(),taskOfUser.getSubject()+"[运维任务]", taskEntity.getMarks());
                    map.put("msg", "operation");
                }

            }else {
                map.put("msg", "errorMail");
            }

        }else {
            map.put("msg", "errorTask");
        }
        return map;
    }


}