package com.woodpecker.service.payment.trade;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈绑卡相关Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@AutoProxy
public class BindCardServiceImpl implements BindCardService {

  @AutoHttpRequest
  private HttpApi httpApi;

  @Autowired
  private Host host;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 请求绑卡接口
   */
  private final String requestBindCardUrl = "/bindCard/requestBindCardWithPlatfrom";

  /**
   * 确认绑卡接口
   */
  private final String sureBindCardUrl = "/bindCard/newBindCard";


  /**
   * 方法功能描述: 请求绑卡
   *
   * @param platform platform
   * @param bankId bankId
   * @param idCardNo 身份证
   * @param userName 用户名
   * @param mobile 手机号
   * @param cardNo 卡号
   * @param trustProjectCode 信托计划
   * @param platformUserId 用户在资金渠道的开户id
   * @param returnUrl url
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse requestBindCard(String platform, String bankId, String idCardNo,
      String userName, String mobile, String cardNo, String trustProjectCode, String platformUserId,
      String returnUrl) {
    Map<String, String> param = new HashMap<>();
    param.put("deductPlatform", platform);
    param.put("bankId", bankId);
    param.put("idCardNo", idCardNo);
    param.put("userName", userName);
    param.put("mobile", mobile);
    param.put("cardNo", cardNo);
    param.put("trustProjectCode", trustProjectCode);
    param.put("platformUserId", platformUserId);
    param.put("returnUrl", returnUrl);
    String url = host.getPaymentTradeHost() + requestBindCardUrl;
    logger.debug("请求绑卡url=[{}],param=[{}]", url, param);
    return doRequest(url, param);
  }


  /**
   * 方法功能描述: 确认绑卡
   *
   * @param mobile 手机号
   * @param bankId bankId
   * @param userId userId
   * @param code 短信验证码
   * @param idCardNo 身份证
   * @param orderNo 请求绑卡接口返回的data
   * @param cardNo 卡号
   * @param oriOrderId 原始订单ID
   * @param trustProjectCode 信托计划
   * @param platformUserId 用户在资金渠道的开户id
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse sureBindCard(String mobile, String bankId, String userId, String code,
      String idCardNo, String orderNo, String cardNo, String oriOrderId, String trustProjectCode,
      String platformUserId) {
    Map<String, String> param = new HashMap<>();
    param.put("mobile", mobile);
    param.put("bankId", bankId);
    param.put("userId", userId);
    param.put("code", code);
    param.put("idCardNo", idCardNo);
    param.put("orderNo", orderNo);
    param.put("cardNo", cardNo);
    param.put("oriOrderId", oriOrderId);
    param.put("trustProjectCode", trustProjectCode);
    param.put("platformUserId", platformUserId);
    String url = host.getPaymentTradeHost() + sureBindCardUrl;
    logger.debug("确认绑卡url=[{}],param=[{}]", url, param);
    return doRequest(url, param);
  }


  /**
   * 方法功能描述: 发送请求/确认绑卡请求
   *
   * @param url url
   * @param data param
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  private HttpResponse doRequest(String url, Map<String, String> data) {
    return httpApi.get(url, data);
  }


  public HttpApi getHttpApi() {
    return httpApi;
  }

  public void setHttpApi(HttpApi httpApi) {
    this.httpApi = httpApi;
  }

  public Host getHost() {
    return host;
  }

  public void setHost(Host host) {
    this.host = host;
  }

  public String getRequestBindCardUrl() {
    return requestBindCardUrl;
  }

  public String getSureBindCardUrl() {
    return sureBindCardUrl;
  }
}
