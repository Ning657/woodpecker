package com.woodpecker.service.superdiamond;

import com.woodpecker.pageobject.superdiamond.LoginPageObject;
import com.woodpecker.pageobject.superdiamond.ProjectProfilesPageObject;
import com.woodpecker.pageobject.superdiamond.ProjectSearchPageObject;
import com.woodpecker.service.WebUIService;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.Commons.Lang.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈superdiamond相关Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class SuperdiamondServiceImpl extends WebUIService implements SuperdiamondService {

  @Autowired
  private LoginPageObject loginPageObject;

  @Autowired
  private ProjectSearchPageObject projectSearchPageObject;

  @Autowired
  private ProjectProfilesPageObject projectProfilesPageObject;

  /**
   * Superdiamond登录页面
   */
  private String loginUrl = "/superdiamond";

  /**
   * Superdiamond首页
   */
  private String indexUrl = "/superdiamond/index";


  /**
   * 方法功能描述: 打开Superdiamond登录页面
   *
   * @return void
   */
  @Override
  public void openSuperdiamond() {
    String url = host.getSuperdiamondHost() + loginUrl;
    logger.debug("打开Superdiamond登录页面url=[{}]", url);
    webElementManager.open(url);
    ThreadUtil.sleep(waitPageLoadTime);
    webElementManager.waitPageLoadByJS();
  }


  /**
   * 方法功能描述: 登录superdiamond
   *
   * @param userName 用户名
   * @param password 密码
   * @return boolean
   */
  @Override
  public boolean login(String userName, String password) {
    boolean loginResult = false;
    logger.debug("登录superdiamond的userName=[{}];password=[{}]", userName, password);
    if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
      logger.error("登录superdiamond时，账号或密码不能为空!");
      return false;
    }
    //判断当前是否在登录页面
    boolean isLoginPage = loginPageObject.isLoginPage();
    if (!isLoginPage) {
      logger.error("当前页面不是superdiamond登录页面isLoginPage=[{}]", isLoginPage);
      return false;
    }
    //输入用户名和密码
    loginPageObject.inputUserName(userName);
    loginPageObject.inputPassword(password);
    //登录
    loginPageObject.clickLoginBtn();
    //判断是否登录成功
    boolean isLoginFail = loginPageObject.isLoginFail();
    if (isLoginFail) {
      logger.error("isLoginFail=[{}]", isLoginFail);
      //获取登录失败信息
      String loginFailMsg = loginPageObject.getLoginFailMsg();
      logger.error("登录失败 --> {}", loginFailMsg);
      loginResult = false;
    } else {
      //判断项目搜索页面的注销超链接有没有显示出来，如果真正登录成功的话，是会显示的
      boolean isDisplayLogoutHref = projectSearchPageObject.isDisplayLogoutHref();
      if (!isDisplayLogoutHref) {
        //没有登录成功
        loginResult = false;
        logger.error("登录页面检测到登录成功，但未在项目搜索页面检测到注销按钮isDisplayLogoutHref=[{}]", isDisplayLogoutHref);
      } else {
        //真正登录成功
        loginResult = true;
        logger.debug("登录superdiamond成功");
      }
    }
    return loginResult;
  }


  /**
   * 方法功能描述: 搜索项目，如果当前不在首页，则会自行进入首页
   *
   * @param projectName 项目名称
   * @return boolean
   */
  @Override
  public boolean search(String projectName) {
    boolean searchResult = false;
    logger.debug("搜索项目projectName=[{}]", projectName);
    if (StringUtil.isEmpty(projectName)) {
      logger.error("搜索的项目名称不能为空!");
      return false;
    }
    //判断是否在首页
    boolean isIndexPage = projectSearchPageObject.isIndexPage();
    logger.debug("isIndexPage=[{}]", isIndexPage);
    if (!isIndexPage) {
      //当前不在首页
      //则自行进入首页
      String url = host.getSuperdiamondHost() + indexUrl;
      logger.debug("当前不在首页，自行进入首页url=[{}]", url);
      projectSearchPageObject.gotoIndexPage(url);
    }
    //输入需要搜索的项目
    projectSearchPageObject.inputProjectName(projectName);
    //点击搜索按钮
    projectSearchPageObject.clickSearchBtn();
    //判断是否搜索到此项目
    boolean flag = projectSearchPageObject.isExistProject(projectName);
    logger.debug("搜索项目[{}]结果flag=[{}]", projectName, flag);
    if (flag) {
      //找到项目
      searchResult = true;
      logger.debug("搜索到项目projectName=[{}]", projectName);
    } else {
      //没有找到项目
      searchResult = false;
      logger.error("没有搜索到项目projectName=[{}]", projectName);
    }
    return searchResult;
  }


  /**
   * 方法功能描述: 进入项目配置页面
   *
   * @param projectName 项目名称
   * @param profiles Profiles
   * @return boolean
   */
  @Override
  public boolean gotoProject(String projectName, String profiles) {
    boolean result = false;
    //判断当前是否有此项目
    boolean flag = projectSearchPageObject.isExistProject(projectName);
    if (!flag) {
      logger.error("当前页面上不存在项目projectName=[{}]", projectName);
      return false;
    }
    //点击Profiles
    projectSearchPageObject.clickProfiles(projectName, profiles);
    //判断是否进入了配置页面
    boolean isProjectPage = projectProfilesPageObject.isProjectPage(profiles);
    logger.debug("判断是否进入了配置页面isProjectPage=[{}]", isProjectPage);
    if (isProjectPage) {
      //进入了项目配置页面
      result = true;
      logger.debug("进入了项目projectName=[{}]的profiles=[{}]配置页面", projectName, profiles);
    } else {
      //没有进入项目配置页面
      result = false;
      logger.error("没有进入项目projectName=[{}]的profiles=[{}]配置页面", projectName, profiles);
    }
    return result;
  }


  /**
   * 方法功能描述: 选择Module
   *
   * @param module Module
   * @return void
   */
  @Override
  public void selectModule(String module) {
    logger.debug("选择Module=[{}]", module);
    if (StringUtil.isEmpty(module)) {
      logger.error("参数module=[{}]为空", module);
      return;
    }
    //选择Module
    projectProfilesPageObject.selectModule(module);
  }


  /**
   * 方法功能描述: 更新配置
   *
   * @param configKey key
   * @param configValue value
   * @param append 是否追加
   * @param deleteLastChar 是否删除原内容的最后一个字符(当追加内容时，一般需要删除最后一个字符)
   * @param separator 跟原内容之间的分隔符(当追加内容时，一般需要有个分隔符隔开)
   * @return boolean
   */
  @Override
  public boolean updateConfig(String configKey, String configValue, boolean append,
      boolean deleteLastChar, String separator) {
    boolean result = false;
    logger.debug("更新配置configKey=[{}];configValue=[{}]", configKey, configValue);
    if (StringUtil.isEmpty(configKey) || StringUtil.isEmpty(configValue)) {
      logger.error("configKey或configValue不能为空");
      return result;
    }
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
      return result;
    }
    //点击修改按钮
    projectProfilesPageObject.clickUpdateConfigBtn(configKey);
    //输入配置值
    projectProfilesPageObject
        .inputConfigValue(configKey, configValue, append, deleteLastChar, separator);
    result = true;
    //返回首页
    gotoIndexPage();
    return result;
  }


  /**
   * 方法功能描述: 判断配置值是否存在
   *
   * @param configKey key
   * @param configValue value
   * @param ignoreCase 是否忽略大小写
   * @return boolean
   */
  @Override
  public boolean configIsExist(String configKey, String configValue, boolean ignoreCase) {
    boolean result = false;
    logger.debug("判断配置值是否存在configKey=[{}];configValue=[{}]", configKey, configValue);
    if (StringUtil.isEmpty(configKey) || StringUtil.isEmpty(configValue)) {
      logger.error("configKey或configValue不能为空");
      return result;
    }
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
      return result;
    }
    //点击修改按钮
    projectProfilesPageObject.clickUpdateConfigBtn(configKey);
    //获取配置值
    String value = projectProfilesPageObject.getConfigValue(configKey);
    logger.debug("读取到的配置值为[{}]", value);
    if (null != value) {
      //是否忽略大小写
      if (ignoreCase) {
        //判断值是否存在
        result = StringUtil.containsIgnoreCase(value, configValue);
      } else {
        //判断值是否存在
        result = StringUtil.contains(value, configValue);
      }
    }
    //点击关闭按钮
    projectProfilesPageObject.clickCloseBtn();
    //返回首页
    gotoIndexPage();
    return result;
  }


  /**
   * 方法功能描述: 返回首页
   *
   * @return void
   */
  @Override
  public void gotoIndexPage() {
    //点击首页按钮
    projectProfilesPageObject.clickIndexPageBtn();
  }


}
