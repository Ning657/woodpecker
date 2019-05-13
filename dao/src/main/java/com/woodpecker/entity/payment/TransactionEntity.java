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
 * 类描述:〈t_tp_transaction表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_tp_transaction")
public class TransactionEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "orderNo")
  private String orderNo;

  @Column(name = "userId")
  private Long userId;

  @Column(name = "bizUserId")
  private String bizUserId;

  @Column(name = "idCardNo")
  private String idCardNo;

  @Column(name = "mobile")
  private String mobile;

  @Column(name = "userName")
  private String userName;

  @Column(name = "bankId")
  private String bankId;

  @Column(name = "platId")
  private Byte platId;

  @Column(name = "channelId")
  private Byte channelId;

  @Column(name = "cardId")
  private String cardId;

  @Column(name = "reqData")
  private String reqData;

  @Column(name = "resCode")
  private String resCode;

  @Column(name = "resData")
  private String resData;

  @Column(name = "tranStatus")
  private String tranStatus;

  @Column(name = "desc")
  private String desc;

  @Column(name = "platTransNo")
  private String platTransNo;

  @Column(name = "action")
  private String action;

  @Column(name = "settleDate")
  private Date settleDate;

  @Column(name = "createDate")
  private Date createDate;

  @Column(name = "updateDate")
  private Date updateDate;

  @Column(name = "merData")
  private String merData;


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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getBizUserId() {
    return bizUserId;
  }

  public void setBizUserId(String bizUserId) {
    this.bizUserId = bizUserId;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public Byte getPlatId() {
    return platId;
  }

  public void setPlatId(Byte platId) {
    this.platId = platId;
  }

  public Byte getChannelId() {
    return channelId;
  }

  public void setChannelId(Byte channelId) {
    this.channelId = channelId;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public String getReqData() {
    return reqData;
  }

  public void setReqData(String reqData) {
    this.reqData = reqData;
  }

  public String getResCode() {
    return resCode;
  }

  public void setResCode(String resCode) {
    this.resCode = resCode;
  }

  public String getResData() {
    return resData;
  }

  public void setResData(String resData) {
    this.resData = resData;
  }

  public String getTranStatus() {
    return tranStatus;
  }

  public void setTranStatus(String tranStatus) {
    this.tranStatus = tranStatus;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getPlatTransNo() {
    return platTransNo;
  }

  public void setPlatTransNo(String platTransNo) {
    this.platTransNo = platTransNo;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public Date getSettleDate() {
    return settleDate;
  }

  public void setSettleDate(Date settleDate) {
    this.settleDate = settleDate;
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

  public String getMerData() {
    return merData;
  }

  public void setMerData(String merData) {
    this.merData = merData;
  }

  @Override
  public String toString() {
    return "TransactionEntity{" +
        "id=" + id +
        ", orderNo='" + orderNo + '\'' +
        ", userId=" + userId +
        ", bizUserId='" + bizUserId + '\'' +
        ", idCardNo='" + idCardNo + '\'' +
        ", mobile='" + mobile + '\'' +
        ", userName='" + userName + '\'' +
        ", bankId='" + bankId + '\'' +
        ", platId=" + platId +
        ", channelId=" + channelId +
        ", cardId='" + cardId + '\'' +
        ", reqData='" + reqData + '\'' +
        ", resCode='" + resCode + '\'' +
        ", resData='" + resData + '\'' +
        ", tranStatus='" + tranStatus + '\'' +
        ", desc='" + desc + '\'' +
        ", platTransNo='" + platTransNo + '\'' +
        ", action='" + action + '\'' +
        ", settleDate=" + settleDate +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", merData='" + merData + '\'' +
        '}';
  }
}
