package com.woodpecker.service.databuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈DataBuild贷款期限枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum LoanPeriodEnum {

  Three("3"),

  Six("6"),

  Twelve("12"),

  Eighteen("18"),

  TwentyFour("24");


  String value;


  LoanPeriodEnum(String value) {
    this.value = value;
  }


  public String getValue() {
    return value;
  }


  public void setValue(String value) {
    this.value = value;
  }


  private static final Map<String, LoanPeriodEnum> map = new HashMap<>();


  static {
    LoanPeriodEnum[] loanPeriodEnums = LoanPeriodEnum.values();
    for (LoanPeriodEnum loanPeriodEnum : loanPeriodEnums) {
      map.put(loanPeriodEnum.getValue(), loanPeriodEnum);
    }
  }


  /**
   * 方法功能描述: 判断LoanPeriodEnum枚举中，是否含有此value(贷款期限)
   *
   * @param value 贷款期限
   * @return boolean
   */
  public static boolean contain(String value) {
    return map.containsKey(value);
  }


  /**
   * 方法功能描述: 通过value(贷款期限)，获取出实际的LoanPeriodEnum枚举
   *
   * @param value 贷款期限
   * @return LoanPeriodEnum
   */
  public static LoanPeriodEnum convert(String value) {
    if (contain(value)) {
      return map.get(value);
    } else {
      return null;
    }
  }

}
