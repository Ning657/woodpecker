package com.woodpecker.framework.pay.impl;

import com.woodpecker.framework.pay.PayProcessor;
import com.woodpecker.framework.pay.PayProcessorRoute;
import com.woodpecker.framework.pay.PayService;
import com.woodpecker.framework.pay.RepayTypeEnum;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈支付路由，路由相应的PayProcessor实现类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class PayProcessorRouteImpl implements PayProcessorRoute {

  Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * PayProcessor实现类集合
   */
  @Autowired
  protected List<PayProcessor> payProcessors;


  /**
   * 方法功能描述: 路由相应的PayProcessor实现类
   *
   * @param repayType RepayTypeEnum
   * @return com.woodpecker.framework.pay.PayProcessor
   */
  @Override
  public PayProcessor route(RepayTypeEnum repayType) {
    log.debug("开始路由RepayTypeEnum=[{}({})]的支付服务", repayType.name(), repayType.getDesc());
    PayProcessor payProcessorImpl = null;
    if (null != payProcessors) {
      log.debug("共注册了[{}]个PayProcessor支付服务", payProcessors.size());
      //
      for (PayProcessor payProcessor : payProcessors) {
        PayService payService = payProcessor.getClass().getAnnotation(PayService.class);
        if (null != payService) {
          //当前类有PayService注解
          //获取出还款方式枚举
          RepayTypeEnum type = payService.repayType();
          //判断还款方式枚举是否相同
          if (repayType.equals(type)) {
            //此PayProcessor实现类，是相应的还款方式实现类
            //路由到相应的还款方式实现类
            payProcessorImpl = payProcessor;
            break;
          }
        }
      }
    }
    if (null != payProcessorImpl) {
      log.debug("支付路由结果：[{}]", payProcessorImpl.getClass().getName());
    } else {
      log.debug("支付路由结果：[null]");
    }
    return payProcessorImpl;
  }


}
