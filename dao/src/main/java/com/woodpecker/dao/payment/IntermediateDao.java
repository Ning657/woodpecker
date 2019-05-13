package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.IntermediateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_tp_intermediate表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface IntermediateDao extends JpaRepository<IntermediateEntity, Long> {

  IntermediateEntity findById(Long id);

  IntermediateEntity findByOrderNo(String orderNo);

}
