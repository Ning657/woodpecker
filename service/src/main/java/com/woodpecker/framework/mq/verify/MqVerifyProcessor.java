package com.woodpecker.framework.mq.verify;

/**
 * 接口描述:〈MQ消息校验执行器〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface MqVerifyProcessor {


  /**
   * 方法功能描述: 执行MQ消息校验
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @return boolean
   */
  boolean handle(String payNo, String id);


}
