package com.woodpecker.testcase.payment.bindcard;

import com.woodpecker.framework.bind.AuthService;
import com.woodpecker.service.payment.bind.check.VerifyBindData;
import com.woodpecker.service.pub.DataAnalysisService;
import com.woodpecker.service.pub.user.UserService;
import com.woodpecker.service.superdiamond.Superdiamond;
import com.woodpecker.testcase.TestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈绑卡TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class BindTestCase extends TestCase {

  @Autowired
  protected Superdiamond superdiamond;

  @Autowired
  protected AuthService authService;

  @Autowired
  protected UserService userService;

  @Autowired
  protected DataAnalysisService dataAnalysisService;

  @Autowired
  protected VerifyBindData verifyBindData;
  
}
