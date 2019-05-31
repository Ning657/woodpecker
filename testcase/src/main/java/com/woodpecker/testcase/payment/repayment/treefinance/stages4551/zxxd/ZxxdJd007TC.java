package com.woodpecker.testcase.payment.repayment.treefinance.stages4551.zxxd;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.testcase.payment.repayment.treefinance.stages4551.Stages4551TestCase;
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
 * 类描述:〈中新小贷走京东两方代扣007(JDAGREEMENTPAY_4551)，通道42〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class ZxxdJd007TC extends Stages4551TestCase {

  /**
   * 用例的支付渠道，京东007 - 42（不可改）
   */
  protected int channel = 42;

  protected String payChannel = "JDAGREEMENTPAY_4551";

  /**
   * 资金渠道，指定为中新小贷渠道，不可改
   */
  private final PlatformIdEnum platformIdEnum = PlatformIdEnum.ZXXD;

  protected String version = "1";

  private String orderId;

  private String loanOrderId;


  @BeforeClass
  public void ready() {
    //删除用户的支付渠道，防止别人新增过别的支付渠道，导致再新增一个另外的支付渠道，就一个userid同时配置了2个支付渠道了
    super.deleteUserPayChannelConfig();
    //mock京东(007-4551)
    super.mockChannel(payChannel);
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


  @Test(description = "还款方式-->催收代扣")
  public void collectionWithhold() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId是否为null");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId是否为null");
    //将第一期置为已还清
    super.clearRepaymentSchedule(Integer.parseInt(loanOrderId), (byte) 1);
    //此时还第二期，就是提前还未来期，走的是「京东007」42
    //对第二期进行还款
    //修改用户的支付渠道
    super.updateUserPayChannelConfig(channel);
    //第二期
    RepaymentScheduleEntity secondRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("2"));
    //对第二期进行还款
    //通过催收代扣方式还款
    HttpResponse httpResponse2 = repaymentFactory
        .collectionWithhold(Integer.valueOf(userId), (long) secondRepaymentSchedule.getId());
    String result2 = httpResponse2.getContent();
    log.debug("催收代扣接口返回-->[{}]", result2);
    //判断催收代扣请求是否成功
    //将接口返回的内容转换成JSON
    JSONObject json2 = JsonUtil.parseObject(result2);
    String code2 = json2.getString("code");
    String message2 = json2.getString("message");
    JSONObject data2 = json2.getJSONObject("data");
    String tradeNo2 = data2.getString("tradeNo");
    String payNo2 = data2.getString("payNo");
    Assert.assertEquals(code2, "0000", "校验发送催收代扣接口是否成功");
    Assert.assertEquals(message2, "请求成功", "校验发送催收代扣接口是否成功");
    //还款后校验
    //校验点1：t_tp_trade_order表的UserId、Amount、PayWay、PayPlatform、Channel、IsDeprecated
    BigDecimal amount2 = secondRepaymentSchedule.getAmount();
    //PayWay原本应该是6，但因为我是直接指定了用户的支付通道，把长剑生成的PayWay=6覆盖掉了。所以暂时要么不校验这个PayWay，或者PayWay=99
    Byte payWay2 = 99;
    Byte payPlatform2 = 42;
    String channel2 = "102";
    Byte isDeprecated2 = 0;
    super.checkTradeOrder(tradeNo2, Integer.valueOf(userId), amount2, payWay2, payPlatform2,
        channel2, isDeprecated2);
    //校验点2：是否发送topic:recharge的MQ，通知账务入账
    int repaymentScheduleId2 = secondRepaymentSchedule.getId();
    super.checkMQ(topic, payNo2, userId, repaymentScheduleId2);
    //校验点3：t_repayment_schedule表是否 已还
    secondRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("2"));
    super.checkRepaymentSchedule(secondRepaymentSchedule);
    //校验点4：t_tp_transaction表的platId、tranStatus
    super.checkTransaction(tradeNo2, userId, payPlatform2);
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
    //还原用户的支付渠道
    super.deleteUserPayChannelConfig();
  }


}
