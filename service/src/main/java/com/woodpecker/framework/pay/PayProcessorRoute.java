package com.woodpecker.framework.pay;

/**
 * 接口描述:〈支付路由，路由相应的PayProcessor实现类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface PayProcessorRoute {


  /**
   * 方法功能描述: 路由相应的PayProcessor实现类
   *
   * @param repayType RepayTypeEnum
   * @return com.woodpecker.framework.pay.PayProcessor
   */
  PayProcessor route(RepayTypeEnum repayType);


}
