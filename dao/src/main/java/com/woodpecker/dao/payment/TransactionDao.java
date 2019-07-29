package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.TransactionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 接口描述:〈t_tp_transaction表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface TransactionDao extends JpaRepository<TransactionEntity, Long> {

  Optional<TransactionEntity> findById(Long id);

  TransactionEntity findByOrderNo(String orderNo);

  List<TransactionEntity> findByUserId(Long userId);

  @Query(value = "select * from t_tp_transaction where bizUserId=? and idCardNo=? and mobile=? and userName=? and platId=? and cardId=? and tranStatus=? and action=? and createDate>=now()-interval ? minute;", nativeQuery = true)
  List<TransactionEntity> find(String bizUserId, String idCardNo, String mobile, String userName,
      Byte platId, String cardId, String tranStatus, String action, Integer timeMinute);

}
