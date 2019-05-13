package com.woodpecker.pageobject.superdiamond;

import com.sword.autotest.framework.annotation.PageObject;
import com.woodpecker.pageobject.Page;
import com.xujinjian.Commons.IO.FileUtil;
import com.xujinjian.Commons.Lang.DateUtil;
import java.io.File;
import java.io.IOException;

/**
 * 类描述:〈superdiamond〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@PageObject
public class SuperdiamondBasePageObject extends Page {


  public void saveOperLog(OperationTypeEnum operationTypeEnum, String newConfig, String oldConfig) {
    StringBuilder buffer = new StringBuilder();
    buffer.append("【时间：").append(DateUtil.getNowTime(DateUtil.FORMAT_FULL)).append("】 - ");
    switch (operationTypeEnum) {
      case ADD:
        buffer.append("【配置中心】 - 【添加配置】 -->");
        break;
      case DELETE:
        buffer.append("【配置中心】 - 【删除配置】 -->");
        break;
      case UPDATE:
        buffer.append("【配置中心】 - 【更新配置】 -->");
        break;
    }
    buffer.append(System.getProperty("line.separator"));
    buffer.append("【新配置为:").append(newConfig).append("】");
    buffer.append(System.getProperty("line.separator"));
    buffer.append("【旧配置为:").append(oldConfig).append("】");
    buffer.append(System.getProperty("line.separator"));
    buffer.append("--------------------------------------------");
    buffer.append(System.getProperty("line.separator"));
    try {
      FileUtil
          .writeStringToFile(new File(infoOperationLogFilePath), buffer.toString(), encoding, true);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }


}
