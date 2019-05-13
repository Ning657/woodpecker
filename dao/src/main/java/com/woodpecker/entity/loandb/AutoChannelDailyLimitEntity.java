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
 * 类描述:〈qs_auto_channel_daily_limit表实体类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Entity
@Table(name = "qs_auto_channel_daily_limit")
public class AutoChannelDailyLimitEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @Column(name = "date")
  private Date date;

  @Column(name = "route_platform")
  private Integer routePlatform;

  @Column(name = "day_limit")
  private BigDecimal dayLimit;

  @Column(name = "remain_limit")
  private BigDecimal remainLimit;

  @Column(name = "status")
  private Integer status;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "last_update_time")
  private Date lastUpdateTime;

  @Column(name = "editor")
  private String editor;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getRoutePlatform() {
    return routePlatform;
  }

  public void setRoutePlatform(Integer routePlatform) {
    this.routePlatform = routePlatform;
  }

  public BigDecimal getDayLimit() {
    return dayLimit;
  }

  public void setDayLimit(BigDecimal dayLimit) {
    this.dayLimit = dayLimit;
  }

  public BigDecimal getRemainLimit() {
    return remainLimit;
  }

  public void setRemainLimit(BigDecimal remainLimit) {
    this.remainLimit = remainLimit;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  public String getEditor() {
    return editor;
  }

  public void setEditor(String editor) {
    this.editor = editor;
  }


  @Override
  public String toString() {
    return "AutoChannelDailyLimitEntity{" +
        "id=" + id +
        ", date=" + date +
        ", routePlatform=" + routePlatform +
        ", dayLimit=" + dayLimit +
        ", remainLimit=" + remainLimit +
        ", status=" + status +
        ", createTime=" + createTime +
        ", lastUpdateTime=" + lastUpdateTime +
        ", editor='" + editor + '\'' +
        '}';
  }
}
