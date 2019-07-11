package com.stylefeng.guns.modular.pm.module.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author sunwei
 * @date 2019/5/29
 */
@TableName("sys_pm_module")
public class Module {

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    private String moduleName;//模块名称

    private String projectId;//所属项目id
    private String projectName;//所屬項目名稱
    private String moduleDescription;//模块描述
    private String startTime;//开始时间

    private String endTime;//结束时间

    private String time;//开发周期
    private String createUserId;//当前创建者ID
    private String createUserDeptId;//当前创建者部门ID


    private String accepterEmployee;//领取任务的人

    private String marks;//备注
    private String createDate;//创建时间
    private String updateDate;//更新时间
    private String createUser;//创建者
    private String updateUser;//更新者

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserDeptId() {
        return createUserDeptId;
    }

    public void setCreateUserDeptId(String createUserDeptId) {
        this.createUserDeptId = createUserDeptId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id='" + id + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", moduleDescription='" + moduleDescription + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", time='" + time + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", createUserDeptId='" + createUserDeptId + '\'' +
                ", accepterEmployee='" + accepterEmployee + '\'' +
                ", marks='" + marks + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}