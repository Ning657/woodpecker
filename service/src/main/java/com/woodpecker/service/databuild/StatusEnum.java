package com.woodpecker.service.databuild;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类描述:〈DataBuild订单进程枚举类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public enum StatusEnum {

  newOrder("1", "新订单"),

  waitDistribute("2", "已存证待分发"),

  waitPush("3", "已签约待推送");


  String status;

  String desc;

  StatusEnum(String status, String desc) {
    this.status = status;
    this.desc = desc;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  private static final Map<String, StatusEnum> map = new HashMap<>();


  static {
    StatusEnum[] statusEnums = StatusEnum.values();
    for (StatusEnum statusEnum : statusEnums) {
      map.put(statusEnum.getStatus(), statusEnum);
    }
  }


  /**
   * 方法功能描述: 判断StatusEnum枚举中，是否含有此status(订单进程)
   *
   * @param status 订单进程
   * @return boolean
   */
  public static boolean contain(String status) {
    return map.containsKey(status);
  }


  /**
   * 方法功能描述: 通过status(订单进程)，获取出实际的StatusEnum枚举
   *
   * @param status 订单进程
   * @return StatusEnum
   */
  public static StatusEnum convert(String status) {
    if (contain(status)) {
      return map.get(status);
    } else {
      return null;
    }
  }

}
