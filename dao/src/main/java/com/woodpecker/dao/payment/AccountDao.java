package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.AccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_tp_account表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface AccountDao extends JpaRepository<AccountEntity, Long> {

  AccountEntity findById(Long id);

  AccountEntity findByOrderNo(String orderNo);

  List<AccountEntity> findByUserId(Long userId);

}
