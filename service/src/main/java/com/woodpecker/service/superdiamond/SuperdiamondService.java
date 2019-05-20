package com.woodpecker.service.superdiamond;

/**
 * 接口描述:〈superdiamond相关Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface SuperdiamondService {


  /**
   * 方法功能描述: 打开Superdiamond登录页面
   *
   * @return void
   */
  void openSuperdiamond();


  /**
   * 方法功能描述: 登录superdiamond
   *
   * @param userName 用户名
   * @param password 密码
   * @return boolean
   */
  boolean login(String userName, String password);


  /**
   * 方法功能描述: 搜索项目，如果当前不在首页，则会自行进入首页
   *
   * @param projectName 项目名称
   * @return boolean
   */
  boolean search(String projectName);


  /**
   * 方法功能描述: 进入项目配置页面
   *
   * @param projectName 项目名称
   * @param profiles Profiles
   * @return boolean
   */
  boolean gotoProject(String projectName, String profiles);


  /**
   * 方法功能描述: 选择Module
   *
   * @param module Module
   * @return void
   */
  void selectModule(String module);


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
  boolean updateConfig(String configKey, String configValue, boolean append,
      boolean deleteLastChar, String separator);


  /**
   * 方法功能描述: 判断配置值是否存在
   *
   * @param configKey key
   * @param configValue value
   * @param ignoreCase 是否忽略大小写
   * @return boolean
   */
  boolean configIsExist(String configKey, String configValue, boolean ignoreCase);


  /**
   * 方法功能描述: 返回首页
   *
   * @return void
   */
  void gotoIndexPage();


}
