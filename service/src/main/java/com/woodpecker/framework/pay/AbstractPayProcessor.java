package com.woodpecker.framework.pay;

import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类描述:〈支付操作执行器，所有具体的支付实现类都需要继承此类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public abstract class AbstractPayProcessor implements PayProcessor {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * appId：1
   */
  protected String appId = "1";


  /**
   * 方法功能描述: 执行支付动作，发送支付请求至trade
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  public HttpResponse handle(long id, BigDecimal amount) {
    if (id <= 0) {
      logger.error("执行支付请求时，参数id=[{}]有误！id为还款计划id/趸交计划id/loanOrderId", id);
      return null;
    }
    //执行具体的支付请求，抛出钩子供真正的支付服务调用
    return doPay(id, amount);
  }


  /**
   * 方法功能描述: 支付
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  protected abstract HttpResponse doPay(long id, BigDecimal amount);


}
