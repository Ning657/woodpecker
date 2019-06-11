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
 * 类描述:〈rt_pay_channel表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "rt_pay_channel")
public class PayChannelEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @Column(name = "status")
  private Integer status;

  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "update_date")
  private Date updateDate;

  @Column(name = "version")
  private String version;

  @Column(name = "last_update_user")
  private String lastUpdateUser;

  @Column(name = "rt_key")
  private String rtKey;


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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getLastUpdateUser() {
    return lastUpdateUser;
  }

  public void setLastUpdateUser(String lastUpdateUser) {
    this.lastUpdateUser = lastUpdateUser;
  }

  public String getRtKey() {
    return rtKey;
  }

  public void setRtKey(String rtKey) {
    this.rtKey = rtKey;
  }

  @Override
  public String toString() {
    return "PayChannelEntity{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", name='" + name + '\'' +
        ", status=" + status +
        ", createDate=" + createDate +
        ", updateDate=" + updateDate +
        ", version='" + version + '\'' +
        ", lastUpdateUser='" + lastUpdateUser + '\'' +
        ", rtKey='" + rtKey + '\'' +
        '}';
  }
  
}
