package com.woodpecker.service.databuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈DataBuild造订单时候的「风险等级」枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum RiskLevelEnum {

  A("A"),

  B("B"),

  C("C"),

  D("D"),

  E("E");

  String value;


  RiskLevelEnum(String value) {
    this.value = value;
  }


  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  private static final Map<String, RiskLevelEnum> map = new HashMap<>();


  static {
    RiskLevelEnum[] riskLevelEnums = RiskLevelEnum.values();
    for (RiskLevelEnum riskLevelEnum : riskLevelEnums) {
      map.put(riskLevelEnum.getValue(), riskLevelEnum);
    }
  }


  /**
   * 方法功能描述: 判断RiskLevelEnum枚举中，是否含有此value(风险等级)
   *
   * @param value 风险等级
   * @return boolean
   */
  public static boolean contain(String value) {
    return map.containsKey(value);
  }


  /**
   * 方法功能描述: 通过value(风险等级)，获取出实际的RiskLevelEnum枚举
   *
   * @param value 风险等级
   * @return RiskLevelEnum
   */
  public static RiskLevelEnum convert(String value) {
    if (contain(value)) {
      return map.get(value);
    } else {
      return null;
    }
  }

}
