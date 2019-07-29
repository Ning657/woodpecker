package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.OrderThirdpartyRefEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_order_thirdparty_ref表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface OrderThirdpartyRefDao extends JpaRepository<OrderThirdpartyRefEntity, Integer> {

  Optional<OrderThirdpartyRefEntity> findById(Integer id);

  OrderThirdpartyRefEntity findByOrderId(Integer orderId);

  List<OrderThirdpartyRefEntity> findByUserId(Integer userId);

}
