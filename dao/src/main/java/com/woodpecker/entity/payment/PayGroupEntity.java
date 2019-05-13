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
 * 类描述:〈rt_pay_group表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "rt_pay_group")
public class PayGroupEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "platformId")
  private Integer platformId;

  @Column(name = "groupName")
  private String groupName;

  @Column(name = "primaryPayWay")
  private Integer primaryPayWay;

  @Column(name = "secondaryPayWay")
  private Integer secondaryPayWay;

  @Column(name = "dueEndDate")
  private Integer dueEndDate;

  @Column(name = "dueEndTime")
  private String dueEndTime;

  @Column(name = "dayStartTime")
  private String dayStartTime;

  @Column(name = "dayEndTime")
  private String dayEndTime;

  @Column(name = "canFuture")
  private Byte canFuture;

  @Column(name = "buyBack")
  private Byte buyBack;

  @Column(name = "haveRepaid")
  private Byte haveRepaid;

  @Column(name = "payWayType")
  private Byte payWayType;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "update_time")
  private Date updateTime;

  @Column(name = "version")
  private String version;

  @Column(name = "last_update_user")
  private String lastUpdateUser;

  @Column(name = "historyOrder")
  private Byte historyOrder;

  @Column(name = "historyInfo")
  private String historyInfo;

  @Column(name = "payForBind")
  private Byte payForBind;

  @Column(name = "effective")
  private Byte effective;

  @Column(name = "effectiveInfo")
  private String effectiveInfo;

  @Column(name = "status")
  private Byte status;

  @Column(name = "rt_key")
  private String rtKey;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getPlatformId() {
    return platformId;
  }

  public void setPlatformId(Integer platformId) {
    this.platformId = platformId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public Integer getPrimaryPayWay() {
    return primaryPayWay;
  }

  public void setPrimaryPayWay(Integer primaryPayWay) {
    this.primaryPayWay = primaryPayWay;
  }

  public Integer getSecondaryPayWay() {
    return secondaryPayWay;
  }

  public void setSecondaryPayWay(Integer secondaryPayWay) {
    this.secondaryPayWay = secondaryPayWay;
  }

  public Integer getDueEndDate() {
    return dueEndDate;
  }

  public void setDueEndDate(Integer dueEndDate) {
    this.dueEndDate = dueEndDate;
  }

  public String getDueEndTime() {
    return dueEndTime;
  }

  public void setDueEndTime(String dueEndTime) {
    this.dueEndTime = dueEndTime;
  }

  public String getDayStartTime() {
    return dayStartTime;
  }

  public void setDayStartTime(String dayStartTime) {
    this.dayStartTime = dayStartTime;
  }

  public String getDayEndTime() {
    return dayEndTime;
  }

  public void setDayEndTime(String dayEndTime) {
    this.dayEndTime = dayEndTime;
  }

  public Byte getCanFuture() {
    return canFuture;
  }

  public void setCanFuture(Byte canFuture) {
    this.canFuture = canFuture;
  }

  public Byte getBuyBack() {
    return buyBack;
  }

  public void setBuyBack(Byte buyBack) {
    this.buyBack = buyBack;
  }

  public Byte getHaveRepaid() {
    return haveRepaid;
  }

  public void setHaveRepaid(Byte haveRepaid) {
    this.haveRepaid = haveRepaid;
  }

  public Byte getPayWayType() {
    return payWayType;
  }

  public void setPayWayType(Byte payWayType) {
    this.payWayType = payWayType;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getLastUpdateUser() {
    return lastUpdateUser;
  }

  public void setLastUpdateUser(String lastUpdateUser) {
    this.lastUpdateUser = lastUpdateUser;
  }

  public Byte getHistoryOrder() {
    return historyOrder;
  }

  public void setHistoryOrder(Byte historyOrder) {
    this.historyOrder = historyOrder;
  }

  public String getHistoryInfo() {
    return historyInfo;
  }

  public void setHistoryInfo(String historyInfo) {
    this.historyInfo = historyInfo;
  }

  public Byte getPayForBind() {
    return payForBind;
  }

  public void setPayForBind(Byte payForBind) {
    this.payForBind = payForBind;
  }

  public Byte getEffective() {
    return effective;
  }

  public void setEffective(Byte effective) {
    this.effective = effective;
  }

  public String getEffectiveInfo() {
    return effectiveInfo;
  }

  public void setEffectiveInfo(String effectiveInfo) {
    this.effectiveInfo = effectiveInfo;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public String getRtKey() {
    return rtKey;
  }

  public void setRtKey(String rtKey) {
    this.rtKey = rtKey;
  }

  @Override
  public String toString() {
    return "PayGroupEntity{" +
        "id=" + id +
        ", platformId=" + platformId +
        ", groupName='" + groupName + '\'' +
        ", primaryPayWay=" + primaryPayWay +
        ", secondaryPayWay=" + secondaryPayWay +
        ", dueEndDate=" + dueEndDate +
        ", dueEndTime='" + dueEndTime + '\'' +
        ", dayStartTime='" + dayStartTime + '\'' +
        ", dayEndTime='" + dayEndTime + '\'' +
        ", canFuture=" + canFuture +
        ", buyBack=" + buyBack +
        ", haveRepaid=" + haveRepaid +
        ", payWayType=" + payWayType +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", version='" + version + '\'' +
        ", lastUpdateUser='" + lastUpdateUser + '\'' +
        ", historyOrder=" + historyOrder +
        ", historyInfo='" + historyInfo + '\'' +
        ", payForBind=" + payForBind +
        ", effective=" + effective +
        ", effectiveInfo='" + effectiveInfo + '\'' +
        ", status=" + status +
        ", rtKey='" + rtKey + '\'' +
        '}';
  }
}
