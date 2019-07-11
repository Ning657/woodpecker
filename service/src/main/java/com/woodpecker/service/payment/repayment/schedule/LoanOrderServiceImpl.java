package com.woodpecker.service.payment.repayment.schedule;

import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈LoanOrder-Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class LoanOrderServiceImpl implements LoanOrderService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private LoanOrderDao loanOrderDao;


  /**
   * 方法功能描述: 获取LoanOrderEntity
   *
   * @param orderId 原始订单ID
   * @return com.woodpecker.entity.loandb.LoanOrderEntity
   */
  @Override
  public LoanOrderEntity getLoanOrder(int orderId) {
    List<LoanOrderEntity> loanOrderEntityList = getLoanOrders(orderId);
    if (null == loanOrderEntityList || loanOrderEntityList.size() <= 0) {
      return null;
    }
    return loanOrderEntityList.get(0);
  }


  /**
   * 方法功能描述: 获取LoanOrderEntity(联合放贷时，1个orderId会对应多个loanOrderId)
   *
   * @param orderId 原始订单ID
   * @return com.woodpecker.entity.loandb.LoanOrderEntity
   */
  @Override
  public List<LoanOrderEntity> getLoanOrders(int orderId) {
    return loanOrderDao.findByOrderId(orderId);
  }


}
