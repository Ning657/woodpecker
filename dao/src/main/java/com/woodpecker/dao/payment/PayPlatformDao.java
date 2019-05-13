package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.PayPlatformEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈rt_pay_platform表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface PayPlatformDao extends JpaRepository<PayPlatformEntity, Integer> {

  List<PayPlatformEntity> findByCode(String code);

  PayPlatformEntity findByCodeAndVersion(String code, String version);

  List<PayPlatformEntity> findByCodeAndStatus(String code, Byte status);

  PayPlatformEntity findByCodeAndStatusAndVersion(String code, Byte status, String version);

}
