package com.woodpecker.service.payment.repayment.check;

import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import com.woodpecker.entity.loandb.TradeOrderEntity;
import com.woodpecker.entity.payment.TransactionEntity;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.xujinjian.HttpClient.HttpResponse;

/**
 * 接口描述:〈还款后校验点〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface RepaymentCheckPointService {


  /**
   * 方法功能描述: 校验t_tp_trade_order表数据
   *
   * @param tradeOrderEntity TradeOrderEntity
   * @param payWay PayWay
   * @param payPlatform PayPlatform
   * @param channel Channel
   * @return void
   */
  void checkTradeOrderTable(TradeOrderEntity tradeOrderEntity, byte payWay, byte payPlatform,
      String channel);


  /**
   * 方法功能描述: 校验t_tp_transaction表数据
   *
   * @param transactionEntity TransactionEntity
   * @param platId platId
   * @return void
   */
  void checkTransactionTable(TransactionEntity transactionEntity, byte platId);


  /**
   * 方法功能描述: 校验t_repayment_schedule表数据
   *
   * @param repaymentScheduleEntity RepaymentScheduleEntity
   * @return void
   */
  void checkRepaymentScheduleTable(RepaymentScheduleEntity repaymentScheduleEntity);


  /**
   * 方法功能描述: 校验qs_single_premium_schedule表数据
   *
   * @param singlePremiumScheduleEntity SinglePremiumScheduleEntity
   * @return void
   */
  void checkSinglePremiumScheduleTable(SinglePremiumScheduleEntity singlePremiumScheduleEntity);


  /**
   * 方法功能描述: 校验MQ是否发送成功
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @param scheduleType ScheduleTypeEnum
   * @return void
   */
  void checkMqSendSuccess(String payNo, String id, ScheduleTypeEnum scheduleType);


  /**
   * 方法功能描述: 校验具体支付请求的HttpResponse
   *
   * @param httpResponse HttpResponse
   * @return void
   */
  void checkHttpResponse(HttpResponse httpResponse);


}
