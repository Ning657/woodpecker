package com.woodpecker.framework.aop.limit.impl;

import com.woodpecker.dao.payment.PayPlatformDao;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.framework.aop.limit.PayPlatformService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类描述:〈PayPlatform Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service(value = "payPlatformUtil")
public class PayPlatformServiceImpl implements PayPlatformService {

  @Autowired
  private PayPlatformDao payPlatformDao;


  /**
   * 方法功能描述: 禁用除指定的支付通道外的通道
   *
   * @param payPlatformEntityList List<PayPlatformEntity>
   * @param codes code
   * @return java.util.List<com.woodpecker.entity.payment.PayPlatformEntity>
   */
  @Transactional(value = "paymentTransactionManager")
  @Override
  public List<PayPlatformEntity> banOtherPayPlatformByCode(
      List<PayPlatformEntity> payPlatformEntityList, String[] codes) {
    List<PayPlatformEntity> list = new ArrayList<>();
    List<String> codeList = new ArrayList<>(Arrays.asList(codes));
    //禁用除指定支付通道外的通道
    for (PayPlatformEntity payPlatformEntity : payPlatformEntityList) {
      //判断是否是指定的支付通道
      String code = payPlatformEntity.getCode();
      int status = payPlatformEntity.getStatus();
      if (codeList.contains(code)) {
        //是指定的支付通道
        //判断支付通道是否启用
        if (status == 0) {
          //通道被禁用，把通道启用起来
          PayPlatformEntity bak = new PayPlatformEntity();
          BeanUtils.copyProperties(payPlatformEntity, bak);
          list.add(bak);
          //
          PayPlatformEntity entity = new PayPlatformEntity();
          BeanUtils.copyProperties(payPlatformEntity, entity);
          entity.setStatus((byte) 1);
          payPlatformDao.save(entity);
        }
      } else {
        //不是指定支付通道
        //判断是否被禁用
        if (status == 1) {
          //通道处于启用状态，禁用它
          PayPlatformEntity bak = new PayPlatformEntity();
          BeanUtils.copyProperties(payPlatformEntity, bak);
          list.add(bak);
          //
          PayPlatformEntity entity = new PayPlatformEntity();
          BeanUtils.copyProperties(payPlatformEntity, entity);
          entity.setStatus((byte) 0);
          payPlatformDao.save(entity);
        }
      }
    }
    return list;
    //return delRepetition(list);
  }


  /**
   * 方法功能描述: 禁用指定的支付通道
   *
   * @param payPlatformEntityList List<PayPlatformEntity>
   * @param codes code
   * @return java.util.List<com.woodpecker.entity.payment.PayPlatformEntity>
   */
  @Transactional(value = "paymentTransactionManager")
  @Override
  public List<PayPlatformEntity> banPayPlatformByCode(List<PayPlatformEntity> payPlatformEntityList,
      String[] codes) {
    List<PayPlatformEntity> list = new ArrayList<>();
    List<String> codeList = new ArrayList<>(Arrays.asList(codes));
    //禁用除指定支付通道外的通道
    for (PayPlatformEntity payPlatformEntity : payPlatformEntityList) {
      //判断是否是指定的支付通道
      String code = payPlatformEntity.getCode();
      int status = payPlatformEntity.getStatus();
      if (codeList.contains(code)) {
        //是指定的支付通道
        //判断支付通道是否启用
        if (status == 1) {
          //通道处于启用状态，禁用它
          PayPlatformEntity bak = new PayPlatformEntity();
          BeanUtils.copyProperties(payPlatformEntity, bak);
          list.add(bak);
          //
          PayPlatformEntity entity = new PayPlatformEntity();
          BeanUtils.copyProperties(payPlatformEntity, entity);
          entity.setStatus((byte) 0);
          payPlatformDao.save(entity);
        }
      }
    }
    return list;
    //return delRepetition(list);
  }


  /**
   * 方法功能描述: 还原支付通道状态
   *
   * @param list List<PayPlatformEntity>
   * @return void
   */
  @Transactional(value = "paymentTransactionManager")
  @Override
  public void recoverPayPlatform(List<PayPlatformEntity> list) {
    for (PayPlatformEntity payPlatformEntity : list) {
      payPlatformDao.save(payPlatformEntity);
    }
  }


  /**
   * 方法功能描述: 去重
   *
   * @param list List<PayPlatformEntity>
   * @return java.util.List<com.woodpecker.entity.payment.PayPlatformEntity>
   */
  private List<PayPlatformEntity> delRepetition(List<PayPlatformEntity> list) {
    List<Integer> lists = new ArrayList<>();
    List<PayPlatformEntity> result = new ArrayList<>();
    for (PayPlatformEntity entity : list) {
      int id = entity.getId();
      if (!lists.contains(id)) {
        lists.add(id);
        result.add(entity);
      }
    }
    return result;
  }


}
