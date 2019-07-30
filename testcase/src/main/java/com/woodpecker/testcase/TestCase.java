package com.woodpecker.testcase;

import com.sword.autotest.framework.listener.ITestListener;
import com.sword.autotest.framework.testcase.BaseTestCase;
import com.woodpecker.WoodpeckerApplication;
import com.woodpecker.service.databuild.DataBuildOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Listeners;


/**
 * 类描述:〈TestCase基类〉
 *
 * @version 1.0
 * @author: xujinjian
 */
@SpringBootTest(classes = {WoodpeckerApplication.class})
@Listeners({ITestListener.class})
public class TestCase extends BaseTestCase {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${testcase.userId}")
  protected String userId;

  @Autowired
  protected DataBuildOrderService dataBuildOrderService;


  public String getUserId() {
    return userId;
  }


  public void setUserId(String userId) {
    this.userId = userId;
  }


}
