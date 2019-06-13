package com.woodpecker.testcase.payment.repayment;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sword.autotest.framework.assertion.Validate;
import com.sword.autotest.framework.exception.TestCaseException;
import com.woodpecker.dao.payment.PayChannelBankDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.loandb.TradeOrderEntity;
import com.woodpecker.entity.payment.PayChannelBankEntity;
import com.woodpecker.entity.payment.TransactionEntity;
import com.woodpecker.pageobject.superdiamond.ProjectProfilesPageObject;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.service.payment.cache.RedisCacheFactory;
import com.woodpecker.service.pub.MQService;
import com.woodpecker.testcase.payment.PaymentTestCase;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.Commons.Lang.ThreadUtil;
import java.math.BigDecimal;
import java.util.List;
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
  protected RedisCacheFactory redisCacheFactory;

  @Autowired
  protected ProjectProfilesPageObject projectProfilesPageObject;

  @Autowired
  protected MQService mqService;

  @Autowired
  protected PayChannelBankDao payChannelBankDao;

  /**
   * 还款成功后的MQ的topic
   */
  protected String topic = "recharge";

  /**
   * Redis删除后等待的时间，3秒
   */
  protected int redisSleepTime = 3;

  /**
   * router.env.version
   */
  protected String routerEnvVersion = null;

  
  /**
   * 方法功能描述: 删除用户名下的订单
   *
   * @param platformIdEnum 资金渠道
   * @return void
   */
  public void deleteUserOrders(PlatformIdEnum platformIdEnum) {
    //删除用户名下的订单
    boolean deleteResult = dataBuildOrderService.delete(userId, platformIdEnum);
    Assert.assertTrue(deleteResult, "校验删除用户" + userId + "名下的订单是否成功deleteResult=" + deleteResult);
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
    Assert.assertTrue(deleteResult, "校验删除订单" + orderIds + "是否成功deleteResult=" + deleteResult);
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
    Assert.assertEquals(map.size(), 2, "校验是否生成用户" + userId + "的还款订单成功map=" + map.toString());
    return map;
  }


  /**
   * 方法功能描述: 清除会影响支付的Redis缓存
   *
   * @return void
   */
  public void cleanRedis() {
    redisCacheFactory.delete();
    //删除缓存后，暂停几秒，等待生效
    ThreadUtil.sleep(redisSleepTime);
  }


  /**
   * 方法功能描述: 删除已经配置了的用户的支付渠道配置（就是在配置中删除指定userid走指定的支付渠道的配置）
   *
   * @return void
   */
  public void deleteUserPayChannelConfig() {
    String userName = account.getSdUserCode();
    String password = account.getSdPassword();
    Assert.assertNotNull(userName, "校验登录Superdiamond的账号是否为空");
    Assert.assertNotNull(password, "校验登录Superdiamond的密码是否为空");
    //打开Superdiamond登录页面
    superdiamondService.openSuperdiamond();
    //登录superdiamond
    boolean loginResult = superdiamondService.login(userName, password);
    Assert.assertTrue(loginResult, "校验登录Superdiamond是否成功");
    //搜索tp-payment-router项目的配置
    String projectName = "tp-payment-router";
    boolean isExistProject = superdiamondService.search(projectName);
    Assert.assertTrue(isExistProject, "校验是否搜索到项目" + projectName);
    //点击Profiles --> development
    String profiles = "development";
    boolean isProjectPage = superdiamondService.gotoProject(projectName, profiles);
    Assert.assertTrue(isProjectPage, "校验是否进入到tp-payment-router项目的development配置");
    //选择Module --> ROUTER
    String moduleName = "ROUTER";
    superdiamondService.selectModule(moduleName);
    //
    String configKey = "router.assign.payplatform";
    //循环查找key
    boolean isExistKey = false;
    for (int i = 0; i < 30; i++) {
      //判断当前页是否存在key
      boolean flag = projectProfilesPageObject.isExistKey(configKey);
      if (flag) {
        //当前页存在这个key
        isExistKey = true;
        break;
      }
      logger.debug("当前页不存在configKey=[{}]配置项", configKey);
      //判断是否有下一页
      boolean f1 = projectProfilesPageObject.isExistNextPageBtn();
      if (f1) {
        //还有下一页
        //点击下一页按钮
        projectProfilesPageObject.clickNextPageBtn();
        ThreadUtil.sleep();
      } else {
        //最后一页了，还是找不到key
        break;
      }
    }
    if (!isExistKey) {
      //如果没有这个key，则直接返回
      logger.error("不存在configKey=[{}]配置项", configKey);
      throw new TestCaseException("不存在" + configKey + "配置项");
    }
    //点击修改按钮
    projectProfilesPageObject.clickUpdateConfigBtn(configKey);
    //获取出配置值
    String oldValue = projectProfilesPageObject.getConfigValue(configKey);
    log.debug("oldValue={}", oldValue);
    //判断是否存在当前userid
    if (StringUtil.contains(oldValue, userId)) {
      //当前userid有配置支付渠道
      log.debug("userid=[{}]有配置支付渠道，接下去删除它", userId);
      //删除这个userid配置的支付渠道
      String reg1 = ",\"" + userId + "\":\\d{1,3}";
      String reg2 = "\"" + userId + "\":\\d{1,3}";
      log.debug("reg1=[{}]", reg1);
      log.debug("reg2=[{}]", reg2);
      String newConfigValue = null;
      newConfigValue = oldValue.replaceAll(reg1, "");
      log.debug("newConfigValue={}", newConfigValue);
      newConfigValue = newConfigValue.replaceAll(reg2, "");
      log.debug("newConfigValue={}", newConfigValue);
      //输入配置值
      projectProfilesPageObject.setConfigValue(newConfigValue);
      //点击保存
      projectProfilesPageObject.clickSaveConfigBtn();
    } else {
      //当前userid没有配置支付渠道
      log.debug("userid=[{}]没有配置支付渠道", userId);
      //直接点击「关闭」按钮
      projectProfilesPageObject.clickCloseBtn();
    }
    //返回首页
    superdiamondService.gotoIndexPage();
    //修改配置中心后，暂停几秒，等待配置生效
    ThreadUtil.sleep(sleepTime);
  }


  /**
   * 方法功能描述: 校验t_tp_transaction表数据
   *
   * @param tradeNo tradeNo
   * @param userId userId
   * @param payPlatform payPlatform
   * @return void
   */
  public void checkTransaction(String tradeNo, String userId, Byte payPlatform) {
    //根据TradeNo查找t_tp_transaction表的记录
    TransactionEntity transactionEntity = transactionDao.findByOrderNo(tradeNo);
    Assert.assertNotNull(transactionEntity, "校验是否生成了t_tp_transaction记录");
    Validate.isEquals(userId, transactionEntity.getBizUserId(), "校验bizUserId是否正确");
    Validate.isEquals(payPlatform, transactionEntity.getPlatId(), "校验platId是否正确");
    Validate.isEquals("2", transactionEntity.getTranStatus(), "校验tranStatus是否正确");
  }


  /**
   * 方法功能描述: 校验t_tp_transaction表数据
   *
   * @param tradeNo tradeNo
   * @param userId userId
   * @param payPlatforms payPlatform
   * @return void
   */
  public void checkTransaction(String tradeNo, String userId, String[] payPlatforms) {
    //根据TradeNo查找t_tp_transaction表的记录
    TransactionEntity transactionEntity = transactionDao.findByOrderNo(tradeNo);
    Assert.assertNotNull(transactionEntity, "校验是否生成了t_tp_transaction记录");
    Validate.isEquals(userId, transactionEntity.getBizUserId(), "校验bizUserId是否正确");
    boolean flag = false;
    for (String payPlatform : payPlatforms) {
      if (StringUtil.equals(payPlatform, String.valueOf(transactionEntity.getPlatId()))) {
        flag = true;
      }
    }
    Validate.isTrue(flag, "校验platId是否正确");
    Validate.isEquals("2", transactionEntity.getTranStatus(), "校验tranStatus是否正确");
  }


  /**
   * 方法功能描述: 校验t_tp_trade_order表数据
   *
   * @param tradeNo tradeNo
   * @param userId userId
   * @param amount amount
   * @param payWay payWay
   * @param payPlatform payPlatform
   * @param channel channel
   * @param isDeprecated isDeprecated
   * @return void
   */
  public void checkTradeOrder(String tradeNo, Integer userId, BigDecimal amount, Byte payWay,
      Byte payPlatform, String channel, Byte isDeprecated) {
    //根据TradeNo查找t_tp_trade_order表的记录
    TradeOrderEntity tradeOrderEntity = tradeOrderDao.findByTradeNo(tradeNo);
    Assert.assertNotNull(tradeOrderEntity, "校验是否生成了t_tp_trade_order记录");
    Validate.isEquals(userId, tradeOrderEntity.getUserId(), "校验userId是否正确");
    Validate
        .isEquals(amount.doubleValue(), tradeOrderEntity.getAmount().doubleValue(), "校验amount是否正确");
    Validate.isEquals(payWay, tradeOrderEntity.getPayWay(), "校验payWay是否正确");
    Validate.isEquals(payPlatform, tradeOrderEntity.getPayPlatform(), "校验payPlatform是否正确");
    Validate.isEquals(2, tradeOrderEntity.getTranStatus(), "校验TranStatus是否正确");
    Validate.isEquals(channel, tradeOrderEntity.getChannel(), "校验channel是否正确");
    Validate.isEquals(isDeprecated, tradeOrderEntity.getIsDeprecated(), "校验isDeprecated是否正确");
  }


  /**
   * 方法功能描述: 校验t_tp_trade_order表数据
   *
   * @param tradeNo tradeNo
   * @param userId userId
   * @param amount amount
   * @param payWays payWays
   * @param payPlatforms payPlatforms
   * @param channels channels
   * @param isDeprecated isDeprecated
   * @return void
   */
  public void checkTradeOrder(String tradeNo, Integer userId, BigDecimal amount, String[] payWays,
      String[] payPlatforms, String[] channels, Byte isDeprecated) {
    //根据TradeNo查找t_tp_trade_order表的记录
    TradeOrderEntity tradeOrderEntity = tradeOrderDao.findByTradeNo(tradeNo);
    Assert.assertNotNull(tradeOrderEntity, "校验是否生成了t_tp_trade_order记录");
    Validate.isEquals(userId, tradeOrderEntity.getUserId(), "校验userId是否正确");
    Validate
        .isEquals(amount.doubleValue(), tradeOrderEntity.getAmount().doubleValue(), "校验amount是否正确");
    boolean flag = false;
    for (String payWay : payWays) {
      if (StringUtil.equals(String.valueOf(tradeOrderEntity.getPayWay()), payWay)) {
        flag = true;
      }
    }
    Validate.isTrue(flag, "校验payWay是否正确");
    flag = false;
    for (String payPlatform : payPlatforms) {
      if (StringUtil.equals(String.valueOf(tradeOrderEntity.getPayPlatform()), payPlatform)) {
        flag = true;
      }
    }
    Validate.isTrue(flag, "校验payPlatform是否正确");
    Validate.isEquals(2, tradeOrderEntity.getTranStatus(), "校验TranStatus是否正确");
    flag = false;
    for (String channel : channels) {
      if (StringUtil.equals(tradeOrderEntity.getChannel(), channel)) {
        flag = true;
      }
    }
    Validate.isTrue(flag, "校验channel是否正确");
    Validate.isEquals(isDeprecated, tradeOrderEntity.getIsDeprecated(), "校验isDeprecated是否正确");
  }


  /**
   * 方法功能描述: 校验MQ是否发送成功
   *
   * @param topic topic
   * @param payNo payNo
   * @param userId userId
   * @param scheduleId scheduleId
   * @return void
   */
  public void checkMQ(String topic, String payNo, String userId, int scheduleId) {
    //MQ check
    boolean flag = false;
    List<JSONObject> jsonObjectList = mqService.queryMessageByTopic(2, topic);
    for (JSONObject json : jsonObjectList) {
      String msgId = json.getString("msgId");
      JSONObject msgJsonObject = mqService.queryMessageByMsgId(msgId, topic);
      JSONArray array = msgJsonObject.getJSONArray("messageTrackList");
      if (array.size() > 0) {
        JSONObject j = array.getJSONObject(0);
        String consumerGroup = j.getString("consumerGroup");
        boolean f = StringUtil.equalsIgnoreCase("accounting", consumerGroup);
        if (f) {
          JSONObject s = msgJsonObject.getJSONObject("messageView");
          JSONObject messageBody = s.getJSONObject("messageBody");
          String payNoA = messageBody.getString("payNo");
          String userIdA = messageBody.getString("userId");
          int scheduleIdA = messageBody.getIntValue("scheduleId");
          if (StringUtil.equals(payNoA, payNo) && StringUtil.equals(userIdA, userId) && (
              scheduleId - scheduleIdA == 0)) {
            log.debug("找到发出去的MQ=[{}]", msgJsonObject);
            flag = true;
            break;
          }
        }
      }
    }
    Validate.isTrue(flag, "校验是否向accounting发送MQ消息");
  }


  /**
   * 方法功能描述: 校验MQ是否发送成功
   *
   * @param topic topic
   * @param payNo payNo
   * @param userId userId
   * @param scheduleId scheduleId
   * @return void
   */
  public void checkMQ(String topic, String payNo, String userId) {
    //MQ check
    boolean flag = false;
    List<JSONObject> jsonObjectList = mqService.queryMessageByTopic(2, topic);
    for (JSONObject json : jsonObjectList) {
      String msgId = json.getString("msgId");
      JSONObject msgJsonObject = mqService.queryMessageByMsgId(msgId, topic);
      JSONArray array = msgJsonObject.getJSONArray("messageTrackList");
      if (array.size() > 0) {
        JSONObject j = array.getJSONObject(0);
        String consumerGroup = j.getString("consumerGroup");
        boolean f = StringUtil.equalsIgnoreCase("accounting", consumerGroup);
        if (f) {
          JSONObject s = msgJsonObject.getJSONObject("messageView");
          JSONObject messageBody = s.getJSONObject("messageBody");
          String payNoA = messageBody.getString("payNo");
          String userIdA = messageBody.getString("userId");
          if (StringUtil.equals(payNoA, payNo) && StringUtil.equals(userIdA, userId)) {
            log.debug("找到发出去的MQ=[{}]", msgJsonObject);
            flag = true;
            break;
          }
        }
      }
    }
    Validate.isTrue(flag, "校验是否向accounting发送MQ消息");
  }


  /**
   * 方法功能描述: 校验还款计划
   *
   * @param repaymentScheduleEntity 还款计划
   * @return void
   */
  public void checkRepaymentSchedule(RepaymentScheduleEntity repaymentScheduleEntity) {
    Byte isClear = repaymentScheduleEntity.getIsClear();
    Byte status = repaymentScheduleEntity.getStatus();
    Validate.isEquals(1, isClear, "校验t_repayment_schedule表的IsClear是否为1");
    Validate.isEquals(11, status, "校验t_repayment_schedule表的Status是否为11");
  }


  /**
   * 方法功能描述: 获取BankId
   *
   * @param loanOrderId loanOrderId
   * @return java.lang.Integer
   */
  public Integer getBankId(Integer loanOrderId) {
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId);
    Long bankAccountId = loanOrderEntity.getBankAccountId();
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId.intValue());
    return bankAccountEntity.getBankId();
  }


  /**
   * 方法功能描述: 获取t_loan_order所对应的银行卡卡号
   *
   * @param loanOrderId loanOrderId
   * @return java.lang.String
   */
  public String getCardNo(Integer loanOrderId) {
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId);
    Long bankAccountId = loanOrderEntity.getBankAccountId();
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId.intValue());
    String account = bankAccountEntity.getAccount();
    String cardNo = dataAnalysisService.aesDecrypt(account);
    return cardNo;
  }


  /**
   * 方法功能描述: 获取银行日限额
   *
   * @param code bankId
   * @param payChannelCode payChannelCode
   * @return java.lang.Integer
   */
  public Integer getDayAmountLimit(String code, String payChannelCode) {
    PayChannelBankEntity payChannelBankEntity = payChannelBankDao
        .findByCodeAndPayChannelCodeAndVersion(code, payChannelCode, "test");
    return payChannelBankEntity.getDayAmount();
  }


  /**
   * 方法功能描述: 设置银行日限额
   *
   * @param code bankId
   * @param dayAmount 日限额
   * @param payChannelCode payChannelCode
   * @return com.woodpecker.entity.payment.PayChannelBankEntity
   */
  public PayChannelBankEntity setDayAmountLimit(String code, Integer dayAmount,
      String payChannelCode) {
    PayChannelBankEntity payChannelBankEntity = payChannelBankDao
        .findByCodeAndPayChannelCodeAndVersion(code, payChannelCode, getRouterEnvVersion());
    payChannelBankEntity.setDayAmount(dayAmount);
    return payChannelBankDao.save(payChannelBankEntity);
  }


  /**
   * 方法功能描述: 校验t_tp_trade_order表数据
   *
   * @param tradeNo tradeNo
   * @param userId userId
   * @param amount amount
   * @param isDeprecated isDeprecated
   * @return void
   */
  public void checkTradeOrder(String tradeNo, Integer userId, BigDecimal amount,
      Byte isDeprecated) {
    //根据TradeNo查找t_tp_trade_order表的记录
    TradeOrderEntity tradeOrderEntity = tradeOrderDao.findByTradeNo(tradeNo);
    Assert.assertNotNull(tradeOrderEntity, "校验是否生成了t_tp_trade_order记录");
    Validate.isEquals(userId, tradeOrderEntity.getUserId(), "校验userId是否正确");
    Validate
        .isEquals(amount.doubleValue(), tradeOrderEntity.getAmount().doubleValue(), "校验amount是否正确");
    Validate.isEquals(isDeprecated, tradeOrderEntity.getIsDeprecated(), "校验isDeprecated是否正确");
  }


  /**
   * 方法功能描述: 获取Router-router.env.version的配置值
   *
   * @return java.lang.String
   */
  public String getRouterEnvVersion() {
    if (StringUtil.isNotEmpty(this.routerEnvVersion)) {
      log.debug("routerEnvVersion=[{}]", this.routerEnvVersion);
      return this.routerEnvVersion;
    }
    String userName = account.getSdUserCode();
    String password = account.getSdPassword();
    Assert.assertNotNull(userName, "校验登录Superdiamond的账号是否为空");
    Assert.assertNotNull(password, "校验登录Superdiamond的密码是否为空");
    //打开Superdiamond登录页面
    superdiamondService.openSuperdiamond();
    //登录superdiamond
    boolean loginResult = superdiamondService.login(userName, password);
    Assert.assertTrue(loginResult, "校验登录Superdiamond是否成功");
    //搜索tp-payment-router项目的配置
    String projectName = "tp-payment-router";
    boolean isExistProject = superdiamondService.search(projectName);
    Assert.assertTrue(isExistProject, "校验是否搜索到项目" + projectName);
    //点击Profiles --> development
    String profiles = "development";
    boolean isProjectPage = superdiamondService.gotoProject(projectName, profiles);
    Assert.assertTrue(isProjectPage, "校验是否进入到tp-payment-router项目的development配置");
    //获取配置值
    String configKey = "router.env.version";
    this.routerEnvVersion = superdiamondService.getConfigValue(configKey);
    logger.debug("配置[{}]的值为[{}]", configKey, this.routerEnvVersion);
    return this.routerEnvVersion;
  }

  
}
