package com.woodpecker.entity.loandb;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈t_order_thirdparty_ref表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_order_thirdparty_ref")
public class OrderThirdpartyRefEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "OrderId")
  private Integer orderId;

  @Column(name = "UserId")
  private Integer userId;

  @Column(name = "ProjectId")
  private String projectId;

  @Column(name = "PaymentNo")
  private String paymentNo;

  @Column(name = "ThirdpartyPaymentNo")
  private String thirdpartyPaymentNo;

  @Column(name = "PaymentTime")
  private Date paymentTime;

  @Column(name = "ApplyTime")
  private Date applyTime;

  @Column(name = "ThirdpartyUserId")
  private String thirdpartyUserId;

  @Column(name = "PlatformCode")
  private String platformCode;

  @Column(name = "GlobalPlatformId")
  private Integer globalPlatformId;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;

  @Column(name = "IsChannelPushed")
  private Byte isChannelPushed;

  @Column(name = "IsSecurePushed")
  private Byte isSecurePushed;

  @Column(name = "BusNumber")
  private String busNumber;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getPaymentNo() {
    return paymentNo;
  }

  public void setPaymentNo(String paymentNo) {
    this.paymentNo = paymentNo;
  }

  public String getThirdpartyPaymentNo() {
    return thirdpartyPaymentNo;
  }

  public void setThirdpartyPaymentNo(String thirdpartyPaymentNo) {
    this.thirdpartyPaymentNo = thirdpartyPaymentNo;
  }

  public Date getPaymentTime() {
    return paymentTime;
  }

  public void setPaymentTime(Date paymentTime) {
    this.paymentTime = paymentTime;
  }

  public Date getApplyTime() {
    return applyTime;
  }

  public void setApplyTime(Date applyTime) {
    this.applyTime = applyTime;
  }

  public String getThirdpartyUserId() {
    return thirdpartyUserId;
  }

  public void setThirdpartyUserId(String thirdpartyUserId) {
    this.thirdpartyUserId = thirdpartyUserId;
  }

  public String getPlatformCode() {
    return platformCode;
  }

  public void setPlatformCode(String platformCode) {
    this.platformCode = platformCode;
  }

  public Integer getGlobalPlatformId() {
    return globalPlatformId;
  }

  public void setGlobalPlatformId(Integer globalPlatformId) {
    this.globalPlatformId = globalPlatformId;
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

  public Byte getIsChannelPushed() {
    return isChannelPushed;
  }

  public void setIsChannelPushed(Byte isChannelPushed) {
    this.isChannelPushed = isChannelPushed;
  }

  public Byte getIsSecurePushed() {
    return isSecurePushed;
  }

  public void setIsSecurePushed(Byte isSecurePushed) {
    this.isSecurePushed = isSecurePushed;
  }

  public String getBusNumber() {
    return busNumber;
  }

  public void setBusNumber(String busNumber) {
    this.busNumber = busNumber;
  }


  @Override
  public String toString() {
    return "OrderThirdpartyRefEntity{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", userId=" + userId +
        ", projectId='" + projectId + '\'' +
        ", paymentNo='" + paymentNo + '\'' +
        ", thirdpartyPaymentNo='" + thirdpartyPaymentNo + '\'' +
        ", paymentTime=" + paymentTime +
        ", applyTime=" + applyTime +
        ", thirdpartyUserId='" + thirdpartyUserId + '\'' +
        ", platformCode='" + platformCode + '\'' +
        ", globalPlatformId=" + globalPlatformId +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        ", isChannelPushed=" + isChannelPushed +
        ", isSecurePushed=" + isSecurePushed +
        ", busNumber='" + busNumber + '\'' +
        '}';
  }
}
