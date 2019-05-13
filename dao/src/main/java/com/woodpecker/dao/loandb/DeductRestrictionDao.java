package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.DeductRestrictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_deduct_restriction表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface DeductRestrictionDao extends JpaRepository<DeductRestrictionEntity, Integer> {

}
