package com.woodpecker.testcase.payment.repayment.jingning.stages5213;

import com.woodpecker.service.payment.repayment.schedule.RepaymentScheduleService;
import com.woodpecker.testcase.payment.repayment.RepaymentTestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈分期还款走5213的测试用例基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class Stages5213TestCase extends RepaymentTestCase {

  @Autowired
  protected RepaymentScheduleService repaymentScheduleService;

  protected String payGroupCode = "31";//支付组code

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
