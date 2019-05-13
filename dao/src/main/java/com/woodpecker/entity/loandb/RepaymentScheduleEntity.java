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
 * 类描述:〈t_repayment_schedule表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_repayment_schedule")
public class RepaymentScheduleEntity implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "LoanOrderId")
  private Integer loanOrderId;

  @Column(name = "BusiNessPlatformId")
  private Integer busiNessPlatformId;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "Stage")
  private Byte stage;

  @Column(name = "StartAt")
  private Date startAt;

  @Column(name = "DueAt")
  private Date dueAt;

  @Column(name = "UserDueAt")
  private Date userDueAt;

  @Column(name = "RepayAt")
  private Date repayAt;

  @Column(name = "IsClear")
  private Byte isClear;

  @Column(name = "CanClear")
  private Byte canClear;

  @Column(name = "SerialNum")
  private String serialNum;

  @Column(name = "Capital")
  private BigDecimal capital;

  @Column(name = "Interest")
  private BigDecimal interest;

  @Column(name = "Gratuity")
  private BigDecimal gratuity;

  @Column(name = "Penalty")
  private BigDecimal penalty;

  @Column(name = "Compensation")
  private BigDecimal compensation;

  @Column(name = "DebtInterest")
  private BigDecimal debtInterest;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "CiticCanPay")
  private BigDecimal citicCanPay;

  @Column(name = "RepaidCapital")
  private BigDecimal repaidCapital;

  @Column(name = "RepaidInterest")
  private BigDecimal repaidInterest;

  @Column(name = "RepaidGratuity")
  private BigDecimal repaidGratuity;

  @Column(name = "RepaidPenalty")
  private BigDecimal repaidPenalty;

  @Column(name = "RepaidCapitalCitic")
  private BigDecimal repaidCapitalCitic;

  @Column(name = "RepaidInterestCitic")
  private BigDecimal repaidInterestCitic;

  @Column(name = "RepaidGratuityCitic")
  private BigDecimal repaidGratuityCitic;

  @Column(name = "RepaidPenaltyCitic")
  private BigDecimal repaidPenaltyCitic;

  @Column(name = "RepaidAmount")
  private BigDecimal repaidAmount;

  @Column(name = "RepaidAmountCitic")
  private BigDecimal repaidAmountCitic;

  @Column(name = "Exemption")
  private BigDecimal exemption;

  @Column(name = "IsRepaidAdvance")
  private Byte isRepaidAdvance;

  @Column(name = "OverDue")
  private Integer overDue;

  @Column(name = "Status")
  private Byte status;

  @Column(name = "Uuid")
  private String uuid;

  @Column(name = "UpdatedType")
  private Byte updatedType;

  @Column(name = "IsDeprecated")
  private Byte isDeprecated;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "UserStartAt")
  private Date userStartAt;

  @Column(name = "PlatGratuity")
  private BigDecimal platGratuity;

  @Column(name = "OwnGratuity")
  private BigDecimal ownGratuity;

  @Column(name = "RepaidPlatGratuity")
  private BigDecimal repaidPlatGratuity;

  @Column(name = "RepaidOwnGratuity")
  private BigDecimal repaidOwnGratuity;

  @Column(name = "DeductStatus")
  private Byte deductStatus;

  @Column(name = "PlatRepayId")
  private String platRepayId;

  @Column(name = "Version")
  private Byte version;

  @Column(name = "GlobalPlatformId")
  private Integer globalPlatformId;

  @Column(name = "CompositeCompanyId")
  private Long compositeCompanyId;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getLoanOrderId() {
    return loanOrderId;
  }

  public void setLoanOrderId(Integer loanOrderId) {
    this.loanOrderId = loanOrderId;
  }

  public Integer getBusiNessPlatformId() {
    return busiNessPlatformId;
  }

  public void setBusiNessPlatformId(Integer busiNessPlatformId) {
    this.busiNessPlatformId = busiNessPlatformId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Byte getStage() {
    return stage;
  }

  public void setStage(Byte stage) {
    this.stage = stage;
  }

  public Date getStartAt() {
    return startAt;
  }

  public void setStartAt(Date startAt) {
    this.startAt = startAt;
  }

  public Date getDueAt() {
    return dueAt;
  }

  public void setDueAt(Date dueAt) {
    this.dueAt = dueAt;
  }

  public Date getUserDueAt() {
    return userDueAt;
  }

  public void setUserDueAt(Date userDueAt) {
    this.userDueAt = userDueAt;
  }

  public Date getRepayAt() {
    return repayAt;
  }

  public void setRepayAt(Date repayAt) {
    this.repayAt = repayAt;
  }

  public Byte getIsClear() {
    return isClear;
  }

  public void setIsClear(Byte isClear) {
    this.isClear = isClear;
  }

  public Byte getCanClear() {
    return canClear;
  }

  public void setCanClear(Byte canClear) {
    this.canClear = canClear;
  }

  public String getSerialNum() {
    return serialNum;
  }

  public void setSerialNum(String serialNum) {
    this.serialNum = serialNum;
  }

  public BigDecimal getCapital() {
    return capital;
  }

  public void setCapital(BigDecimal capital) {
    this.capital = capital;
  }

  public BigDecimal getInterest() {
    return interest;
  }

  public void setInterest(BigDecimal interest) {
    this.interest = interest;
  }

  public BigDecimal getGratuity() {
    return gratuity;
  }

  public void setGratuity(BigDecimal gratuity) {
    this.gratuity = gratuity;
  }

  public BigDecimal getPenalty() {
    return penalty;
  }

  public void setPenalty(BigDecimal penalty) {
    this.penalty = penalty;
  }

  public BigDecimal getCompensation() {
    return compensation;
  }

  public void setCompensation(BigDecimal compensation) {
    this.compensation = compensation;
  }

  public BigDecimal getDebtInterest() {
    return debtInterest;
  }

  public void setDebtInterest(BigDecimal debtInterest) {
    this.debtInterest = debtInterest;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getCiticCanPay() {
    return citicCanPay;
  }

  public void setCiticCanPay(BigDecimal citicCanPay) {
    this.citicCanPay = citicCanPay;
  }

  public BigDecimal getRepaidCapital() {
    return repaidCapital;
  }

  public void setRepaidCapital(BigDecimal repaidCapital) {
    this.repaidCapital = repaidCapital;
  }

  public BigDecimal getRepaidInterest() {
    return repaidInterest;
  }

  public void setRepaidInterest(BigDecimal repaidInterest) {
    this.repaidInterest = repaidInterest;
  }

  public BigDecimal getRepaidGratuity() {
    return repaidGratuity;
  }

  public void setRepaidGratuity(BigDecimal repaidGratuity) {
    this.repaidGratuity = repaidGratuity;
  }

  public BigDecimal getRepaidPenalty() {
    return repaidPenalty;
  }

  public void setRepaidPenalty(BigDecimal repaidPenalty) {
    this.repaidPenalty = repaidPenalty;
  }

  public BigDecimal getRepaidCapitalCitic() {
    return repaidCapitalCitic;
  }

  public void setRepaidCapitalCitic(BigDecimal repaidCapitalCitic) {
    this.repaidCapitalCitic = repaidCapitalCitic;
  }

  public BigDecimal getRepaidInterestCitic() {
    return repaidInterestCitic;
  }

  public void setRepaidInterestCitic(BigDecimal repaidInterestCitic) {
    this.repaidInterestCitic = repaidInterestCitic;
  }

  public BigDecimal getRepaidGratuityCitic() {
    return repaidGratuityCitic;
  }

  public void setRepaidGratuityCitic(BigDecimal repaidGratuityCitic) {
    this.repaidGratuityCitic = repaidGratuityCitic;
  }

  public BigDecimal getRepaidPenaltyCitic() {
    return repaidPenaltyCitic;
  }

  public void setRepaidPenaltyCitic(BigDecimal repaidPenaltyCitic) {
    this.repaidPenaltyCitic = repaidPenaltyCitic;
  }

  public BigDecimal getRepaidAmount() {
    return repaidAmount;
  }

  public void setRepaidAmount(BigDecimal repaidAmount) {
    this.repaidAmount = repaidAmount;
  }

  public BigDecimal getRepaidAmountCitic() {
    return repaidAmountCitic;
  }

  public void setRepaidAmountCitic(BigDecimal repaidAmountCitic) {
    this.repaidAmountCitic = repaidAmountCitic;
  }

  public BigDecimal getExemption() {
    return exemption;
  }

  public void setExemption(BigDecimal exemption) {
    this.exemption = exemption;
  }

  public Byte getIsRepaidAdvance() {
    return isRepaidAdvance;
  }

  public void setIsRepaidAdvance(Byte isRepaidAdvance) {
    this.isRepaidAdvance = isRepaidAdvance;
  }

  public Integer getOverDue() {
    return overDue;
  }

  public void setOverDue(Integer overDue) {
    this.overDue = overDue;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Byte getUpdatedType() {
    return updatedType;
  }

  public void setUpdatedType(Byte updatedType) {
    this.updatedType = updatedType;
  }

  public Byte getIsDeprecated() {
    return isDeprecated;
  }

  public void setIsDeprecated(Byte isDeprecated) {
    this.isDeprecated = isDeprecated;
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

  public Date getUserStartAt() {
    return userStartAt;
  }

  public void setUserStartAt(Date userStartAt) {
    this.userStartAt = userStartAt;
  }

  public BigDecimal getPlatGratuity() {
    return platGratuity;
  }

  public void setPlatGratuity(BigDecimal platGratuity) {
    this.platGratuity = platGratuity;
  }

  public BigDecimal getOwnGratuity() {
    return ownGratuity;
  }

  public void setOwnGratuity(BigDecimal ownGratuity) {
    this.ownGratuity = ownGratuity;
  }

  public BigDecimal getRepaidPlatGratuity() {
    return repaidPlatGratuity;
  }

  public void setRepaidPlatGratuity(BigDecimal repaidPlatGratuity) {
    this.repaidPlatGratuity = repaidPlatGratuity;
  }

  public BigDecimal getRepaidOwnGratuity() {
    return repaidOwnGratuity;
  }

  public void setRepaidOwnGratuity(BigDecimal repaidOwnGratuity) {
    this.repaidOwnGratuity = repaidOwnGratuity;
  }

  public Byte getDeductStatus() {
    return deductStatus;
  }

  public void setDeductStatus(Byte deductStatus) {
    this.deductStatus = deductStatus;
  }

  public String getPlatRepayId() {
    return platRepayId;
  }

  public void setPlatRepayId(String platRepayId) {
    this.platRepayId = platRepayId;
  }

  public Byte getVersion() {
    return version;
  }

  public void setVersion(Byte version) {
    this.version = version;
  }

  public Integer getGlobalPlatformId() {
    return globalPlatformId;
  }

  public void setGlobalPlatformId(Integer globalPlatformId) {
    this.globalPlatformId = globalPlatformId;
  }

  public Long getCompositeCompanyId() {
    return compositeCompanyId;
  }

  public void setCompositeCompanyId(Long compositeCompanyId) {
    this.compositeCompanyId = compositeCompanyId;
  }

  @Override
  public String toString() {
    return "RepaymentScheduleEntity{" +
        "id=" + id +
        ", loanOrderId=" + loanOrderId +
        ", busiNessPlatformId=" + busiNessPlatformId +
        ", userId=" + userId +
        ", stage=" + stage +
        ", startAt=" + startAt +
        ", dueAt=" + dueAt +
        ", userDueAt=" + userDueAt +
        ", repayAt=" + repayAt +
        ", isClear=" + isClear +
        ", canClear=" + canClear +
        ", serialNum='" + serialNum + '\'' +
        ", capital=" + capital +
        ", interest=" + interest +
        ", gratuity=" + gratuity +
        ", penalty=" + penalty +
        ", compensation=" + compensation +
        ", debtInterest=" + debtInterest +
        ", amount=" + amount +
        ", citicCanPay=" + citicCanPay +
        ", repaidCapital=" + repaidCapital +
        ", repaidInterest=" + repaidInterest +
        ", repaidGratuity=" + repaidGratuity +
        ", repaidPenalty=" + repaidPenalty +
        ", repaidCapitalCitic=" + repaidCapitalCitic +
        ", repaidInterestCitic=" + repaidInterestCitic +
        ", repaidGratuityCitic=" + repaidGratuityCitic +
        ", repaidPenaltyCitic=" + repaidPenaltyCitic +
        ", repaidAmount=" + repaidAmount +
        ", repaidAmountCitic=" + repaidAmountCitic +
        ", exemption=" + exemption +
        ", isRepaidAdvance=" + isRepaidAdvance +
        ", overDue=" + overDue +
        ", status=" + status +
        ", uuid='" + uuid + '\'' +
        ", updatedType=" + updatedType +
        ", isDeprecated=" + isDeprecated +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", userStartAt=" + userStartAt +
        ", platGratuity=" + platGratuity +
        ", ownGratuity=" + ownGratuity +
        ", repaidPlatGratuity=" + repaidPlatGratuity +
        ", repaidOwnGratuity=" + repaidOwnGratuity +
        ", deductStatus=" + deductStatus +
        ", platRepayId='" + platRepayId + '\'' +
        ", version=" + version +
        ", globalPlatformId=" + globalPlatformId +
        ", compositeCompanyId=" + compositeCompanyId +
        '}';
  }
}
