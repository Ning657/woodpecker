package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.TradeOrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈t_tp_trade_order表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface TradeOrderDao extends JpaRepository<TradeOrderEntity, String> {

  TradeOrderEntity findByTradeNo(String tradeNo);

  TradeOrderEntity findByPayNo(String payNo);

  List<TradeOrderEntity> findByUserId(Integer userId);

  TradeOrderEntity findByUuid(String uuid);

  TradeOrderEntity findByBizTypeAndBizValue(String bizType, String bizValue);

  List<TradeOrderEntity> findByBizValue(String bizValue);

}
