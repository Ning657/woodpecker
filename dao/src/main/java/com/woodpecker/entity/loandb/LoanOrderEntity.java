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
 * 类描述:〈t_loan_order表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_loan_order")
public class LoanOrderEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "BusinessPlatformId")
  private Integer businessPlatformId;

  @Column(name = "GlobalPlatformId")
  private Integer globalPlatformId;

  @Column(name = "OrderId")
  private Integer orderId;

  @Column(name = "BatchId")
  private Integer batchId;

  @Column(name = "BankId")
  private Integer bankId;

  @Column(name = "TrustUnitId")
  private Long trustUnitId;

  @Column(name = "UserName")
  private String userName;

  @Column(name = "BankAccountId")
  private Long bankAccountId;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "ActualAmount")
  private BigDecimal actualAmount;

  @Column(name = "AccrueAmount")
  private BigDecimal accrueAmount;

  @Column(name = "CreditServiceAmount")
  private BigDecimal creditServiceAmount;

  @Column(name = "Period")
  private Byte period;

  @Column(name = "Frequency")
  private Integer frequency;

  @Column(name = "Interest")
  private BigDecimal interest;

  @Column(name = "InterestLender")
  private BigDecimal interestLender;

  @Column(name = "InterestOwn")
  private BigDecimal interestOwn;

  @Column(name = "PlatFee")
  private BigDecimal platFee;

  @Column(name = "InterestPlat")
  private BigDecimal interestPlat;

  @Column(name = "Status")
  private Byte status;

  @Column(name = "PlatReceivedAt")
  private Date platReceivedAt;

  @Column(name = "PlatPublishedAt")
  private Date platPublishedAt;

  @Column(name = "PlatFullfilledAt")
  private Date platFullfilledAt;

  @Column(name = "PlatfullfilledAtLong")
  private Long platfullfilledAtLong;

  @Column(name = "PlatTransferedAt")
  private Date platTransferedAt;

  @Column(name = "PlatProjectId")
  private String platProjectId;

  @Column(name = "LoanType")
  private Integer loanType;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "Category")
  private String category;

  @Column(name = "PayAt")
  private Date payAt;

  @Column(name = "PayType")
  private String payType;

  @Column(name = "appId")
  private Integer appId;

  @Column(name = "IsPacked")
  private Byte isPacked;

  @Column(name = "ExtStatus")
  private Integer extStatus;

  @Column(name = "CurrentStage")
  private Integer currentStage;

  @Column(name = "RepaymentType")
  private Byte repaymentType;

  @Column(name = "CanRepay")
  private Byte canRepay;

  @Column(name = "IsSupervise")
  private Byte isSupervise;

  @Column(name = "ZhanQiTime")
  private Date zhanQiTime;

  @Column(name = "CreditServiceFeeRate")
  private BigDecimal creditServiceFeeRate;

  @Column(name = "penaltyId")
  private Integer penaltyId;

  @Column(name = "SinglePremiumFlag")
  private Byte singlePremiumFlag;

  @Column(name = "repurchaseFlag")
  private Byte repurchaseFlag;

  @Column(name = "WangXinType")
  private Byte wangXinType;

  @Column(name = "UnionLoanFlag")
  private Byte unionLoanFlag;

  @Column(name = "repayConfigId")
  private Integer repayConfigId;

  @Column(name = "ApplyAmount")
  private BigDecimal applyAmount;

  @Column(name = "Version")
  private Byte version;

  @Column(name = "CompositeCompanyId")
  private Long compositeCompanyId;


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

  public Integer getBusinessPlatformId() {
    return businessPlatformId;
  }

  public void setBusinessPlatformId(Integer businessPlatformId) {
    this.businessPlatformId = businessPlatformId;
  }

  public Integer getGlobalPlatformId() {
    return globalPlatformId;
  }

  public void setGlobalPlatformId(Integer globalPlatformId) {
    this.globalPlatformId = globalPlatformId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getBatchId() {
    return batchId;
  }

  public void setBatchId(Integer batchId) {
    this.batchId = batchId;
  }

  public Integer getBankId() {
    return bankId;
  }

  public void setBankId(Integer bankId) {
    this.bankId = bankId;
  }

  public Long getTrustUnitId() {
    return trustUnitId;
  }

  public void setTrustUnitId(Long trustUnitId) {
    this.trustUnitId = trustUnitId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getBankAccountId() {
    return bankAccountId;
  }

  public void setBankAccountId(Long bankAccountId) {
    this.bankAccountId = bankAccountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getActualAmount() {
    return actualAmount;
  }

  public void setActualAmount(BigDecimal actualAmount) {
    this.actualAmount = actualAmount;
  }

  public BigDecimal getAccrueAmount() {
    return accrueAmount;
  }

  public void setAccrueAmount(BigDecimal accrueAmount) {
    this.accrueAmount = accrueAmount;
  }

  public BigDecimal getCreditServiceAmount() {
    return creditServiceAmount;
  }

  public void setCreditServiceAmount(BigDecimal creditServiceAmount) {
    this.creditServiceAmount = creditServiceAmount;
  }

  public Byte getPeriod() {
    return period;
  }

  public void setPeriod(Byte period) {
    this.period = period;
  }

  public Integer getFrequency() {
    return frequency;
  }

  public void setFrequency(Integer frequency) {
    this.frequency = frequency;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public BigDecimal getInterestLender() {
    return interestLender;
  }

  public void setInterestLender(BigDecimal interestLender) {
    this.interestLender = interestLender;
  }

  public BigDecimal getInterestOwn() {
    return interestOwn;
  }

  public void setInterestOwn(BigDecimal interestOwn) {
    this.interestOwn = interestOwn;
  }

  public BigDecimal getPlatFee() {
    return platFee;
  }

  public void setPlatFee(BigDecimal platFee) {
    this.platFee = platFee;
  }

  public BigDecimal getInterestPlat() {
    return interestPlat;
  }

  public void setInterestPlat(BigDecimal interestPlat) {
    this.interestPlat = interestPlat;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Date getPlatReceivedAt() {
    return platReceivedAt;
  }

  public void setPlatReceivedAt(Date platReceivedAt) {
    this.platReceivedAt = platReceivedAt;
  }

  public Date getPlatPublishedAt() {
    return platPublishedAt;
  }

  public void setPlatPublishedAt(Date platPublishedAt) {
    this.platPublishedAt = platPublishedAt;
  }

  public Date getPlatFullfilledAt() {
    return platFullfilledAt;
  }

  public void setPlatFullfilledAt(Date platFullfilledAt) {
    this.platFullfilledAt = platFullfilledAt;
  }

  public Long getPlatfullfilledAtLong() {
    return platfullfilledAtLong;
  }

  public void setPlatfullfilledAtLong(Long platfullfilledAtLong) {
    this.platfullfilledAtLong = platfullfilledAtLong;
  }

  public Date getPlatTransferedAt() {
    return platTransferedAt;
  }

  public void setPlatTransferedAt(Date platTransferedAt) {
    this.platTransferedAt = platTransferedAt;
  }

  public String getPlatProjectId() {
    return platProjectId;
  }

  public void setPlatProjectId(String platProjectId) {
    this.platProjectId = platProjectId;
  }

  public Integer getLoanType() {
    return loanType;
  }

  public void setLoanType(Integer loanType) {
    this.loanType = loanType;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public Date getPayAt() {
    return payAt;
  }

  public void setPayAt(Date payAt) {
    this.payAt = payAt;
  }

  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }

  public Integer getAppId() {
    return appId;
  }

  public void setAppId(Integer appId) {
    this.appId = appId;
  }

  public Byte getIsPacked() {
    return isPacked;
  }

  public void setIsPacked(Byte isPacked) {
    this.isPacked = isPacked;
  }

  public Integer getExtStatus() {
    return extStatus;
  }

  public void setExtStatus(Integer extStatus) {
    this.extStatus = extStatus;
  }

  public Integer getCurrentStage() {
    return currentStage;
  }

  public void setCurrentStage(Integer currentStage) {
    this.currentStage = currentStage;
  }

  public Byte getRepaymentType() {
    return repaymentType;
  }

  public void setRepaymentType(Byte repaymentType) {
    this.repaymentType = repaymentType;
  }

  public Byte getCanRepay() {
    return canRepay;
  }

  public void setCanRepay(Byte canRepay) {
    this.canRepay = canRepay;
  }

  public Byte getIsSupervise() {
    return isSupervise;
  }

  public void setIsSupervise(Byte isSupervise) {
    this.isSupervise = isSupervise;
  }

  public Date getZhanQiTime() {
    return zhanQiTime;
  }

  public void setZhanQiTime(Date zhanQiTime) {
    this.zhanQiTime = zhanQiTime;
  }

  public BigDecimal getCreditServiceFeeRate() {
    return creditServiceFeeRate;
  }

  public void setCreditServiceFeeRate(BigDecimal creditServiceFeeRate) {
    this.creditServiceFeeRate = creditServiceFeeRate;
  }

  public Integer getPenaltyId() {
    return penaltyId;
  }

  public void setPenaltyId(Integer penaltyId) {
    this.penaltyId = penaltyId;
  }

  public Byte getSinglePremiumFlag() {
    return singlePremiumFlag;
  }

  public void setSinglePremiumFlag(Byte singlePremiumFlag) {
    this.singlePremiumFlag = singlePremiumFlag;
  }

  public Byte getRepurchaseFlag() {
    return repurchaseFlag;
  }

  public void setRepurchaseFlag(Byte repurchaseFlag) {
    this.repurchaseFlag = repurchaseFlag;
  }

  public Byte getWangXinType() {
    return wangXinType;
  }

  public void setWangXinType(Byte wangXinType) {
    this.wangXinType = wangXinType;
  }

  public Byte getUnionLoanFlag() {
    return unionLoanFlag;
  }

  public void setUnionLoanFlag(Byte unionLoanFlag) {
    this.unionLoanFlag = unionLoanFlag;
  }

  public Integer getRepayConfigId() {
    return repayConfigId;
  }

  public void setRepayConfigId(Integer repayConfigId) {
    this.repayConfigId = repayConfigId;
  }

  public BigDecimal getApplyAmount() {
    return applyAmount;
  }

  public void setApplyAmount(BigDecimal applyAmount) {
    this.applyAmount = applyAmount;
  }

  public Byte getVersion() {
    return version;
  }

  public void setVersion(Byte version) {
    this.version = version;
  }

  public Long getCompositeCompanyId() {
    return compositeCompanyId;
  }

  public void setCompositeCompanyId(Long compositeCompanyId) {
    this.compositeCompanyId = compositeCompanyId;
  }


  @Override
  public String toString() {
    return "LoanOrderEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", businessPlatformId=" + businessPlatformId +
        ", globalPlatformId=" + globalPlatformId +
        ", orderId=" + orderId +
        ", batchId=" + batchId +
        ", bankId=" + bankId +
        ", trustUnitId=" + trustUnitId +
        ", userName='" + userName + '\'' +
        ", bankAccountId=" + bankAccountId +
        ", amount=" + amount +
        ", actualAmount=" + actualAmount +
        ", accrueAmount=" + accrueAmount +
        ", creditServiceAmount=" + creditServiceAmount +
        ", period=" + period +
        ", frequency=" + frequency +
        ", interest=" + interest +
        ", interestLender=" + interestLender +
        ", interestOwn=" + interestOwn +
        ", platFee=" + platFee +
        ", interestPlat=" + interestPlat +
        ", status=" + status +
        ", platReceivedAt=" + platReceivedAt +
        ", platPublishedAt=" + platPublishedAt +
        ", platFullfilledAt=" + platFullfilledAt +
        ", platfullfilledAtLong=" + platfullfilledAtLong +
        ", platTransferedAt=" + platTransferedAt +
        ", platProjectId='" + platProjectId + '\'' +
        ", loanType=" + loanType +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", category='" + category + '\'' +
        ", payAt=" + payAt +
        ", payType='" + payType + '\'' +
        ", appId=" + appId +
        ", isPacked=" + isPacked +
        ", extStatus=" + extStatus +
        ", currentStage=" + currentStage +
        ", repaymentType=" + repaymentType +
        ", canRepay=" + canRepay +
        ", isSupervise=" + isSupervise +
        ", zhanQiTime=" + zhanQiTime +
        ", creditServiceFeeRate=" + creditServiceFeeRate +
        ", penaltyId=" + penaltyId +
        ", singlePremiumFlag=" + singlePremiumFlag +
        ", repurchaseFlag=" + repurchaseFlag +
        ", wangXinType=" + wangXinType +
        ", unionLoanFlag=" + unionLoanFlag +
        ", repayConfigId=" + repayConfigId +
        ", applyAmount=" + applyAmount +
        ", version=" + version +
        ", compositeCompanyId=" + compositeCompanyId +
        '}';
  }
  
}
