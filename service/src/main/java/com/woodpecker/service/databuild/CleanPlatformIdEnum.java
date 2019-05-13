package com.woodpecker.service.databuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈DataBuild造订单时候的「cleanPlatformId」枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum CleanPlatformIdEnum {

  ZX("25", "大树-藏信"),

  BY("13", "包银"),

  WX("15", "网信"),

  JS("16", "晋商"),

  ZXXD("17", "中新"),

  SSJ("18", "随手记"),

  YK("20", "营口行"),

  BH("23", "中兵-渤海"),

  MNW("24", "玛瑙湾单标"),

  ALL("99", "全部");


  String value;

  String name;


  CleanPlatformIdEnum(String value, String name) {
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


  private static final Map<String, CleanPlatformIdEnum> map = new HashMap<>();


  static {
    CleanPlatformIdEnum[] cleanPlatformIdEnums = CleanPlatformIdEnum.values();
    for (CleanPlatformIdEnum cleanPlatformIdEnum : cleanPlatformIdEnums) {
      map.put(cleanPlatformIdEnum.getValue(), cleanPlatformIdEnum);
    }
  }


  /**
   * 方法功能描述: 判断CleanPlatformIdEnum枚举中，是否含有此value(需要洗数的渠道)
   *
   * @param value 需要洗数的渠道
   * @return boolean
   */
  public static boolean contain(String value) {
    return map.containsKey(value);
  }


  /**
   * 方法功能描述: 通过value(需要洗数的渠道)，获取出实际的CleanPlatformIdEnum枚举
   *
   * @param value 需要洗数的渠道
   * @return CleanPlatformIdEnum
   */
  public static CleanPlatformIdEnum convert(String value) {
    if (contain(value)) {
      return map.get(value);
    } else {
      return null;
    }
  }

}
