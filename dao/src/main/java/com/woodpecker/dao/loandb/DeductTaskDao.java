package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.DeductTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_deduct_task表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface DeductTaskDao extends JpaRepository<DeductTaskEntity, Integer> {

}
