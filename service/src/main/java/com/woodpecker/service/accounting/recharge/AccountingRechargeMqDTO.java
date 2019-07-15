package com.woodpecker.service.accounting.recharge;

import java.io.Serializable;

/**
 * 类描述:〈accounting发送还款成功通知入账MQ的参数〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class AccountingRechargeMqDTO implements Serializable {

  String topic = "recharge";

  String tag = "*";

  MessageDTO message;


  public AccountingRechargeMqDTO(MessageDTO message) {
    this.message = message;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public MessageDTO getMessage() {
    return message;
  }

  public void setMessage(MessageDTO message) {
    this.message = message;
  }
}
