package com.woodpecker.testcase.payment.repayment.treefinance.premium4551;

import com.woodpecker.service.payment.repayment.schedule.SinglePremiumScheduleService;
import com.woodpecker.testcase.payment.repayment.RepaymentTestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈趸交还款测试用例基类 - 居间方：大树〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class Premium4551TestCase extends RepaymentTestCase {

  @Autowired
  protected SinglePremiumScheduleService singlePremiumScheduleService;

}
