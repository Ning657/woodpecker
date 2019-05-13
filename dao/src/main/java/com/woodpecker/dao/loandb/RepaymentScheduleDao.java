package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_repayment_schedule表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface RepaymentScheduleDao extends JpaRepository<RepaymentScheduleEntity, Integer> {

  RepaymentScheduleEntity findById(Integer id);

  List<RepaymentScheduleEntity> findByLoanOrderId(Integer loanOrderId);

  List<RepaymentScheduleEntity> findByUserId(Integer userId);

  List<RepaymentScheduleEntity> findByLoanOrderIdAndStatus(Integer LoanOrderId, Byte status);

  List<RepaymentScheduleEntity> findByLoanOrderIdAndStage(Integer loanOrderId, Byte stage);

  List<RepaymentScheduleEntity> findByLoanOrderIdAndIsClear(Integer loanOrderId, Byte isClear);

}
