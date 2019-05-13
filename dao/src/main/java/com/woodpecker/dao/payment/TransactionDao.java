package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.TransactionEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_tp_transaction表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface TransactionDao extends JpaRepository<TransactionEntity, Long> {

  TransactionEntity findById(Long id);

  TransactionEntity findByOrderNo(String orderNo);

  List<TransactionEntity> findByUserId(Long userId);

}
