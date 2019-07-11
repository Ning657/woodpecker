package com.woodpecker.testcase.payment.repayment;

import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.service.payment.pay.platform.PayPlatformService;
import com.woodpecker.service.payment.repayment.api.PayHttpResponseService;
import com.woodpecker.service.payment.repayment.check.RepaymentCheckPointService;
import com.woodpecker.service.payment.repayment.check.VerifyRepaymentData;
import com.woodpecker.service.payment.repayment.schedule.RepaymentScheduleService;
import com.woodpecker.testcase.payment.PaymentTestCase;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * 类描述:〈还款TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class RepaymentTestCase extends PaymentTestCase {

  @Autowired
  protected PayPlatformService payPlatformService;

  @Autowired
  protected RepaymentScheduleService repaymentScheduleService;

  @Autowired
  protected VerifyRepaymentData verifyRepaymentData;

  @Autowired
  protected PayHttpResponseService payHttpResponseService;

  @Autowired
  protected Repayment repayment;

  @Autowired
  protected RepaymentCheckPointService repaymentCheckPointService;

  /**
   * 等待入账时间，3秒
   */
  protected int recordedTime = 3;


  /**
   * 方法功能描述: 删除用户名下的订单
   *
   * @param platformIdEnum 资金渠道
   * @return void
   */
  public void deleteUserOrders(PlatformIdEnum platformIdEnum) {
    //删除用户名下的订单
    boolean deleteResult = dataBuildOrderService.delete(userId, platformIdEnum);
    Assert.assertTrue(deleteResult, "删除用户" + userId + "名下的订单");
  }


  /**
   * 方法功能描述: 删除指定订单
   *
   * @param orderIds 原始订单Id(多个用英文,间隔)
   * @return void
   */
  public void deleteOrders(String orderIds) {
    //删除指定订单
    boolean deleteResult = dataBuildOrderService.delete(orderIds);
    Assert.assertTrue(deleteResult, "删除订单" + orderIds);
  }


  /**
   * 方法功能描述: 生成还款订单
   *
   * @param platformIdEnum 资金渠道
   * @param version version
   * @return java.util.Map
   */
  public Map<String, String> createOrder(PlatformIdEnum platformIdEnum, String version) {
    //生成还款订单
    Map<String, String> map = dataBuildOrderService.create(userId, platformIdEnum, version);
    Assert.assertEquals(map.size(), 2, "生成用户" + userId + "的还款订单");
    return map;
  }


}
