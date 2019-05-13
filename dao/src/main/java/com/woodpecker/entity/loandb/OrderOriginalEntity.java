package com.woodpecker.entity.loandb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈t_order_original表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_order_original")
public class OrderOriginalEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "UserName")
  private String userName;

  @Column(name = "ChannelId")
  private Integer channelId;

  @Column(name = "AppId")
  private Integer appId;

  @Column(name = "RepaymentMethod")
  private Byte repaymentMethod;

  @Column(name = "BankAccountId")
  private Long bankAccountId;

  @Column(name = "ProductCategoryId")
  private Integer productCategoryId;

  @Column(name = "Period")
  private Integer period;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "ApplyAmount")
  private BigDecimal applyAmount;

  @Column(name = "Interest")
  private BigDecimal interest;

  @Column(name = "ApplyTime")
  private Date applyTime;

  @Column(name = "LoanUse")
  private String loanUse;

  @Column(name = "OrderType")
  private Byte orderType;

  @Column(name = "isQueue")
  private Byte isQueue;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "ProductCode")
  private String productCode;

  @Column(name = "Status")
  private Byte status;

  @Column(name = "BindCardType")
  private Byte bindCardType;

  @Column(name = "AuditType")
  private Byte auditType;

  @Column(name = "AuditTime")
  private Date auditTime;

  @Column(name = "AuditResult")
  private Byte auditResult;

  @Column(name = "RefusedType")
  private Byte refusedType;

  @Column(name = "FinalRoute")
  private String finalRoute;

  @Column(name = "Uuid")
  private String uuid;

  @Column(name = "Frequency")
  private Integer frequency;

  @Column(name = "CreditId")
  private Long creditId;

  @Column(name = "UnionLoanFlag")
  private Byte unionLoanFlag;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getChannelId() {
    return channelId;
  }

  public void setChannelId(Integer channelId) {
    this.channelId = channelId;
  }

  public Integer getAppId() {
    return appId;
  }

  public void setAppId(Integer appId) {
    this.appId = appId;
  }

  public Byte getRepaymentMethod() {
    return repaymentMethod;
  }

  public void setRepaymentMethod(Byte repaymentMethod) {
    this.repaymentMethod = repaymentMethod;
  }

  public Long getBankAccountId() {
    return bankAccountId;
  }

  public void setBankAccountId(Long bankAccountId) {
    this.bankAccountId = bankAccountId;
  }

  public Integer getProductCategoryId() {
    return productCategoryId;
  }

  public void setProductCategoryId(Integer productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  public Integer getPeriod() {
    return period;
  }

  public void setPeriod(Integer period) {
    this.period = period;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getApplyAmount() {
    return applyAmount;
  }

  public void setApplyAmount(BigDecimal applyAmount) {
    this.applyAmount = applyAmount;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public Date getApplyTime() {
    return applyTime;
  }

  public void setApplyTime(Date applyTime) {
    this.applyTime = applyTime;
  }

  public String getLoanUse() {
    return loanUse;
  }

  public void setLoanUse(String loanUse) {
    this.loanUse = loanUse;
  }

  public Byte getOrderType() {
    return orderType;
  }

  public void setOrderType(Byte orderType) {
    this.orderType = orderType;
  }

  public Byte getIsQueue() {
    return isQueue;
  }

  public void setIsQueue(Byte isQueue) {
    this.isQueue = isQueue;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Byte getBindCardType() {
    return bindCardType;
  }

  public void setBindCardType(Byte bindCardType) {
    this.bindCardType = bindCardType;
  }

  public Byte getAuditType() {
    return auditType;
  }

  public void setAuditType(Byte auditType) {
    this.auditType = auditType;
  }

  public Date getAuditTime() {
    return auditTime;
  }

  public void setAuditTime(Date auditTime) {
    this.auditTime = auditTime;
  }

  public Byte getAuditResult() {
    return auditResult;
  }

  public void setAuditResult(Byte auditResult) {
    this.auditResult = auditResult;
  }

  public Byte getRefusedType() {
    return refusedType;
  }

  public void setRefusedType(Byte refusedType) {
    this.refusedType = refusedType;
  }

  public String getFinalRoute() {
    return finalRoute;
  }

  public void setFinalRoute(String finalRoute) {
    this.finalRoute = finalRoute;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Integer getFrequency() {
    return frequency;
  }

  public void setFrequency(Integer frequency) {
    this.frequency = frequency;
  }

  public Long getCreditId() {
    return creditId;
  }

  public void setCreditId(Long creditId) {
    this.creditId = creditId;
  }

  public Byte getUnionLoanFlag() {
    return unionLoanFlag;
  }

  public void setUnionLoanFlag(Byte unionLoanFlag) {
    this.unionLoanFlag = unionLoanFlag;
  }

  @Override
  public String toString() {
    return "OrderOriginalEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", userName='" + userName + '\'' +
        ", channelId=" + channelId +
        ", appId=" + appId +
        ", repaymentMethod=" + repaymentMethod +
        ", bankAccountId=" + bankAccountId +
        ", productCategoryId=" + productCategoryId +
        ", period=" + period +
        ", amount=" + amount +
        ", applyAmount=" + applyAmount +
        ", interest=" + interest +
        ", applyTime=" + applyTime +
        ", loanUse='" + loanUse + '\'' +
        ", orderType=" + orderType +
        ", isQueue=" + isQueue +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", productCode='" + productCode + '\'' +
        ", status=" + status +
        ", bindCardType=" + bindCardType +
        ", auditType=" + auditType +
        ", auditTime=" + auditTime +
        ", auditResult=" + auditResult +
        ", refusedType=" + refusedType +
        ", finalRoute='" + finalRoute + '\'' +
        ", uuid='" + uuid + '\'' +
        ", frequency=" + frequency +
        ", creditId=" + creditId +
        ", unionLoanFlag=" + unionLoanFlag +
        '}';
  }
}
