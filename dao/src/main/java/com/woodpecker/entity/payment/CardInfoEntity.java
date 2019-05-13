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
 * 类描述:〈t_card_info表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_card_info")
public class CardInfoEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "ORDER_NO")
  private String orderNo;

  @Column(name = "BIND_ID")
  private String bindId;

  @Column(name = "CARD_NO")
  private String cardNo;

  @Column(name = "BANK_ID")
  private String bankId;

  @Column(name = "ACC_NAME")
  private String accName;

  @Column(name = "ACC_ID")
  private String accId;

  @Column(name = "MOBILE")
  private String mobile;

  @Column(name = "RET_CODE")
  private String retCode;

  @Column(name = "RET_DESC")
  private String retDesc;

  @Column(name = "STATUS")
  private Byte status;

  @Column(name = "CHANNEL")
  private Integer channel;

  @Column(name = "CREATED_DATETIME")
  private Date createdDatetime;

  @Column(name = "UPDATED_DATETIME")
  private Date updatedDatetime;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getBindId() {
    return bindId;
  }

  public void setBindId(String bindId) {
    this.bindId = bindId;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getAccName() {
    return accName;
  }

  public void setAccName(String accName) {
    this.accName = accName;
  }

  public String getAccId() {
    return accId;
  }

  public void setAccId(String accId) {
    this.accId = accId;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getRetCode() {
    return retCode;
  }

  public void setRetCode(String retCode) {
    this.retCode = retCode;
  }

  public String getRetDesc() {
    return retDesc;
  }

  public void setRetDesc(String retDesc) {
    this.retDesc = retDesc;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public Date getCreatedDatetime() {
    return createdDatetime;
  }

  public void setCreatedDatetime(Date createdDatetime) {
    this.createdDatetime = createdDatetime;
  }

  public Date getUpdatedDatetime() {
    return updatedDatetime;
  }

  public void setUpdatedDatetime(Date updatedDatetime) {
    this.updatedDatetime = updatedDatetime;
  }


  @Override
  public String toString() {
    return "CardInfoEntity{" +
        "id=" + id +
        ", orderNo='" + orderNo + '\'' +
        ", bindId='" + bindId + '\'' +
        ", cardNo='" + cardNo + '\'' +
        ", bankId='" + bankId + '\'' +
        ", accName='" + accName + '\'' +
        ", accId='" + accId + '\'' +
        ", mobile='" + mobile + '\'' +
        ", retCode='" + retCode + '\'' +
        ", retDesc='" + retDesc + '\'' +
        ", status=" + status +
        ", channel=" + channel +
        ", createdDatetime=" + createdDatetime +
        ", updatedDatetime=" + updatedDatetime +
        '}';
  }
}
