package com.woodpecker.service.payment.repayment.schedule;

import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈还款计划Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class RepaymentScheduleServiceImpl implements RepaymentScheduleService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private RepaymentScheduleDao repaymentScheduleDao;


  /**
   * 方法功能描述: 获取还款计划
   *
   * @param loanOrderId loanOrderId
   * @param stage 期数
   * @return com.woodpecker.entity.loandb.RepaymentScheduleEntity
   */
  @Override
  public RepaymentScheduleEntity getRepaymentSchedule(int loanOrderId, byte stage) {
    return repaymentScheduleDao.findByLoanOrderIdAndStage(loanOrderId, stage);
  }


}
