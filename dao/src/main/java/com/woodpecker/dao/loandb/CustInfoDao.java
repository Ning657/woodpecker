package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.CustInfoEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_cust_info表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface CustInfoDao extends JpaRepository<CustInfoEntity, Integer> {

  Optional<CustInfoEntity> findById(Integer id);

  CustInfoEntity findByUserName(String UserName);

}
