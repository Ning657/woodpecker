package com.woodpecker.framework.pay.impl;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
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
 * 类描述:〈还款方式：趸交〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@AutoProxy
@PayService(repayType = RepayTypeEnum.PREMIUM, desc = "趸交")
public class PremiumProxyPayProcessor extends AbstractPayProcessor {

  @AutoHttpRequest
  protected HttpApi httpApi;

  @Autowired
  protected Host host;

  @Autowired
  protected SinglePremiumScheduleDao singlePremiumScheduleDao;

  /**
   * 接口url
   */
  protected final String url = "/premium/pay/new";


  /**
   * 方法功能描述: 支付
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  protected HttpResponse doPay(long id, BigDecimal amount) {
    //获取趸交计划
    SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findById((int) id).get();

    logger.debug("开始还款->{}", singlePremiumScheduleEntity.toString());
    //组装还款接口参数
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", singlePremiumScheduleEntity.getUserId().toString());
    //趸交接口的「source」也是「COLLECTION_PROXY」，跟「催收代扣」的「source」是一样的
    data.put("source", RepayTypeEnum.COLLECTION_PROXY.name());
    data.put("premiumScheduleId", String.valueOf(id));
    //组装url
    String url = host.getPaymentTradeHost() + this.url;
    logger.debug("还款方式：趸交;[{}]接口参数为[{}]", url, data);
    //发送还款请求
    return httpApi.get(url, data);
  }


}
