package com.woodpecker.testcase.payment.repayment.treefinance.stages4551;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sword.autotest.framework.assertion.Validate;
import com.sword.autotest.framework.exception.TestCaseException;
import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.dao.loandb.TradeOrderDao;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.loandb.TradeOrderEntity;
import com.woodpecker.pageobject.superdiamond.ProjectProfilesPageObject;
import com.woodpecker.service.config.Account;
import com.woodpecker.service.payment.trade.RepaymentFactory;
import com.woodpecker.service.pub.MQService;
import com.woodpecker.service.superdiamond.SuperdiamondService;
import com.woodpecker.testcase.payment.PaymentTestCase;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.Commons.Lang.ThreadUtil;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * 类描述:〈分期还款走4551的测试用例基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class Stages4551TestCase extends PaymentTestCase {

  @Autowired
  protected Account account;

  @Autowired
  protected SuperdiamondService superdiamondService;

  @Autowired
  protected RepaymentScheduleDao repaymentScheduleDao;

  @Autowired
  protected RepaymentFactory repaymentFactory;

  @Autowired
  protected ProjectProfilesPageObject projectProfilesPageObject;

  @Autowired
  protected TradeOrderDao tradeOrderDao;

  @Autowired
  protected MQService mqService;

  /**
   * 还款成功后的MQ的topic
   */
  protected String topic = "recharge";


  /**
   * 方法功能描述: 修改用户的支付渠道（就是在配置中配置指定userid走指定的支付渠道）
   *
   * @param channel 支付渠道
   * @return void
   */
  public void updateUserPayChannelConfig(int channel) {
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
    //新增配置，配置用户的支付渠道为指定channel
    String configKey = "router.assign.payplatform";
    String configValue = "\"" + userId + "\":" + channel;
    boolean append = true;
    boolean deleteLastChar = true;
    String separator = ",";
    boolean updateSuccess = superdiamondService
        .addConfig(configKey, configValue, append, deleteLastChar, separator);
    Assert.assertTrue(updateSuccess, "校验指定用户的支付渠道为指定渠道是否成功");
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
    ThreadUtil.sleep();
    //返回首页
    superdiamondService.gotoIndexPage();
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
    Validate.isEquals(channel, tradeOrderEntity.getChannel(), "校验channel是否正确");
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


}
