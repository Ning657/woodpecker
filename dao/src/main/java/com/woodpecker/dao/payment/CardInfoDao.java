package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.CardInfoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_card_info表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface CardInfoDao extends JpaRepository<CardInfoEntity, Long> {

  CardInfoEntity findById(Long id);

  CardInfoEntity findByOrderNo(String orderNo);

  CardInfoEntity findByBindId(String bindId);

  List<CardInfoEntity> findByCardNo(String cardNo);

}
