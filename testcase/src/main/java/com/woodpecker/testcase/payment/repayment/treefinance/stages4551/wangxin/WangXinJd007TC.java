package com.woodpecker.testcase.payment.repayment.treefinance.stages4551.wangxin;

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
 * 类描述:〈网信走京东两方代扣(007)，通道42〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class WangXinJd007TC extends Stages4551TestCase {

  /**
   * 用例的支付渠道，京东007 - 42（不可改）
   */
  protected int channel = 42;

  protected String payChannel = "WANGXIN";

  protected String payChannel2 = "JDAGREEMENTPAY_4551";

  /**
   * 资金渠道，指定为网信渠道，不可改
   */
  private final PlatformIdEnum platformIdEnum = PlatformIdEnum.WX;

  protected String version = "2";

  private String orderId;

  private String loanOrderId;

  String payChannelCode = "WX";


  @BeforeClass
  public void ready() {
    //删除用户的支付渠道，防止别人新增过别的支付渠道，导致再新增一个另外的支付渠道，就一个userid同时配置了2个支付渠道了
    super.deleteUserPayChannelConfig();
    //mock网信扣款
    super.mockChannel(payChannel);
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


  @Test(description = "还款方式-->催收代扣")
  public void collectionWithhold() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId是否为null");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId是否为null");
    //读取还款计划表的前面二期的还款计划
    //因为第一期是正常还款，走的是「网信存管户」，也就是「网信还款-先锋支付 23」
    //第一期
    RepaymentScheduleEntity firstRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("1"));
    //注意点：需要看下银行限额，如果超过限额，则第一期还款原本是走「网信存管户」的，会变成走「京东两方代扣（007）42」
    //获取出bankId
    Integer bankId = super.getBankId(Integer.parseInt(loanOrderId));
    //获取出银行限额
    Integer dayAmountLimit = super.getDayAmountLimit(String.valueOf(bankId), payChannelCode);
    //获取出还款计划当期的金额
    BigDecimal amount1 = firstRepaymentSchedule.getAmount();
    //是否需要恢复银行原来的限额标记
    boolean needRestore = false;
    //判断当前还款金额是否超过限额
    if (amount1.compareTo(new BigDecimal(dayAmountLimit)) == 1) {
      log.debug("当前订单[{}]金额[{}]超过了银行[{}]日限额[{}],", orderId, amount1, bankId, dayAmountLimit);
      //计算新的银行限额
      Integer newDayAmountLimit = amount1.add(new BigDecimal(100)).intValue();
      log.debug("将银行[{}]原来的日限额[{}]修改为[{}]", bankId, dayAmountLimit, newDayAmountLimit);
      //修改银行限额
      super.setDayAmountLimit(String.valueOf(bankId), newDayAmountLimit, payChannelCode);
      //删除Redis缓存
      super.cleanRedis();
      //需要恢复银行原来的限额
      needRestore = true;
    }
    //对第一期进行还款
    //通过催收代扣方式还款
    HttpResponse httpResponse1 = null;
    try {
      httpResponse1 = repaymentFactory
          .collectionWithhold(Integer.valueOf(userId), (long) firstRepaymentSchedule.getId());
    } catch (Throwable e) {
      throw e;
    } finally {
      //判断是否需要恢复银行原来的限额
      if (needRestore) {
        log.debug("恢复银行[{}]原来的日限额[{}]", bankId, dayAmountLimit);
        //恢复日限额
        super.setDayAmountLimit(String.valueOf(bankId), dayAmountLimit, payChannelCode);
        //删除Redis缓存
        super.cleanRedis();
        //恢复了日限额后，重新置为false
        needRestore = false;
      }
    }
    String result1 = httpResponse1.getContent();
    log.debug("催收代扣接口返回-->[{}]", result1);
    //判断催收代扣请求是否成功
    //将接口返回的内容转换成JSON
    JSONObject json1 = JsonUtil.parseObject(result1);
    String code1 = json1.getString("code");
    String message1 = json1.getString("message");
    JSONObject data1 = json1.getJSONObject("data");
    String tradeNo1 = data1.getString("tradeNo");
    String payNo1 = data1.getString("payNo");
    Assert.assertEquals(code1, "0000", "校验发送催收代扣接口是否成功");
    Assert.assertEquals(message1, "请求成功", "校验发送催收代扣接口是否成功");
    //还款后校验
    //校验点1：t_tp_trade_order表的UserId、Amount、PayWay、PayPlatform、Channel、IsDeprecated
    Byte payWay1 = 17;
    Byte payPlatform1 = 23;
    String channel1 = "92";
    Byte isDeprecated1 = 0;
    super.checkTradeOrder(tradeNo1, Integer.valueOf(userId), amount1, payWay1, payPlatform1,
        channel1, isDeprecated1);
    //校验点2：是否发送topic:recharge的MQ，通知账务入账
    int repaymentScheduleId1 = firstRepaymentSchedule.getId();
    super.checkMQ(topic, payNo1, userId, repaymentScheduleId1);
    //校验点3：t_repayment_schedule表是否 已还
    firstRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("1"));
    super.checkRepaymentSchedule(firstRepaymentSchedule);

    ////////////////////////////////////////////////////////////////

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
