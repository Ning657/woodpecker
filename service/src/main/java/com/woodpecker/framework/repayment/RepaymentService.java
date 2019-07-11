package com.woodpecker.framework.repayment;

import com.woodpecker.framework.pay.RepayTypeEnum;
import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;

/**
 * 接口描述:〈还款Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface RepaymentService {


  /**
   * 方法功能描述: 还款
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @param repayType RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse repayment(long id, BigDecimal amount, RepayTypeEnum repayType);


}
