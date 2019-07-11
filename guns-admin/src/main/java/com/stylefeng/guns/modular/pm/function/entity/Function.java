package com.stylefeng.guns.modular.pm.function.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author sunwei
 * @date 2019/5/29
 */
@TableName("sys_pm_function")
public class Function {
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    private String functionName;//功能名称

    private String functionDescription;//功能描述

    private String moduleId;//所属模块id

    private String projectId; //所属项目id
    private String moduleName;//所属模块名称
    private String projectName;//所属项目名称

    private String startTime;//开始时间

    private String endTime;//结束时间

    private String time;//研发周期
    private String accepterEmployee;// 领任务的员工（被指派人）

    private String marks;//备注
    private String createDate;//创建时间
    private String updateDate;//更新时间
    private String createUser;//创建者
    private String updateUser;//更新者

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "Function{" +
                "id=" + id +
                ", functionName='" + functionName + '\'' +
                ", functionDescription='" + functionDescription + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", modlueName='" + moduleName + '\'' +
                ", projectName='" + projectName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", time='" + time + '\'' +
                ", accepterEmployee='" + accepterEmployee + '\'' +
                ", marks='" + marks + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAccepterEmployee() {
        return accepterEmployee;
    }

    public void setAccepterEmployee(String accepterEmployee) {
        this.accepterEmployee = accepterEmployee;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }



    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}