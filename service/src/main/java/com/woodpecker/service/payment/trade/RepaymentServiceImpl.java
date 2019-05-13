package com.woodpecker.service.payment.trade;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈还款接口〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
@AutoProxy
public class RepaymentServiceImpl implements RepaymentService {

  @AutoHttpRequest
  private HttpApi httpApi;

  @Autowired
  private Host host;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 主动还款/催收代扣
   */
  private final String financierPayUrl = "/repaynew/financierpay";

  /**
   * 催收充值
   */
  private final String chargeRepayUrl = "/repaynew/chargerepay";

  /**
   * 定时扣款
   */
  private final String batchDeductUrl = "/batch/batchDeduct";

  /**
   * 趸交
   */
  private final String premiumPayUrl = "/premium/pay/new";


  /**
   * 方法功能描述: 催收充值
   *
   * @param loanOrderId loanOrderId
   * @param accountNo accountNo
   * @param refId refId
   * @param refType refType
   * @param source source
   * @param amount amount
   * @param auditUid auditUid
   * @param memo memo
   * @param returnUrl returnUrl
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse chargeRepay(Long loanOrderId, Long accountNo, Long refId, String refType,
      String source, BigDecimal amount, Integer auditUid, String memo, String returnUrl) {
    HashMap<String, String> data = new HashMap<>();
    data.put("loanOrderId", loanOrderId.toString());
    data.put("accountNo", accountNo.toString());
    data.put("refId", refId.toString());
    data.put("refType", refType);
    data.put("source", source);
    data.put("amount", amount.stripTrailingZeros().toPlainString());
    data.put("auditUid", auditUid.toString());
    data.put("memo", memo);
    data.put("returnUrl", returnUrl);
    String url = host.getPaymentTradeHost() + chargeRepayUrl;
    logger.debug("催收充值[{}]参数为[{}]", url, data);
    return httpApi.get(url, data);
  }


  /**
   * 方法功能描述: 定时扣款
   *
   * @param business business
   * @param source source
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse batchDeduct(Integer business, String source) {
    HashMap<String, String> data = new HashMap<>();
    data.put("business", business.toString());
    data.put("source", source);
    String url = host.getPaymentTradeHost() + batchDeductUrl;
    logger.debug("定时扣款[{}]参数为[{}]", url, data);
    return httpApi.get(url, data);
  }


  /**
   * 方法功能描述: 主动还款/催收代扣
   *
   * @param userId userId
   * @param scheduleId scheduleId
   * @param appId appId
   * @param source source
   * @param returnUrl returnUrl
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse financierPay(Integer userId, Long scheduleId, Integer appId, String source,
      String returnUrl) {
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", userId.toString());
    data.put("scheduleId", scheduleId.toString());
    data.put("appId", appId.toString());
    data.put("source", source);
    data.put("returnUrl", returnUrl);
    String url = host.getPaymentTradeHost() + financierPayUrl;
    logger.debug("主动还款/催收代扣[{}]参数为[{}]", url, data);
    return httpApi.get(url, data);
  }


  /**
   * 方法功能描述: 趸交
   *
   * @param userId userId
   * @param source source
   * @param premiumScheduleId premiumScheduleId
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse premiumPay(Integer userId, String source, Long premiumScheduleId) {
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", userId.toString());
    data.put("source", source);
    data.put("premiumScheduleId", premiumScheduleId.toString());
    String url = host.getPaymentTradeHost() + premiumPayUrl;
    logger.debug("趸交[{}]参数为[{}]", url, data);
    return httpApi.get(url, data);
  }


  /**
   * 方法功能描述: 对公代付
   *
   * @param amount amount
   * @param memo memo
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse paidCashChannelPay(BigDecimal amount, String memo) {
    return null;
  }


  /**
   * 方法功能描述: 代偿充值
   *
   * @param channel channel
   * @param amount amount
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse paidCashChannelRecharge(String channel, BigDecimal amount) {
    return null;
  }


  public String getFinancierPayUrl() {
    return financierPayUrl;
  }

  public String getChargeRepayUrl() {
    return chargeRepayUrl;
  }

  public String getBatchDeductUrl() {
    return batchDeductUrl;
  }

  public String getPremiumPayUrl() {
    return premiumPayUrl;
  }
}
