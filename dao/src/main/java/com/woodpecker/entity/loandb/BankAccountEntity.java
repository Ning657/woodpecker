package com.woodpecker.entity.loandb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈t_bank_account表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_bank_account")
public class BankAccountEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "Account")
  private String account;

  @Column(name = "Name")
  private String name;

  @Column(name = "CertificateNo")
  private String certificateNo;

  @Column(name = "CertificateType")
  private Integer certificateType;

  @Column(name = "Mobile")
  private String mobile;

  @Column(name = "BankId")
  private Integer bankId;

  @Column(name = "BindId")
  private String bindId;

  @Column(name = "Province")
  private String province;

  @Column(name = "City")
  private String city;

  @Column(name = "AccountType")
  private Byte accountType;

  @Column(name = "Status")
  private Byte status;

  @Column(name = "IsDefault")
  private Byte isDefault;

  @Column(name = "Channel")
  private String channel;

  @Column(name = "Prefix")
  private String prefix;

  @Column(name = "PreferPlats")
  private String preferPlats;

  @Column(name = "Comment")
  private String comment;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "BankBranchName")
  private String bankBranchName;

  @Column(name = "InStatus")
  private Byte inStatus;

  @Column(name = "OutStatus")
  private Byte outStatus;

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

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCertificateNo() {
    return certificateNo;
  }

  public void setCertificateNo(String certificateNo) {
    this.certificateNo = certificateNo;
  }

  public Integer getCertificateType() {
    return certificateType;
  }

  public void setCertificateType(Integer certificateType) {
    this.certificateType = certificateType;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Integer getBankId() {
    return bankId;
  }

  public void setBankId(Integer bankId) {
    this.bankId = bankId;
  }

  public String getBindId() {
    return bindId;
  }

  public void setBindId(String bindId) {
    this.bindId = bindId;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Byte getAccountType() {
    return accountType;
  }

  public void setAccountType(Byte accountType) {
    this.accountType = accountType;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Byte getIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Byte isDefault) {
    this.isDefault = isDefault;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getPreferPlats() {
    return preferPlats;
  }

  public void setPreferPlats(String preferPlats) {
    this.preferPlats = preferPlats;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
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

  public String getBankBranchName() {
    return bankBranchName;
  }

  public void setBankBranchName(String bankBranchName) {
    this.bankBranchName = bankBranchName;
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

  @Override
  public String toString() {
    return "BankAccountEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", account='" + account + '\'' +
        ", name='" + name + '\'' +
        ", certificateNo='" + certificateNo + '\'' +
        ", certificateType=" + certificateType +
        ", mobile='" + mobile + '\'' +
        ", bankId=" + bankId +
        ", bindId='" + bindId + '\'' +
        ", province='" + province + '\'' +
        ", city='" + city + '\'' +
        ", accountType=" + accountType +
        ", status=" + status +
        ", isDefault=" + isDefault +
        ", channel='" + channel + '\'' +
        ", prefix='" + prefix + '\'' +
        ", preferPlats='" + preferPlats + '\'' +
        ", comment='" + comment + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", bankBranchName='" + bankBranchName + '\'' +
        ", inStatus=" + inStatus +
        ", outStatus=" + outStatus +
        '}';
  }
}
