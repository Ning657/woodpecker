package com.woodpecker.testcase.payment.repayment.treefinance.stages4551;

import com.woodpecker.service.payment.repayment.schedule.RepaymentScheduleService;
import com.woodpecker.testcase.payment.repayment.RepaymentTestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈分期还款走4551的测试用例基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class Stages4551TestCase extends RepaymentTestCase {

  @Autowired
  protected RepaymentScheduleService repaymentScheduleService;

  protected byte stage = 2;//需要还款的期数


  /**
   * 方法功能描述: 将第一期置为已还清
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  protected void clearFirstRepaymentSchedule(int loanOrderId) {
    //将第一期置为已还清
    super.orderService.clearRepaymentSchedule(loanOrderId, (byte) 1);
  }


}
