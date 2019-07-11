package com.woodpecker.framework.bind.impl;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.framework.bind.BindCardService;
import com.woodpecker.framework.bind.dto.BindCardDto;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈绑卡Service〉
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
   * @param bindCardDto BindCardDto
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse requestBindCard(BindCardDto bindCardDto) {
    String url = host.getPaymentTradeHost() + requestBindCardUrl;
    Map<String, String> param = bindCardDto.toRequestBindCardMap();
    logger.debug("请求绑卡url=[{}],param=[{}]", url, param);
    return httpApi.get(url, param);
  }


  /**
   * 方法功能描述: 确认绑卡
   *
   * @param bindCardDto BindCardDto
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse sureBindCard(BindCardDto bindCardDto) {
    String url = host.getPaymentTradeHost() + sureBindCardUrl;
    Map<String, String> param = bindCardDto.toSureBindCardMap();
    logger.debug("确认绑卡url=[{}],param=[{}]", url, param);
    return httpApi.get(url, param);
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
