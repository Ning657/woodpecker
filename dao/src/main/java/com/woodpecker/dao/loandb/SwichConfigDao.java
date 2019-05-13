package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.SwichConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈qs_swich_config表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface SwichConfigDao extends JpaRepository<SwichConfigEntity, Integer> {

}
