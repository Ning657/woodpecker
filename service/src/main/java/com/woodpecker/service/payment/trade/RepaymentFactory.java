package com.woodpecker.service.payment.trade;

import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈还款接口工厂〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
public class RepaymentFactory {

  @Autowired
  private RepaymentService repaymentService;


  /**
   * 方法功能描述: 主动还款
   *
   * @param userId userId
   * @param scheduleId 还款计划ID
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  public HttpResponse activeRepayment(Integer userId, Long scheduleId) {
    return repaymentService.financierPay(userId, scheduleId, 1, "CUSTOMER", null);
  }


  /**
   * 方法功能描述: 催收代扣
   *
   * @param userId userId
   * @param scheduleId 还款计划ID
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  public HttpResponse collectionWithhold(Integer userId, Long scheduleId) {
    return repaymentService.financierPay(userId, scheduleId, 1, "COLLECTION_PROXY", null);
  }


  /**
   * 方法功能描述: 催收充值
   *
   * @param loanOrderId loanOrderId
   * @param accountNo 卡号
   * @param refId 时间戳，只要不重复即可
   * @param amount 金额
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  public HttpResponse collectionRecharge(Long loanOrderId, Long accountNo, Long refId,
      BigDecimal amount) {
    return repaymentService
        .chargeRepay(loanOrderId, accountNo, refId, "BORROWER_CHARGE", "COLLECTION_DEPOSIT", amount,
            1, "tyurty", null);
  }


  /**
   * 方法功能描述: 趸交
   *
   * @param userId userId
   * @param premiumScheduleId 趸交计划ID
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  public HttpResponse premium(Integer userId, Long premiumScheduleId) {
    return repaymentService.premiumPay(userId, "COLLECTION_PROXY", premiumScheduleId);
  }


  /**
   * 方法功能描述: 定时扣款
   *
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  public HttpResponse batchDeduct() {
    return repaymentService.batchDeduct(1, "GARONA");
  }


}
