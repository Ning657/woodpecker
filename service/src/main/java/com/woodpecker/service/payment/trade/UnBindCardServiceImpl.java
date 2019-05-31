package com.woodpecker.service.payment.trade;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈解绑卡相关Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@AutoProxy
public class UnBindCardServiceImpl implements UnBindCardService {

  @AutoHttpRequest
  private HttpApi httpApi;

  @Autowired
  private Host host;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 解绑卡接口
   */
  private final String unBindCardUrl = "/bindCard/unBindCard";


  /**
   * 方法功能描述: 解绑卡
   *
   * @param userId userId
   * @param accountNo 银行卡卡号
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse unBindCard(String userId, String accountNo) {
    String url = host.getPaymentTradeHost() + unBindCardUrl;
    HashMap<String, String> param = new HashMap<>();
    param.put("userId", userId);
    param.put("accountNo", accountNo);
    logger.debug("解绑卡请求参数param=[{}]", param);
    //发送解绑卡请求
    return httpApi.get(url, param);
  }


  public String getUnBindCardUrl() {
    return unBindCardUrl;
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

}
