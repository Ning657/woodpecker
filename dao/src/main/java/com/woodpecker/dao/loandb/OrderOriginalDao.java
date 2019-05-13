package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.OrderOriginalEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_order_original表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface OrderOriginalDao extends JpaRepository<OrderOriginalEntity, Integer> {

  OrderOriginalEntity findById(Integer id);

  List<OrderOriginalEntity> findByUserId(Integer userId);

}
