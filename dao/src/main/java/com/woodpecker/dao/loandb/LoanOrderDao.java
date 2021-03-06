package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_loan_order表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface LoanOrderDao extends JpaRepository<LoanOrderEntity, Integer> {
  
  List<LoanOrderEntity> findByOrderId(Integer orderId);

  List<LoanOrderEntity> findByUserId(Integer userId);

  Optional<LoanOrderEntity> findById(Integer id);

}
