package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.ThirdpartyScheduleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_thirdparty_schedule表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface ThirdpartyScheduleDao extends JpaRepository<ThirdpartyScheduleEntity, Integer> {

  Optional<ThirdpartyScheduleEntity> findById(Integer id);

  List<ThirdpartyScheduleEntity> findByLoanOrderId(Integer loanOrderId);

  List<ThirdpartyScheduleEntity> findByOrderId(Integer orderId);

  List<ThirdpartyScheduleEntity> findByUserId(Integer userId);

  ThirdpartyScheduleEntity findByOrderIdAndStage(Integer orderId, Byte stage);

  ThirdpartyScheduleEntity findByLoanOrderIdAndStage(Integer loanOrderId, Byte stage);

  ThirdpartyScheduleEntity findByScheduleId(Integer scheduleId);

  
}
