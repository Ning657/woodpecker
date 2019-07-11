package com.woodpecker.framework.mq.verify;

/**
 * 枚举类描述:〈计划类型枚举〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum ScheduleTypeEnum {

  REPAYMENT("还款计划"),

  PREMIUM("趸交计划"),

  LOAN_ORDER("loan_order"),

  NONE("");

  String desc;


  ScheduleTypeEnum(String desc) {
    this.desc = desc;
  }


  public String getDesc() {
    return desc;
  }

}
