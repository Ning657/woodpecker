package com.woodpecker.testcase.payment.repayment.treefinance.premium4551.youli;

import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.testcase.payment.repayment.treefinance.premium4551.Premium4551TestCase;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.Commons.Lang.ThreadUtil;
import com.xujinjian.HttpClient.HttpResponse;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 类描述:〈有利趸交还款用例，走京东 42〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class YouLiPremiumJd007TC extends Premium4551TestCase {

  String payPlatformCode = "42";//支付通道code

  String channel = "102";//下游渠道号

  PlatformIdEnum platformIdEnum = PlatformIdEnum.YLW;

  String orderVersion = "1";//订单的version

  String orderId;

  String loanOrderId;


  @BeforeClass
  public void ready() {
    //删除用户的支付渠道，防止别人新增过别的支付渠道，导致再新增一个另外的支付渠道，就一个userid同时配置了2个支付渠道了
    super.superdiamond.deleteUserPayChannel(super.userId);
    //获取router.env.version
    if (StringUtil.isEmpty(super.routerEnvVersion)) {
      super.routerEnvVersion = super.superdiamond.getRouterEnvVersion();
    }
    //
    PayPlatformEntity payPlatformEntity = payPlatformService
        .getPayPlatform(payPlatformCode, super.routerEnvVersion);
    //mock京东(007-4551)
    super.superdiamond.mockChannel(payPlatformEntity.getName());
  }


  @BeforeMethod
  public void createOrder() {
    //删除用户名下所有订单，避免对本次测试有所影响，可以不删，因为了指定还款计划去还款
    super.deleteUserOrders(PlatformIdEnum.ALL);
    //生成还款订单
    Map<String, String> map = super.createOrder(platformIdEnum, orderVersion);
    orderId = map.get("orderId");
    loanOrderId = map.get("loanorderId");
  }


  /**
   * 用例说明：趸交还款，走大树 --> 京东 ;注意点：之所以走京东，是因为用例步骤中禁用了通联；如果不禁用通联，则京东和通联都有可能走
   */
  @Test(description = "趸交", timeOut = 180000)
  public void premium() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId");
    //读取趸交计划
    SinglePremiumScheduleEntity singlePremiumSchedule = super.singlePremiumScheduleService
        .getSinglePremiumSchedule(Integer.parseInt(loanOrderId));
    int scheduleId = singlePremiumSchedule.getId();
    //还款
    HttpResponse httpResponse = super.repayment.jdPremium(scheduleId, super.routerEnvVersion);
    //校验是否请求成功
    super.verifyRepaymentData.verify(httpResponse);
    //
    String tradeNo = super.payHttpResponseService.getTradeNo(httpResponse);
    String payNo = super.payHttpResponseService.getPayNo(httpResponse);
    //等待X秒，给后台足够的入账时间
    log.debug("暂停[{}]秒，给后台足够的入账时间", super.recordedTime);
    ThreadUtil.sleep(super.recordedTime);
    //还款后校验
    super.repaymentCheckPointService
        .checkMqSendSuccess(payNo, String.valueOf(scheduleId), ScheduleTypeEnum.PREMIUM);
    super.verifyRepaymentData
        .verify(tradeNo, payNo, Byte.parseByte(payGroupCode), Byte.parseByte(payPlatformCode),
            channel, ScheduleTypeEnum.PREMIUM, scheduleId);
  }


  @AfterMethod
  public void deleteOrder() {
    //删除订单
    super.deleteOrders(String.valueOf(orderId));
  }


  @AfterClass
  public void restoreUserPayChannelAndDelCache() {

  }

}