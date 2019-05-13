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
 * 类描述:〈rt_pay_group_platform表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "rt_pay_group_platform")
public class PayGroupPlatformEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "pay_group_code")
  private String payGroupCode;

  @Column(name = "pay_platform_code")
  private String payPlatformCode;

  @Column(name = "weight")
  private Double weight;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "version")
  private String version;

  @Column(name = "pay_group_name")
  private String payGroupName;

  @Column(name = "pay_platform_name")
  private String payPlatformName;

  @Column(name = "rt_key")
  private String rtKey;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPayGroupCode() {
    return payGroupCode;
  }

  public void setPayGroupCode(String payGroupCode) {
    this.payGroupCode = payGroupCode;
  }

  public String getPayPlatformCode() {
    return payPlatformCode;
  }

  public void setPayPlatformCode(String payPlatformCode) {
    this.payPlatformCode = payPlatformCode;
  }

  public Double getWeight() {
    return weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getPayGroupName() {
    return payGroupName;
  }

  public void setPayGroupName(String payGroupName) {
    this.payGroupName = payGroupName;
  }

  public String getPayPlatformName() {
    return payPlatformName;
  }

  public void setPayPlatformName(String payPlatformName) {
    this.payPlatformName = payPlatformName;
  }

  public String getRtKey() {
    return rtKey;
  }

  public void setRtKey(String rtKey) {
    this.rtKey = rtKey;
  }


  @Override
  public String toString() {
    return "PayGroupPlatformEntity{" +
        "id=" + id +
        ", payGroupCode='" + payGroupCode + '\'' +
        ", payPlatformCode='" + payPlatformCode + '\'' +
        ", weight=" + weight +
        ", updateDate=" + updateDate +
        ", createDate=" + createDate +
        ", version='" + version + '\'' +
        ", payGroupName='" + payGroupName + '\'' +
        ", payPlatformName='" + payPlatformName + '\'' +
        ", rtKey='" + rtKey + '\'' +
        '}';
  }
}
