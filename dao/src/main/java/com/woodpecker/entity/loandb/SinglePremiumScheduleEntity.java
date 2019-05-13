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
 * 类描述:〈qs_single_premium_schedule表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "qs_single_premium_schedule")
public class SinglePremiumScheduleEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "loan_order_id")
  private Integer loanOrderId;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "global_platform_id")
  private Integer globalPlatformId;

  @Column(name = "gratuity")
  private BigDecimal gratuity;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "start_at")
  private Date startAt;

  @Column(name = "user_due_at")
  private Date userDueAt;

  @Column(name = "cleared")
  private Byte cleared;

  @Column(name = "repay_at")
  private Date repayAt;

  @Column(name = "repaid_gratuity")
  private BigDecimal repaidGratuity;

  @Column(name = "repaid_amount")
  private BigDecimal repaidAmount;

  @Column(name = "status")
  private Byte status;

  @Column(name = "deduct_status")
  private Byte deductStatus;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "last_update_date")
  private Date lastUpdateDate;

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

  public Integer getLoanOrderId() {
    return loanOrderId;
  }

  public void setLoanOrderId(Integer loanOrderId) {
    this.loanOrderId = loanOrderId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getGlobalPlatformId() {
    return globalPlatformId;
  }

  public void setGlobalPlatformId(Integer globalPlatformId) {
    this.globalPlatformId = globalPlatformId;
  }

  public BigDecimal getGratuity() {
    return gratuity;
  }

  public void setGratuity(BigDecimal gratuity) {
    this.gratuity = gratuity;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Date getStartAt() {
    return startAt;
  }

  public void setStartAt(Date startAt) {
    this.startAt = startAt;
  }

  public Date getUserDueAt() {
    return userDueAt;
  }

  public void setUserDueAt(Date userDueAt) {
    this.userDueAt = userDueAt;
  }

  public Byte getCleared() {
    return cleared;
  }

  public void setCleared(Byte cleared) {
    this.cleared = cleared;
  }

  public Date getRepayAt() {
    return repayAt;
  }

  public void setRepayAt(Date repayAt) {
    this.repayAt = repayAt;
  }

  public BigDecimal getRepaidGratuity() {
    return repaidGratuity;
  }

  public void setRepaidGratuity(BigDecimal repaidGratuity) {
    this.repaidGratuity = repaidGratuity;
  }

  public BigDecimal getRepaidAmount() {
    return repaidAmount;
  }

  public void setRepaidAmount(BigDecimal repaidAmount) {
    this.repaidAmount = repaidAmount;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Byte getDeductStatus() {
    return deductStatus;
  }

  public void setDeductStatus(Byte deductStatus) {
    this.deductStatus = deductStatus;
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
    return "SinglePremiumScheduleEntity{" +
        "id=" + id +
        ", loanOrderId=" + loanOrderId +
        ", userId=" + userId +
        ", globalPlatformId=" + globalPlatformId +
        ", gratuity=" + gratuity +
        ", amount=" + amount +
        ", startAt=" + startAt +
        ", userDueAt=" + userDueAt +
        ", cleared=" + cleared +
        ", repayAt=" + repayAt +
        ", repaidGratuity=" + repaidGratuity +
        ", repaidAmount=" + repaidAmount +
        ", status=" + status +
        ", deductStatus=" + deductStatus +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", version=" + version +
        ", compositeCompanyId=" + compositeCompanyId +
        '}';
  }
}
