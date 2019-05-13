package com.woodpecker.pageobject;

import com.sword.autotest.framework.annotation.PageObject;
import com.sword.autotest.framework.object.pageobject.BasePageObject;
import com.xujinjian.Commons.Lang.ThreadUtil;
import org.springframework.beans.factory.annotation.Value;

/**
 * 类描述:〈页面对象〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@PageObject
public class Page extends BasePageObject {

  /**
   * 元素加载超时时间
   */
  @Value("${autotest.ui.elementLoadTimeout:15}")
  public int elementLoadTimeout;

  /**
   * 自动化运行时，一些重要操作日志，会记录到这个文件中
   */
  @Value("${autotest.infoOperationLogFilePath}")
  public String infoOperationLogFilePath;

  /**
   * 编码
   */
  @Value("${autotest.default-encoding:UTF-8}")
  public String encoding;

  /**
   * 等待元素加载时间，默认3秒
   */
  protected int waitElementLoadTime = 3;


  /**
   * 方法功能描述: 等待页面加载完成
   *
   * @return void
   */
  public void waitPageLoad() {
    super.webElementManager.waitPageLoadByJS();
  }


  /**
   * 方法功能描述: 等待文本输入结束
   *
   * @param text 需要输入的字符串
   * @return void
   */
  protected void waitInputEnd(String text) {
    if (null != text) {
      if (text.length() <= 20) {
        ThreadUtil.sleep();
      } else if (text.length() > 20 && text.length() <= 40) {
        ThreadUtil.sleep(2);
      } else if (text.length() > 40 && text.length() <= 60) {
        ThreadUtil.sleep(3);
      } else if (text.length() > 60 && text.length() <= 80) {
        ThreadUtil.sleep(4);
      } else if (text.length() > 80 && text.length() <= 100) {
        ThreadUtil.sleep(5);
      } else if (text.length() > 100 && text.length() <= 120) {
        ThreadUtil.sleep(6);
      } else if (text.length() > 120 && text.length() <= 140) {
        ThreadUtil.sleep(7);
      } else {
        ThreadUtil.sleep(8);
      }
    }
  }


}
