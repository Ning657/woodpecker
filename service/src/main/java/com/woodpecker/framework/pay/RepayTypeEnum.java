package com.woodpecker.framework.pay;

/**
 * 枚举类描述:〈还款方式枚举〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum RepayTypeEnum {

  CUSTOMER("用户发起"),

  DEVELOPER("开发发起"),

  EMPLOYEE("员工操作"),

  COLLECTION("催收系统"),

  COLLECTION_AUTORUN("催收定时任务"),

  COLLECTION_PROXY("催收代扣"),

  PREMIUM("趸交"),

  COLLECTION_DEPOSIT("催收充值"),

  COLLECTION_BATCH("催收手动批量代扣"),

  CLEARING("清算系统"),

  CLEARING_BATCH("清算批量代扣"),

  OTHER("其他系统"),

  UNKNOWN("未知"),

  GARONA("GARONA系统"),

  GARONA_AUTORUN("GARONA系统定时任务"),

  CRM("CRM发起"),

  WX_COLLECTION("网信发起");


  private String desc;


  RepayTypeEnum(String desc) {
    this.desc = desc;
  }


  public String getDesc() {
    return desc;
  }

}
