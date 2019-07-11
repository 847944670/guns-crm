package com.stylefeng.guns.modular.pm.role.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 项目参与人员角色表
 */

@TableName("sys_pm_role")
public class EmployeeRole {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String roleName;//角色名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "EmployeeRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}