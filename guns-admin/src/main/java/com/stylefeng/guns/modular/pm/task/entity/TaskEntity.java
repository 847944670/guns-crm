package com.stylefeng.guns.modular.pm.task.entity;

/**
 * @author sunwei
 * @date 2019/5/28
 */

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 任务
 */
@TableName("sys_pm_task")
public class TaskEntity {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    private String name; //任务名称
    private String employeeRole;//角色
    private String deptName;//所属部门
    private String projectName;//所在项目
    private String moduleName;//所在模块
    private String funName;//需要完成的功能
    private String projectId;//所在项目
    private String moduleId;//所在项目
    private String funId;//所在项目
    private String projectTime;//项目研发周期(单位:天)
    private String moduleTime;//模块研发周期(单位:天)
    private String functionTime;//功能开发周期(单位:天)
    private boolean isDelayOfProject;//所处项目是否延时
    private boolean isDelayOfModule;//所处模块是否延时
    private boolean isDelayOfFunction;//所处功能是否延时
    private String delayDayOfProject;//项目延时天数
    private String delayDayOfModule;//模块延时天数
    private String delayDayOfFunction;//功能延时天数
    private String status;//任务是否开启:0为进行中，1.完成
    private String statusName;//进行中|已完成
    private String statusOfTest;//测试状态：0为未开始 ，1为进行中，2为已完成
    private String statusOfTestName;//测试状态名称：进行中、已完成
    private String statusOfOperation;//运维状态：0为未开始 ，1为进行中，2为已完成
    private String statusOfOperationName;//运维状态名称：进行中、已完成
    private String redeployStatus;//任务转派，状态码=0：已转派。1：未转派
    private String redeployStatusName;//任务转派，状态码=0：已转派。1：未转派
    private String taskType;//任务类型：1.下放，2.转派
    private String redeployUser;//转派者
    private String getTaskUserName;//领取开发任务的人
    private String userId;//领取任务的人的id（研发）
    private String testOfUserId;//领取任务的人的id（测试）
    private String testOfUserName;//领取任务的人的姓名（测试）
    private String operationOfUserId;//领取任务的人的id（运维）
    private String operationOfUserName;//领取任务的人的姓名（运维）
    private String createTaskUserDeptId;//当前任务为当前登陆者下放的部门
    private String marks;//备注
    private String createDate;//创建时间
    private String updateDate;//更新时间
    private String createUser;//创建者
    private String updateUser;//更新者
    private String createTaskUserId;//当前创建任务的人的id
    private String testBug;//测试bug
    private String type;//任务类型：module任务，function任务，test任务，redeploy任务，operation任务

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTestBug() {
        return testBug;
    }

    public void setTestBug(String testBug) {
        this.testBug = testBug;
    }

    public String getRedeployStatusName() {
        return redeployStatusName;
    }

    public void setRedeployStatusName(String redeployStatusName) {
        this.redeployStatusName = redeployStatusName;
    }

    public String getTestOfUserId() {
        return testOfUserId;
    }

    public void setTestOfUserId(String testOfUserId) {
        this.testOfUserId = testOfUserId;
    }

    public String getTestOfUserName() {
        return testOfUserName;
    }

    public void setTestOfUserName(String testOfUserName) {
        this.testOfUserName = testOfUserName;
    }

    public String getOperationOfUserId() {
        return operationOfUserId;
    }

    public void setOperationOfUserId(String operationOfUserId) {
        this.operationOfUserId = operationOfUserId;
    }

    public String getOperationOfUserName() {
        return operationOfUserName;
    }

    public void setOperationOfUserName(String operationOfUserName) {
        this.operationOfUserName = operationOfUserName;
    }

    public String getStatusOfTestName() {
        return statusOfTestName;
    }

    public void setStatusOfTestName(String statusOfTestName) {
        this.statusOfTestName = statusOfTestName;
    }

    public String getStatusOfOperationName() {
        return statusOfOperationName;
    }

    public void setStatusOfOperationName(String statusOfOperationName) {
        this.statusOfOperationName = statusOfOperationName;
    }

    public String getCreateTaskUserId() {
        return createTaskUserId;
    }

    public void setCreateTaskUserId(String createTaskUserId) {
        this.createTaskUserId = createTaskUserId;
    }

    public String getGetTaskUserName() {
        return getTaskUserName;
    }

