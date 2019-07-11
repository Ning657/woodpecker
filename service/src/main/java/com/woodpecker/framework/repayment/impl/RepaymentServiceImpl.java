package com.woodpecker.framework.repayment.impl;

import com.woodpecker.framework.pay.PayProcessor;
import com.woodpecker.framework.pay.PayProcessorRoute;
import com.woodpecker.framework.pay.RepayTypeEnum;
import com.woodpecker.framework.repayment.RepaymentService;
import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈还款Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class RepaymentServiceImpl implements RepaymentService {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected PayProcessorRoute payProcessorRoute;


  /**
   * 方法功能描述: 还款
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @param repayType RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse repayment(long id, BigDecimal amount, RepayTypeEnum repayType) {
    //路由相应的PayProcessor实现类
    PayProcessor payProcessor = payProcessorRoute.route(repayType);
    if (null == payProcessor) {
      return null;
    }
    //还款
    return payProcessor.handle(id, amount);
  }


}
