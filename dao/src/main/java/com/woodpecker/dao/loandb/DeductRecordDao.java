package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.DeductRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_deduct_record表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface DeductRecordDao extends JpaRepository<DeductRecordEntity, Integer> {

}
