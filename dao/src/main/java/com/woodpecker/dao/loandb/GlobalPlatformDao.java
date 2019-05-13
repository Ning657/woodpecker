package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.GlobalPlatformEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_global_platform表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface GlobalPlatformDao extends JpaRepository<GlobalPlatformEntity, Integer> {

  GlobalPlatformEntity findById(Integer id);

  List<GlobalPlatformEntity> findByStatus(Byte status);

  List<GlobalPlatformEntity> findByIsSupervise(Byte isSupervise);

}
