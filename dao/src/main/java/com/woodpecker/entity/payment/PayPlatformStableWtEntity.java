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
 * 类描述:〈rt_pay_platform_stable_wt表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "rt_pay_platform_stable_wt")
public class PayPlatformStableWtEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "pay_platform_code")
  private String payPlatformCode;

  @Column(name = "ave_delay_wt")
  private String aveDelayWt;

  @Column(name = "error_count_wt")
  private String errorCountWt;

  @Column(name = "out_time_count_wt")
  private String outTimeCountWt;

  @Column(name = "fee_wt")
  private String feeWt;

  @Column(name = "user_error_count_wt")
  private String userErrorCountWt;

  @Column(name = "day_amount_wt")
  private String dayAmountWt;

  @Column(name = "day_count_wt")
  private String dayCountWt;

  @Column(name = "month_amount_wt")
  private String monthAmountWt;

  @Column(name = "month_count_wt")
  private String monthCountWt;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "version")
  private String version;

  @Column(name = "user_assign_error_count_wt")
  private String userAssignErrorCountWt;

  @Column(name = "rt_key")
  private String rtKey;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPayPlatformCode() {
    return payPlatformCode;
  }

  public void setPayPlatformCode(String payPlatformCode) {
    this.payPlatformCode = payPlatformCode;
  }

  public String getAveDelayWt() {
    return aveDelayWt;
  }

  public void setAveDelayWt(String aveDelayWt) {
    this.aveDelayWt = aveDelayWt;
  }

  public String getErrorCountWt() {
    return errorCountWt;
  }

  public void setErrorCountWt(String errorCountWt) {
    this.errorCountWt = errorCountWt;
  }

  public String getOutTimeCountWt() {
    return outTimeCountWt;
  }

  public void setOutTimeCountWt(String outTimeCountWt) {
    this.outTimeCountWt = outTimeCountWt;
  }

  public String getFeeWt() {
    return feeWt;
  }

  public void setFeeWt(String feeWt) {
    this.feeWt = feeWt;
  }

  public String getUserErrorCountWt() {
    return userErrorCountWt;
  }

  public void setUserErrorCountWt(String userErrorCountWt) {
    this.userErrorCountWt = userErrorCountWt;
  }

  public String getDayAmountWt() {
    return dayAmountWt;
  }

  public void setDayAmountWt(String dayAmountWt) {
    this.dayAmountWt = dayAmountWt;
  }

  public String getDayCountWt() {
    return dayCountWt;
  }

  public void setDayCountWt(String dayCountWt) {
    this.dayCountWt = dayCountWt;
  }

  public String getMonthAmountWt() {
    return monthAmountWt;
  }

  public void setMonthAmountWt(String monthAmountWt) {
    this.monthAmountWt = monthAmountWt;
  }

  public String getMonthCountWt() {
    return monthCountWt;
  }

  public void setMonthCountWt(String monthCountWt) {
    this.monthCountWt = monthCountWt;
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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getUserAssignErrorCountWt() {
    return userAssignErrorCountWt;
  }

  public void setUserAssignErrorCountWt(String userAssignErrorCountWt) {
    this.userAssignErrorCountWt = userAssignErrorCountWt;
  }

  public String getRtKey() {
    return rtKey;
  }

  public void setRtKey(String rtKey) {
    this.rtKey = rtKey;
  }

  @Override
  public String toString() {
    return "PayPlatformStableWtEntity{" +
        "id=" + id +
        ", payPlatformCode='" + payPlatformCode + '\'' +
        ", aveDelayWt='" + aveDelayWt + '\'' +
        ", errorCountWt='" + errorCountWt + '\'' +
        ", outTimeCountWt='" + outTimeCountWt + '\'' +
        ", feeWt='" + feeWt + '\'' +
        ", userErrorCountWt='" + userErrorCountWt + '\'' +
        ", dayAmountWt='" + dayAmountWt + '\'' +
        ", dayCountWt='" + dayCountWt + '\'' +
        ", monthAmountWt='" + monthAmountWt + '\'' +
        ", monthCountWt='" + monthCountWt + '\'' +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", version='" + version + '\'' +
        ", userAssignErrorCountWt='" + userAssignErrorCountWt + '\'' +
        ", rtKey='" + rtKey + '\'' +
        '}';
  }
}
