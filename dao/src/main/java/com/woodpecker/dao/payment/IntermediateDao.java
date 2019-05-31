package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.IntermediateEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 接口描述:〈t_tp_intermediate表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface IntermediateDao extends JpaRepository<IntermediateEntity, Long> {

  IntermediateEntity findById(Long id);

  IntermediateEntity findByOrderNo(String orderNo);

  List<IntermediateEntity> findByBizUserIdAndIdCardNoAndCardIdAndPlatIdAndStatus(Long bizUserId,
      String idCardNo, String cardId, Byte platId, Byte status);

  @Query(value = "select * from t_tp_intermediate where bizUserId=? and idCardNo=? and cardId=? and platId=? and status=? and createDate>=now()-interval ? minute;", nativeQuery = true)
  List<IntermediateEntity> find(Long bizUserId, String idCardNo, String cardId, Byte platId,
      Byte status, Integer timeMinute);

}
