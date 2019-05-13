package com.woodpecker.service;

import com.woodpecker.service.config.Host;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sword.selenium3.WebElementManager;

/**
 * 类描述:〈WEB-UI相关的Service基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class WebUIService {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired(required = false)
  protected WebDriver webDriver;

  @Autowired(required = false)
  protected WebElementManager webElementManager;

//  @Autowired
//  protected Account account;

  @Autowired
  protected Host host;

  /**
   * 等待页面加载时间(秒)
   */
  protected int waitPageLoadTime = 1;

}
