package com.woodpecker.service.payment.trade;

import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;

/**
 * 接口描述:〈还款接口〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface RepaymentService {


  /**
   * 方法功能描述: 催收充值
   *
   * @param loanOrderId loanOrderId
   * @param accountNo accountNo
   * @param refId refId
   * @param refType refType
   * @param source source
   * @param amount amount
   * @param auditUid auditUid
   * @param memo memo
   * @param returnUrl returnUrl
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse chargeRepay(Long loanOrderId, Long accountNo, Long refId, String refType,
      String source, BigDecimal amount, Integer auditUid, String memo, String returnUrl);


  /**
   * 方法功能描述: 定时扣款
   *
   * @param business business
   * @param source source
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse batchDeduct(Integer business, String source);


  /**
   * 方法功能描述: 主动还款/催收代扣
   *
   * @param userId userId
   * @param scheduleId scheduleId
   * @param appId appId
   * @param source source
   * @param returnUrl returnUrl
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse financierPay(Integer userId, Long scheduleId, Integer appId, String source,
      String returnUrl);


  /**
   * 方法功能描述: 趸交
   *
   * @param userId userId
   * @param source source
   * @param premiumScheduleId premiumScheduleId
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse premiumPay(Integer userId, String source, Long premiumScheduleId);


  /**
   * 方法功能描述: 对公代付
   *
   * @param amount amount
   * @param memo memo
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse paidCashChannelPay(BigDecimal amount, String memo);


  /**
   * 方法功能描述: 代偿充值
   *
   * @param channel channel
   * @param amount amount
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse paidCashChannelRecharge(String channel, BigDecimal amount);


}
