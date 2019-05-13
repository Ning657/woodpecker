package com.woodpecker.service.payment.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈清除Redis缓存工厂〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
public class RedisCacheFactory {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private RedisCacheService redisCacheService;

  private final String transaction = "TP-PAYMENT-TRANSACTION:MIN#10*";

  private final String trade = "TP-PAYMENT-TRADE:MIN#10*";

  private final String deduct = "LOAN_DEDUCT:MIN#10*";

  private final String router = "ROUTER_PLATFORM_*";

  private final String errorCount = "ERROR_COUNT_*";

  private final String userFailedCount = "*_*_USER_FAILED_COUNT_*";


  /**
   * 方法功能描述: 删除会影响支付的Redis缓存
   *
   * @return void
   */
  public void delete() {
    if (redisCacheService.hasCache(transaction)) {
      redisCacheService.deleteCache(transaction);
    }
    if (redisCacheService.hasCache(trade)) {
      redisCacheService.deleteCache(trade);
    }
    if (redisCacheService.hasCache(deduct)) {
      redisCacheService.deleteCache(deduct);
    }
    if (redisCacheService.hasCache(router)) {
      redisCacheService.deleteCache(router);
    }
    if (redisCacheService.hasCache(errorCount)) {
      redisCacheService.deleteCache(errorCount);
    }
    if (redisCacheService.hasCache(userFailedCount)) {
      redisCacheService.deleteCache(userFailedCount);
    }
    // redisCacheService.deleteCache(transaction, trade, deduct, router, errorCount, userFailedCount);
  }


  public String getTransaction() {
    return transaction;
  }

  public String getTrade() {
    return trade;
  }

  public String getDeduct() {
    return deduct;
  }

  public String getRouter() {
    return router;
  }

  public String getErrorCount() {
    return errorCount;
  }

  public String getUserFailedCount() {
    return userFailedCount;
  }

}
