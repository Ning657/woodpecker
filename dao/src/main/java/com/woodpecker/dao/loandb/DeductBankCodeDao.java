package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.DeductBankCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_deduct_bank_code表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface DeductBankCodeDao extends JpaRepository<DeductBankCodeEntity, Integer> {

}
