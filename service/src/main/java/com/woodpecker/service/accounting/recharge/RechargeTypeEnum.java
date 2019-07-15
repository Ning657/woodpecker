package com.woodpecker.service.accounting.recharge;

/**
 * 枚举类描述:〈rechargeType枚举〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum RechargeTypeEnum {

  ALL(0, "趸交和分期"),

  REPAYMENT(1, "分期"),

  PREMIUM(2, "趸交");


  int value;

  String desc;

  RechargeTypeEnum(int value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public int getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }
}
