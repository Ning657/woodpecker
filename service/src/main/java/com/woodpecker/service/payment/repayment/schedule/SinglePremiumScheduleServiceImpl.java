package com.woodpecker.service.payment.repayment.schedule;

import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈趸交计划Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class SinglePremiumScheduleServiceImpl implements SinglePremiumScheduleService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private SinglePremiumScheduleDao singlePremiumScheduleDao;
  

  /**
   * 方法功能描述: 获取趸交计划
   *
   * @param loanOrderId loanOrderId
   * @return com.woodpecker.entity.loandb.SinglePremiumScheduleEntity
   */
  @Override
  public SinglePremiumScheduleEntity getSinglePremiumSchedule(int loanOrderId) {
    return singlePremiumScheduleDao.findByLoanOrderId(loanOrderId);
  }


}
