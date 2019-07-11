package com.woodpecker.service.payment.repayment.api;

import com.alibaba.fastjson.JSONObject;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈还款接口返回值HttpResponse的Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class PayHttpResponseServiceImpl implements PayHttpResponseService {


  /**
   * 方法功能描述: 将接口返回的内容转换成JSON
   *
   * @param httpResponse HttpResponse
   * @return com.alibaba.fastjson.JSONObject
   */
  private JSONObject toJSONObject(HttpResponse httpResponse) {
    String content = httpResponse.getContent();
    //将接口返回的内容转换成JSON
    JSONObject jsonObject = JsonUtil.parseObject(content);
    return jsonObject;
  }


  /**
   * 方法功能描述: 获取还款接口返回的JSON的data部分数据
   *
   * @param httpResponse HttpResponse
   * @return com.alibaba.fastjson.JSONObject
   */
  @Override
  public JSONObject getData(HttpResponse httpResponse) {
    JSONObject jsonObject = toJSONObject(httpResponse);
    return jsonObject.getJSONObject("data");
  }


  /**
   * 方法功能描述: 获取还款接口返回的tradeNo
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  @Override
  public String getTradeNo(HttpResponse httpResponse) {
    JSONObject data = getData(httpResponse);
    return data.getString("tradeNo");
  }


  /**
   * 方法功能描述: 获取还款接口返回的payNo
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  @Override
  public String getPayNo(HttpResponse httpResponse) {
    JSONObject data = getData(httpResponse);
    return data.getString("payNo");
  }


  /**
   * 方法功能描述: 获取还款接口返回的code
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  @Override
  public String getCode(HttpResponse httpResponse) {
    JSONObject jsonObject = toJSONObject(httpResponse);
    return jsonObject.getString("code");
  }


  /**
   * 方法功能描述: 获取还款接口返回的message
   *
   * @param httpResponse HttpResponse
   * @return java.lang.String
   */
  @Override
  public String getMessage(HttpResponse httpResponse) {
    JSONObject jsonObject = toJSONObject(httpResponse);
    return jsonObject.getString("message");
  }


}
