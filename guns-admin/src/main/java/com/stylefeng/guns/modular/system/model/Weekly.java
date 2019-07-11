package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 秦渊渊
 * @since 2019-03-04
 */
@TableName("sys_staff_weekly")
public class Weekly extends Model<Weekly> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 提交周报员工的id
     */
    private Integer usernid;
    /**
     * 登录此帐号员工姓名
     */
    private String name;
    /**
     * 员工周报
     */
    private String weekly;
    /**
     * 员工提交时间
     */
    private Date time;
    /**
     * 要接收的用户id
     */
    private Integer userid;
    /**
     * 要接收的用户角色id
     */
    private Integer roleid;
    /**
     * 要接收的用户部门id
     */
    private Integer deptid;
    /**
     * 扩展字段一
     */
    private Integer extendone;
    /**
     * 扩展字段二
     */
    private String extendtwo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsernid() {
        return usernid;
    }

    public void setUsernid(Integer usernid) {
        this.usernid = usernid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getExtendone() {
        return extendone;
    }

    public void setExtendone(Integer extendone) {
        this.extendone = extendone;
    }

    public String getExtendtwo() {
        return extendtwo;
    }

    public void setExtendtwo(String extendtwo) {
        this.extendtwo = extendtwo;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Weekly{" +
        "id=" + id +
        ", usernid=" + usernid +
        ", name=" + name +
        ", weekly=" + weekly +
        ", time=" + time +
        ", userid=" + userid +
        ", roleid=" + roleid +
        ", deptid=" + deptid +
        ", extendone=" + extendone +
        ", extendtwo=" + extendtwo +
        "}";
    }
}
