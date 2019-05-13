package com.woodpecker.pageobject.superdiamond;

import com.sword.autotest.framework.annotation.PageObject;
import com.xujinjian.Commons.Lang.ThreadUtil;
import javax.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.sword.selenium3.PageObjectFactory;

/**
 * 类描述:〈登录页面〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@PageObject
public class LoginPageObject extends SuperdiamondBasePageObject {

  @PostConstruct
  public void init() {
    PageObjectFactory.getPageObject(super.webDriver, this, super.elementLoadTimeout);
  }


  /**
   * 用户名输入框
   */
  @FindBy(name = "userCode")
  WebElement userNameInput;

  /**
   * 密码输入框
   */
  @FindBy(name = "password")
  WebElement passwordInput;

  /**
   * 登录按钮
   */
  @FindBy(xpath = "//button[@type='submit']")
  WebElement loginBtn;

  /**
   * 重置按钮
   */
  @FindBy(xpath = "//button[@type='reset']")
  WebElement resetBtn;

  /**
   * 错误提示
   */
  By errorTip = By
      .xpath("//form[@action='/superdiamond/login']//div[@class='alert alert-error clearfix']");


  /**
   * 方法功能描述: 输入用户名
   *
   * @param userName 用户名
   * @return void
   */
  public void inputUserName(String userName) {
    webElementManager.inputUntilEnabled(userNameInput, userName);
  }


  /**
   * 方法功能描述: 输入密码
   *
   * @param password 密码
   * @return void
   */
  public void inputPassword(String password) {
    webElementManager.inputUntilEnabled(passwordInput, password);
  }


  /**
   * 方法功能描述: 点击登录按钮
   *
   * @return void
   */
  public void clickLoginBtn() {
    webElementManager.clickUntilEnabled(loginBtn);
  }


  /**
   * 方法功能描述: 点击重置按钮
   *
   * @return void
   */
  public void clickResetBtn() {
    webElementManager.clickUntilEnabled(resetBtn);
  }


  /**
   * 方法功能描述: 判断是否显示登录失败的Tip
   *
   * @return boolean
   */
  public boolean isLoginFail() {
    boolean result = false;
    for (int i = 0; i < super.waitElementLoadTime; i++) {
      boolean flag = webElementManager.isDisplayed(errorTip);
      if (flag) {
        //登录失败信息已显示，直接返回true
        result = true;
        break;
      } else {
        ThreadUtil.sleep();
      }
    }
    return result;
  }


  /**
   * 方法功能描述: 获取登录失败信息
   *
   * @return java.lang.String
   */
  public String getLoginFailMsg() {
    return webElementManager.getTextUntilDisplay(errorTip);
  }


  /**
   * 方法功能描述: 判断当前是否是登录页面
   *
   * @return boolean
   */
  public boolean isLoginPage() {
    return webElementManager.isDisplayed(By.xpath("//button[@type='submit']"));
  }


}
