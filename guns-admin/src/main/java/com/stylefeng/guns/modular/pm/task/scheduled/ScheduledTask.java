package com.stylefeng.guns.modular.pm.task.scheduled;

import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.modular.pm.function.entity.Function;
import com.stylefeng.guns.modular.pm.function.service.FunctionService;
import com.stylefeng.guns.modular.pm.module.entity.Module;
import com.stylefeng.guns.modular.pm.module.service.ModuleService;
import com.stylefeng.guns.modular.pm.task.controller.TaskController;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;
import com.stylefeng.guns.modular.pm.task.service.TaskService;
import com.stylefeng.guns.modular.pm.taskofuser.entity.TaskOfUser;
import com.stylefeng.guns.modular.pm.taskofuser.service.EmailService;
import com.stylefeng.guns.modular.pm.taskofuser.service.TaskOfUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * @author sunwei
 * @date 2019/6/22
 */
@Configuration
@EnableScheduling
public class ScheduledTask {
    private static Logger LOG= LoggerFactory.getLogger(TaskController.class);
    @Value("${spring.mail.username}")
    private String username;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ModuleService moduleService;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private TaskOfUserService taskOfUserService;

    @Scheduled(cron = "0 12 10 ? * * ")
    private void configureTasks() {

        //查询全部正在进行中的任务
        List<TaskEntity> taskEntities = taskService.selectAllTaskProgress();
        if (taskEntities.size() > 0) {
            for (TaskEntity task : taskEntities) {
                //当前时间

                String newDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
                //获取任务类型
                String type = task.getType();
                if (type.equals("module")) {
                    Module module = moduleService.selectById(task.getModuleId());
                    if (DateUtil.compareDate(newDate, module.getEndTime())) {
                        long daySub = DateUtil.getDaySub(module.getEndTime(), newDate);
                        TaskOfUser taskOfUser = taskOfUserService.selectById(task.getUserId());
                        emailService.sendHtmlMail(username, taskOfUser.getEmail(), "您的任务:[" + task.getName() + "]已超时，超时天数：[" + daySub +"天]", task.getMarks());
                    }else {
                        LOG.info("没有[模块任务]延时");
                    }

                } else if (type.equals("function")) {
                    Function function = functionService.selectById(task.getFunId());
                    if (DateUtil.compareDate(newDate, function.getEndTime())) {
                        long daySub = DateUtil.getDaySub(function.getEndTime(), newDate);
                        TaskOfUser taskOfUser = taskOfUserService.selectById(task.getUserId());
                        emailService.sendHtmlMail(username, taskOfUser.getEmail(), "您的任务:[" + task.getName() + "]已超时，超时天数：[" + daySub +"天]", task.getMarks());
                    }else {
                        LOG.info("没有[功能任务]延时");
                    }
                }
            }
        }else {
            LOG.info("没有正在运行的任务！");
        }
    }
}