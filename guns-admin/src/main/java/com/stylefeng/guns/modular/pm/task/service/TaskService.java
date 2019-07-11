package com.stylefeng.guns.modular.pm.task.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;

import java.util.List;

public interface TaskService extends IService<TaskEntity> {



    //研发组组长的所有任务
    List<TaskEntity> selectAllTask(String createTaskUserId,String userId,  String deptId);
    //研发组成员获取的任务
    List<TaskEntity> selectMyTask(String userId, String deptId);
    //测试组组长的所有任务
    List<TaskEntity> selectAllTestTask(String userId, String deptId);
    //测试组成员获取的任务
    List<TaskEntity> selectMyTestTask(String userId, String deptId);
    //运维组组长的所有任务
    List<TaskEntity> selectAllOperationTask(String userId, String deptId);
    //运维组成员获取的任务
    List<TaskEntity> selectMyOperationTask(String userId, String deptId);
    //进行中的任务
    List<TaskEntity> selectTaskProgress(String id);
    //已完成的任务
    List<TaskEntity> selectTaskEnd(String id);
    //转派的任务
    List<TaskEntity> selectTaskRedeploy(String id);
    //查询项目经理进行中的任务
    List<TaskEntity> selectTaskProgressManager(String deptId, String userId);
    //查询项目组长进行中的任务
    List<TaskEntity> selectTaskProgressGroup(String userId, String deptId, String userId1);
    //查询项目经理已完成的任务
    List<TaskEntity> selectTaskEndManager(String deptId, String userId);
    //查询项目组长已完成的任务
    List<TaskEntity> selectTaskEndGroup(String createTaskUserId,String userId, String deptId);
    //项目组长查询自己组里的测试任务
    List<TaskEntity> selectTestTaskGroup(String userId,String userid, String deptId);
    //项目开发组成员查询自己派送的测试任务
    List<TaskEntity> selectDevelopTestTask(String userid);


    //项目经理查询已经完成的测试任务
    List<TaskEntity> selectTaskTestEndManager(String deptId, String userId);
    //项目组长查询已经完成的测试任务
    List<TaskEntity> selectTaskTestEndGroup(String userId, String deptId, String userId1);
    //开发组成员查询已经完成的测试任务
    List<TaskEntity> selectTaskEndEmployee(String userId);
    //测试组组长查询已经完成的测试任务
    List<TaskEntity> selectAllTestTaskEnd(String userId, String deptId);
    //测试组成员查询已经完成的测试任务
    List<TaskEntity> selectMyTestTaskEnd(String userId);
    //查询全部正在进行中的任务
    List<TaskEntity> selectAllTaskProgress();
}
