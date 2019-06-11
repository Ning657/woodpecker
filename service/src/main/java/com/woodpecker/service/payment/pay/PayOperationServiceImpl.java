package com.woodpecker.service.payment.pay;

import com.woodpecker.dao.payment.PayChannelDao;
import com.woodpecker.dao.payment.PayGroupDao;
import com.woodpecker.dao.payment.PayGroupPlatformDao;
import com.woodpecker.dao.payment.PayPlatformDao;
import com.woodpecker.entity.payment.PayChannelEntity;
import com.woodpecker.entity.payment.PayGroupEntity;
import com.woodpecker.entity.payment.PayGroupPlatformEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class PayOperationServiceImpl implements PayOperationService {

  @Autowired
  private PayChannelDao payChannelDao;

  @Autowired
  private PayPlatformDao payPlatformDao;

  @Autowired
  private PayGroupDao payGroupDao;

  @Autowired
  private PayGroupPlatformDao payGroupPlatformDao;


  /**
   * 方法功能描述: 获取支付渠道信息
   *
   * @param name name
   * @param status status
   * @param version version
   * @return com.woodpecker.entity.payment.PayChannelEntity
   */
  @Override
  public PayChannelEntity getPayChannel(String name, int status, String version) {
    return payChannelDao.findByNameAndStatusAndVersion(name, status, version);
  }


  /**
   * 方法功能描述: 获取支付通道信息
   *
   * @param code code
   * @param status status
   * @param version version
   * @return com.woodpecker.entity.payment.PayPlatformEntity
   */
  @Override
  public PayPlatformEntity getPayPlatform(String code, byte status, String version) {
    return payPlatformDao.findByCodeAndStatusAndVersion(code, status, version);
  }


  /**
   * 方法功能描述: 获取支付组信息
   *
   * @param platformId platformId
   * @param groupName groupName
   * @param version version
   * @return com.woodpecker.entity.payment.PayGroupEntity
   */
  @Override
  public PayGroupEntity getPayGroup(int platformId, String groupName, String version) {
    return payGroupDao.findByPlatformIdAndGroupNameAndVersion(platformId, groupName, version);
  }


  /**
   * 方法功能描述: 获取支付组-支付通道关联关系信息
   *
   * @param version version
   * @param payGroupName payGroupName
   * @param payPlatformName payPlatformName
   * @return com.woodpecker.entity.payment.PayGroupPlatformEntity
   */
  @Override
  public PayGroupPlatformEntity getPayGroupPlatform(String version, String payGroupName,
      String payPlatformName) {
    return payGroupPlatformDao
        .findByVersionAndPayGroupNameAndPayPlatformName(version, payGroupName, payPlatformName);
  }


}
