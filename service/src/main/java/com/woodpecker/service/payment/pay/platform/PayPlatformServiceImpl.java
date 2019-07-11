package com.woodpecker.service.payment.pay.platform;

import com.woodpecker.dao.payment.PayPlatformDao;
import com.woodpecker.entity.payment.PayPlatformEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈rt_pay_platform表Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@Primary
public class PayPlatformServiceImpl implements PayPlatformService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PayPlatformDao payPlatformDao;


  /**
   * 方法功能描述: 获取PayPlatformEntity
   *
   * @param code code
   * @param version version
   * @return com.woodpecker.entity.payment.PayPlatformEntity
   */
  @Override
  public PayPlatformEntity getPayPlatform(String code, String version) {
    PayPlatformEntity payPlatformEntity = payPlatformDao.findByCodeAndVersion(code, version);
    if (null == payPlatformEntity) {
      logger.error("rt_pay_platform表不存在code=[{}],version=[{}]的记录", code, version);
      return null;
    }
    return payPlatformEntity;
  }


}
