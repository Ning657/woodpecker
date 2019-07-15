package com.woodpecker.framework.pay;

import com.woodpecker.framework.bind.BindCardEnum;
import com.xujinjian.Commons.Lang.StringUtil;

/**
 * 枚举类描述:〈rt_pay_platform表及对应的下游渠道号枚举〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum PayPlatformEnum {

  JDAGREEMENTPAY_4551("42", (byte) 1, "JD", "JDAGREEMENTPAY_4551", "京东(007-4551)", "102", "京东两方代扣",
      BindCardEnum.JDAGREEMENTPAY),

  JDPAY_4551("24", (byte) 0, "JD_FAST", "JDPAY_4551", "京东(006-4551)", "98", "京东快捷",
      BindCardEnum.NONE),

  WANGXIN("23", (byte) 0, "WX", "WANGXIN", "网信扣款", "92", "网信", BindCardEnum.WANGXIN),

  BOHAITRUST("49", (byte) 1, "BOHAI_TRUST", "BOHAITRUST", "渤海协议支付", "49", "渤海信托",
      BindCardEnum.BOHAITRUST),

  SSJPAY("50", (byte) 1, "SSJ", "SSJPAY", "随手记支付", "50", "随手记", BindCardEnum.SSJPAY),

  MNWPAY("52", (byte) 1, "MNW", "MNWPAY", "玛瑙湾支付", "52", "玛瑙湾支付", BindCardEnum.MNWPAY),

  MNW_JN_PAY("53", (byte) 1, "MNW", "MNW_JN_PAY", "玛瑙湾景宁支付", "53", "玛瑙湾支付", BindCardEnum.MNWPAY),

  JD_XY_PAY("57", (byte) 1, "JD", "JD_XY_PAY", "京东-景宁旭雨-代扣", "57", "京东两方代扣",
      BindCardEnum.JDAGREEMENTPAY),

  JD_XY_FAST_PAY("58", (byte) 0, "JD", "JD_XY_FAST_PAY", "京东-景宁-快捷", "58", "京东快捷",
      BindCardEnum.NONE),

  XZ_TRUST_PAY("59", (byte) 0, "XZ_TRUST", "XZ_TRUST_PAY", "西藏信托", "59", "西藏信托", BindCardEnum.NONE),

  ALL_IN_PAY("60", (byte) 1, "ALL_IN", "ALL_IN_PAY", "通联支付", "60", "通联", BindCardEnum.ALL_IN_PAY),

  ALL_IN_JN_PAY("61", (byte) 1, "ALL_IN", "ALL_IN_JN_PAY", "通联景宁支付", "61", "通联",
      BindCardEnum.ALL_IN_PAY),

  YOOLI_PAY("62", (byte) 1, "YOOLI", "YOOLI_PAY", "有利网支付", "62", "有利网", BindCardEnum.YOOLI_PAY),

  GDTRUST_PAY("64", (byte) 1, "GDTRUST", "GDTRUST_PAY", "光大信托支付", "64", "光大信托",
      BindCardEnum.GDTRUST_PAY);


  /**
   * 支付通道编码
   */
  String code;

  /**
   * 是否需要绑卡 0：不需要1：需要
   */
  byte bindCard;

  /**
   * 所属支付渠道
   */
  String payChannelCode;

  /**
   * 支付通道名称
   */
  String name;

  /**
   *
   */
  String platformName;

  /**
   * 支付通道对应的下游渠道号
   */
  String channel;

  /**
   * 所属支付渠道名称
   */
  String payChannelName;

  /**
   * 绑卡渠道枚举
   */
  BindCardEnum bindCardEnum;


  PayPlatformEnum(String code, byte bindCard, String payChannelCode, String name,
      String platformName, String channel, String payChannelName, BindCardEnum bindCardEnum) {
    this.code = code;
    this.bindCard = bindCard;
    this.payChannelCode = payChannelCode;
    this.name = name;
    this.platformName = platformName;
    this.channel = channel;
    this.payChannelName = payChannelName;
    this.bindCardEnum = bindCardEnum;
  }


  public String getCode() {
    return code;
  }

  public byte getBindCard() {
    return bindCard;
  }

  public String getPayChannelCode() {
    return payChannelCode;
  }

  public String getName() {
    return name;
  }

  public String getPlatformName() {
    return platformName;
  }

  public String getChannel() {
    return channel;
  }

  public String getPayChannelName() {
    return payChannelName;
  }

  public BindCardEnum getBindCardEnum() {
    return bindCardEnum;
  }


  public static PayPlatformEnum getPayPlatformEnumByCode(String code) {
    for (PayPlatformEnum payPlatformEnum : values()) {
      if (StringUtil.equals(payPlatformEnum.code, code)) {
        return payPlatformEnum;
      }
    }
    throw new IllegalArgumentException("Undefined code:" + code);
  }


  public static PayPlatformEnum getPayPlatformEnumByName(String name) {
    for (PayPlatformEnum payPlatformEnum : values()) {
      if (StringUtil.equals(payPlatformEnum.name, name)) {
        return payPlatformEnum;
      }
    }
    throw new IllegalArgumentException("Undefined name:" + name);
  }


  public static PayPlatformEnum getPayPlatformEnumByChannel(String channel) {
    for (PayPlatformEnum payPlatformEnum : values()) {
      if (StringUtil.equals(payPlatformEnum.channel, channel)) {
        return payPlatformEnum;
      }
    }
    throw new IllegalArgumentException("Undefined channel:" + channel);
  }

}
