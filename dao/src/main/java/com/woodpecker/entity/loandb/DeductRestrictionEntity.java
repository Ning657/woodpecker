package com.woodpecker.entity.loandb;

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
 * 类描述:〈t_deduct_restriction表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "t_deduct_restriction")
public class DeductRestrictionEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "RefId")
  private String refId;

  @Column(name = "Type")
  private Byte type;

  @Column(name = "PerAmount")
  private BigDecimal perAmount;

  @Column(name = "TotalAmount")
  private BigDecimal totalAmount;

  @Column(name = "Times")
  private Byte times;

  @Column(name = "Period")
  private Byte period;

  @Column(name = "Mode")
  private String mode;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "UpdateDate")
  private Date updateDate;

  @Column(name = "IsDeprecated")
  private Byte isDeprecated;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRefId() {
    return refId;
  }

  public void setRefId(String refId) {
    this.refId = refId;
  }

  public Byte getType() {
    return type;
  }

  public void setType(Byte type) {
    this.type = type;
  }

  public BigDecimal getPerAmount() {
    return perAmount;
  }

  public void setPerAmount(BigDecimal perAmount) {
    this.perAmount = perAmount;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public Byte getTimes() {
    return times;
  }

  public void setTimes(Byte times) {
    this.times = times;
  }

  public Byte getPeriod() {
    return period;
  }

  public void setPeriod(Byte period) {
    this.period = period;
  }

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
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

  public Byte getIsDeprecated() {
    return isDeprecated;
  }

  public void setIsDeprecated(Byte isDeprecated) {
    this.isDeprecated = isDeprecated;
  }


  @Override
  public String toString() {
    return "DeductRestrictionEntity{" +
        "id=" + id +
        ", refId='" + refId + '\'' +
        ", type=" + type +
        ", perAmount=" + perAmount +
        ", totalAmount=" + totalAmount +
        ", times=" + times +
        ", period=" + period +
        ", mode='" + mode + '\'' +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", isDeprecated=" + isDeprecated +
        '}';
  }
}
