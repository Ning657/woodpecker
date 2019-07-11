package com.woodpecker.framework.pay;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈rt_pay_channel表的code字段枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum PayChannelCodeEnum {

  JD("JD", "京东两方代扣"),

  WX("WX", "网信"),

  JD_FAST("JD_FAST", "京东快捷"),

  BOHAI_TRUST("BOHAI_TRUST", "渤海信托"),

  SSJ("SSJ", "随手记"),

  JD_AGENCY("JD_AGENCY", "京东代付"),

  JD_AGREEMENT("JD_AGREEMENT", "京东协议支付"),

  MNW("MNW", "玛瑙湾支付"),

  XZ_TRUST("XZ_TRUST", "西藏信托"),

  ALL_IN("ALL_IN", "通联"),

  YOOLI("YOOLI", "有利网");


  private String code;

  private String name;


  PayChannelCodeEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }


  public String getCode() {
    return code;
  }


  public String getName() {
    return name;
  }


  private static final Map<String, PayChannelCodeEnum> map = new HashMap<>();


  static {
    PayChannelCodeEnum[] payChannelCodeEnums = PayChannelCodeEnum.values();
    for (PayChannelCodeEnum payChannelCodeEnum : payChannelCodeEnums) {
      map.put(payChannelCodeEnum.getCode(), payChannelCodeEnum);
    }
  }


  public static boolean contain(String code) {
    return map.containsKey(code);
  }


  public static PayChannelCodeEnum convert(String code) {
    if (contain(code)) {
      return map.get(code);
    } else {
      return null;
    }
  }

}
