package com.woodpecker.service.payment.pay;

import com.woodpecker.dao.payment.PayChannelDao;
import com.woodpecker.dao.payment.PayGroupDao;
import com.woodpecker.dao.payment.PayGroupPlatformDao;
import com.woodpecker.dao.payment.PayPlatformDao;
import com.woodpecker.entity.payment.PayChannelEntity;
import com.woodpecker.entity.payment.PayGroupEntity;
import com.woodpecker.entity.payment.PayGroupPlatformEntity;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.xujinjian.Commons.Lang.StringUtil;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  /**
   * 方法功能描述: 禁用除指定的支付通道外的通道
   *
   * @param payPlatformEntityList List<PayPlatformEntity>
   * @param codes code
   * @return void
   */
  @Transactional
  @Override
  public void banOtherPayPlatformByCode(List<PayPlatformEntity> payPlatformEntityList,
      String[] codes) {
    //禁用除指定支付通道外的通道
    for (PayPlatformEntity payPlatformEntity : payPlatformEntityList) {
      for (String code : codes) {
        //判断是否是指定的支付通道
        String c = payPlatformEntity.getCode();
        int status = payPlatformEntity.getStatus();
        if (StringUtil.equals(c, code)) {
          //判断支付通道是否启用
          if (status == 0) {
            //通道被禁用，把通道启用起来
            PayPlatformEntity entity = new PayPlatformEntity();
            BeanUtils.copyProperties(payPlatformEntity, entity);
            entity.setStatus((byte) 1);
            payPlatformDao.save(entity);
          }
        } else {
          //是指定支付通道外的通道
          //判断是否被禁用
          if (status == 1) {
            //通道处于启用状态，禁用它
            PayPlatformEntity entity = new PayPlatformEntity();
            BeanUtils.copyProperties(payPlatformEntity, entity);
            entity.setStatus((byte) 0);
            payPlatformDao.save(entity);
          }
        }
      }
    }
  }


  /**
   * 方法功能描述: 禁用指定的支付通道
   *
   * @param payPlatformEntityList List<PayPlatformEntity>
   * @param codes code
   * @return void
   */
  @Transactional
  @Override
  public void banPayPlatformByCode(List<PayPlatformEntity> payPlatformEntityList, String[] codes) {
    //禁用除指定支付通道外的通道
    for (PayPlatformEntity payPlatformEntity : payPlatformEntityList) {
      for (String code : codes) {
        //判断是否是指定的支付通道
        String c = payPlatformEntity.getCode();
        int status = payPlatformEntity.getStatus();
        if (StringUtil.equals(c, code)) {
          //判断支付通道是否启用
          if (status == 1) {
            //通道处于启用状态，禁用它
            PayPlatformEntity entity = new PayPlatformEntity();
            BeanUtils.copyProperties(payPlatformEntity, entity);
            entity.setStatus((byte) 0);
            payPlatformDao.save(entity);
          }
        }
      }
    }
  }


}
