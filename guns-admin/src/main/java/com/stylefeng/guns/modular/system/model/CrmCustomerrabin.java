package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户回收表
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
@TableName("co_crm_customerrabin")
public class CrmCustomerrabin extends Model<CrmCustomerrabin> {

    private static final long serialVersionUID = 1L;

    /**
     * 回收客户表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 客户UUID 外键
     */
    private Long customerId;
    /**
     * 前销售人
     */
    private Long cusOldSaler;
    /**
     * 回收时间
     */
    private Date createTime;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCusOldSaler() {
        return cusOldSaler;
    }

    public void setCusOldSaler(Long cusOldSaler) {
        this.cusOldSaler = cusOldSaler;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        return "CrmCustomerrabin{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", cusOldSaler=" + cusOldSaler +
        ", createTime=" + createTime +
        ", other1=" + other1 +
        ", other2=" + other2 +
        ", other3=" + other3 +
        "}";
    }
}
