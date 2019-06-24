package com.woodpecker.service.payment.pay;

import com.woodpecker.entity.payment.PayChannelEntity;
import com.woodpecker.entity.payment.PayGroupEntity;
import com.woodpecker.entity.payment.PayGroupPlatformEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import java.util.List;

/**
 * 接口描述:〈〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface PayOperationService {


  /**
   * 方法功能描述: 获取支付渠道信息
   *
   * @param name name
   * @param status status
   * @param version version
   * @return com.woodpecker.entity.payment.PayChannelEntity
   */
  PayChannelEntity getPayChannel(String name, int status, String version);


  /**
   * 方法功能描述: 获取支付通道信息
   *
   * @param code code
   * @param status status
   * @param version version
   * @return com.woodpecker.entity.payment.PayPlatformEntity
   */
  PayPlatformEntity getPayPlatform(String code, byte status, String version);


  /**
   * 方法功能描述: 获取支付组信息
   *
   * @param platformId platformId
   * @param groupName groupName
   * @param version version
   * @return com.woodpecker.entity.payment.PayGroupEntity
   */
  PayGroupEntity getPayGroup(int platformId, String groupName, String version);


  /**
   * 方法功能描述: 获取支付组-支付通道关联关系信息
   *
   * @param version version
   * @param payGroupName payGroupName
   * @param payPlatformName payPlatformName
   * @return com.woodpecker.entity.payment.PayGroupPlatformEntity
   */
  PayGroupPlatformEntity getPayGroupPlatform(String version, String payGroupName,
      String payPlatformName);


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
