package com.woodpecker.framework.mq.verify;

/**
 * 枚举类描述:〈MQ消息-消费组枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum ConsumerGroupEnum {

  ACCOUNTING("accounting");


  String value;


  ConsumerGroupEnum(String value) {
    this.value = value;
  }


  public String getValue() {
    return value;
  }

}
