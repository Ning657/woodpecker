package com.woodpecker.service.payment.repayment.schedule;

import com.woodpecker.entity.loandb.RepaymentScheduleEntity;

/**
 * 接口描述:〈还款计划Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface RepaymentScheduleService {


  /**
   * 方法功能描述: 获取还款计划
   *
   * @param loanOrderId loanOrderId
   * @param stage 期数
   * @return com.woodpecker.entity.loandb.RepaymentScheduleEntity
   */
  RepaymentScheduleEntity getRepaymentSchedule(int loanOrderId, byte stage);


}
