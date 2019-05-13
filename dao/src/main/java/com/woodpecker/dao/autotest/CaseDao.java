package com.woodpecker.dao.autotest;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.autotest.CaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_case表dao〉
 *
 * @version 1.0
 * @author: xujinjian
 */
@Dao
public interface CaseDao extends JpaRepository<CaseEntity, Integer> {

}
