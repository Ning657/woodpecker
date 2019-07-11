package com.woodpecker.testcase.payment.repayment.treefinance.capital.xizang;

import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.testcase.payment.repayment.treefinance.capital.CapitalRepaymentTestCase;
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
 * 类描述:〈西藏信托走XZ_TRUST，通道59〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class XiZangCapitalTC extends CapitalRepaymentTestCase {

  String payGroupCode = "32";//支付组code

  String payPlatformCode = "59";//支付通道code

  String channel = "59";//下游渠道号

  PlatformIdEnum platformIdEnum = PlatformIdEnum.ZX;

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
    //mock西藏信托
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
   * 用例说明：当期还款，走体内 --> 西藏信托
   */
  @Test(description = "支付通道：西藏信托", timeOut = 180000)
  public void xzRepayment() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId");
    //对第一期进行还款
    //第一期
    RepaymentScheduleEntity repaymentSchedule = super.repaymentScheduleService
        .getRepaymentSchedule(Integer.parseInt(loanOrderId), stage);
    int scheduleId = repaymentSchedule.getId();
    //还款
    HttpResponse httpResponse = super.repayment.zxRepayment(scheduleId, super.routerEnvVersion);
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
        .checkMqSendSuccess(payNo, String.valueOf(scheduleId), ScheduleTypeEnum.REPAYMENT);
    super.verifyRepaymentData
        .verify(tradeNo, payNo, Byte.parseByte(payGroupCode), Byte.parseByte(payPlatformCode),
            channel, ScheduleTypeEnum.REPAYMENT, scheduleId);
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
