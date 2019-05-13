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
 * 类描述:〈qs_swich_config表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "qs_swich_config")
public class SwichConfigEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Id")
  private Integer id;

  @Column(name = "Type")
  private String type;

  @Column(name = "Status")
  private Byte status;

  @Column(name = "Configure")
  private String configure;

  @Column(name = "CreateDate")
  private Date createDate;

  @Column(name = "LastUpdateDate")
  private Date lastUpdateDate;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Byte getStatus() {
    return status;
  }

  public void setStatus(Byte status) {
    this.status = status;
  }

  public String getConfigure() {
    return configure;
  }

  public void setConfigure(String configure) {
    this.configure = configure;
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
    return "SwichConfigEntity{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", status=" + status +
        ", configure='" + configure + '\'' +
        ", createDate=" + createDate +
        ", lastUpdateDate=" + lastUpdateDate +
        '}';
  }
}
