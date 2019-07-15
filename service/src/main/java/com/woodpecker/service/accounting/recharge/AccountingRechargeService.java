package com.woodpecker.service.accounting.recharge;

import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;

/**
 * 接口描述:〈accounting-发送还款成功通知入账MQ的Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface AccountingRechargeService {


  /**
   * 方法功能描述: 通知accounting入账，模拟还款成功，跳过支付，直接通知入账
   *
   * @param accountingRechargeMqDTO AccountingRechargeMqDTO
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse repayment(AccountingRechargeMqDTO accountingRechargeMqDTO);


  /**
   * 方法功能描述: 通知accounting入账，模拟还款成功，跳过支付，直接通知入账
   *
   * @param scheduleType ScheduleTypeEnum
   * @param scheduleId 还款计划ID/趸交计划ID/loanOrderID
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse repayment(ScheduleTypeEnum scheduleType, long scheduleId);


  /**
   * 方法功能描述: 通知accounting入账，模拟还款成功，跳过支付，直接通知入账
   *
   * @param scheduleType ScheduleTypeEnum
   * @param scheduleId 还款计划ID/趸交计划ID/loanOrderID
   * @param amount 金额
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse repayment(ScheduleTypeEnum scheduleType, long scheduleId, BigDecimal amount);


}
