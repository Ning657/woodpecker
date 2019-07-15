package com.woodpecker.service.accounting.recharge;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈accounting-发送还款成功通知入账MQ的Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@AutoProxy
public class AccountingRechargeServiceImpl implements AccountingRechargeService {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @AutoHttpRequest
  private HttpApi httpApi;

  @Autowired
  private Host host;

  private String url = "/mq/easy/send";


  /**
   * 方法功能描述: 通知accounting入账，模拟还款成功，跳过支付，直接通知入账
   *
   * @param accountingRechargeMqDTO AccountingRechargeMqDTO
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse repayment(AccountingRechargeMqDTO accountingRechargeMqDTO) {
    //将POST请求参数转换成JSON字符串
    String data = JsonUtil.toJSONString(accountingRechargeMqDTO);
    logger.debug("通知accounting入账MQ的请求参数为：[{}]", data);
    //组装请求url
    String url = host.getAccountingHost() + this.url;
    logger.debug("发送通知accounting入账MQ的请求url=[{}]", url);
    //
    return httpApi.post(url, data);
  }

  /**
   * 方法功能描述: 通知accounting入账，模拟还款成功，跳过支付，直接通知入账
   *
   * @param scheduleType ScheduleTypeEnum
   * @param scheduleId 还款计划ID/趸交计划ID/loanOrderID
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse repayment(ScheduleTypeEnum scheduleType, long scheduleId) {
    return null;
  }

  /**
   * 方法功能描述: 通知accounting入账，模拟还款成功，跳过支付，直接通知入账
   *
   * @param scheduleType ScheduleTypeEnum
   * @param scheduleId 还款计划ID/趸交计划ID/loanOrderID
   * @param amount 金额
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse repayment(ScheduleTypeEnum scheduleType, long scheduleId, BigDecimal amount) {
    return null;
  }


  public String getUrl() {
    return url;
  }


}
