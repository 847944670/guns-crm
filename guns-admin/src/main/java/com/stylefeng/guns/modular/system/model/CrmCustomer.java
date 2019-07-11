package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
@TableName("co_crm_customer")
public class CrmCustomer extends Model<CrmCustomer> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户UUID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 客户行业
     */
    private Integer customerHy;
    /**
     * 客户星级
     */
    private Integer customerStar;
    /**
     * 客户创建时间
     */
    private Date createTime;
    /**
     * 客户联系电话
     */
    private String cusPhoneNum;
    /**
     * 跟进状态
     */
    private Integer customerState;
    /**
     * 客户简称
     */
    private String customerMess;
    /**
     * 联系人
     */
    private String customerRelation;
    /**
     * 地区
     */
    private String area;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 客户官网
     */
    private String www;
    /**
     * 备注
     */
    private String customerRemark;
    /**
     * 客户来源
     */
    private String customerFrom;
    /**
     * 销售人名称
     */
    private String salerName;
    /**
     * 销售时间
     */
    private Date salerCreateTime;
    /**
     * 销售人ID
     */
    private Long salerId;
    /**
     * 删除标识符
     */
    private Integer IsDelete;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerHy() {
        return customerHy;
    }

    public void setCustomerHy(Integer customerHy) {
        this.customerHy = customerHy;
    }

    public Integer getCustomerStar() {
        return customerStar;
    }

    public void setCustomerStar(Integer customerStar) {
        this.customerStar = customerStar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCusPhoneNum() {
        return cusPhoneNum;
    }

    public void setCusPhoneNum(String cusPhoneNum) {
        this.cusPhoneNum = cusPhoneNum;
    }

    public Integer getCustomerState() {
        return customerState;
    }

    public void setCustomerState(Integer customerState) {
        this.customerState = customerState;
    }

    public String getCustomerMess() {
        return customerMess;
    }

    public void setCustomerMess(String customerMess) {
        this.customerMess = customerMess;
    }

    public String getCustomerRelation() {
        return customerRelation;
    }

    public void setCustomerRelation(String customerRelation) {
        this.customerRelation = customerRelation;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getCustomerFrom() {
        return customerFrom;
    }

    public void setCustomerFrom(String customerFrom) {
        this.customerFrom = customerFrom;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public Date getSalerCreateTime() {
        return salerCreateTime;
    }

    public void setSalerCreateTime(Date salerCreateTime) {
        this.salerCreateTime = salerCreateTime;
    }

    public Long getSalerId() {
        return salerId;
    }

    public void setSalerId(Long salerId) {
        this.salerId = salerId;
    }

    public Integer getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(Integer IsDelete) {
        this.IsDelete = IsDelete;
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
        return "CrmCustomer{" +
        "id=" + id +
        ", customerName=" + customerName +
        ", customerHy=" + customerHy +
        ", customerStar=" + customerStar +
        ", createTime=" + createTime +
        ", cusPhoneNum=" + cusPhoneNum +
        ", customerState=" + customerState +
        ", customerMess=" + customerMess +
        ", customerRelation=" + customerRelation +
        ", area=" + area +
        ", address=" + address +
        ", www=" + www +
        ", customerRemark=" + customerRemark +
        ", customerFrom=" + customerFrom +
        ", salerName=" + salerName +
        ", salerCreateTime=" + salerCreateTime +
        ", salerId=" + salerId +
        ", IsDelete=" + IsDelete +
        ", other1=" + other1 +
        ", other2=" + other2 +
        ", other3=" + other3 +
        "}";
    }
}
