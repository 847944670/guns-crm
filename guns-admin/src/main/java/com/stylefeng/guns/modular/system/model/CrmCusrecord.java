package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 跟进记录表
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
@TableName("co_crm_cusrecord")
public class CrmCusrecord extends Model<CrmCusrecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 客户UUID
     */
    private Long customerId;
    /**
     * 跟进记录时间
     */
    private Date createDate;
    /**
     * 删除标识符
     */
    private Integer IsDelete;
    /**
     * 跟进机会类型
     */
    private Integer followType;
    /**
     * 跟进业务
     */
    private String followBuiness;
    /**
     * 跟进详情
     */
    private String followDetail;
    /**
     * 销售人员ID
     */
    private Long salerId;
    /**
     * 其他1
     */
    private String other1;
    /**
     * 其他2
     */
    private String other2;
    /**
     * 其他3
     */
    private String other3;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(Integer IsDelete) {
        this.IsDelete = IsDelete;
    }

    public Integer getFollowType() {
        return followType;
    }

    public void setFollowType(Integer followType) {
        this.followType = followType;
    }

    public String getFollowBuiness() {
        return followBuiness;
    }

    public void setFollowBuiness(String followBuiness) {
        this.followBuiness = followBuiness;
    }

    public String getFollowDetail() {
        return followDetail;
    }

    public void setFollowDetail(String followDetail) {
        this.followDetail = followDetail;
    }

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CrmCusrecord{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", createDate=" + createDate +
        ", IsDelete=" + IsDelete +
        ", followType=" + followType +
        ", followBuiness=" + followBuiness +
        ", followDetail=" + followDetail +
        ", salerId=" + salerId +
        ", other1=" + other1 +
        ", other2=" + other2 +
        ", other3=" + other3 +
        "}";
    }
}
