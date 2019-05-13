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
 * 类描述:〈t_deduct_record表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_deduct_record")
public class DeductRecordEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "DeductTaskId")
  private Integer deductTaskId;

  @Column(name = "Count")
  private String count;

  @Column(name = "PlatformId")
  private Byte platformId;

  @Column(name = "SourceType")
  private Byte sourceType;

  @Column(name = "FromAccountNo")
  private String fromAccountNo;

  @Column(name = "FromUserName")
  private String fromUserName;

  @Column(name = "FromUserIdCard")
  private String fromUserIdCard;

  @Column(name = "FromMobileNo")
  private String fromMobileNo;

  @Column(name = "FromBankCode")
  private String fromBankCode;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "Status")
  private Integer status;

  @Column(name = "OurSerialId")
  private String ourSerialId;

  @Column(name = "LoanBillingId")
  private String loanBillingId;

  @Column(name = "TheirSerialId")
  private String theirSerialId;

  @Column(name = "ReturnCode")
  private String returnCode;

  @Column(name = "ReturnDesc")
  private String returnDesc;

  @Column(name = "ExceptionLog")
  private String exceptionLog;

  @Column(name = "CallBackResult")
  private String callBackResult;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "UpdateDate")
  private Date updateDate;

  @Column(name = "PayChannel")
  private String payChannel;

  @Column(name = "Channel")
  private String channel;

  @Column(name = "IsDeprecated")
  private Byte isDeprecated;

  @Column(name = "SendSmsStatus")
  private Integer sendSmsStatus;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getDeductTaskId() {
    return deductTaskId;
  }

  public void setDeductTaskId(Integer deductTaskId) {
    this.deductTaskId = deductTaskId;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public Byte getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Byte platformId) {
    this.platformId = platformId;
  }

  public Byte getSourceType() {
    return sourceType;
  }

  public void setSourceType(Byte sourceType) {
    this.sourceType = sourceType;
  }

  public String getFromAccountNo() {
    return fromAccountNo;
  }

  public void setFromAccountNo(String fromAccountNo) {
    this.fromAccountNo = fromAccountNo;
  }

  public String getFromUserName() {
    return fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public String getFromUserIdCard() {
    return fromUserIdCard;
  }

  public void setFromUserIdCard(String fromUserIdCard) {
    this.fromUserIdCard = fromUserIdCard;
  }

  public String getFromMobileNo() {
    return fromMobileNo;
  }

  public void setFromMobileNo(String fromMobileNo) {
    this.fromMobileNo = fromMobileNo;
  }

  public String getFromBankCode() {
    return fromBankCode;
  }

  public void setFromBankCode(String fromBankCode) {
    this.fromBankCode = fromBankCode;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getOurSerialId() {
    return ourSerialId;
  }

  public void setOurSerialId(String ourSerialId) {
    this.ourSerialId = ourSerialId;
  }

  public String getLoanBillingId() {
    return loanBillingId;
  }

  public void setLoanBillingId(String loanBillingId) {
    this.loanBillingId = loanBillingId;
  }

  public String getTheirSerialId() {
    return theirSerialId;
  }

  public void setTheirSerialId(String theirSerialId) {
    this.theirSerialId = theirSerialId;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnDesc() {
    return returnDesc;
  }

  public void setReturnDesc(String returnDesc) {
    this.returnDesc = returnDesc;
  }

  public String getExceptionLog() {
    return exceptionLog;
  }

  public void setExceptionLog(String exceptionLog) {
    this.exceptionLog = exceptionLog;
  }

  public String getCallBackResult() {
    return callBackResult;
  }

  public void setCallBackResult(String callBackResult) {
    this.callBackResult = callBackResult;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public String getPayChannel() {
    return payChannel;
  }

  public void setPayChannel(String payChannel) {
    this.payChannel = payChannel;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public Byte getIsDeprecated() {
    return isDeprecated;
  }

  public void setIsDeprecated(Byte isDeprecated) {
    this.isDeprecated = isDeprecated;
  }

  public Integer getSendSmsStatus() {
    return sendSmsStatus;
  }

  public void setSendSmsStatus(Integer sendSmsStatus) {
    this.sendSmsStatus = sendSmsStatus;
  }


  @Override
  public String toString() {
    return "DeductRecordEntity{" +
        "id=" + id +
        ", deductTaskId=" + deductTaskId +
        ", count='" + count + '\'' +
        ", platformId=" + platformId +
        ", sourceType=" + sourceType +
        ", fromAccountNo='" + fromAccountNo + '\'' +
        ", fromUserName='" + fromUserName + '\'' +
        ", fromUserIdCard='" + fromUserIdCard + '\'' +
        ", fromMobileNo='" + fromMobileNo + '\'' +
        ", fromBankCode='" + fromBankCode + '\'' +
        ", amount=" + amount +
        ", status=" + status +
        ", ourSerialId='" + ourSerialId + '\'' +
        ", loanBillingId='" + loanBillingId + '\'' +
        ", theirSerialId='" + theirSerialId + '\'' +
        ", returnCode='" + returnCode + '\'' +
        ", returnDesc='" + returnDesc + '\'' +
        ", exceptionLog='" + exceptionLog + '\'' +
        ", callBackResult='" + callBackResult + '\'' +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", payChannel='" + payChannel + '\'' +
        ", channel='" + channel + '\'' +
        ", isDeprecated=" + isDeprecated +
        ", sendSmsStatus=" + sendSmsStatus +
        '}';
  }
}
