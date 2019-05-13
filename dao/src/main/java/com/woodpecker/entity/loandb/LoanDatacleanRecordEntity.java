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
 * 类描述:〈fk_loan_dataclean_record表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "fk_loan_dataclean_record")
public class LoanDatacleanRecordEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Long id;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "OrderId")
  private Integer orderId;

  @Column(name = "BatchId")
  private Long batchId;

  @Column(name = "BatchTime")
  private Date batchTime;

  @Column(name = "ChannelId")
  private Integer channelId;

  @Column(name = "ParameterName")
  private String parameterName;

  @Column(name = "ParameterValue")
  private String parameterValue;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Long getBatchId() {
    return batchId;
  }

  public void setBatchId(Long batchId) {
    this.batchId = batchId;
  }

  public Date getBatchTime() {
    return batchTime;
  }

  public void setBatchTime(Date batchTime) {
    this.batchTime = batchTime;
  }

  public Integer getChannelId() {
    return channelId;
  }

  public void setChannelId(Integer channelId) {
    this.channelId = channelId;
  }

  public String getParameterName() {
    return parameterName;
  }

  public void setParameterName(String parameterName) {
    this.parameterName = parameterName;
  }

  public String getParameterValue() {
    return parameterValue;
  }

  public void setParameterValue(String parameterValue) {
    this.parameterValue = parameterValue;
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

  @Override
  public String toString() {
    return "LoanDatacleanRecordEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", orderId=" + orderId +
        ", batchId=" + batchId +
        ", batchTime=" + batchTime +
        ", channelId=" + channelId +
        ", parameterName='" + parameterName + '\'' +
        ", parameterValue='" + parameterValue + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        '}';
  }
}
