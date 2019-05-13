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
 * 类描述:〈rt_pay_channel_bank表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "rt_pay_channel_bank")
public class PayChannelBankEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @Column(name = "day_amount")
  private Integer dayAmount;

  @Column(name = "day_count")
  private Integer dayCount;

  @Column(name = "month_amount")
  private Integer monthAmount;

  @Column(name = "month_count")
  private Integer monthCount;

  @Column(name = "status")
  private Byte status;

  @Column(name = "pay_channel_code")
  private String payChannelCode;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "version")
  private String version;

  @Column(name = "rt_key")
  private String rtKey;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getDayAmount() {
    return dayAmount;
  }

  public void setDayAmount(Integer dayAmount) {
    this.dayAmount = dayAmount;
  }

  public Integer getDayCount() {
    return dayCount;
  }

  public void setDayCount(Integer dayCount) {
    this.dayCount = dayCount;
  }

  public Integer getMonthAmount() {
    return monthAmount;
  }

  public void setMonthAmount(Integer monthAmount) {
    this.monthAmount = monthAmount;
  }

  public Integer getMonthCount() {
    return monthCount;
  }

  public void setMonthCount(Integer monthCount) {
    this.monthCount = monthCount;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public String getPayChannelCode() {
    return payChannelCode;
  }

  public void setPayChannelCode(String payChannelCode) {
    this.payChannelCode = payChannelCode;
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

  public String getRtKey() {
    return rtKey;
  }

  public void setRtKey(String rtKey) {
    this.rtKey = rtKey;
  }


  @Override
  public String toString() {
    return "PayChannelBankEntity{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", dayAmount=" + dayAmount +
        ", dayCount=" + dayCount +
        ", monthAmount=" + monthAmount +
        ", monthCount=" + monthCount +
        ", status=" + status +
        ", payChannelCode='" + payChannelCode + '\'' +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", version='" + version + '\'' +
        ", rtKey='" + rtKey + '\'' +
        '}';
  }
}
