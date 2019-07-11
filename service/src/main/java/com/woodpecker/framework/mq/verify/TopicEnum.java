package com.woodpecker.framework.mq.verify;

/**
 * 枚举类描述:〈mq的topic枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum TopicEnum {

  RECHARGE("recharge");


  String value;


  TopicEnum(String value) {
    this.value = value;
  }


  public String getValue() {
    return value;
  }

}
