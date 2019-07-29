package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.OrderRouteEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_order_route表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface OrderRouteDao extends JpaRepository<OrderRouteEntity, Integer> {

  Optional<OrderRouteEntity> findById(Integer id);

  OrderRouteEntity findByOrderId(Integer orderId);

  List<OrderRouteEntity> findByUserId(Integer userId);

}
