package com.woodpecker.service.payment.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

  static String transaction = "TP-PAYMENT-TRANSACTION:MIN#10*";

  static String trade = "TP-PAYMENT-TRADE:MIN#10*";

  static String deduct = "LOAN_DEDUCT:MIN#10*";

  static String router = "ROUTER_PLATFORM_*";

  static String errorCount = "ERROR_COUNT_*";

  static String userFailedCount = "*_*_USER_FAILED_COUNT_*";

  static List<String> patterns = new ArrayList<>();

  static {
    patterns.add(transaction);
    patterns.add(trade);
    patterns.add(deduct);
    patterns.add(router);
    patterns.add(errorCount);
    patterns.add(userFailedCount);
  }


  /**
   * 方法功能描述: 删除会影响支付的Redis缓存
   *
   * @return void
   */
  public void delete() {
    for (String pattern : patterns) {
      try {
        //模糊获取出key
        Set<String> keys = redisCacheService.getKeys(pattern);
        //遍历模糊匹配到的所有key
        for (String key : keys) {
          try {
            //删除key
            redisCacheService.delete(key);
          } catch (Throwable e) {
            logger.error(e.getMessage(), e);
          }
        }
      } catch (Throwable e) {
        logger.error(e.getMessage(), e);
      }
    }
  }


}
