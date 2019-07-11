package com.woodpecker.service.payment.repayment.check;

import com.sword.autotest.framework.assertion.Validate;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import com.woodpecker.entity.loandb.TradeOrderEntity;
import com.woodpecker.entity.payment.TransactionEntity;
import com.woodpecker.framework.mq.verify.MqCheckService;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.service.payment.repayment.api.PayHttpResponseService;
import com.xujinjian.HttpClient.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;

/**
 * 类描述:〈还款后校验点〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class RepaymentCheckPointServiceImpl implements RepaymentCheckPointService {

  @Autowired
  private MqCheckService mqCheckService;

  @Autowired
  private PayHttpResponseService payHttpResponseService;


  /**
   * 方法功能描述: 校验t_tp_trade_order表数据
   *
   * @param tradeOrderEntity TradeOrderEntity
   * @param payWay PayWay
   * @param payPlatform PayPlatform
   * @param channel Channel
   * @return void
   */
  @Override
  public void checkTradeOrderTable(TradeOrderEntity tradeOrderEntity, byte payWay, byte payPlatform,
      String channel) {
    Assert.assertNotNull(tradeOrderEntity, "校验t_tp_trade_order表存在此记录");
    //校验PayWay字段
    Validate.isEquals(payWay, tradeOrderEntity.getPayWay(), "校验t_tp_trade_order表PayWay字段");
    //校验PayPlatform字段
    Validate.isEquals(payPlatform, tradeOrderEntity.getPayPlatform(),
        "校验t_tp_trade_order表PayPlatform字段");
    //校验TranStatus字段
    Validate.isEquals(2, tradeOrderEntity.getTranStatus(), "校验t_tp_trade_order表TranStatus字段");
    //校验Channel字段
    Validate.isEquals(channel, tradeOrderEntity.getChannel(), "校验t_tp_trade_order表Channel字段");
    //校验IsDeprecated字段
    Validate
        .isEquals(0, tradeOrderEntity.getIsDeprecated(), "校验t_tp_trade_order表IsDeprecated字段");
  }


  /**
   * 方法功能描述: 校验t_tp_transaction表数据
   *
   * @param transactionEntity TransactionEntity
   * @param platId platId
   * @return void
   */
  @Override
  public void checkTransactionTable(TransactionEntity transactionEntity, byte platId) {
    Assert.assertNotNull(transactionEntity, "校验t_tp_transaction表存在此记录");
    //校验platId字段
    Validate.isEquals(platId, transactionEntity.getPlatId(), "校验t_tp_transaction表platId字段");
    //校验tranStatus字段
    Validate.isEquals("2", transactionEntity.getTranStatus(), "校验t_tp_transaction表tranStatus字段");
  }


  /**
   * 方法功能描述: 校验t_repayment_schedule表数据
   *
   * @param repaymentScheduleEntity RepaymentScheduleEntity
   * @return void
   */
  @Override
  public void checkRepaymentScheduleTable(RepaymentScheduleEntity repaymentScheduleEntity) {
    Assert.assertNotNull(repaymentScheduleEntity, "校验t_repayment_schedule表存在此记录");
    //校验IsClear字段
    Validate.isEquals(1, repaymentScheduleEntity.getIsClear(), "校验t_repayment_schedule表IsClear字段");
    //校验Status字段
    Validate.isEquals(11, repaymentScheduleEntity.getStatus(), "校验t_repayment_schedule表Status字段");
    //校验IsDeprecated字段
    Validate.isEquals(0, repaymentScheduleEntity.getIsDeprecated(),
        "校验t_repayment_schedule表IsDeprecated字段");
  }


  /**
   * 方法功能描述: 校验qs_single_premium_schedule表数据
   *
   * @param singlePremiumScheduleEntity SinglePremiumScheduleEntity
   * @return void
   */
  @Override
  public void checkSinglePremiumScheduleTable(
      SinglePremiumScheduleEntity singlePremiumScheduleEntity) {
    Assert.assertNotNull(singlePremiumScheduleEntity, "校验qs_single_premium_schedule表存在此记录");
    //校验cleared字段
    Validate.isEquals(1, singlePremiumScheduleEntity.getCleared(),
        "校验qs_single_premium_schedule表cleared字段");
    //校验status字段
    Validate.isEquals(11, singlePremiumScheduleEntity.getStatus(),
        "校验qs_single_premium_schedule表status字段");
  }


  /**
   * 方法功能描述: 校验MQ是否发送成功
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @param scheduleType ScheduleTypeEnum
   * @return void
   */
  @Override
  public void checkMqSendSuccess(String payNo, String id, ScheduleTypeEnum scheduleType) {
    Validate.isTrue(mqCheckService.verify(payNo, id, scheduleType), "校验MQ发送成功");
  }


  /**
   * 方法功能描述: 校验具体支付请求的HttpResponse
   *
   * @param httpResponse HttpResponse
   * @return void
   */
  @Override
  public void checkHttpResponse(HttpResponse httpResponse) {
    String code = payHttpResponseService.getCode(httpResponse);
    Assert.assertEquals(code, "0000", "校验还款接口返回的code");
  }


}
