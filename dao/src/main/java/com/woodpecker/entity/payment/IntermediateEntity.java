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
 * 类描述:〈t_tp_intermediate表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_tp_intermediate")
public class IntermediateEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "orderNo")
  private String orderNo;

  @Column(name = "userId")
  private Long userId;

  @Column(name = "bizUserId")
  private Long bizUserId;

  @Column(name = "idCardNo")
  private String idCardNo;

  @Column(name = "cardId")
  private String cardId;

  @Column(name = "bindId")
  private String bindId;

  @Column(name = "platId")
  private Byte platId;

  @Column(name = "channelId")
  private String channelId;

  @Column(name = "memberId")
  private String memberId;

  @Column(name = "status")
  private Byte status;

  @Column(name = "createDate")
  private Date createDate;

  @Column(name = "updateDate")
  private Date updateDate;


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

  public Long getBizUserId() {
    return bizUserId;
  }

  public void setBizUserId(Long bizUserId) {
    this.bizUserId = bizUserId;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public String getBindId() {
    return bindId;
  }

  public void setBindId(String bindId) {
    this.bindId = bindId;
  }

  public Byte getPlatId() {
    return platId;
  }

  public void setPlatId(Byte platId) {
    this.platId = platId;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public String getMemberId() {
    return memberId;
  }

  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
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


  @Override
  public String toString() {
    return "IntermediateEntity{" +
        "id=" + id +
        ", orderNo='" + orderNo + '\'' +
        ", userId=" + userId +
        ", bizUserId=" + bizUserId +
        ", idCardNo='" + idCardNo + '\'' +
        ", cardId='" + cardId + '\'' +
        ", bindId='" + bindId + '\'' +
        ", platId=" + platId +
        ", channelId='" + channelId + '\'' +
        ", memberId='" + memberId + '\'' +
        ", status=" + status +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        '}';
  }
}
