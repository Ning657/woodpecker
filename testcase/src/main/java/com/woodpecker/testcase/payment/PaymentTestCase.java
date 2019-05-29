package com.woodpecker.testcase.payment;

import com.woodpecker.service.config.Account;
import com.woodpecker.service.pub.DataAnalysisService;
import com.woodpecker.service.superdiamond.SuperdiamondService;
import com.woodpecker.testcase.TestCase;
import com.xujinjian.Commons.Lang.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

/**
 * 类描述:〈支付TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class PaymentTestCase extends TestCase {

  @Value("${testcase.userId}")
  protected String userId;

  @Autowired
  protected Account account;

  @Autowired
  protected SuperdiamondService superdiamondService;

  @Autowired
  protected DataAnalysisService dataAnalysisService;

  /**
   * 常规的等待时间，2秒
   */
  protected int sleepTime = 2;


  /**
   * 方法功能描述: mock支付渠道
   *
   * @param payChannel 支付渠道
   * @return void
   */
  public void mockChannel(String payChannel) {
    String userName = account.getSdUserCode();
    String password = account.getSdPassword();
    Assert.assertNotNull(userName, "校验登录Superdiamond的账号是否为空");
    Assert.assertNotNull(password, "校验登录Superdiamond的密码是否为空");
    //打开Superdiamond登录页面
    superdiamondService.openSuperdiamond();
    //登录superdiamond
    boolean loginResult = superdiamondService.login(userName, password);
    Assert.assertTrue(loginResult, "校验登录Superdiamond是否成功");
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

}
