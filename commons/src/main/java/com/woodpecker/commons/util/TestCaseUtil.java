package com.woodpecker.commons.util;

import com.sword.autotest.framework.assertion.Validate;

/**
 * 类描述:〈测试用例相关工具类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class TestCaseUtil {

  /**
   * Http响应状态码-200
   */
  private static final int okStatusCode = 200;


  /**
   * 方法功能描述: 检查Http响应状态码是否为200
   *
   * @param statusCode Http响应状态码
   * @param msg 校验点说明
   * @return void
   */
  public static void checkStatusCodeIsOK(int statusCode, String desc) {
    Validate.isEquals(okStatusCode, statusCode, desc);
  }


}
