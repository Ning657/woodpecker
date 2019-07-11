package com.woodpecker.service.payment.repayment.api;

import com.alibaba.fastjson.JSONObject;
import com.xujinjian.HttpClient.HttpResponse;

/**
 * 接口描述:〈还款接口返回值HttpResponse的Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface PayHttpResponseService {


  /**
   * 方法功能描述: 获取还款接口返回的JSON的data部分数据
   *
   * @param httpResponse HttpResponse
   * @return com.alibaba.fastjson.JSONObject
   */
  JSONObject getData(HttpResponse httpResponse);


  /**
   * 方法功能描述: 获取还款接口返回的tradeNo
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  String getTradeNo(HttpResponse httpResponse);


  /**
   * 方法功能描述: 获取还款接口返回的payNo
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  String getPayNo(HttpResponse httpResponse);


  /**
   * 方法功能描述: 获取还款接口返回的code
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  String getCode(HttpResponse httpResponse);


  /**
   * 方法功能描述: 获取还款接口返回的message
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  String getMessage(HttpResponse httpResponse);


}
