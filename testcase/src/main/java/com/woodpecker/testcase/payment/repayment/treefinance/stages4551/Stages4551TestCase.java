package com.woodpecker.testcase.payment.repayment.treefinance.stages4551;

import com.woodpecker.testcase.payment.repayment.RepaymentTestCase;
import com.xujinjian.Commons.Lang.ThreadUtil;
import org.testng.Assert;

/**
 * 类描述:〈分期还款走4551的测试用例基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class Stages4551TestCase extends RepaymentTestCase {


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
    //修改配置中心后，暂停几秒，等待配置生效
    ThreadUtil.sleep(sleepTime);
  }


}
