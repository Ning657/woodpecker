package com.woodpecker.service.payment.pay.platform;

import com.woodpecker.entity.payment.PayPlatformEntity;

/**
 * 接口描述:〈rt_pay_platform表Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface PayPlatformService {


  /**
   * 方法功能描述: 获取PayPlatformEntity
   *
   * @param code code
   * @param version version
   * @return com.woodpecker.entity.payment.PayPlatformEntity
   */
  PayPlatformEntity getPayPlatform(String code, String version);


}
