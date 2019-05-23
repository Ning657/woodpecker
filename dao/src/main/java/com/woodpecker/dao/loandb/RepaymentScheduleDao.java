package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

  RepaymentScheduleEntity findByLoanOrderIdAndStage(Integer loanOrderId, Byte stage);

  List<RepaymentScheduleEntity> findByLoanOrderIdAndIsClear(Integer loanOrderId, Byte isClear);

  @Query(value = "select * from t_repayment_schedule where LoanOrderId=? and Stage<?", nativeQuery = true)
  List<RepaymentScheduleEntity> queryBeforeStageRepaymentSchedules(Integer loanOrderId, Byte stage);

  @Query(value = "select * from t_repayment_schedule where LoanOrderId=? and Stage<=?", nativeQuery = true)
  List<RepaymentScheduleEntity> queryBeforeContainsStageRepaymentSchedules(Integer loanOrderId,
      Byte stage);

  @Query(value = "select * from t_repayment_schedule where LoanOrderId=? and Stage>?", nativeQuery = true)
  List<RepaymentScheduleEntity> queryAfterStageRepaymentSchedules(Integer loanOrderId, Byte stage);

  @Query(value = "select * from t_repayment_schedule where LoanOrderId=? and Stage>=?", nativeQuery = true)
  List<RepaymentScheduleEntity> queryAfterContainsStageRepaymentSchedules(Integer loanOrderId,
      Byte stage);

}