    public void setGetTaskUserName(String getTaskUserName) {
        this.getTaskUserName = getTaskUserName;
    }

    public String getRedeployUser() {
        return redeployUser;
    }

    public void setRedeployUser(String redeployUser) {
        this.redeployUser = redeployUser;
    }

    public String getRedeployStatus() {
        return redeployStatus;
    }

    public void setRedeployStatus(String redeployStatus) {
        this.redeployStatus = redeployStatus;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    public String getModuleTime() {
        return moduleTime;
    }

    public void setModuleTime(String moduleTime) {
        this.moduleTime = moduleTime;
    }

    public String getFunctionTime() {
        return functionTime;
    }

    public void setFunctionTime(String functionTime) {
        this.functionTime = functionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusOfTest() {
        return statusOfTest;
    }

    public void setStatusOfTest(String statusOfTest) {
        this.statusOfTest = statusOfTest;
    }

    public String getStatusOfOperation() {
        return statusOfOperation;
    }

    public void setStatusOfOperation(String statusOfOperation) {
        this.statusOfOperation = statusOfOperation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTaskUserDeptId() {
        return createTaskUserDeptId;
    }

    public void setCreateTaskUserDeptId(String createTaskUserDeptId) {
        this.createTaskUserDeptId = createTaskUserDeptId;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public boolean isDelayOfProject() {
        return isDelayOfProject;
    }

    public void setDelayOfProject(boolean delayOfProject) {
        isDelayOfProject = delayOfProject;
    }

    public boolean isDelayOfModule() {
        return isDelayOfModule;
    }

    public void setDelayOfModule(boolean delayOfModule) {
        isDelayOfModule = delayOfModule;
    }

    public boolean isDelayOfFunction() {
        return isDelayOfFunction;
    }

    public void setDelayOfFunction(boolean delayOfFunction) {
        isDelayOfFunction = delayOfFunction;
    }

    public String getDelayDayOfProject() {
        return delayDayOfProject;
    }

    public void setDelayDayOfProject(String delayDayOfProject) {
        this.delayDayOfProject = delayDayOfProject;
    }

    public String getDelayDayOfModule() {
        return delayDayOfModule;
    }

    public void setDelayDayOfModule(String delayDayOfModule) {
        this.delayDayOfModule = delayDayOfModule;
    }

    public String getDelayDayOfFunction() {
        return delayDayOfFunction;
    }

    public void setDelayDayOfFunction(String delayDayOfFunction) {
        this.delayDayOfFunction = delayDayOfFunction;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getFunId() {
        return funId;
    }

    public void setFunId(String funId) {
        this.funId = funId;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", deptName='" + deptName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", funName='" + funName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", funId='" + funId + '\'' +
                ", projectTime='" + projectTime + '\'' +
                ", moduleTime='" + moduleTime + '\'' +
                ", functionTime='" + functionTime + '\'' +
                ", isDelayOfProject=" + isDelayOfProject +
                ", isDelayOfModule=" + isDelayOfModule +
                ", isDelayOfFunction=" + isDelayOfFunction +
                ", delayDayOfProject='" + delayDayOfProject + '\'' +
                ", delayDayOfModule='" + delayDayOfModule + '\'' +
                ", delayDayOfFunction='" + delayDayOfFunction + '\'' +
                ", status='" + status + '\'' +
                ", statusName='" + statusName + '\'' +
                ", statusOfTest='" + statusOfTest + '\'' +
                ", statusOfTestName='" + statusOfTestName + '\'' +
                ", statusOfOperation='" + statusOfOperation + '\'' +
                ", statusOfOperationName='" + statusOfOperationName + '\'' +
                ", redeployStatus='" + redeployStatus + '\'' +
                ", redeployStatusName='" + redeployStatusName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", redeployUser='" + redeployUser + '\'' +
                ", getTaskUserName='" + getTaskUserName + '\'' +
                ", userId='" + userId + '\'' +
                ", testOfUserId='" + testOfUserId + '\'' +
                ", testOfUserName='" + testOfUserName + '\'' +
                ", operationOfUserId='" + operationOfUserId + '\'' +
                ", operationOfUserName='" + operationOfUserName + '\'' +
                ", createTaskUserDeptId='" + createTaskUserDeptId + '\'' +
                ", marks='" + marks + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", createTaskUserId='" + createTaskUserId + '\'' +
                ", testBug='" + testBug + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}