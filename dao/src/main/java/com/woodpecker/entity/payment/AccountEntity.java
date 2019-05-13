package com.woodpecker.entity.payment;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈t_tp_account表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_tp_account")
public class AccountEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "orderNo")
  private String orderNo;

  @Column(name = "userId")
  private Long userId;

  @Column(name = "accountNo")
  private String accountNo;

  @Column(name = "accountType")
  private Byte accountType;

  @Column(name = "bankId")
  private String bankId;

  @Column(name = "idCardNo")
  private String idCardNo;

  @Column(name = "mobile")
  private String mobile;

  @Column(name = "serial")
  private Byte serial;

  @Column(name = "status")
  private Byte status;

  @Column(name = "channel")
  private String channel;

  @Column(name = "createDate")
  private Date createDate;

  @Column(name = "updateDate")
  private Date updateDate;

  @Column(name = "userName")
  private String userName;

  @Column(name = "failedChannel")
  private String failedChannel;

  @Column(name = "inStatus")
  private Byte inStatus;

  @Column(name = "outStatus")
  private Byte outStatus;

  @Column(name = "inFreezeFrom")
  private Date inFreezeFrom;

  @Column(name = "inFreezeTo")
  private Date inFreezeTo;

  @Column(name = "outFreezeFrom")
  private Date outFreezeFrom;

  @Column(name = "outFreezeTo")
  private Date outFreezeTo;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public Byte getAccountType() {
    return accountType;
  }

  public void setAccountType(Byte accountType) {
    this.accountType = accountType;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Byte getSerial() {
    return serial;
  }

  public void setSerial(Byte serial) {
    this.serial = serial;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFailedChannel() {
    return failedChannel;
  }

  public void setFailedChannel(String failedChannel) {
    this.failedChannel = failedChannel;
  }

  public Byte getInStatus() {
    return inStatus;
  }

  public void setInStatus(Byte inStatus) {
    this.inStatus = inStatus;
  }

  public Byte getOutStatus() {
    return outStatus;
  }

  public void setOutStatus(Byte outStatus) {
    this.outStatus = outStatus;
  }

  public Date getInFreezeFrom() {
    return inFreezeFrom;
  }

  public void setInFreezeFrom(Date inFreezeFrom) {
    this.inFreezeFrom = inFreezeFrom;
  }

  public Date getInFreezeTo() {
    return inFreezeTo;
  }

  public void setInFreezeTo(Date inFreezeTo) {
    this.inFreezeTo = inFreezeTo;
  }

  public Date getOutFreezeFrom() {
    return outFreezeFrom;
  }

  public void setOutFreezeFrom(Date outFreezeFrom) {
    this.outFreezeFrom = outFreezeFrom;
  }

  public Date getOutFreezeTo() {
    return outFreezeTo;
  }

  public void setOutFreezeTo(Date outFreezeTo) {
    this.outFreezeTo = outFreezeTo;
  }


  @Override
  public String toString() {
    return "AccountEntity{" +
        "id=" + id +
        ", orderNo='" + orderNo + '\'' +
        ", userId=" + userId +
        ", accountNo='" + accountNo + '\'' +
        ", accountType=" + accountType +
        ", bankId='" + bankId + '\'' +
        ", idCardNo='" + idCardNo + '\'' +
        ", mobile='" + mobile + '\'' +
        ", serial=" + serial +
        ", status=" + status +
        ", channel='" + channel + '\'' +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", userName='" + userName + '\'' +
        ", failedChannel='" + failedChannel + '\'' +
        ", inStatus=" + inStatus +
        ", outStatus=" + outStatus +
        ", inFreezeFrom=" + inFreezeFrom +
        ", inFreezeTo=" + inFreezeTo +
        ", outFreezeFrom=" + outFreezeFrom +
        ", outFreezeTo=" + outFreezeTo +
        '}';
  }
}
