package com.woodpecker.entity.loandb;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈qs_wangxin_account表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "qs_wangxin_account")
public class WangXinAccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "mobile")
  private String mobile;

  @Column(name = "id_card_no")
  private String idCardNo;

  @Column(name = "bank_account_id")
  private Integer bankAccountId;

  @Column(name = "open_id")
  private String openId;

  @Column(name = "status")
  private Byte status;

  @Column(name = "error_code")
  private Integer errorCode;

  @Column(name = "error_msg")
  private String errorMsg;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "last_update_time")
  private Date lastUpdateTime;

  @Column(name = "ds_open_id")
  private String dsOpenId;

  @Column(name = "open_method")
  private Byte openMethod;


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

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public Integer getBankAccountId() {
    return bankAccountId;
  }

  public void setBankAccountId(Integer bankAccountId) {
    this.bankAccountId = bankAccountId;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
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

  public String getDsOpenId() {
    return dsOpenId;
  }

  public void setDsOpenId(String dsOpenId) {
    this.dsOpenId = dsOpenId;
  }

  public Byte getOpenMethod() {
    return openMethod;
  }

  public void setOpenMethod(Byte openMethod) {
    this.openMethod = openMethod;
  }


  @Override
  public String toString() {
    return "WangXinAccountEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", mobile='" + mobile + '\'' +
        ", idCardNo='" + idCardNo + '\'' +
        ", bankAccountId=" + bankAccountId +
        ", openId='" + openId + '\'' +
        ", status=" + status +
        ", errorCode=" + errorCode +
        ", errorMsg='" + errorMsg + '\'' +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        ", dsOpenId='" + dsOpenId + '\'' +
        ", openMethod=" + openMethod +
        '}';
  }
}
