package com.woodpecker.service.payment.pay;

import com.woodpecker.entity.payment.PayChannelEntity;
import com.woodpecker.entity.payment.PayGroupEntity;
import com.woodpecker.entity.payment.PayGroupPlatformEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;

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


}
