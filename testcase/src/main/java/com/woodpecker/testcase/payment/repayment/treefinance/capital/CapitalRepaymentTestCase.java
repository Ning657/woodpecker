package com.woodpecker.testcase.payment.repayment.treefinance.capital;

import com.woodpecker.service.payment.repayment.schedule.RepaymentScheduleService;
import com.woodpecker.testcase.payment.repayment.RepaymentTestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈分期还款走资金方的测试用例基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class CapitalRepaymentTestCase extends RepaymentTestCase {

  @Autowired
  protected RepaymentScheduleService repaymentScheduleService;

  protected byte stage = 1;//需要还款的期数
  
}
