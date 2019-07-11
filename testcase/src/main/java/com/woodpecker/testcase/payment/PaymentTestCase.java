package com.woodpecker.testcase.payment;

import com.woodpecker.service.pub.order.OrderService;
import com.woodpecker.service.superdiamond.Superdiamond;
import com.woodpecker.testcase.TestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈支付TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class PaymentTestCase extends TestCase {

  /**
   * router.env.version
   */
  protected String routerEnvVersion = null;

  @Autowired
  protected Superdiamond superdiamond;

  @Autowired
  protected OrderService orderService;

}
