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
 * 类描述:〈t_channel_peroid_limit表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_channel_peroid_limit")
public class ChannelPeroidLimitEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "ChannelID")
  private Integer channelId;

  @Column(name = "IdentifyId")
  private Long identifyId;

  @Column(name = "TotalAmount")
  private BigDecimal totalAmount;

  @Column(name = "PushRemainAmount")
  private BigDecimal pushRemainAmount;

  @Column(name = "RemainAmount")
  private BigDecimal remainAmount;

  @Column(name = "PeroidType")
  private Integer peroidType;

  @Column(name = "PeroidStart")
  private Date peroidStart;

  @Column(name = "PeroidEnd")
  private Date peroidEnd;

  @Column(name = "CreateTime")
  private Date createTime;

  @Column(name = "LastUpdateTime")
  private Date lastUpdateTime;

  @Column(name = "GlobalPlatformId")
  private Integer globalPlatformId;

  @Column(name = "SendFile")
  private Integer sendFile;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getChannelId() {
    return channelId;
  }

  public void setChannelId(Integer channelId) {
    this.channelId = channelId;
  }

  public Long getIdentifyId() {
    return identifyId;
  }

  public void setIdentifyId(Long identifyId) {
    this.identifyId = identifyId;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public BigDecimal getPushRemainAmount() {
    return pushRemainAmount;
  }

  public void setPushRemainAmount(BigDecimal pushRemainAmount) {
    this.pushRemainAmount = pushRemainAmount;
  }

  public BigDecimal getRemainAmount() {
    return remainAmount;
  }

  public void setRemainAmount(BigDecimal remainAmount) {
    this.remainAmount = remainAmount;
  }

  public Integer getPeroidType() {
    return peroidType;
  }

  public void setPeroidType(Integer peroidType) {
    this.peroidType = peroidType;
  }

  public Date getPeroidStart() {
    return peroidStart;
  }

  public void setPeroidStart(Date peroidStart) {
    this.peroidStart = peroidStart;
  }

  public Date getPeroidEnd() {
    return peroidEnd;
  }

  public void setPeroidEnd(Date peroidEnd) {
    this.peroidEnd = peroidEnd;
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

  public Integer getGlobalPlatformId() {
    return globalPlatformId;
  }

  public void setGlobalPlatformId(Integer globalPlatformId) {
    this.globalPlatformId = globalPlatformId;
  }

  public Integer getSendFile() {
    return sendFile;
  }

  public void setSendFile(Integer sendFile) {
    this.sendFile = sendFile;
  }


  @Override
  public String toString() {
    return "ChannelPeroidLimitEntity{" +
        "id=" + id +
        ", channelId=" + channelId +
        ", identifyId=" + identifyId +
        ", totalAmount=" + totalAmount +
        ", pushRemainAmount=" + pushRemainAmount +
        ", remainAmount=" + remainAmount +
        ", peroidType=" + peroidType +
        ", peroidStart=" + peroidStart +
        ", peroidEnd=" + peroidEnd +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        ", globalPlatformId=" + globalPlatformId +
        ", sendFile=" + sendFile +
        '}';
  }
}
