package com.woodpecker.entity.payment;

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
 * 类描述:〈acct_cash_flow表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "acct_cash_flow")
public class AcctCashFlowEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "serial_no")
  private String serialNo;

  @Column(name = "account_no")
  private String accountNo;

  @Column(name = "account_name")
  private String accountName;

  @Column(name = "account_value")
  private String accountValue;

  @Column(name = "user_id")
  private String userId;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "account_type")
  private String accountType;

  @Column(name = "property")
  private String property;

  @Column(name = "in_out_flag")
  private Byte inOutFlag;

  @Column(name = "change_type")
  private Byte changeType;

  @Column(name = "pre_amount")
  private BigDecimal preAmount;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "cash_amount")
  private BigDecimal cashAmount;

  @Column(name = "uncash_amount")
  private BigDecimal uncashAmount;

  @Column(name = "freeze_cash_amount")
  private BigDecimal freezeCashAmount;

  @Column(name = "freeze_uncash_amount")
  private BigDecimal freezeUncashAmount;

  @Column(name = "ref_type")
  private String refType;

  @Column(name = "ref_value")
  private String refValue;

  @Column(name = "is_settled")
  private Byte isSettled;

  @Column(name = "settle_date")
  private Date settleDate;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "last_update_date")
  private Date lastUpdate_date;

  @Column(name = "is_deprecated")
  private Byte isDeprecated;


  public String getSerialNo() {
    return serialNo;
  }

  public void setSerialNo(String serialNo) {
    this.serialNo = serialNo;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public String getAccountValue() {
    return accountValue;
  }

  public void setAccountValue(String accountValue) {
    this.accountValue = accountValue;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public Byte getInOutFlag() {
    return inOutFlag;
  }

  public void setInOutFlag(Byte inOutFlag) {
    this.inOutFlag = inOutFlag;
  }

  public Byte getChangeType() {
    return changeType;
  }

  public void setChangeType(Byte changeType) {
    this.changeType = changeType;
  }

  public BigDecimal getPreAmount() {
    return preAmount;
  }

  public void setPreAmount(BigDecimal preAmount) {
    this.preAmount = preAmount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public BigDecimal getCashAmount() {
    return cashAmount;
  }

  public void setCashAmount(BigDecimal cashAmount) {
    this.cashAmount = cashAmount;
  }

  public BigDecimal getUncashAmount() {
    return uncashAmount;
  }

  public void setUncashAmount(BigDecimal uncashAmount) {
    this.uncashAmount = uncashAmount;
  }

  public BigDecimal getFreezeCashAmount() {
    return freezeCashAmount;
  }

  public void setFreezeCashAmount(BigDecimal freezeCashAmount) {
    this.freezeCashAmount = freezeCashAmount;
  }

  public BigDecimal getFreezeUncashAmount() {
    return freezeUncashAmount;
  }

  public void setFreezeUncashAmount(BigDecimal freezeUncashAmount) {
    this.freezeUncashAmount = freezeUncashAmount;
  }

  public String getRefType() {
    return refType;
  }

  public void setRefType(String refType) {
    this.refType = refType;
  }

  public String getRefValue() {
    return refValue;
  }

  public void setRefValue(String refValue) {
    this.refValue = refValue;
  }

  public Byte getIsSettled() {
    return isSettled;
  }

  public void setIsSettled(Byte isSettled) {
    this.isSettled = isSettled;
  }

  public Date getSettleDate() {
    return settleDate;
  }

  public void setSettleDate(Date settleDate) {
    this.settleDate = settleDate;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getLastUpdate_date() {
    return lastUpdate_date;
  }

  public void setLastUpdate_date(Date lastUpdate_date) {
    this.lastUpdate_date = lastUpdate_date;
  }

  public Byte getIsDeprecated() {
    return isDeprecated;
  }

  public void setIsDeprecated(Byte isDeprecated) {
    this.isDeprecated = isDeprecated;
  }


  @Override
  public String toString() {
    return "AcctCashFlowEntity{" +
        "serialNo='" + serialNo + '\'' +
        ", accountNo='" + accountNo + '\'' +
        ", accountName='" + accountName + '\'' +
        ", accountValue='" + accountValue + '\'' +
        ", userId='" + userId + '\'' +
        ", userName='" + userName + '\'' +
        ", accountType='" + accountType + '\'' +
        ", property='" + property + '\'' +
        ", inOutFlag=" + inOutFlag +
        ", changeType=" + changeType +
        ", preAmount=" + preAmount +
        ", amount=" + amount +
        ", cashAmount=" + cashAmount +
        ", uncashAmount=" + uncashAmount +
        ", freezeCashAmount=" + freezeCashAmount +
        ", freezeUncashAmount=" + freezeUncashAmount +
        ", refType='" + refType + '\'' +
        ", refValue='" + refValue + '\'' +
        ", isSettled=" + isSettled +
        ", settleDate=" + settleDate +
        ", createDate=" + createDate +
        ", lastUpdate_date=" + lastUpdate_date +
        ", isDeprecated=" + isDeprecated +
        '}';
  }
}
