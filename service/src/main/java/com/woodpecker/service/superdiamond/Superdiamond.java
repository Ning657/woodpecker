package com.woodpecker.service.superdiamond;

import com.sword.autotest.framework.exception.TestCaseException;
import com.woodpecker.pageobject.superdiamond.ProjectProfilesPageObject;
import com.woodpecker.service.config.Account;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.Commons.Lang.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.Assert;

/**
 * 类描述:〈Superdiamond〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class Superdiamond {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private SuperdiamondService superdiamondService;

  @Autowired
  private ProjectProfilesPageObject projectProfilesPageObject;

  @Autowired
  protected Account account;

  /**
   * 常规的等待时间，2秒
   */
  protected int sleepTime = 2;

  /**
   * router.env.version
   */
  protected String routerEnvVersion = null;


  private void login() {
    String userName = account.getSdUserCode();
    String password = account.getSdPassword();
    Assert.assertNotNull(userName, "校验登录Superdiamond的账号是否为空");
    Assert.assertNotNull(password, "校验登录Superdiamond的密码是否为空");
    //打开Superdiamond登录页面
    superdiamondService.openSuperdiamond();
    //登录superdiamond
    boolean loginResult = superdiamondService.login(userName, password);
    Assert.assertTrue(loginResult, "校验登录Superdiamond是否成功");
  }


  /**
   * 方法功能描述: 删除已经配置了的用户的支付渠道配置（就是在配置中删除指定userid走指定的支付渠道的配置）
   *
   * @param userId userId
   * @return void
   */
  public void deleteUserPayChannel(String userId) {
    //判断是否已经登录
    if (!superdiamondService.isLogin()) {
      login();
    }
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
    logger.debug("oldValue={}", oldValue);
    //判断是否存在当前userid
    if (StringUtil.contains(oldValue, userId)) {
      //当前userid有配置支付渠道
      logger.debug("userid=[{}]有配置支付渠道，接下去删除它", userId);
      //删除这个userid配置的支付渠道
      String reg1 = ",\"" + userId + "\":\\d{1,3}";
      String reg2 = "\"" + userId + "\":\\d{1,3}";
      logger.debug("reg1=[{}]", reg1);
      logger.debug("reg2=[{}]", reg2);
      String newConfigValue = null;
      newConfigValue = oldValue.replaceAll(reg1, "");
      logger.debug("newConfigValue={}", newConfigValue);
      newConfigValue = newConfigValue.replaceAll(reg2, "");
      logger.debug("newConfigValue={}", newConfigValue);
      //输入配置值
      projectProfilesPageObject.setConfigValue(newConfigValue);
      //点击保存
      projectProfilesPageObject.clickSaveConfigBtn();
    } else {
      //当前userid没有配置支付渠道
      logger.debug("userid=[{}]没有配置支付渠道", userId);
      //直接点击「关闭」按钮
      projectProfilesPageObject.clickCloseBtn();
    }
    //返回首页
    superdiamondService.gotoIndexPage();
    //修改配置中心后，暂停几秒，等待配置生效
    ThreadUtil.sleep(sleepTime);
  }


  /**
   * 方法功能描述: 获取Router-router.env.version的配置值
   *
   * @return java.lang.String
   */
  public String getRouterEnvVersion() {
    if (StringUtil.isNotEmpty(this.routerEnvVersion)) {
      logger.debug("routerEnvVersion=[{}]", this.routerEnvVersion);
      return this.routerEnvVersion;
    }
    //判断是否已经登录
    if (!superdiamondService.isLogin()) {
      login();
    }
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
    ThreadUtil.sleep();
    //返回主页
    superdiamondService.gotoIndexPage();
    return this.routerEnvVersion;
  }


  /**
   * 方法功能描述: mock支付渠道
   *
   * @param payChannel 支付渠道
   * @return void
   */
  public void mockChannel(String payChannel) {
    //判断是否已经登录
    if (!superdiamondService.isLogin()) {
      login();
    }
    //搜索tp-payment-transaction项目的配置
    String projectName = "tp-payment-transaction";
    boolean isExistProject = superdiamondService.search(projectName);
    Assert.assertTrue(isExistProject, "校验是否搜索到项目" + projectName);
    //点击Profiles --> development
    String profiles = "development";
    boolean isProjectPage = superdiamondService.gotoProject(projectName, profiles);
    Assert.assertTrue(isProjectPage, "校验是否进入到tp-payment-transaction项目的development配置");
    //选择Module --> MOCK
    String moduleName = "MOCK";
    superdiamondService.selectModule(moduleName);
    //新增配置，mock指定支付渠道
    String configKey = "transaction.tran.mock.platforms";
    String configValue = "\"" + payChannel + "\"";
    boolean append = true;
    boolean deleteLastChar = true;
    String separator = ",";
    boolean updateSuccess = superdiamondService
        .addConfig(configKey, configValue, append, deleteLastChar, separator);
    Assert.assertTrue(updateSuccess, "校验指定用户的支付渠道为指定渠道是否成功");
    //修改配置中心后，暂停几秒，等待配置生效
    ThreadUtil.sleep(sleepTime);
  }


  /**
   * 方法功能描述: 指定用户的支付渠道（就是在配置中配置指定userid走指定的支付渠道）
   *
   * @param userId userId
   * @param channel 支付渠道
   * @return void
   */
  public void assignUserPayChannel(String userId, int channel) {
    //判断是否已经登录
    if (!superdiamondService.isLogin()) {
      login();
    }
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
    //修改配置中心后，暂停几秒，等待配置生效
    ThreadUtil.sleep(sleepTime);
  }


}
