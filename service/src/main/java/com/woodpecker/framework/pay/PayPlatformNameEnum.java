package com.woodpecker.framework.pay;

/**
 * 枚举类描述:〈rt_pay_platform表的name字段枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum PayPlatformNameEnum {

  JDAGREEMENTPAY_CITIC("JDAGREEMENTPAY_CITIC", "41"),

  JDAGREEMENTPAY_4551("JDAGREEMENTPAY_4551", "42"),

  JDAGREEMENTPAY_CLEARING("JDAGREEMENTPAY_CLEARING", "43"),

  JDAGREEMENTPAY_BAOYIN("JDAGREEMENTPAY_BAOYIN", "44"),

  JDPAY("JDPAY", "6"),

  CHINAPNR("CHINAPNR", "7"),

  JDPAY_4551("JDPAY_4551", "24"),

  WANGXIN("WANGXIN", "23"),

  BOHAITRUST("BOHAITRUST", "49"),

  SSJPAY("SSJPAY", "50"),

  JD_AGENCY_PAY("JD_AGENCY_PAY", "47"),

  MNWPAY("MNWPAY", "52"),

  MNW_JN_PAY("MNW_JN_PAY", "53"),

  JDAGREEMENTPAY("JDAGREEMENTPAY", "40"),

  JD_XY_PAY("JD_XY_PAY", "57"),

  JD_XY_FAST_PAY("JD_XY_FAST_PAY", "58"),

  XZ_TRUST_PAY("XZ_TRUST_PAY", "59"),

  ALL_IN_PAY("ALL_IN_PAY", "60"),

  ALL_IN_JN_PAY("ALL_IN_JN_PAY", "61"),

  YOOLI_PAY("YOOLI_PAY", "62");


  private String name;


  private String code;


  PayPlatformNameEnum(String name, String code) {
    this.name = name;
    this.code = code;
  }


  public String getName() {
    return name;
  }


  public String getCode() {
    return code;
  }

}
