package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.BankEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_bank表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface BankDao extends JpaRepository<BankEntity, Integer> {

  Optional<BankEntity> findById(Integer id);

  BankEntity findByBankName(String BankName);

}
