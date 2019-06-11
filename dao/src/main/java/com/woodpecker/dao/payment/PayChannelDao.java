package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.PayChannelEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 类描述:〈rt_pay_channel表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface PayChannelDao extends JpaRepository<PayChannelEntity, Integer> {


  PayChannelEntity findByNameAndStatusAndVersion(String name, int status, String version);


  List<PayChannelEntity> findByNameAndVersion(String name, String version);


  List<PayChannelEntity> findByName(String name);


  List<PayChannelEntity> findByVersion(String version);


  List<PayChannelEntity> findByCode(String code);


  List<PayChannelEntity> findByCodeAndVersion(String code, String version);


  PayChannelEntity findByCodeAndStatusAndVersion(String code, int status, String version);


}
