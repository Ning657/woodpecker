package com.woodpecker.service.databuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈DataBuild造订单时候的「platformId」枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum PlatformIdEnum {

  ALL("", "全部"),

  YQG("9", "洋钱罐"),

  WSM("11", "微神马"),

  AQJ("12", "爱钱进"),

  BY("13", "包银"),

  WX("15", "网信"),

  JS("16", "晋商"),

  ZXXD("17", "中新"),

  SSJ("18", "随手记"),

  YK("20", "营口行"),

  WX_ZX("21", "网信&中新"),

  SSJ_ZX("22", "随手记&中新"),

  BH("23", "渤海信托"),

  MNW("24", "玛瑙湾单标"),

  ZX("25", "西藏信托"),

  YLW("26", "有利网");


  String value;

  String name;


  PlatformIdEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  private static final Map<String, PlatformIdEnum> map = new HashMap<>();


  static {
    PlatformIdEnum[] platformIdEnums = PlatformIdEnum.values();
    for (PlatformIdEnum platformIdEnum : platformIdEnums) {
      map.put(platformIdEnum.getValue(), platformIdEnum);
    }
  }


  /**
   * 方法功能描述: 判断PlatformIdEnum枚举中，是否含有此value(资金渠道)
   *
   * @param value 资金渠道
   * @return boolean
   */
  public static boolean contain(String value) {
    return map.containsKey(value);
  }


  /**
   * 方法功能描述: 通过value(资金渠道)，获取出实际的PlatformIdEnum枚举
   *
   * @param value 资金渠道
   * @return PlatformIdEnum
   */
  public static PlatformIdEnum convert(String value) {
    if (contain(value)) {
      return map.get(value);
    } else {
      return null;
    }
  }

}
