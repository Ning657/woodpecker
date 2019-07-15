package com.woodpecker.framework.pay;

/**
 * 枚举类描述:〈支付组和支付通道对应关系枚举〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum PayGroupPlatformEnum {

  WANGXIN("17", PayPlatformEnum.WANGXIN, "网信支付组", "网信扣款"),

  JD007("6", PayPlatformEnum.JDAGREEMENTPAY_4551, "洋钱罐支付组", "京东(007-4551)"),

  JD006("6", PayPlatformEnum.JDPAY_4551, "洋钱罐支付组", "京东(006-4551)"),

  BOHAI("19", PayPlatformEnum.BOHAITRUST, "渤海支付组", "渤海协议支付"),

  SSJ("20", PayPlatformEnum.SSJPAY, "随手记支付组", "随手记"),

  MNW("21", PayPlatformEnum.MNWPAY, "玛瑙湾支付组", "玛瑙湾"),

  MNW_JN("22", PayPlatformEnum.MNW_JN_PAY, "玛瑙湾-景宁", "玛瑙湾景宁"),

  JD_JN_XY("31", PayPlatformEnum.JD_XY_PAY, "景宁旭雨", "京东-景宁旭雨(5213)"),

  JD_FAST_JN_XY("31", PayPlatformEnum.JD_XY_FAST_PAY, "景宁旭雨", "京东快捷-景宁旭雨(5213)"),

  XIZANG("32", PayPlatformEnum.XZ_TRUST_PAY, "西藏信托支付组", "西藏信托"),

  ALL_IN("6", PayPlatformEnum.ALL_IN_PAY, "洋钱罐支付组", "通联支付"),

  ALL_IN_JN("31", PayPlatformEnum.ALL_IN_JN_PAY, "洋钱罐支付组", "通联景宁支付"),

  YOOLI("33", PayPlatformEnum.YOOLI_PAY, "有利网-支付组", "有利网支付"),

  GUANGDA("29", PayPlatformEnum.GDTRUST_PAY, "光大信托-支付组", "光大信托支付");


  /**
   * 支付组code
   */
  String payGroupCode;

  /**
   * 支付通道code
   */
  PayPlatformEnum payPlatformCode;

  String payGroupName;

  String payPlatformName;


  PayGroupPlatformEnum(String payGroupCode,
      PayPlatformEnum payPlatformCode, String payGroupName, String payPlatformName) {
    this.payGroupCode = payGroupCode;
    this.payPlatformCode = payPlatformCode;
    this.payGroupName = payGroupName;
    this.payPlatformName = payPlatformName;
  }


  public String getPayGroupCode() {
    return payGroupCode;
  }

  public PayPlatformEnum getPayPlatformCode() {
    return payPlatformCode;
  }

  public String getPayGroupName() {
    return payGroupName;
  }

  public String getPayPlatformName() {
    return payPlatformName;
  }

}
