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
 * 类描述:〈t_thirdparty_schedule表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_thirdparty_schedule")
public class ThirdpartyScheduleEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "OrderId")
  private Integer orderId;

  @Column(name = "LoanOrderId")
  private Integer loanOrderId;

  @Column(name = "PackageNo")
  private String packageNo;

  @Column(name = "ScheduleId")
  private Integer scheduleId;

  @Column(name = "userId")
  private Integer userId;

  @Column(name = "stage")
  private Byte stage;

  @Column(name = "PlatName")
  private String platName;

  @Column(name = "GlobalPlatformId")
  private Integer globalPlatformId;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "capital")
  private BigDecimal capital;

  @Column(name = "interest")
  private BigDecimal interest;

  @Column(name = "Fee")
  private BigDecimal fee;

  @Column(name = "startAt")
  private Date startAt;

  @Column(name = "DueAt")
  private Date dueAt;

  @Column(name = "repaymentMethod")
  private String repaymentMethod;

  @Column(name = "WithdrawTime")
  private Date withdrawTime;

  @Column(name = "WithdrawSerialNum")
  private String withdrawSerialNum;

  @Column(name = "CreateTime")
  private Date createTime;

  @Column(name = "LastUpdateTime")
  private Date lastUpdateTime;

  @Column(name = "status")
  private String status;

  @Column(name = "ActualDueAt")
  private Date actualDueAt;

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

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getLoanOrderId() {
    return loanOrderId;
  }

  public void setLoanOrderId(Integer loanOrderId) {
    this.loanOrderId = loanOrderId;
  }

  public String getPackageNo() {
    return packageNo;
  }

  public void setPackageNo(String packageNo) {
    this.packageNo = packageNo;
  }

  public Integer getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Integer scheduleId) {
    this.scheduleId = scheduleId;
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

  public String getPlatName() {
    return platName;
  }

  public void setPlatName(String platName) {
    this.platName = platName;
  }

  public Integer getGlobalPlatformId() {
    return globalPlatformId;
  }

  public void setGlobalPlatformId(Integer globalPlatformId) {
    this.globalPlatformId = globalPlatformId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
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

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
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

  public String getRepaymentMethod() {
    return repaymentMethod;
  }

  public void setRepaymentMethod(String repaymentMethod) {
    this.repaymentMethod = repaymentMethod;
  }

  public Date getWithdrawTime() {
    return withdrawTime;
  }

  public void setWithdrawTime(Date withdrawTime) {
    this.withdrawTime = withdrawTime;
  }

  public String getWithdrawSerialNum() {
    return withdrawSerialNum;
  }

  public void setWithdrawSerialNum(String withdrawSerialNum) {
    this.withdrawSerialNum = withdrawSerialNum;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getActualDueAt() {
    return actualDueAt;
  }

  public void setActualDueAt(Date actualDueAt) {
    this.actualDueAt = actualDueAt;
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
    return "ThirdpartyScheduleEntity{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", loanOrderId=" + loanOrderId +
        ", packageNo='" + packageNo + '\'' +
        ", scheduleId=" + scheduleId +
        ", userId=" + userId +
        ", stage=" + stage +
        ", platName='" + platName + '\'' +
        ", globalPlatformId=" + globalPlatformId +
        ", amount=" + amount +
        ", capital=" + capital +
        ", interest=" + interest +
        ", fee=" + fee +
        ", startAt=" + startAt +
        ", dueAt=" + dueAt +
        ", repaymentMethod='" + repaymentMethod + '\'' +
        ", withdrawTime=" + withdrawTime +
        ", withdrawSerialNum='" + withdrawSerialNum + '\'' +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        ", status='" + status + '\'' +
        ", actualDueAt=" + actualDueAt +
        ", version=" + version +
        ", compositeCompanyId=" + compositeCompanyId +
        '}';
  }
}
