package com.woodpecker.testcase.payment;

import com.woodpecker.testcase.TestCase;
import org.springframework.beans.factory.annotation.Value;

/**
 * 类描述:〈支付用例基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class PaymentTestCase extends TestCase {

  @Value("${testcase.userId}")
  protected String userId;

}
