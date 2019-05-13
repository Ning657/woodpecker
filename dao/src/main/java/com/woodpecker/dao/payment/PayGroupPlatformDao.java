package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.PayGroupPlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈rt_pay_group_platform表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface PayGroupPlatformDao extends JpaRepository<PayGroupPlatformEntity, Integer> {

}
