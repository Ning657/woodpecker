package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.LoanDatacleanRecordEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈fk_loan_dataclean_record表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface LoanDatacleanRecordDao extends JpaRepository<LoanDatacleanRecordEntity, Long> {

  List<LoanDatacleanRecordEntity> findByOrderId(Integer orderId);

  List<LoanDatacleanRecordEntity> findByUserId(Integer userId);
  
}
