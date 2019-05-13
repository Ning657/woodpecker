package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.PayGroupEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈rt_pay_group表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface PayGroupDao extends JpaRepository<PayGroupEntity, Integer> {

  List<PayGroupEntity> findByPlatformId(Integer platformId);

}
