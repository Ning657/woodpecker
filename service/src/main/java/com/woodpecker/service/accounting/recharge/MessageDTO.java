package com.woodpecker.service.accounting.recharge;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 类描述:〈accounting发送还款成功通知入账MQ的message参数对象〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class MessageDTO implements Serializable {

  BigDecimal amount;

  String channel;

  long userId;

  byte rechargeType;

  long scheduleId;


  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public byte getRechargeType() {
    return rechargeType;
  }

  public void setRechargeType(byte rechargeType) {
    this.rechargeType = rechargeType;
  }

  public long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(long scheduleId) {
    this.scheduleId = scheduleId;
  }
}
