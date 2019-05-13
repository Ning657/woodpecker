package com.woodpecker.pageobject.superdiamond;

import com.sword.autotest.framework.annotation.PageObject;
import com.xujinjian.Commons.Lang.ThreadUtil;
import javax.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.sword.selenium3.PageObjectFactory;

/**
 * 类描述:〈项目搜索页面〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@PageObject
public class ProjectSearchPageObject extends SuperdiamondBasePageObject {

  @PostConstruct
  public void init() {
    PageObjectFactory.getPageObject(super.webDriver, this, super.elementLoadTimeout);
  }


  /**
   * 项目搜索输入框
   */
  @FindBy(id = "project_search")
  WebElement projectSearchInput;

  /**
   * 搜索按钮
   */
  @FindBy(xpath = "//input[@type='button' and @value='搜索' and @onclick='search()']")
  WebElement searchBtn;

  /**
   * 注销链接
   */
  By logoutHref = By.xpath("//a[@href='/superdiamond/logout']");


  /**
   * 方法功能描述: 输入项目名称
   *
   * @param projectName 项目名
   * @return void
   */
  public void inputProjectName(String projectName) {
    webElementManager.inputUntilEnabled(projectSearchInput, projectName);
  }


  /**
   * 方法功能描述: 判断当前页面是否是首页
   *
   * @return boolean
   */
  public boolean isIndexPage() {
    boolean result = false;
    for (int i = 0; i < super.waitElementLoadTime; i++) {
      boolean flag = webElementManager.isDisplayed(By.id("project_search"));
      if (flag) {
        //输入项目编码Input已显示，直接返回true
        result = true;
        break;
      } else {
        ThreadUtil.sleep();
      }
    }
    return result;
  }


  /**
   * 方法功能描述: 进入首页
   *
   * @param url 首页url(需要域名)
   * @return void
   */
  public void gotoIndexPage(String url) {
    webElementManager.open(url);
  }


  /**
   * 方法功能描述: 点击搜索按钮
   *
   * @return void
   */
  public void clickSearchBtn() {
    webElementManager.clickUntilEnabled(searchBtn);
  }


  /**
   * 方法功能描述: 判断注销超链接是否显示
   *
   * @return boolean
   */
  public boolean isDisplayLogoutHref() {
    boolean result = false;
    for (int i = 0; i < super.waitElementLoadTime; i++) {
      boolean flag = webElementManager.isDisplayed(logoutHref);
      if (flag) {
        //注销超链接已显示，直接返回true
        result = true;
        break;
      } else {
        ThreadUtil.sleep();
      }
    }
    return result;
  }


  /**
   * 方法功能描述: 判断搜索结果中是否有这个项目
   *
   * @param projectName 项目名称
   * @return boolean
   */
  public boolean isExistProject(String projectName) {
    boolean result = false;
    try {
      String xpath = "//tbody/tr/td[normalize-space(text())='" + projectName + "']";
      By projectLi = By.xpath(xpath);
      WebElement proLi = webElementManager.getElementUntilDisplay(projectLi);
      if (null != proLi) {
        result = true;
      }
    } catch (Throwable e) {
      result = false;
    }
    return result;
  }


  /**
   * 方法功能描述: 点击指定项目的指定Profiles
   *
   * @param projectName 项目名称
   * @param profiles Profiles
   * @return void
   */
  public void clickProfiles(String projectName, String profiles) {
    String xpath = "//tbody/tr/td[normalize-space(text())='" + projectName
        + "']/parent::tr/td/a[normalize-space(text())='" + profiles + "']";
    By projectProfiles = By.xpath(xpath);
    webElementManager.clickUntilDisplayed(projectProfiles);
  }


}
