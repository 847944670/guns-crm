package com.stylefeng.guns.modular.pm.task.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.pm.task.dao.TaskDao;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;
import com.stylefeng.guns.modular.pm.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sunwei
 * @date 2019/5/28
 */
@Service
public class TaskServiceImp extends ServiceImpl<TaskDao, TaskEntity> implements TaskService {


    @Override
    public List<TaskEntity> selectAllTask(String createTaskUserId,String userId , String deptId) {
        return this.baseMapper.selectAllTask(createTaskUserId, userId ,deptId);
    }

    @Override
    public List<TaskEntity> selectMyTask(String userId, String deptId) {
        return this.baseMapper.selectMyTask(userId, deptId);
    }

    @Override
    public List<TaskEntity> selectAllTestTask(String userId, String deptId) {
        return this.baseMapper.selectAllTestTask(userId, deptId);
    }

    @Override
    public List<TaskEntity> selectMyTestTask(String userId, String deptId) {
        return this.baseMapper.selectMyTestTask(userId, deptId);
    }

    @Override
    public List<TaskEntity> selectAllOperationTask(String userId, String deptId) {
        return this.baseMapper.selectAllOperationTask(userId, deptId);
    }

    @Override
    public List<TaskEntity> selectMyOperationTask(String userId, String deptId) {
        return this.baseMapper.selectMyOperationTask(userId, deptId);
    }

    @Override
    public List<TaskEntity> selectTaskProgress(String id) {
        return this.baseMapper.selectTaskProgress(id);
    }

    @Override
    public List<TaskEntity> selectTaskEnd(String id) {
        return this.baseMapper.selectTaskEnd(id);
    }

    @Override
    public List<TaskEntity> selectTaskRedeploy(String id) {
        return this.baseMapper.selectTaskRedeploy(id);
    }

    @Override
    public List<TaskEntity> selectTaskProgressManager(String deptId, String userId) {
        return this.baseMapper.selectTaskProgressManager(deptId,userId);
    }

    @Override
    public List<TaskEntity> selectTaskProgressGroup(String userId, String deptId, String userId1) {
        return this.baseMapper.selectTaskProgressGroup(userId, deptId, userId1);
    }

    @Override
    public List<TaskEntity> selectTaskEndManager(String deptId, String userId) {
        return this.baseMapper.selectTaskEndManager(deptId, userId);
    }


    @Override
    public List<TaskEntity> selectTaskEndGroup(String createTaskUserId,String userId,  String deptId) {
        return this.baseMapper.selectTaskEndGroup(createTaskUserId,userId,  deptId);
    }

    @Override
    public List<TaskEntity> selectTestTaskGroup(String userId,String userid,  String deptId) {
        return this.baseMapper.selectTestTaskGroup(userId,userid, deptId);
    }

    @Override
    public List<TaskEntity> selectDevelopTestTask(String userid) {
        return this.baseMapper.selectDevelopTestTask(userid);
    }

    @Override
    public List<TaskEntity> selectTaskTestEndManager(String deptId, String userId) {
        return this.baseMapper.selectTaskTestEndManager(deptId,userId);
    }

    @Override
    public List<TaskEntity> selectTaskTestEndGroup(String userId, String deptId, String userId1) {
        return this.baseMapper.selectTaskTestEndGroup(userId, deptId, userId1);
    }

    @Override
    public List<TaskEntity> selectTaskEndEmployee(String userId) {
        return this.baseMapper.selectTaskEndEmployee(userId);
    }

    @Override
    public List<TaskEntity> selectAllTestTaskEnd(String userId, String deptId) {
        return this.baseMapper.selectAllTestTaskEnd(userId, deptId);
    }

    @Override
    public List<TaskEntity> selectMyTestTaskEnd(String userId) {
        return this.baseMapper.selectMyTestTaskEnd(userId);
    }

    @Override
    public List<TaskEntity> selectAllTaskProgress() {
        return this.baseMapper.selectAllTaskProgress();
    }
}