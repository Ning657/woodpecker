package com.woodpecker.testcase.payment.repayment.treefinance.premium4551.baoyin;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.testcase.payment.repayment.treefinance.premium4551.PremiumRepaymentTestCase;
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
 * 类描述:〈包银趸交还款用例，走通联协议支付 60〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class BaoYinPremiumTongLianTC extends PremiumRepaymentTestCase {

  String payChannel = "ALL_IN_PAY";

  /**
   * 资金渠道
   */
  final PlatformIdEnum platformIdEnum = PlatformIdEnum.BY;

  String version = "2";

  String orderId;

  String loanOrderId;

  String payChannelCode = "ALL_IN";

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
    //mock通联协议支付
    super.mockChannel(payChannel);
    //删除Redis缓存
    super.cleanRedis();
  }


  @BeforeMethod
  public void createOrder() {
    //生成还款订单
    Map<String, String> map = super.createOrder(platformIdEnum, version);
    orderId = map.get("orderId");
    loanOrderId = map.get("loanorderId");
  }


  @Test(description = "趸交")
  public void premium() {
    log.debug("orderId=[{}];loanOrderId=[{}]", orderId, loanOrderId);
    Assert.assertNotNull(orderId, "校验orderId是否为null");
    Assert.assertNotNull(loanOrderId, "校验loanOrderId是否为null");
    //判断银行卡是否已鉴权，如果没有鉴权，则先执行鉴权操作
    super.bindCard(loanOrderId, "60", "ALL_IN_PAY");
    //读取趸交计划
    SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findByLoanOrderId(Integer.parseInt(loanOrderId));
    //注意点：需要看下银行限额，如果超过限额，则需要调大限额金额
    //获取出bankId
    Integer bankId = super.getBankId(Integer.parseInt(loanOrderId));
    //获取出银行限额
    Integer dayAmountLimit = super.getDayAmountLimit(String.valueOf(bankId), payChannelCode);
    //获取出趸交计划的金额
    BigDecimal amount = singlePremiumScheduleEntity.getAmount();
    //是否需要恢复银行原来的限额标记
    boolean needRestore = false;
    //判断当前还款金额是否超过限额
    if (amount.compareTo(new BigDecimal(dayAmountLimit)) == 1) {
      log.debug("当前订单[{}]金额[{}]超过了银行[{}]日限额[{}],", orderId, amount, bankId, dayAmountLimit);
      //计算新的银行限额
      Integer newDayAmountLimit = amount.add(super.upAmount).intValue();
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
    //还趸交
    HttpResponse httpResponse = null;
    try {
      httpResponse = repaymentFactory
          .premium(Integer.valueOf(userId), Long.valueOf(singlePremiumScheduleEntity.getId()));
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
    String result = httpResponse.getContent();
    log.debug("还趸交接口返回-->[{}]", result);
    //判断还趸交请求是否成功
    //将接口返回的内容转换成JSON
    JSONObject json = JsonUtil.parseObject(result);
    String code = json.getString("code");
    String message = json.getString("message");
    JSONObject data = json.getJSONObject("data");
    String tradeNo = data.getString("tradeNo");
    String payNo = data.getString("payNo");
    Assert.assertEquals(code, "0000", "校验发送还趸交接口是否成功");
    Assert.assertEquals(message, "请求成功", "校验发送还趸交接口是否成功");
    //等待X秒，给后台足够的入账时间
    ThreadUtil.sleep(super.recordedTime);
    //还款后校验
    //校验点1：t_tp_trade_order表的UserId、Amount、PayWay、PayPlatform、Channel、IsDeprecated
    //PayWay原本应该是6，但因为我是直接指定了用户的支付通道，把长剑生成的PayWay=6覆盖掉了。所以暂时要么不校验这个PayWay，或者PayWay=99
    Byte payWay = 6;
    Byte payPlatform = 60;
    String channel = "60";
    Byte isDeprecated = 0;
    super.checkTradeOrder(tradeNo, Integer.valueOf(userId), amount, payWay, payPlatform,
        channel, isDeprecated);
    //校验点2：是否发送topic:recharge的MQ，通知账务入账
    int singlePremiumScheduleId = singlePremiumScheduleEntity.getId();
    super.checkMQ(topic, payNo, userId, singlePremiumScheduleId);
    //校验点3：qs_single_premium_schedule表是否 已还
    singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findByLoanOrderId(Integer.parseInt(loanOrderId));
    super.checkSinglePremiumSchedule(singlePremiumScheduleEntity);
    //校验点4：t_tp_transaction表的platId、tranStatus
    super.checkTransaction(tradeNo, userId, payPlatform);
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
