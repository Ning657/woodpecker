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
 * 类描述:〈t_tp_trade_order表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_tp_trade_order")
public class TradeOrderEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TradeNo")
  private String tradeNo;

  @Column(name = "PayNo")
  private String payNo;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "AcctType")
  private String acctType;

  @Column(name = "AcctNo")
  private String acctNo;

  @Column(name = "UserIdCard")
  private String userIdCard;

  @Column(name = "UserName")
  private String userName;

  @Column(name = "Mobile")
  private String mobile;

  @Column(name = "BankId")
  private String bankId;

  @Column(name = "BizType")
  private String bizType;

  @Column(name = "BizValue")
  private String bizValue;

  @Column(name = "BizExtData")
  private String bizExtData;

  @Column(name = "BizAmount")
  private BigDecimal bizAmount;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "SourceType")
  private String sourceType;

  @Column(name = "PayWay")
  private Byte payWay;

  @Column(name = "PayPlatform")
  private Byte payPlatform;

  @Column(name = "SysValue")
  private String sysValue;

  @Column(name = "Uuid")
  private String uuid;

  @Column(name = "TaskTime")
  private Date taskTime;

  @Column(name = "ProcessTime")
  private Date processTime;

  @Column(name = "StartTime")
  private Date startTime;

  @Column(name = "EndTime")
  private Date endTime;

  @Column(name = "ExpireTime")
  private Integer expireTime;

  @Column(name = "SettleTime")
  private Date settleTime;

  @Column(name = "QueryId")
  private String queryId;

  @Column(name = "MerBizData")
  private String merBizData;

  @Column(name = "TranStatus")
  private Integer tranStatus;

  @Column(name = "CurrentAction")
  private String currentAction;

  @Column(name = "ActionStatus")
  private Integer actionStatus;

  @Column(name = "Memo")
  private String memo;

  @Column(name = "Remark")
  private String remark;

  @Column(name = "Channel")
  private String channel;

  @Column(name = "FrontCBWay")
  private String frontCBWay;

  @Column(name = "FrontCBParameter")
  private String frontCBParameter;

  @Column(name = "SysCBWay")
  private String sysCBWay;

  @Column(name = "SysCBParameter")
  private String sysCBParameter;

  @Column(name = "FailureCBWay")
  private String failureCBWay;

  @Column(name = "FailureCBParameter")
  private String failureCBParameter;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "IsDeprecated")
  private Byte isDeprecated;


  public String getTradeNo() {
    return tradeNo;
  }

  public void setTradeNo(String tradeNo) {
    this.tradeNo = tradeNo;
  }

  public String getPayNo() {
    return payNo;
  }

  public void setPayNo(String payNo) {
    this.payNo = payNo;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getAcctType() {
    return acctType;
  }

  public void setAcctType(String acctType) {
    this.acctType = acctType;
  }

  public String getAcctNo() {
    return acctNo;
  }

  public void setAcctNo(String acctNo) {
    this.acctNo = acctNo;
  }

  public String getUserIdCard() {
    return userIdCard;
  }

  public void setUserIdCard(String userIdCard) {
    this.userIdCard = userIdCard;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getBizType() {
    return bizType;
  }

  public void setBizType(String bizType) {
    this.bizType = bizType;
  }

  public String getBizValue() {
    return bizValue;
  }

  public void setBizValue(String bizValue) {
    this.bizValue = bizValue;
  }

  public String getBizExtData() {
    return bizExtData;
  }

  public void setBizExtData(String bizExtData) {
    this.bizExtData = bizExtData;
  }

  public BigDecimal getBizAmount() {
    return bizAmount;
  }

  public void setBizAmount(BigDecimal bizAmount) {
    this.bizAmount = bizAmount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getSourceType() {
    return sourceType;
  }

  public void setSourceType(String sourceType) {
    this.sourceType = sourceType;
  }

  public Byte getPayWay() {
    return payWay;
  }

  public void setPayWay(Byte payWay) {
    this.payWay = payWay;
  }

  public Byte getPayPlatform() {
    return payPlatform;
  }

  public void setPayPlatform(Byte payPlatform) {
    this.payPlatform = payPlatform;
  }

  public String getSysValue() {
    return sysValue;
  }

  public void setSysValue(String sysValue) {
    this.sysValue = sysValue;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public Date getTaskTime() {
    return taskTime;
  }

  public void setTaskTime(Date taskTime) {
    this.taskTime = taskTime;
  }

  public Date getProcessTime() {
    return processTime;
  }

  public void setProcessTime(Date processTime) {
    this.processTime = processTime;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Integer getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Integer expireTime) {
    this.expireTime = expireTime;
  }

  public Date getSettleTime() {
    return settleTime;
  }

  public void setSettleTime(Date settleTime) {
    this.settleTime = settleTime;
  }

  public String getQueryId() {
    return queryId;
  }

  public void setQueryId(String queryId) {
    this.queryId = queryId;
  }

  public String getMerBizData() {
    return merBizData;
  }

  public void setMerBizData(String merBizData) {
    this.merBizData = merBizData;
  }

  public Integer getTranStatus() {
    return tranStatus;
  }

  public void setTranStatus(Integer tranStatus) {
    this.tranStatus = tranStatus;
  }

  public String getCurrentAction() {
    return currentAction;
  }

  public void setCurrentAction(String currentAction) {
    this.currentAction = currentAction;
  }

  public Integer getActionStatus() {
    return actionStatus;
  }

  public void setActionStatus(Integer actionStatus) {
    this.actionStatus = actionStatus;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getFrontCBWay() {
    return frontCBWay;
  }

  public void setFrontCBWay(String frontCBWay) {
    this.frontCBWay = frontCBWay;
  }

  public String getFrontCBParameter() {
    return frontCBParameter;
  }

  public void setFrontCBParameter(String frontCBParameter) {
    this.frontCBParameter = frontCBParameter;
  }

  public String getSysCBWay() {
    return sysCBWay;
  }

  public void setSysCBWay(String sysCBWay) {
    this.sysCBWay = sysCBWay;
  }

  public String getSysCBParameter() {
    return sysCBParameter;
  }

  public void setSysCBParameter(String sysCBParameter) {
    this.sysCBParameter = sysCBParameter;
  }

  public String getFailureCBWay() {
    return failureCBWay;
  }

  public void setFailureCBWay(String failureCBWay) {
    this.failureCBWay = failureCBWay;
  }

  public String getFailureCBParameter() {
    return failureCBParameter;
  }

  public void setFailureCBParameter(String failureCBParameter) {
    this.failureCBParameter = failureCBParameter;
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

  public Byte getIsDeprecated() {
    return isDeprecated;
  }

  public void setIsDeprecated(Byte isDeprecated) {
    this.isDeprecated = isDeprecated;
  }


  @Override
  public String toString() {
    return "TradeOrderEntity{" +
        "tradeNo='" + tradeNo + '\'' +
        ", payNo='" + payNo + '\'' +
        ", userId=" + userId +
        ", acctType='" + acctType + '\'' +
        ", acctNo='" + acctNo + '\'' +
        ", userIdCard='" + userIdCard + '\'' +
        ", userName='" + userName + '\'' +
        ", mobile='" + mobile + '\'' +
        ", bankId='" + bankId + '\'' +
        ", bizType='" + bizType + '\'' +
        ", bizValue='" + bizValue + '\'' +
        ", bizExtData='" + bizExtData + '\'' +
        ", bizAmount=" + bizAmount +
        ", amount=" + amount +
        ", sourceType='" + sourceType + '\'' +
        ", payWay=" + payWay +
        ", payPlatform=" + payPlatform +
        ", sysValue='" + sysValue + '\'' +
        ", uuid='" + uuid + '\'' +
        ", taskTime=" + taskTime +
        ", processTime=" + processTime +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", expireTime=" + expireTime +
        ", settleTime=" + settleTime +
        ", queryId='" + queryId + '\'' +
        ", merBizData='" + merBizData + '\'' +
        ", tranStatus=" + tranStatus +
        ", currentAction='" + currentAction + '\'' +
        ", actionStatus=" + actionStatus +
        ", memo='" + memo + '\'' +
        ", remark='" + remark + '\'' +
        ", channel='" + channel + '\'' +
        ", frontCBWay='" + frontCBWay + '\'' +
        ", frontCBParameter='" + frontCBParameter + '\'' +
        ", sysCBWay='" + sysCBWay + '\'' +
        ", sysCBParameter='" + sysCBParameter + '\'' +
        ", failureCBWay='" + failureCBWay + '\'' +
        ", failureCBParameter='" + failureCBParameter + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isDeprecated=" + isDeprecated +
        '}';
  }
}
