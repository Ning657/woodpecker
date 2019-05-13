package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.CompositeCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈qs_composite_company表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface CompositeCompanyDao extends JpaRepository<CompositeCompanyEntity, Long> {

}
