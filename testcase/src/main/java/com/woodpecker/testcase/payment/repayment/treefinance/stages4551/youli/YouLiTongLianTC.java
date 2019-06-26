package com.woodpecker.testcase.payment.repayment.treefinance.stages4551.youli;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.testcase.payment.repayment.treefinance.stages4551.Stages4551TestCase;
import com.xujinjian.Commons.Lang.ThreadUtil;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 类描述:〈有利走通联协议支付，通道60〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class YouLiTongLianTC extends Stages4551TestCase {

  /**
   * 用例的支付渠道，60（不可改）
   */
  //protected int channel = 60;

  protected String payChannel2 = "ALL_IN_PAY";

  /**
   * 资金渠道，指定为有利渠道，不可改
   */
  private final PlatformIdEnum platformIdEnum = PlatformIdEnum.YLW;

  protected String version = "1";

  private String orderId;

  private String loanOrderId;

  private String payChannelCode = "ALL_IN";

  /**
   * 需要禁用的支付通道code
   */
  String[] codes = {"42"};

  /**
   * 被修改过的支付通道
   */
  List<PayPlatformEntity> payPlatformList = null;


  @BeforeClass
  public void ready() {
    //删除用户的支付渠道，防止别人新增过别的支付渠道，导致再新增一个另外的支付渠道，就一个userid同时配置了2个支付渠道了
    super.deleteUserPayChannelConfig();
    //mock通联
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


  /**
   * 提前还未来期(整期)，是走有利的；部分还款时，是走大树4551的。催收充值，都是走大树4551的。
   */
  @Test(description = "还款方式-->催收充值")
  public void collectionRecharge() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId是否为null");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId是否为null");
    //判断银行卡是否已鉴权，如果没有鉴权，则先执行鉴权操作
    super.bindCard(loanOrderId, "60", "ALL_IN_PAY");
    //将趸交计划置为已结清
    super.clearSinglePremiumSchedule(Integer.parseInt(loanOrderId));
    //对订单进行还款
    //修改用户的支付渠道
    //super.updateUserPayChannelConfig(channel);//可以不用指定支付通道，因为就应该走这个通道，所以不用指定
    //第一期
    RepaymentScheduleEntity secondRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("1"));
    //注意点：需要看下银行限额，如果超过限额，则需要调大限额金额
    //获取出bankId
    Integer bankId = super.getBankId(Integer.parseInt(loanOrderId));
    //获取出银行限额
    Integer dayAmountLimit = super.getDayAmountLimit(String.valueOf(bankId), payChannelCode);
    //获取出还款计划当期的金额
    BigDecimal amount1 = secondRepaymentSchedule.getAmount();
    //是否需要恢复银行原来的限额标记
    boolean needRestore = false;
    //判断当前还款金额是否超过限额
    if (amount1.compareTo(new BigDecimal(dayAmountLimit)) == 1) {
      log.debug("当前订单[{}]金额[{}]超过了银行[{}]日限额[{}],", orderId, amount1, bankId, dayAmountLimit);
      //计算新的银行限额
      Integer newDayAmountLimit = amount1.add(super.upAmount).intValue();
      log.debug("将银行[{}]原来的日限额[{}]修改为[{}]", bankId, dayAmountLimit, newDayAmountLimit);
      //修改银行限额
      super.setDayAmountLimit(String.valueOf(bankId), newDayAmountLimit, payChannelCode);
      //删除Redis缓存
      super.cleanRedis();
      //需要恢复银行原来的限额
      needRestore = true;
    }
    //禁用京东，如果不禁用京东，则会有一定的概率会去走京东
    payPlatformList = super.banPayPlatformByCode(codes);
    //对第二期进行还款
    //通过催收代扣方式还款
    HttpResponse httpResponse2 = null;
    try {
      String cardNo = super.getCardNo(Integer.parseInt(loanOrderId));
      httpResponse2 = repaymentFactory
          .collectionRecharge(Long.parseLong(loanOrderId), Long.parseLong(cardNo),
              System.currentTimeMillis(), amount1);
    } finally {
      try {
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
      } finally {
        //恢复被修改过的支付通道
        super.recoverPayPlatform(payPlatformList);
      }
    }
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
    //等待X秒，给后台足够的入账时间
    ThreadUtil.sleep(super.recordedTime);
    //还款后校验
    //校验点1：t_tp_trade_order表的UserId、Amount、PayWay、PayPlatform、Channel、IsDeprecated
    BigDecimal amount2 = secondRepaymentSchedule.getAmount();
    //PayWay原本应该是6，但因为我是直接指定了用户的支付通道，把长剑生成的PayWay=6覆盖掉了。所以暂时要么不校验这个PayWay，或者校验PayWay=99
    Byte payWay2 = 6;
    Byte payPlatform2 = 60;
    String channel2 = "60";
    Byte isDeprecated2 = 0;
    super.checkTradeOrder(tradeNo2, Integer.valueOf(userId), amount2, payWay2, payPlatform2,
        channel2, isDeprecated2);
    //校验点2：是否发送topic:recharge的MQ，通知账务入账
    super.checkMQ(topic, payNo2, userId, loanOrderId);
    //校验点3：t_repayment_schedule表是否 已还
    secondRepaymentSchedule = repaymentScheduleDao
        .findByLoanOrderIdAndStage(Integer.parseInt(loanOrderId), Byte.parseByte("1"));
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
    //super.deleteUserPayChannelConfig();//测试开始前已经删除过了，且测试中未设置用户支付渠道，所以测试结束后不用再删
  }


  public String getPayChannelCode() {
    return payChannelCode;
  }


}
