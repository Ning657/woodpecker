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
 * 类描述:〈t_fee_detail表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_fee_detail")
public class FeeDetailEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "Channel")
  private String channel;

  @Column(name = "OnDate")
  private Date onDate;

  @Column(name = "FeeType")
  private String feeType;

  @Column(name = "BizAmount")
  private BigDecimal bizAmount;

  @Column(name = "Fee")
  private BigDecimal fee;

  @Column(name = "RefType")
  private String refType;

  @Column(name = "RefValue")
  private String refValue;

  @Column(name = "Memo")
  private String memo;

  @Column(name = "IsDeprecated")
  private Byte isDeprecated;

  @Column(name = "CreateTime")
  private Date createTime;

  @Column(name = "LastUpdateTime")
  private Date lastUpdateTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public Date getOnDate() {
    return onDate;
  }

  public void setOnDate(Date onDate) {
    this.onDate = onDate;
  }

  public String getFeeType() {
    return feeType;
  }

  public void setFeeType(String feeType) {
    this.feeType = feeType;
  }

  public BigDecimal getBizAmount() {
    return bizAmount;
  }

  public void setBizAmount(BigDecimal bizAmount) {
    this.bizAmount = bizAmount;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public String getRefType() {
    return refType;
  }

  public void setRefType(String refType) {
    this.refType = refType;
  }

  public String getRefValue() {
    return refValue;
  }

  public void setRefValue(String refValue) {
    this.refValue = refValue;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public Byte getIsDeprecated() {
    return isDeprecated;
  }

  public void setIsDeprecated(Byte isDeprecated) {
    this.isDeprecated = isDeprecated;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }


  @Override
  public String toString() {
    return "FeeDetailEntity{" +
        "id=" + id +
        ", channel='" + channel + '\'' +
        ", onDate=" + onDate +
        ", feeType='" + feeType + '\'' +
        ", bizAmount=" + bizAmount +
        ", fee=" + fee +
        ", refType='" + refType + '\'' +
        ", refValue='" + refValue + '\'' +
        ", memo='" + memo + '\'' +
        ", isDeprecated=" + isDeprecated +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        '}';
  }
}
