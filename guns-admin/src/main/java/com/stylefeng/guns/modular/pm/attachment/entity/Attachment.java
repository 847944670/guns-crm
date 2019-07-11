package com.stylefeng.guns.modular.pm.attachment.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author sunwei
 * @date 2019/6/24
 */
@TableName("sys_pm_attachment")
public class Attachment {

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    private String attachmentName;
    private String fileId;
    private String sourceAttachmentName;
    private String filePath;
    private String projectId;
    private String createDate;
    private String updateDate;
    private String createUser;
    private String updateUser;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getSourceAttachmentName() {
        return sourceAttachmentName;
    }

    public void setSourceAttachmentName(String sourceAttachmentName) {
        this.sourceAttachmentName = sourceAttachmentName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    @Override
    public String toString() {
        return "Attachment{" +
                "id='" + id + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", fileId='" + fileId + '\'' +
                ", sourceAttachmentName='" + sourceAttachmentName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", projectId='" + projectId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                '}';
    }
}