package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈qs_single_premium_schedule表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface SinglePremiumScheduleDao extends
    JpaRepository<SinglePremiumScheduleEntity, Integer> {

  Optional<SinglePremiumScheduleEntity> findById(Integer id);

  SinglePremiumScheduleEntity findByLoanOrderId(Integer loanOrderId);

  List<SinglePremiumScheduleEntity> findByUserId(Integer userId);

}
