package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.AcctCashFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈acct_cash_flow表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface AcctCashFlowDao extends JpaRepository<AcctCashFlowEntity, String> {

  AcctCashFlowEntity findBySerialNo(String serialNo);

  AcctCashFlowEntity findByRefTypeAndRefValue(String refType, String refValue);

  AcctCashFlowEntity findByRefValue(String refValue);

}
