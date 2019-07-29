package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.FeeDetailEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_fee_detail表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface FeeDetailDao extends JpaRepository<FeeDetailEntity, Integer> {

  Optional<FeeDetailEntity> findById(Integer id);

  FeeDetailEntity findByRefTypeAndRefValue(String refType, String refValue);

  FeeDetailEntity findByRefValue(String refValue);

}
