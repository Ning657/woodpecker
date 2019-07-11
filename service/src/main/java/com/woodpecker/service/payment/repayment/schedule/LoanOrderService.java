package com.woodpecker.service.payment.repayment.schedule;

import com.woodpecker.entity.loandb.LoanOrderEntity;
import java.util.List;

/**
 * 接口描述:〈LoanOrder-Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface LoanOrderService {


  /**
   * 方法功能描述: 获取LoanOrderEntity
   *
   * @param orderId 原始订单ID
   * @return com.woodpecker.entity.loandb.LoanOrderEntity
   */
  LoanOrderEntity getLoanOrder(int orderId);


  /**
   * 方法功能描述: 获取LoanOrderEntity(联合放贷时，1个orderId会对应多个loanOrderId)
   *
   * @param orderId 原始订单ID
   * @return com.woodpecker.entity.loandb.LoanOrderEntity
   */
  List<LoanOrderEntity> getLoanOrders(int orderId);


}
