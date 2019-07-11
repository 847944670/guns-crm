package com.stylefeng.guns.modular.pm.project.entity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.stylefeng.guns.modular.pm.task.entity.TaskEntity;

import java.util.List;

/**
 * 项目表
 */
@TableName("sys_pm_project")
public class Project {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    private String projectName;//项目名称

    private String projectDescription;//项目描述

    private String startTime;//项目开始时间

    private String endTime;//项目结束时间

    private String time;//项目研发周期

    private List<TaskEntity> accepterEmployee;// 领任务的员工（被指派人）
    private String fileId;//附件名称所在的div的ID
    private String attachmentName;//附件文件名称
    private String marks;//备注
    private String createDate;//创建时间
    private String updateDate;//更新时间
    private String createUser;//创建者
    private String updateUser;//更新者

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    public List<TaskEntity> getAccepterEmployee() {
        return accepterEmployee;
    }

    public void setAccepterEmployee(List<TaskEntity> accepterEmployee) {
        this.accepterEmployee = accepterEmployee;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", time='" + time + '\'' +
                ", accepterEmployee=" + accepterEmployee +
                ", fileId='" + fileId + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", marks='" + marks + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}