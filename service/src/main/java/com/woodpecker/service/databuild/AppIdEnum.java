package com.woodpecker.service.databuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈DataBuild造订单时的「appId」枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum AppIdEnum {

  GFD("1", "功夫贷"),

  XQD("6", "小期贷"),

  KN("7", "卡牛");


  String value;

  String desc;


  AppIdEnum(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }


  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  public String getDesc() {
    return desc;
  }


  public void setDesc(String desc) {
    this.desc = desc;
  }


  private static final Map<String, AppIdEnum> map = new HashMap<>();


  static {
    AppIdEnum[] appIdEnums = AppIdEnum.values();
    for (AppIdEnum appIdEnum : appIdEnums) {
      map.put(appIdEnum.getValue(), appIdEnum);
    }
  }


  /**
   * 方法功能描述: 判断AppIdEnum枚举中，是否含有此value(appName)
   *
   * @param value appName
   * @return boolean
   */
  public static boolean contain(String value) {
    return map.containsKey(value);
  }


  /**
   * 方法功能描述: 通过value(appName)，获取出实际的AppIdEnum枚举
   *
   * @param value appName
   * @return RiskLevelEnum
   */
  public static AppIdEnum convert(String value) {
    if (contain(value)) {
      return map.get(value);
    } else {
      return null;
    }
  }

}
