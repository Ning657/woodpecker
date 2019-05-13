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
 * 类描述:〈t_deduct_bank_code表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_deduct_bank_code")
public class DeductBankCodeEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "BankCode")
  private String bankCode;

  @Column(name = "BankName")
  private String bankName;

  @Column(name = "PayPlatformId")
  private Integer payPlatformId;

  @Column(name = "ThirdBankId")
  private String thirdBankId;

  @Column(name = "Description")
  private String description;

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

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public Integer getPayPlatformId() {
    return payPlatformId;
  }

  public void setPayPlatformId(Integer payPlatformId) {
    this.payPlatformId = payPlatformId;
  }

  public String getThirdBankId() {
    return thirdBankId;
  }

  public void setThirdBankId(String thirdBankId) {
    this.thirdBankId = thirdBankId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
    return "DeductBankCodeEntity{" +
        "id=" + id +
        ", bankCode='" + bankCode + '\'' +
        ", bankName='" + bankName + '\'' +
        ", payPlatformId=" + payPlatformId +
        ", thirdBankId='" + thirdBankId + '\'' +
        ", description='" + description + '\'' +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", isDeprecated=" + isDeprecated +
        '}';
  }
}
