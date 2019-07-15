package com.woodpecker.testcase.payment.repayment.jingning.stages5213.mnw;

import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.testcase.payment.repayment.jingning.stages5213.Stages5213TestCase;
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
 * 类描述:〈玛瑙湾走京东代扣，通道57〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class MnwJdTC extends Stages5213TestCase {

  String payPlatformCode = "57";//支付通道code

  String channel = "57";//下游渠道号

  PlatformIdEnum platformIdEnum = PlatformIdEnum.MNW;

  String orderVersion = "4";//订单的version

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
   * 用例说明：提前还未来期，走大树 --> 京东 ;注意点：之所以走京东，是因为用例步骤中禁用了通联；如果不禁用通联，则京东和通联都有可能走
   */
  @Test(description = "还款方式-->催收代扣", timeOut = 180000)
  public void collectionWithhold() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId");
    //将第一期置为已还清
    super.clearFirstRepaymentSchedule(Integer.parseInt(loanOrderId));
    //此时还第二期，就是提前还未来期，走的是「京东007」42
    //对第二期进行还款
    //第二期
    RepaymentScheduleEntity repaymentSchedule = super.repaymentScheduleService
        .getRepaymentSchedule(Integer.parseInt(loanOrderId), stage);
    int scheduleId = repaymentSchedule.getId();
    //还款
    HttpResponse httpResponse = super.repayment
        .jdRepaymentByCollectionProxyJN(scheduleId, super.routerEnvVersion);
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