package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_bank_account表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface BankAccountDao extends JpaRepository<BankAccountEntity, Integer> {

  BankAccountEntity findById(Integer id);

  List<BankAccountEntity> findByUserId(Integer userId);

}
