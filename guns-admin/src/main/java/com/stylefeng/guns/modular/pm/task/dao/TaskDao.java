package com.stylefeng.guns.modular.pm.task.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskDao extends BaseMapper<TaskEntity> {

    //研发组组长的所有任务
    List<TaskEntity> selectAllTask(@Param("createTaskUserId")String createTaskUserId,@Param("userId") String userId,  @Param("deptId") String deptId);
    //研发组成员获取的任务
    List<TaskEntity> selectMyTask(@Param("userid")String userId, @Param("deptid")String deptId);
    //测试组组长的所有任务
    List<TaskEntity> selectAllTestTask(@Param("userid")String userId, @Param("deptid")String deptId);
    //测试组成员获取的任务
    List<TaskEntity> selectMyTestTask(@Param("userid")String userId, @Param("deptid")String deptId);
    //运维组组长的所有任务
    List<TaskEntity> selectAllOperationTask(@Param("userid")String userId, @Param("deptid")String deptId);
    //运维组成员获取的任务
    List<TaskEntity> selectMyOperationTask(@Param("userid")String userId, @Param("deptid")String deptId);
    //进行中的任务
    List<TaskEntity> selectTaskProgress(@Param("userid")String id);
    //已完成的任务
    List<TaskEntity> selectTaskEnd(@Param("userid")String id);
    //转派的任务
    List<TaskEntity> selectTaskRedeploy(@Param("userid")String id);
    //查询项目经理进行中的任务
    List<TaskEntity> selectTaskProgressManager(@Param("deptId")String deptId, @Param("userId")String userId);
    //查询项目组长进行中的任务
    List<TaskEntity> selectTaskProgressGroup(@Param("createTaskUserId")String userId, @Param("deptId")String deptId, @Param("userId")String userId1);
    //查询项目经理已完成的任务
    List<TaskEntity> selectTaskEndManager(@Param("deptId")String deptId,@Param("createTaskUserId") String userId);
    //查询项目组长已完成的任务
    List<TaskEntity> selectTaskEndGroup(@Param("createTaskUserId")String createTaskUserId, @Param("userId") String userId, @Param("deptId")String deptId);
    //项目组长查询自己组里的测试任务
    List<TaskEntity> selectTestTaskGroup(@Param("createTaskUserId")String createTaskUserId, @Param("userId")String userId,@Param("deptId")String deptId);
    //项目开发组成员查询自己派送的测试任务
    List<TaskEntity> selectDevelopTestTask(@Param("userid") String userid);

    //项目经理查询已经完成的测试任务
    List<TaskEntity> selectTaskTestEndManager(@Param("deptId") String deptId, @Param("userId") String userId);
    //项目组长查询已经完成的测试任务
    List<TaskEntity> selectTaskTestEndGroup(@Param("userId") String userId, @Param("deptId")String  deptId, @Param("userId1") String userId1);
    //开发组成员查询已经完成的测试任务
    List<TaskEntity> selectTaskEndEmployee(@Param("userId") String userId);
    //测试组组长查询已经完成的测试任务
    List<TaskEntity> selectAllTestTaskEnd(@Param("userId") String userId, @Param("deptId") String deptId);
    //测试组成员查询已经完成的测试任务
    List<TaskEntity> selectMyTestTaskEnd(@Param("userId") String userId);
    //查询全部正在进行中的任务
    List<TaskEntity> selectAllTaskProgress();
}
