package com.woodpecker.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈账号信息〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
public class Account {

  @Value("${superdiamond.account.userCode:}")
  public String sdUserCode;

  @Value("${superdiamond.account.password}")
  public String sdPassword;

  public String getSdUserCode() {
    return sdUserCode;
  }

  public void setSdUserCode(String sdUserCode) {
    this.sdUserCode = sdUserCode;
  }

  public String getSdPassword() {
    return sdPassword;
  }

  public void setSdPassword(String sdPassword) {
    this.sdPassword = sdPassword;
  }

  @Override
  public String toString() {
    return "Account{" +
        "sdUserCode='" + sdUserCode + '\'' +
        ", sdPassword='" + sdPassword + '\'' +
        '}';
  }
  
}
