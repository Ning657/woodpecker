package com.woodpecker.framework.pay;

import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;

/**
 * 接口描述:〈支付操作执行器〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface PayProcessor {


  /**
   * 方法功能描述: 执行支付动作，发送支付请求至trade
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse handle(long id, BigDecimal amount);


}
