package com.woodpecker.service.pub.order;

import java.math.BigDecimal;

/**
 * 接口描述:〈订单Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface OrderService {


  /**
   * 方法功能描述: 将订单t_loan_order表置为已结清状态
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  void clearLoanOrder(int loanOrderId);


  /**
   * 方法功能描述: 将趸交计划qs_single_premium_schedule表置为已还清
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  void clearSinglePremiumSchedule(int loanOrderId);


  /**
   * 方法功能描述: 将趸交计划qs_single_premium_schedule表置为未还
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  void setSinglePremiumScheduleNeedRepayment(int loanOrderId);


  /**
   * 方法功能描述: 将还款计划的某一期置为已还清
   *
   * @param loanOrderId loanOrderId
   * @param stage 第几期
   * @return void
   */
  void clearRepaymentSchedule(int loanOrderId, byte stage);


  /**
   * 方法功能描述: 将订单置为已结清
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  void clearLoanOrderSchedule(int loanOrderId);


  /**
   * 方法功能描述: 获取订单总金额(趸交金额+每期还款计划的应还金额)
   *
   * @param loanOrderId loanOrderId
   * @return java.math.BigDecimal
   */
  BigDecimal getRepaymentSumAmount(int loanOrderId);


  /**
   * 方法功能描述: 获取t_loan_order所对应的银行卡卡号
   *
   * @param loanOrderId loanOrderId
   * @return java.lang.String
   */
  String getCardNoByLoanOrderId(int loanOrderId);


  /**
   * 方法功能描述: 获取t_loan_order所对应的银行卡的bankId
   *
   * @param loanOrderId loanOrderId
   * @return int
   */
  int getBankIdByLoanOrderId(int loanOrderId);


}
