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
 * 类描述:〈t_deduct_request表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_deduct_request")
public class DeductRequestEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "DeductTaskId")
  private Integer deductTaskId;

  @Column(name = "SourceType")
  private Byte sourceType;

  @Column(name = "PlatformId")
  private Byte platformId;

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

  @Column(name = "FromCustId")
  private Integer fromCustId;

  @Column(name = "SchedulerIds")
  private String schedulerIds;

  @Column(name = "Uuid")
  private String uuid;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "DeliverWay")
  private Byte deliverWay;

  @Column(name = "Status")
  private Integer status;

  @Column(name = "Description")
  private String description;

  @Column(name = "TaskDate")
  private Date taskDate;

  @Column(name = "EnqueueDate")
  private Date enqueueDate;

  @Column(name = "ProcessDate")
  private Date processDate;

  @Column(name = "CallBackClass")
  private String callBackClass;

  @Column(name = "CallBackParameter")
  private String callBackParameter;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "IsDeprecated")
  private Byte isDeprecated;


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

  public Byte getSourceType() {
    return sourceType;
  }

  public void setSourceType(Byte sourceType) {
    this.sourceType = sourceType;
  }

  public Byte getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Byte platformId) {
    this.platformId = platformId;
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

  public Integer getFromCustId() {
    return fromCustId;
  }

  public void setFromCustId(Integer fromCustId) {
    this.fromCustId = fromCustId;
  }

  public String getSchedulerIds() {
    return schedulerIds;
  }

  public void setSchedulerIds(String schedulerIds) {
    this.schedulerIds = schedulerIds;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Byte getDeliverWay() {
    return deliverWay;
  }

  public void setDeliverWay(Byte deliverWay) {
    this.deliverWay = deliverWay;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getTaskDate() {
    return taskDate;
  }

  public void setTaskDate(Date taskDate) {
    this.taskDate = taskDate;
  }

  public Date getEnqueueDate() {
    return enqueueDate;
  }

  public void setEnqueueDate(Date enqueueDate) {
    this.enqueueDate = enqueueDate;
  }

  public Date getProcessDate() {
    return processDate;
  }

  public void setProcessDate(Date processDate) {
    this.processDate = processDate;
  }

  public String getCallBackClass() {
    return callBackClass;
  }

  public void setCallBackClass(String callBackClass) {
    this.callBackClass = callBackClass;
  }

  public String getCallBackParameter() {
    return callBackParameter;
  }

  public void setCallBackParameter(String callBackParameter) {
    this.callBackParameter = callBackParameter;
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
    return "DeductRequestEntity{" +
        "id=" + id +
        ", deductTaskId=" + deductTaskId +
        ", sourceType=" + sourceType +
        ", platformId=" + platformId +
        ", fromAccountNo='" + fromAccountNo + '\'' +
        ", fromUserName='" + fromUserName + '\'' +
        ", fromUserIdCard='" + fromUserIdCard + '\'' +
        ", fromMobileNo='" + fromMobileNo + '\'' +
        ", fromBankCode='" + fromBankCode + '\'' +
        ", fromCustId=" + fromCustId +
        ", schedulerIds='" + schedulerIds + '\'' +
        ", uuid='" + uuid + '\'' +
        ", amount=" + amount +
        ", deliverWay=" + deliverWay +
        ", status=" + status +
        ", description='" + description + '\'' +
        ", taskDate=" + taskDate +
        ", enqueueDate=" + enqueueDate +
        ", processDate=" + processDate +
        ", callBackClass='" + callBackClass + '\'' +
        ", callBackParameter='" + callBackParameter + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isDeprecated=" + isDeprecated +
        '}';
  }
}
