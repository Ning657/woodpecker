package com.woodpecker.entity.autotest;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 类描述:〈t_case表实体类〉
 *
 * @version 1.0
 * @author: xujinjian
 */
@Entity
@Table(name = "t_case")
public class CaseEntity implements Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


  @Column(name = "case_id")
  private String caseId;


  @Column(name = "status")
  private Integer status;


  @Column(name = "name")
  private String name;


  @Column(name = "value")
  private String value;


  @Column(name = "description")
  private String description;


  @Column(name = "create_date")
  private Date createDate;


  @Column(name = "last_update_date")
  private Date lastUpdateDate;


  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public String getCaseId() {
    return caseId;
  }


  public void setCaseId(String caseId) {
    this.caseId = caseId;
  }


  public Integer getStatus() {
    return status;
  }


  public void setStatus(Integer status) {
    this.status = status;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public Date getCreateDate() {
    return createDate;
  }


  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }


  public Date getLastUpdateDate() {
    return lastUpdateDate;
  }


  public void setLastUpdateDate(Date lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }


  @Override
  public String toString() {
    return "CaseEntity{" +
        "id=" + id +
        ", caseId='" + caseId + '\'' +
        ", status=" + status +
        ", name='" + name + '\'' +
        ", value='" + value + '\'' +
        ", description='" + description + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        '}';
  }


}
