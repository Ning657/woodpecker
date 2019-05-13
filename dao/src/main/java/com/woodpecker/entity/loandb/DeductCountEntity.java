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
 * 类描述:〈t_deduct_count表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_deduct_count")
public class DeductCountEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "AccountNo")
  private String accountNo;

  @Column(name = "UserName")
  private String userName;

  @Column(name = "UserIdCard")
  private String userIdCard;

  @Column(name = "BankCode")
  private String bankCode;

  @Column(name = "MobileNo")
  private String mobileNo;

  @Column(name = "OtherInfo")
  private String otherInfo;

  @Column(name = "RefId")
  private String refId;

  @Column(name = "Type")
  private Byte type;

  @Column(name = "BankRestrictionID")
  private Integer bankRestrictionID;

  @Column(name = "FirstDeductDate")
  private Date firstDeductDate;

  @Column(name = "Count")
  private Integer count;

  @Column(name = "Amount")
  private BigDecimal amount;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "UpdateDate")
  private Date updateDate;

  @Column(name = "IsDeprecated")
  private Byte isDeprecated;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserIdCard() {
    return userIdCard;
  }

  public void setUserIdCard(String userIdCard) {
    this.userIdCard = userIdCard;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }

  public String getOtherInfo() {
    return otherInfo;
  }

  public void setOtherInfo(String otherInfo) {
    this.otherInfo = otherInfo;
  }

  public String getRefId() {
    return refId;
  }

  public void setRefId(String refId) {
    this.refId = refId;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public Integer getBankRestrictionID() {
    return bankRestrictionID;
  }

  public void setBankRestrictionID(Integer bankRestrictionID) {
    this.bankRestrictionID = bankRestrictionID;
  }

  public Date getFirstDeductDate() {
    return firstDeductDate;
  }

  public void setFirstDeductDate(Date firstDeductDate) {
    this.firstDeductDate = firstDeductDate;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
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

  public Byte getIsDeprecated() {
    return isDeprecated;
  }

  public void setIsDeprecated(Byte isDeprecated) {
    this.isDeprecated = isDeprecated;
  }

  @Override
  public String toString() {
    return "DeductCountEntity{" +
        "id=" + id +
        ", accountNo='" + accountNo + '\'' +
        ", userName='" + userName + '\'' +
        ", userIdCard='" + userIdCard + '\'' +
        ", bankCode='" + bankCode + '\'' +
        ", mobileNo='" + mobileNo + '\'' +
        ", otherInfo='" + otherInfo + '\'' +
        ", refId='" + refId + '\'' +
        ", type=" + type +
        ", bankRestrictionID=" + bankRestrictionID +
        ", firstDeductDate=" + firstDeductDate +
        ", count=" + count +
        ", amount=" + amount +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", isDeprecated=" + isDeprecated +
        '}';
  }
}
