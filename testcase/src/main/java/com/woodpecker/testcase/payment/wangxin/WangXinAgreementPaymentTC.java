package com.woodpecker.testcase.payment.wangxin;

import com.woodpecker.service.config.Account;
import com.woodpecker.service.superdiamond.SuperdiamondService;
import com.woodpecker.testcase.payment.PaymentTestCase;
import com.xujinjian.Commons.Lang.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * 类描述:〈网信协议支付测试用例〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class WangXinAgreementPaymentTC extends PaymentTestCase {

  @Autowired
  private Account account;

  @Autowired
  private SuperdiamondService superdiamondService;


  @Test
  public void loginSuperDiamond() {

    superdiamondService.openSuperdiamond();

    String userName = account.getSdUserCode();
    String password = account.getSdPassword();
    logger.info(account.toString());

    boolean loginResult = superdiamondService.login(userName, password);
    log.info("loginResult=[{}]", loginResult);

    if (loginResult) {
      String projectName = "tp-payment-transaction";
      boolean isExistProject = superdiamondService.search(projectName);
      log.info("isExistProject=[{}]", isExistProject);

      if (isExistProject) {
        String profiles = "development";
        boolean isProjectPage = superdiamondService.gotoProject(projectName, profiles);
        log.info("isProjectPage=[{}]", isProjectPage);

        //superdiamondService.selectModule("MOCK");

        String configKey = "transaction.tran.mock.platforms";
        String configValue = "\"JDAGREEMENTPAY\"";

//        boolean configIsExist = superdiamondService.configIsExist(configKey, configValue, true);
//        log.info("configIsExist=[{}]", configIsExist);

        boolean append = true;
        boolean deleteLastChar = true;
        String separator = ",";
        boolean updateSuccess = superdiamondService
            .updateConfig(configKey, configValue, append, deleteLastChar, separator);
        log.info("updateSuccess=[{}]", updateSuccess);

        ThreadUtil.sleep(3);
      }
    }

  }


}
