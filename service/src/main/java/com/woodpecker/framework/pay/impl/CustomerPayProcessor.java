package com.woodpecker.framework.pay.impl;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.framework.pay.AbstractPayProcessor;
import com.woodpecker.framework.pay.PayService;
import com.woodpecker.framework.pay.RepayTypeEnum;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈还款方式：用户发起〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@AutoProxy
@PayService(repayType = RepayTypeEnum.CUSTOMER, desc = "用户发起")
public class CustomerPayProcessor extends AbstractPayProcessor {

  @AutoHttpRequest
  protected HttpApi httpApi;

  @Autowired
  protected Host host;

  @Autowired
  protected RepaymentScheduleDao repaymentScheduleDao;

  /**
   * 接口url
   */
  protected final String url = "/repaynew/financierpay";


  /**
   * 方法功能描述: 支付
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  protected HttpResponse doPay(long id, BigDecimal amount) {
    //根据还款计划ID，查询userId
    RepaymentScheduleEntity repaymentScheduleEntity = repaymentScheduleDao.findById((int) id).get();

    logger.debug("开始还款->{}", repaymentScheduleEntity.toString());
    //组装还款接口参数
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", repaymentScheduleEntity.getUserId().toString());
    data.put("scheduleId", String.valueOf(id));
    data.put("appId", super.appId);
    data.put("source", RepayTypeEnum.CUSTOMER.name());
    data.put("returnUrl", null);
    //组装url
    String url = host.getPaymentTradeHost() + this.url;
    logger.debug("还款方式：用户发起;[{}]接口参数为[{}]", url, data);
    //发送还款请求
    return httpApi.get(url, data);
  }


}
