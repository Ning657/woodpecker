package com.woodpecker.entity.payment;

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
 * 类描述:〈rt_pay_platform表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "rt_pay_platform")
public class PayPlatformEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "code")
  private String code;

  @Column(name = "support_sources")
  private Integer supportSources;

  @Column(name = "bind_card")
  private Byte bindCard;

  @Column(name = "status")
  private Byte status;

  @Column(name = "fee")
  private BigDecimal fee;

  @Column(name = "pay_channel_code")
  private String payChannelCode;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "version")
  private String version;

  @Column(name = "name")
  private String name;

  @Column(name = "rt_key")
  private String rtKey;

  @Column(name = "individual_bizdata")
  private String individualBizdata;

  @Column(name = "thirdpay_init")
  private String thirdpayInit;

  @Column(name = "platform_name")
  private String platformName;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getSupportSources() {
    return supportSources;
  }

  public void setSupportSources(Integer supportSources) {
    this.supportSources = supportSources;
  }

  public Byte getBindCard() {
    return bindCard;
  }

  public void setBindCard(Byte bindCard) {
    this.bindCard = bindCard;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public BigDecimal getFee() {
    return fee;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee;
  }

  public String getPayChannelCode() {
    return payChannelCode;
  }

  public void setPayChannelCode(String payChannelCode) {
    this.payChannelCode = payChannelCode;
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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRtKey() {
    return rtKey;
  }

  public void setRtKey(String rtKey) {
    this.rtKey = rtKey;
  }

  public String getIndividualBizdata() {
    return individualBizdata;
  }

  public void setIndividualBizdata(String individualBizdata) {
    this.individualBizdata = individualBizdata;
  }

  public String getThirdpayInit() {
    return thirdpayInit;
  }

  public void setThirdpayInit(String thirdpayInit) {
    this.thirdpayInit = thirdpayInit;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }


  @Override
  public String toString() {
    return "PayPlatformEntity{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", supportSources=" + supportSources +
        ", bindCard=" + bindCard +
        ", status=" + status +
        ", fee=" + fee +
        ", payChannelCode='" + payChannelCode + '\'' +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", version='" + version + '\'' +
        ", name='" + name + '\'' +
        ", rtKey='" + rtKey + '\'' +
        ", individualBizdata='" + individualBizdata + '\'' +
        ", thirdpayInit='" + thirdpayInit + '\'' +
        ", platformName='" + platformName + '\'' +
        '}';
  }
}
