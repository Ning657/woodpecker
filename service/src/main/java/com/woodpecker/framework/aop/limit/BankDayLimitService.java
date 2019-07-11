package com.woodpecker.framework.aop.limit;

import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import java.math.BigDecimal;

/**
 * 接口描述:〈银行日限额Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface BankDayLimitService {


  /**
   * 方法功能描述: 获取BankId
   *
   * @param scheduleType ScheduleTypeEnum
   * @param id 还款计划ID/趸交计划ID
   * @return int
   */
  int getBankId(ScheduleTypeEnum scheduleType, int id);


  /**
   * 方法功能描述: 获取LoanOrderId
   *
   * @param scheduleType ScheduleTypeEnum
   * @param id 还款计划ID/趸交计划ID/LoanOrderId
   * @return int
   */
  int getLoanOrderId(ScheduleTypeEnum scheduleType, int id);


  /**
   * 方法功能描述: 获取还款计划/趸交计划的金额
   *
   * @param scheduleType ScheduleTypeEnum
   * @param id 还款计划ID/趸交计划ID
   * @return java.math.BigDecimal
   */
  BigDecimal getAmount(ScheduleTypeEnum scheduleType, int id);


  /**
   * 方法功能描述: 获取银行日限额
   *
   * @param bankId bankId
   * @param payChannelCode rt_pay_channel_bank表的pay_channel_code字段值
   * @param version version
   * @return int
   */
  int getDayAmountLimit(String bankId, String payChannelCode, String version);


  /**
   * 方法功能描述: 设置银行日限额
   *
   * @param bankId bankId
   * @param payChannelCode rt_pay_channel_bank表的pay_channel_code字段值
   * @param amount 限额金额
   * @param version version
   * @return void
   */
  void setDayAmountLimit(String bankId, String payChannelCode, int amount, String version);


}
