package com.woodpecker.entity.loandb;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈qs_mnw_account表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "qs_mnw_account")
public class MnwAccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "mobile")
  private String mobile;

  @Column(name = "id_card_no")
  private String idCardNo;

  @Column(name = "bank_name")
  private String bankName;

  @Column(name = "card_no")
  private String cardNo;

  @Column(name = "plat_user_id")
  private Integer platUserId;

  @Column(name = "open_status")
  private Byte openStatus;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "last_update_time")
  private Date lastUpdateTime;


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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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

  public Integer getPlatUserId() {
    return platUserId;
  }

  public void setPlatUserId(Integer platUserId) {
    this.platUserId = platUserId;
  }

  public Byte getOpenStatus() {
    return openStatus;
  }

  public void setOpenStatus(Byte openStatus) {
    this.openStatus = openStatus;
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
    return "MnwAccountEntity{" +
        "id=" + id +
        ", userId=" + userId +
        ", userName='" + userName + '\'' +
        ", mobile='" + mobile + '\'' +
        ", idCardNo='" + idCardNo + '\'' +
        ", bankName='" + bankName + '\'' +
        ", cardNo='" + cardNo + '\'' +
        ", platUserId=" + platUserId +
        ", openStatus=" + openStatus +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        '}';
  }
}
