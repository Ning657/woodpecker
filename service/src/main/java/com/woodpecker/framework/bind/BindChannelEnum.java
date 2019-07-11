package com.woodpecker.framework.bind;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈绑卡/鉴权通道枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum BindChannelEnum {

  JDAGREEMENTPAY("JDAGREEMENTPAY", "40"),

  ALL_IN_PAY("ALL_IN_PAY", "60"),

  BOHAITRUST("BOHAITRUST", "49"),

  SSJPAY("SSJPAY", "50"),

  MNWPAY("MNWPAY", "52"),

  //WANGXIN("WANGXIN", "23"),

  YOOLI_PAY("YOOLI_PAY", "62");


  private String value;

  private String code;


  BindChannelEnum(String value, String code) {
    this.value = value;
    this.code = code;
  }


  public String getValue() {
    return value;
  }


  public String getCode() {
    return code;
  }


  private static final Map<String, BindChannelEnum> map = new HashMap<>();


  static {
    BindChannelEnum[] bindChannelEnums = BindChannelEnum.values();
    for (BindChannelEnum bindChannelEnum : bindChannelEnums) {
      map.put(bindChannelEnum.getValue(), bindChannelEnum);
    }
  }


  public static boolean contain(String value) {
    return map.containsKey(value);
  }


  public static BindChannelEnum convert(String value) {
    if (contain(value)) {
      return map.get(value);
    } else {
      return null;
    }
  }

}
