package com.woodpecker.entity.loandb;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 类描述:〈qs_composite_company表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "qs_composite_company")
public class CompositeCompanyEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "withhold_company_code")
  private String withholdCompanyCode;

  @Column(name = "withhold_company")
  private String withholdCompany;

  @Column(name = "intermediary_company_code")
  private String intermediaryCompanyCode;

  @Column(name = "intermediary_company")
  private String intermediaryCompany;

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

  public String getWithholdCompanyCode() {
    return withholdCompanyCode;
  }

  public void setWithholdCompanyCode(String withholdCompanyCode) {
    this.withholdCompanyCode = withholdCompanyCode;
  }

  public String getWithholdCompany() {
    return withholdCompany;
  }

  public void setWithholdCompany(String withholdCompany) {
    this.withholdCompany = withholdCompany;
  }

  public String getIntermediaryCompanyCode() {
    return intermediaryCompanyCode;
  }

  public void setIntermediaryCompanyCode(String intermediaryCompanyCode) {
    this.intermediaryCompanyCode = intermediaryCompanyCode;
  }

  public String getIntermediaryCompany() {
    return intermediaryCompany;
  }

  public void setIntermediaryCompany(String intermediaryCompany) {
    this.intermediaryCompany = intermediaryCompany;
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
    return "CompositeCompanyEntity{" +
        "id=" + id +
        ", withholdCompanyCode='" + withholdCompanyCode + '\'' +
        ", withholdCompany='" + withholdCompany + '\'' +
        ", intermediaryCompanyCode='" + intermediaryCompanyCode + '\'' +
        ", intermediaryCompany='" + intermediaryCompany + '\'' +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        '}';
  }
}
