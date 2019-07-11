package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 销售机会表
 * </p>
 *
 * @author wzb
 * @since 2018-09-30
 */
@TableName("co_crm_salechance")
public class CrmSalechance extends Model<CrmSalechance> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 客户UUID
     */
    private Long customerId;
    /**
     * 销售机会时间
     */
    private Date createDate;
    /**
     * 销售机会类型
     */
    private Integer changeType;
    /**
     * 销售人员ID
     */
    private Long saleId;
    /**
     * 销售金额
     */
    private BigDecimal amount;
    /**
     * 销售数量
     */
    private BigDecimal number;
    /**
     * 销售单价
     */
    private BigDecimal unitPrice;
    /**
     * 销售状态
     */
    private Integer fstate;
    /**
     * 结单日期
     */
    private Date finishDate;
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

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getFstate() {
        return fstate;
    }

    public void setFstate(Integer fstate) {
        this.fstate = fstate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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

    public BigDecimal getNumber() {
		return number;
	}

	public void setNumber(BigDecimal number) {
		this.number = number;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CrmSalechance{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", createDate=" + createDate +
        ", changeType=" + changeType +
        ", saleId=" + saleId +
        ", amount=" + amount +
        ", unitPrice=" + unitPrice +
        ", number=" + number +
        ", fstate=" + fstate +
        ", finishDate=" + finishDate +
        ", IsDelete=" + IsDelete +
        ", other1=" + other1 +
        ", other2=" + other2 +
        ", other3=" + other3 +
        "}";
    }
}
