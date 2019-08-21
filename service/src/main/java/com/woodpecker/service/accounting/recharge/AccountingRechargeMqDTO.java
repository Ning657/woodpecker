package com.woodpecker.service.accounting.recharge;

import com.xujinjian.Json.JsonUtil;
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

  String message;


  public AccountingRechargeMqDTO(MessageDTO message) {
    this.message = JsonUtil.toJSONString(message);
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
