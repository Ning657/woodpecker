package com.woodpecker.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈Host〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
public class Host {

  @Value("${databuild.host:}")
  public String databuildHost;

  @Value("${payment.trade.host:}")
  public String paymentTradeHost;

  @Value("${loan-clearing.new.host:}")
  public String loanClearingNewHost;

  @Value("${loan-clearing.old.host:}")
  public String loanClearingOldHost;

  @Value("${superdiamond.host:}")
  public String superdiamondHost;

  @Value("${mq.host:}")
  public String mqHost;


  public String getDatabuildHost() {
    return databuildHost;
  }

  public void setDatabuildHost(String databuildHost) {
    this.databuildHost = databuildHost;
  }


  public String getPaymentTradeHost() {
    return paymentTradeHost;
  }

  public void setPaymentTradeHost(String paymentTradeHost) {
    this.paymentTradeHost = paymentTradeHost;
  }

  public String getLoanClearingNewHost() {
    return loanClearingNewHost;
  }

  public void setLoanClearingNewHost(String loanClearingNewHost) {
    this.loanClearingNewHost = loanClearingNewHost;
  }

  public String getLoanClearingOldHost() {
    return loanClearingOldHost;
  }

  public void setLoanClearingOldHost(String loanClearingOldHost) {
    this.loanClearingOldHost = loanClearingOldHost;
  }

  public String getSuperdiamondHost() {
    return superdiamondHost;
  }

  public void setSuperdiamondHost(String superdiamondHost) {
    this.superdiamondHost = superdiamondHost;
  }

  public String getMqHost() {
    return mqHost;
  }

  public void setMqHost(String mqHost) {
    this.mqHost = mqHost;
  }
  
}
