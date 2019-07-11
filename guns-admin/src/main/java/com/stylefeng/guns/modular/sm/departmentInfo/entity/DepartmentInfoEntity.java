package com.stylefeng.guns.modular.sm.departmentInfo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("departmentInfo")
public class DepartmentInfoEntity {
    @TableId(value = "id",type = IdType.UUID)
    private String id;
    //系别姓名

    private String deptName;
    //系别专业
    private  String major;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "DepartmentInfoEntity{" +
                "id='" + id + '\'' +
                ", deptName='" + deptName + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
