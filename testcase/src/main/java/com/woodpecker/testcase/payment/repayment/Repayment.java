package com.woodpecker.testcase.payment.repayment;

import com.woodpecker.framework.aop.annotation.PayHandle;
import com.woodpecker.framework.aop.annotation.RepaymentAmount;
import com.woodpecker.framework.aop.annotation.RouterVersion;
import com.woodpecker.framework.aop.annotation.ScheduleId;
import com.woodpecker.framework.bind.BindChannelEnum;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.framework.pay.PayChannelCodeEnum;
import com.woodpecker.framework.pay.PayPlatformNameEnum;
import com.woodpecker.framework.pay.RepayTypeEnum;
import com.woodpecker.framework.repayment.RepaymentService;
import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈还款〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class Repayment {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected RepaymentService repaymentService;


  /**
   * 方法功能描述: 分期还款方式：催收代扣
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle
  public HttpResponse jdRepaymentByCollectionProxy(@ScheduleId long id,
      @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 分期还款方式：催收代扣 -- 居间方：景宁
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(banCodes = {PayPlatformNameEnum.ALL_IN_JN_PAY})
  public HttpResponse jdRepaymentByCollectionProxyJN(@ScheduleId long id,
      @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 分期还款方式：催收代扣
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.ALL_IN, banCodes = {
      PayPlatformNameEnum.JDAGREEMENTPAY_4551}, bindChannels = {BindChannelEnum.ALL_IN_PAY})
  public HttpResponse allInRepaymentByCollectionProxy(@ScheduleId long id,
      @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 分期还款方式：催收代扣 -- 居间方：景宁
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.ALL_IN, banCodes = {
      PayPlatformNameEnum.JD_XY_PAY}, bindChannels = {BindChannelEnum.ALL_IN_PAY})
  public HttpResponse allInRepaymentByCollectionProxyJN(@ScheduleId long id,
      @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 分期还款方式：催收充值
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(scheduleType = ScheduleTypeEnum.LOAN_ORDER)
  public HttpResponse jdRepaymentByCollectionDeposit(@ScheduleId long id,
      @RepaymentAmount BigDecimal amount, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, amount, RepayTypeEnum.COLLECTION_DEPOSIT);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_DEPOSIT.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 分期还款方式：催收充值
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.ALL_IN, scheduleType = ScheduleTypeEnum.LOAN_ORDER, banCodes = {
      PayPlatformNameEnum.JDAGREEMENTPAY_4551}, bindChannels = {BindChannelEnum.ALL_IN_PAY})
  public HttpResponse allInRepaymentByCollectionDeposit(@ScheduleId long id,
      @RepaymentAmount BigDecimal amount, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, amount, RepayTypeEnum.COLLECTION_DEPOSIT);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_DEPOSIT.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 趸交还款
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(scheduleType = ScheduleTypeEnum.PREMIUM)
  public HttpResponse jdPremium(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService.repayment(id, null, RepayTypeEnum.PREMIUM);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.PREMIUM.getDesc(), httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 趸交还款 -- 居间方：景宁
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(scheduleType = ScheduleTypeEnum.PREMIUM, banCodes = {
      PayPlatformNameEnum.ALL_IN_JN_PAY})
  public HttpResponse jdPremiumJN(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService.repayment(id, null, RepayTypeEnum.PREMIUM);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.PREMIUM.getDesc(), httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 趸交还款
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.ALL_IN, scheduleType = ScheduleTypeEnum.PREMIUM, banCodes = {
      PayPlatformNameEnum.JDAGREEMENTPAY_4551}, bindChannels = {BindChannelEnum.ALL_IN_PAY})
  public HttpResponse allInPremium(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService.repayment(id, null, RepayTypeEnum.PREMIUM);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.PREMIUM.getDesc(), httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 趸交还款 -- 居间方：景宁
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.ALL_IN, scheduleType = ScheduleTypeEnum.PREMIUM, banCodes = {
      PayPlatformNameEnum.JD_XY_PAY}, bindChannels = {BindChannelEnum.ALL_IN_PAY})
  public HttpResponse allInPremiumJN(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService.repayment(id, null, RepayTypeEnum.PREMIUM);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.PREMIUM.getDesc(), httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 支付通道：渤海
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.BOHAI_TRUST, banCodes = {}, bindChannels = {
      BindChannelEnum.BOHAITRUST})
  public HttpResponse boHaiRepayment(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 支付通道：玛瑙湾
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.MNW, banCodes = {}, bindChannels = {
      BindChannelEnum.MNWPAY})
  public HttpResponse mnwRepayment(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 支付通道：随手记
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.SSJ, banCodes = {}, bindChannels = {
      BindChannelEnum.SSJPAY})
  public HttpResponse ssjRepayment(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 支付通道：网信
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.WX, banCodes = {}, bindChannels = {
      BindChannelEnum.JDAGREEMENTPAY})
  public HttpResponse wxRepayment(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 支付通道：西藏(西藏信托不需要绑卡，直接走裸代扣)
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.XZ_TRUST, banCodes = {}, bindChannels = {
      BindChannelEnum.JDAGREEMENTPAY})
  public HttpResponse zxRepayment(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


  /**
   * 方法功能描述: 支付通道：有利
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param version RepayTypeEnum
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @PayHandle(payChannelCode = PayChannelCodeEnum.YOOLI, banCodes = {}, bindChannels = {
      BindChannelEnum.YOOLI_PAY})
  public HttpResponse youLiRepayment(@ScheduleId long id, @RouterVersion String version) {
    HttpResponse httpResponse = repaymentService
        .repayment(id, null, RepayTypeEnum.COLLECTION_PROXY);
    logger.debug("[{}]接口返回的内容为[{}]", RepayTypeEnum.COLLECTION_PROXY.getDesc(),
        httpResponse.getContent());
    return httpResponse;
  }


}
