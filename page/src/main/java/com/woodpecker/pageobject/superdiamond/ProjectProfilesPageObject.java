package com.woodpecker.pageobject.superdiamond;

import com.sword.autotest.framework.annotation.PageObject;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.Commons.Lang.ThreadUtil;
import javax.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.sword.selenium3.PageObjectFactory;

/**
 * 类描述:〈项目配置页面〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@PageObject
public class ProjectProfilesPageObject extends SuperdiamondBasePageObject {

  @PostConstruct
  public void init() {
    PageObjectFactory.getPageObject(super.webDriver, this, super.elementLoadTimeout);
  }

  /**
   * 添加配置按钮
   */
  By addConfigBtn = By.id("addConfig");

  /**
   * 添加Module按钮
   */
  By addModuleBtn = By.id("addModule");

  /**
   * 首页按钮
   */
  @FindBy(xpath = "//a[@href='/superdiamond/index']")
  WebElement indexBtn;


  /**
   * 方法功能描述: 点击添加配置按钮
   *
   * @return void
   */
  public void clickAddConfigBtn() {
    webElementManager.clickUntilDisplayed(addConfigBtn);
  }


  /**
   * 方法功能描述: 点击添加Module按钮
   *
   * @return void
   */
  public void clickAddModuleBtn() {
    webElementManager.clickUntilDisplayed(addModuleBtn);
  }


  /**
   * 方法功能描述: 判断是否进入了配置页面
   *
   * @param profiles Profiles
   * @return boolean
   */
  public boolean isProjectPage(String profiles) {
    String xpath =
        "//a[@href='/superdiamond/index']/following-sibling::b[contains(text(),'" + profiles
            + "')]";
    By nameB = By.xpath(xpath);
    boolean result = false;
    for (int i = 0; i < super.waitElementLoadTime; i++) {
      boolean flag = webElementManager.isDisplayed(nameB);
      if (flag) {
        //直接返回true
        result = true;
        break;
      } else {
        ThreadUtil.sleep();
      }
    }
    return result;
  }


  /**
   * 方法功能描述: 选中指定Module
   *
   * @param module Module
   * @return void
   */
  public void selectModule(String module) {
    webElementManager.selectByVisibleText(By.id("sel-queryModule"), module);
  }


  /**
   * 方法功能描述: 判断当前页是否存在key
   *
   * @param key key
   * @return boolean
   */
  public boolean isExistKey(String key) {
    String xpath = "//tbody/tr/td[@value='" + key + "']";
    By keyTd = By.xpath(xpath);
    boolean result = false;
    for (int i = 0; i < super.waitElementLoadTime; i++) {
      boolean flag = webElementManager.isDisplayed(keyTd);
      if (flag) {
        //直接返回true
        result = true;
        break;
      } else {
        ThreadUtil.sleep();
      }
    }
    return result;
  }


  /**
   * 方法功能描述: 点击更新配置按钮
   *
   * @param key key
   * @return void
   */
  public void clickUpdateConfigBtn(String key) {
    String xpath = "//tbody/tr/td[@value='" + key + "']/following-sibling::td/a[@title='更新']";
    By updateConfigBtn = By.xpath(xpath);
    webElementManager.clickUntilClickable(updateConfigBtn);
  }


  /**
   * 方法功能描述: 获取配置值
   *
   * @param configKey key
   * @return java.lang.String
   */
  public String getConfigValue(String configKey) {
    By configKeyInput = By.id("config-configKey");
    By configValueInput = By.id("config-configValue");
    String value = webElementManager.getValueUntilExist(configKeyInput);
    if (StringUtil.equals(configKey, value)) {
      //读取出内容
      return webElementManager.getValueUntilExist(configValueInput);
    } else {
      return null;
    }
  }


  /**
   * 方法功能描述: 设置配置值
   *
   * @param configValue value
   * @return void
   */
  public void setConfigValue(String configValue) {
    By configValueInput = By.id("config-configValue");
    String oldConfigValue = webElementManager.getValueUntilExist(configValueInput);
    webElementManager.inputUntilDisplayed(configValueInput, configValue);
    waitInputEnd(configValue);
    //修改了配置中心配置，应该做记录，防止配置修改错误，可以回滚
    saveOperLog(OperationTypeEnum.UPDATE, configValue, oldConfigValue);
  }


  /**
   * 方法功能描述: 删除配置
   *
   * @param configKey key
   * @param configValue value
   * @return void
   */
  public void delConfigValue(String configKey, String configValue) {
    By configKeyInput = By.id("config-configKey");
    By configValueInput = By.id("config-configValue");
    String value = webElementManager.getValueUntilExist(configKeyInput);
    if (StringUtil.equals(configKey, value)) {
      //先读取出原内容
      String oldConfigValue = webElementManager.getValueUntilExist(configValueInput);
      log.debug("oldConfigValue=[{}]", oldConfigValue);
      //判断配置值是否存在
      int index = oldConfigValue.indexOf(configValue);
      if (index == -1) {
        log.debug("配置configValue=[{}]不存在，不需要删除", configValue);
        //点击关闭按钮
        clickCloseBtn();
        return;
      }
      String newConfigValue = oldConfigValue.replaceAll(configValue, "");
      log.debug("newConfigValue=[{}]", newConfigValue);
      //输入
      webElementManager.inputUntilDisplayed(configValueInput, newConfigValue);
      waitInputEnd(newConfigValue);
      //修改了配置中心配置，应该做记录，防止配置修改错误，可以回滚
      saveOperLog(OperationTypeEnum.DELETE, newConfigValue, oldConfigValue);
      //点击保存
      clickSaveConfigBtn();
    }
  }


  /**
   * 方法功能描述: 输入配置值(如果添加配置值，已经存在，则不会重复添加)
   *
   * @param configKey key
   * @param configValue value
   * @param append 是否追加
   * @param deleteLastChar 是否删除原内容的最后一个字符(当追加内容时，一般需要删除最后一个字符)
   * @param separator 跟原内容之间的分隔符(当追加内容时，一般需要有个分隔符隔开)
   * @return void
   */
  public void inputConfigValue(String configKey, String configValue, boolean append,
      boolean deleteLastChar, String separator) {
    By configKeyInput = By.id("config-configKey");
    By configValueInput = By.id("config-configValue");
    String value = webElementManager.getValueUntilExist(configKeyInput);
    if (StringUtil.equals(configKey, value)) {
      //先读取出原内容
      String oldConfigValue = webElementManager.getValueUntilExist(configValueInput);
      log.debug("oldConfigValue=[{}]", oldConfigValue);
      //判断配置值是否已经存在
      int index = oldConfigValue.indexOf(configValue);
      if (index >= 0) {
        log.debug("配置configValue=[{}]已经存在，不重复添加", configValue);
        //点击关闭按钮
        clickCloseBtn();
        return;
      }
      if (append) {
        String newConfigValue;
        //是否将配置值添加在最后一个字符的前面
        if (deleteLastChar) {
          //需要将配置值加在最后一个字符的前面
          String str1 = oldConfigValue.substring(0, oldConfigValue.length() - 1);
          String str2 = oldConfigValue
              .substring(oldConfigValue.length() - 1, oldConfigValue.length());
          //拼接新的value
          newConfigValue = str1 + separator + configValue + str2;
        } else {
          //直接将配置值添加在最后
          //拼接新的value
          newConfigValue = oldConfigValue + separator + configValue;
        }
        log.debug("newConfigValue=[{}]", newConfigValue);
        webElementManager.inputUntilDisplayed(configValueInput, newConfigValue);
        waitInputEnd(newConfigValue);
        //修改了配置中心配置，应该做记录，防止配置修改错误，可以回滚
        saveOperLog(OperationTypeEnum.UPDATE, newConfigValue, oldConfigValue);
      } else {
        webElementManager.inputUntilDisplayed(configValueInput, configValue);
        waitInputEnd(configValue);
        //修改了配置中心配置，应该做记录，防止配置修改错误，可以回滚
        saveOperLog(OperationTypeEnum.UPDATE, configValue, oldConfigValue);
      }
      //点击保存
      clickSaveConfigBtn();
    }
  }


  /**
   * 方法功能描述: 点击关闭按钮
   *
   * @return void
   */
  public void clickCloseBtn() {
    String xpath = "//button[@id='saveConfig']/parent::div/button[text()='关闭']";
    try {
      webElementManager.clickUntilClickable(By.xpath(xpath));
    } catch (ElementClickInterceptedException e) {
      ThreadUtil.sleep();
      webElementManager.clickUntilDisplayed(By.xpath(xpath));
    }
  }


  /**
   * 方法功能描述: 点击保存按钮
   *
   * @return void
   */
  public void clickSaveConfigBtn() {
    webElementManager.clickUntilDisplayed(By.id("saveConfig"));
  }


  /**
   * 方法功能描述: 点击保存继续添加按钮
   *
   * @return void
   */
  public void clickSaveConfigExtBtn() {
    webElementManager.clickUntilDisplayed(By.id("saveConfigExt"));
  }


  /**
   * 方法功能描述: 获取下一页按钮
   *
   * @return org.openqa.selenium.By
   */
  private By getNextPageBtn() {
    String xpath = "//div[@id='paginator']/ul/li/a[text()='>']";
    return By.xpath(xpath);
  }


  /**
   * 方法功能描述: 判断是否有下一页
   *
   * @return boolean
   */
  public boolean isExistNextPageBtn() {
    return webElementManager.isDisplayed(getNextPageBtn());
  }


  /**
   * 方法功能描述: 点击下一页
   *
   * @return void
   */
  public void clickNextPageBtn() {
    webElementManager.click(getNextPageBtn());
  }


  /**
   * 方法功能描述: 获取上一页按钮
   *
   * @return org.openqa.selenium.By
   */
  private By getPreviousPageBtn() {
    String xpath = "//div[@id='paginator']/ul/li/a[text()='<']";
    return By.xpath(xpath);
  }


  /**
   * 方法功能描述: 判断是否有上一页
   *
   * @return boolean
   */
  public boolean isExistPreviousPageBtn() {
    return webElementManager.isDisplayed(getPreviousPageBtn());
  }


  /**
   * 方法功能描述: 点击上一页
   *
   * @return void
   */
  public void clickPreviousPageBtn() {
    webElementManager.click(getPreviousPageBtn());
  }


  /**
   * 方法功能描述: 点击首页按钮
   *
   * @return void
   */
  public void clickIndexPageBtn() {
    try {
      webElementManager.clickUntilClickable(indexBtn);
    } catch (ElementClickInterceptedException e) {
      ThreadUtil.sleep();
      webElementManager.clickUntilDisplayed(indexBtn);
    }
  }


}
