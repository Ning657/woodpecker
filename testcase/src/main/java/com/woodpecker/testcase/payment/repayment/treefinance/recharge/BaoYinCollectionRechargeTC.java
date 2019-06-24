package com.woodpecker.testcase.payment.repayment.treefinance.recharge;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.testcase.payment.repayment.RepaymentTestCase;
import com.xujinjian.Commons.Lang.ThreadUtil;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.math.BigDecimal;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 类描述:〈包银渠道催收充值-TestCase〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class BaoYinCollectionRechargeTC extends RepaymentTestCase {

  protected String payChannel2 = "JDAGREEMENTPAY_4551";

  /**
   * 资金渠道，指定为包银渠道，不可改
   */
  private final PlatformIdEnum platformIdEnum = PlatformIdEnum.BY;

  protected String version = "1";

  private String orderId;

  private String loanOrderId;


  @BeforeClass
  public void ready() {
    //删除用户的支付渠道，防止别人新增过别的支付渠道，导致再新增一个另外的支付渠道，就一个userid同时配置了2个支付渠道了
    super.deleteUserPayChannelConfig();
    //mock京东(007-4551)
    super.mockChannel(payChannel2);
    //删除Redis缓存
    super.cleanRedis();
  }


  @BeforeMethod
  public void createOrder() {
    //删除用户名下所有订单，避免对本次测试有所影响，可以不删，因为了指定还款计划去还款
    //super.deleteUserOrders(PlatformIdEnum.ALL);
    //生成还款订单
    Map<String, String> map = super.createOrder(platformIdEnum, version);
    orderId = map.get("orderId");
    loanOrderId = map.get("loanorderId");
  }


  @Test(description = "还款方式-->催收充值")
  public void collectionRecharge() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId是否为null");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId是否为null");
    //读取趸交还款计划，如果趸交没有还清，则直接置为已还清，防止催收充值还款还到了趸交部分
    super.clearSinglePremiumSchedule(Integer.parseInt(loanOrderId));
    //催收充值不管还第几期，都是走大树4551，payway=6，也就是京东007
    //第一期
    RepaymentScheduleEntity firstRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("1"));
    //获取出还款计划当期的金额
    BigDecimal amount1 = firstRepaymentSchedule.getAmount();
    //对第一期进行还款
    //获取出卡号
    String cardNo = super.getCardNo(Integer.parseInt(loanOrderId));
    Long refId = System.currentTimeMillis();
    //通过催收充值方式还款
    HttpResponse httpResponse1 = repaymentFactory
        .collectionRecharge(Long.parseLong(loanOrderId), Long.parseLong(cardNo), refId, amount1);
    String result1 = httpResponse1.getContent();
    log.debug("催收充值接口返回-->[{}]", result1);
    //判断催收充值请求是否成功
    //将接口返回的内容转换成JSON
    JSONObject json1 = JsonUtil.parseObject(result1);
    String code1 = json1.getString("code");
    String message1 = json1.getString("message");
    JSONObject data1 = json1.getJSONObject("data");
    String tradeNo1 = data1.getString("tradeNo");
    String payNo1 = data1.getString("payNo");
    Assert.assertEquals(code1, "0000", "校验发送催收充值接口是否成功");
    Assert.assertEquals(message1, "请求成功", "校验发送催收充值接口是否成功");
    //等待X秒，给后台足够的入账时间
    ThreadUtil.sleep(super.recordedTime);
    //还款后校验
    //校验点1：t_tp_trade_order表的UserId、Amount、PayWay、PayPlatform、Channel、IsDeprecated
    Byte payWay1 = 6;
    Byte payPlatform1 = 42;
    String channel1 = "102";
    Byte isDeprecated1 = 0;
    super.checkTradeOrder(tradeNo1, Integer.valueOf(userId), amount1, payWay1, payPlatform1,
        channel1, isDeprecated1);
    //校验点2：是否发送topic:recharge的MQ，通知账务入账
    super.checkMQ(topic, payNo1, userId);
    //校验点3：t_repayment_schedule表是否 已还
    firstRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("1"));
    super.checkRepaymentSchedule(firstRepaymentSchedule);
    //校验点4：t_tp_transaction表的platId、tranStatus
    super.checkTransaction(tradeNo1, userId, payPlatform1);
  }


  @AfterMethod
  public void deleteOrder() {
    //删除订单
    super.deleteOrders(String.valueOf(orderId));
  }


  @AfterClass
  public void restoreUserPayChannelAndDelCache() {
    //删除Redis缓存
    super.cleanRedis();
  }


}
