package com.woodpecker.framework.pay;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service;

/**
 * 注解描述:〈标记为：支付服务〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PayService {


  /**
   * 还款方式
   */
  RepayTypeEnum repayType();


  /**
   * 说明
   */
  String desc();

  
}
