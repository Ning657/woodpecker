package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.FddContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_fdd_contract表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface FddContractDao extends JpaRepository<FddContractEntity, Integer> {

}
