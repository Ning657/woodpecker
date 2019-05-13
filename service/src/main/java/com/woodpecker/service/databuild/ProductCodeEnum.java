package com.woodpecker.service.databuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈DataBuild造订单时候的「productCode」枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum ProductCodeEnum {

  CQD("CQD"),

  SMY("SMY"),

  DQD("DQD"),

  XQD("XQD"),

  KN("KN");


  String value;


  ProductCodeEnum(String value) {
    this.value = value;
  }


  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  private static final Map<String, ProductCodeEnum> map = new HashMap<>();


  static {
    ProductCodeEnum[] productCodeEnums = ProductCodeEnum.values();
    for (ProductCodeEnum productCodeEnum : productCodeEnums) {
      map.put(productCodeEnum.getValue(), productCodeEnum);
    }
  }


  /**
   * 方法功能描述: 判断ProductCodeEnum枚举中，是否含有此value(productCode)
   *
   * @param value productCode
   * @return boolean
   */
  public static boolean contain(String value) {
    return map.containsKey(value);
  }


  /**
   * 方法功能描述: 通过value(productCode)，获取出实际的ProductCodeEnum枚举
   *
   * @param value productCode
   * @return ProductCodeEnum
   */
  public static ProductCodeEnum convert(String value) {
    if (contain(value)) {
      return map.get(value);
    } else {
      return null;
    }
  }

}
