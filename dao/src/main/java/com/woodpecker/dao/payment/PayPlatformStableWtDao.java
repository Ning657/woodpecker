package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.PayPlatformStableWtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈rt_pay_platform_stable_wt表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface PayPlatformStableWtDao extends JpaRepository<PayPlatformStableWtEntity, Integer> {

}
