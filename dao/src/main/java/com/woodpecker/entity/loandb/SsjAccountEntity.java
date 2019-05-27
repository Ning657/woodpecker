package com.woodpecker.entity.loandb;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈qs_ssj_account表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "qs_ssj_account")
public class SsjAccountEntity {

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

  @Column(name = "bank_name")
  private String bankName;

  @Column(name = "card_no")
  private String cardNo;

  @Column(name = "open_id")
  private String openId;

  @Column(name = "account_status")
  private Byte accountStatus;

  @Column(name = "auth_status")
  private Byte authStatus;

  @Column(name = "bind_card_status")
  private Byte bindCardStatus;

  @Column(name = "msg")
  private String msg;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "last_update_time")
  private Date lastUpdateTime;

  @Column(name = "ssj_card_no")
  private String ssjCardNo;

  @Column(name = "ssj_card_bank_name")
  private String ssjCardBankName;

  @Column(name = "open_method")
  private Byte openMethod;

  @Column(name = "default_pwd")
  private Byte defaultPwd;


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

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

  public Byte getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(Byte accountStatus) {
    this.accountStatus = accountStatus;
  }

  public Byte getAuthStatus() {
    return authStatus;
  }

  public void setAuthStatus(Byte authStatus) {
    this.authStatus = authStatus;
  }

  public Byte getBindCardStatus() {
    return bindCardStatus;
  }

  public void setBindCardStatus(Byte bindCardStatus) {
    this.bindCardStatus = bindCardStatus;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
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

  public String getSsjCardNo() {
    return ssjCardNo;
  }

  public void setSsjCardNo(String ssjCardNo) {
    this.ssjCardNo = ssjCardNo;
  }

  public String getSsjCardBankName() {
    return ssjCardBankName;
  }

  public void setSsjCardBankName(String ssjCardBankName) {
    this.ssjCardBankName = ssjCardBankName;
  }

  public Byte getOpenMethod() {
    return openMethod;
  }

  public void setOpenMethod(Byte openMethod) {
    this.openMethod = openMethod;
  }

  public Byte getDefaultPwd() {
    return defaultPwd;
  }

  public void setDefaultPwd(Byte defaultPwd) {
    this.defaultPwd = defaultPwd;
  }


  @Override
  public String toString() {
    return "SsjAccountEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", mobile='" + mobile + '\'' +
        ", idCardNo='" + idCardNo + '\'' +
        ", bankName='" + bankName + '\'' +
        ", cardNo='" + cardNo + '\'' +
        ", openId='" + openId + '\'' +
        ", accountStatus=" + accountStatus +
        ", authStatus=" + authStatus +
        ", bindCardStatus=" + bindCardStatus +
        ", msg='" + msg + '\'' +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        ", ssjCardNo='" + ssjCardNo + '\'' +
        ", ssjCardBankName='" + ssjCardBankName + '\'' +
        ", openMethod=" + openMethod +
        ", defaultPwd=" + defaultPwd +
        '}';
  }
}
