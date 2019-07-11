package com.woodpecker.framework.aop.limit;

import com.woodpecker.entity.payment.PayPlatformEntity;
import java.util.List;

/**
 * 接口描述:〈PayPlatform Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface PayPlatformService {

  
  /**
   * 方法功能描述: 禁用除指定的支付通道外的通道
   *
   * @param payPlatformEntityList List<PayPlatformEntity>
   * @param codes code
   * @return java.util.List<com.woodpecker.entity.payment.PayPlatformEntity>
   */
  List<PayPlatformEntity> banOtherPayPlatformByCode(List<PayPlatformEntity> payPlatformEntityList,
      String[] codes);


  /**
   * 方法功能描述: 禁用指定的支付通道
   *
   * @param payPlatformEntityList List<PayPlatformEntity>
   * @param codes code
   * @return java.util.List<com.woodpecker.entity.payment.PayPlatformEntity>
   */
  List<PayPlatformEntity> banPayPlatformByCode(List<PayPlatformEntity> payPlatformEntityList,
      String[] codes);


  /**
   * 方法功能描述: 还原支付通道状态
   *
   * @param list List<PayPlatformEntity>
   * @return void
   */
  void recoverPayPlatform(List<PayPlatformEntity> list);


}
