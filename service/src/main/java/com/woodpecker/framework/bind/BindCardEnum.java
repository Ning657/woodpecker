package com.woodpecker.framework.bind;

import com.xujinjian.Commons.Lang.StringUtil;

/**
 * 枚举类描述:〈绑卡渠道枚举〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum BindCardEnum {

  NONE("", "", (byte) -1),

  JDAGREEMENTPAY("JDAGREEMENTPAY", "JD", (byte) 40),

  ALL_IN_PAY("ALL_IN_PAY", "ALL_IN", (byte) 60),

  BOHAITRUST("BOHAITRUST", "BOHAI_TRUST", (byte) 49),

  SSJPAY("SSJPAY", "SSJ", (byte) 50),

  MNWPAY("MNWPAY", "MNW", (byte) 52),

  WANGXIN("WANGXIN", "WX", (byte) 23),

  YOOLI_PAY("YOOLI_PAY", "YOOLI", (byte) 62),

  GDTRUST_PAY("GDTRUST_PAY", "GDTRUST", (byte) 64);


  /**
   * rt_pay_platform表的name
   */
  String payPlatformName;

  /**
   * rt_pay_channel表的code
   */
  String payChannelCode;

  /**
   * t_bank_account表的channel
   */
  byte channel;


  BindCardEnum(String payPlatformName, String payChannelCode, byte channel) {
    this.payPlatformName = payPlatformName;
    this.payChannelCode = payChannelCode;
    this.channel = channel;
  }


  public String getPayPlatformName() {
    return payPlatformName;
  }

  public String getPayChannelCode() {
    return payChannelCode;
  }

  public byte getChannel() {
    return channel;
  }


  public static BindCardEnum getBindCardEnumByPayPlatformName(String payPlatformName) {
    for (BindCardEnum bindCardEnum : values()) {
      if (StringUtil.equals(bindCardEnum.payPlatformName, payPlatformName)) {
        return bindCardEnum;
      }
    }
    throw new IllegalArgumentException("Undefined payPlatformName:" + payPlatformName);
  }


  public static BindCardEnum getBindCardEnumByPayChannelCode(String payChannelCode) {
    for (BindCardEnum bindCardEnum : values()) {
      if (StringUtil.equals(bindCardEnum.payChannelCode, payChannelCode)) {
        return bindCardEnum;
      }
    }
    throw new IllegalArgumentException("Undefined payChannelCode:" + payChannelCode);
  }


  public static BindCardEnum getBindCardEnumByChannel(byte channel) {
    for (BindCardEnum bindCardEnum : values()) {
      if (bindCardEnum.channel == channel) {
        return bindCardEnum;
      }
    }
    throw new IllegalArgumentException("Undefined channel:" + channel);
  }

}
