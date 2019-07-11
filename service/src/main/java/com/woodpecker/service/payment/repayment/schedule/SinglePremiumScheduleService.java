package com.woodpecker.service.payment.repayment.schedule;

import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;

/**
 * 接口描述:〈趸交计划Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface SinglePremiumScheduleService {


  /**
   * 方法功能描述: 获取趸交计划
   *
   * @param loanOrderId loanOrderId
   * @return com.woodpecker.entity.loandb.SinglePremiumScheduleEntity
   */
  SinglePremiumScheduleEntity getSinglePremiumSchedule(int loanOrderId);


}
