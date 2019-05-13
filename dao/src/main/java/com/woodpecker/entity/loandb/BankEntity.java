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
 * 类描述:〈t_bank表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_bank")
public class BankEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "BankName")
  private String bankName;

  @Column(name = "Logo")
  private String logo;

  @Column(name = "BankPayCode")
  private String bankPayCode;

  @Column(name = "BankId")
  private String bankId;

  @Column(name = "SingleLimit")
  private BigDecimal singleLimit;

  @Column(name = "DailyLimit")
  private BigDecimal dailyLimit;

  @Column(name = "MonthlyLimit")
  private BigDecimal monthlyLimit;

  @Column(name = "PreferPlats")
  private String preferPlats;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "IsSupport")
  private Byte isSupport;

  @Column(name = "Sort")
  private Byte sort;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getBankPayCode() {
    return bankPayCode;
  }

  public void setBankPayCode(String bankPayCode) {
    this.bankPayCode = bankPayCode;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public BigDecimal getSingleLimit() {
    return singleLimit;
  }

  public void setSingleLimit(BigDecimal singleLimit) {
    this.singleLimit = singleLimit;
  }

  public BigDecimal getDailyLimit() {
    return dailyLimit;
  }

  public void setDailyLimit(BigDecimal dailyLimit) {
    this.dailyLimit = dailyLimit;
  }

  public BigDecimal getMonthlyLimit() {
    return monthlyLimit;
  }

  public void setMonthlyLimit(BigDecimal monthlyLimit) {
    this.monthlyLimit = monthlyLimit;
  }

  public String getPreferPlats() {
    return preferPlats;
  }

  public void setPreferPlats(String preferPlats) {
    this.preferPlats = preferPlats;
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

  public Byte getIsSupport() {
    return isSupport;
  }

  public void setIsSupport(Byte isSupport) {
    this.isSupport = isSupport;
  }

  public Byte getSort() {
    return sort;
  }

  public void setSort(Byte sort) {
    this.sort = sort;
  }

  @Override
  public String toString() {
    return "BankEntity{" +
        "id=" + id +
        ", bankName='" + bankName + '\'' +
        ", logo='" + logo + '\'' +
        ", bankPayCode='" + bankPayCode + '\'' +
        ", bankId='" + bankId + '\'' +
        ", singleLimit=" + singleLimit +
        ", dailyLimit=" + dailyLimit +
        ", monthlyLimit=" + monthlyLimit +
        ", preferPlats='" + preferPlats + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isSupport=" + isSupport +
        ", sort=" + sort +
        '}';
  }
}
