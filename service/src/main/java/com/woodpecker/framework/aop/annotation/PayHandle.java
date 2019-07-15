package com.woodpecker.framework.aop.annotation;

import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.framework.pay.PayPlatformEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解描述:〈还款拦截器，修改银行日限额、禁用支付通道、支付通道鉴权的注解〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PayHandle {


  /**
   * 支付通道枚举，默认为[JDAGREEMENTPAY_4551 (42)]，用于修改银行限额和绑卡
   */
  PayPlatformEnum payPlatform() default PayPlatformEnum.JDAGREEMENTPAY_4551;


  /**
   * 计划类型（还款计划/趸交计划），默认为还款计划
   */
  ScheduleTypeEnum scheduleType() default ScheduleTypeEnum.REPAYMENT;


  /**
   * 增加/减少的金额，就是在原先限额的金额基础上，再增加/减少这个金额，如果不等于0，则表示需要修改银行限额，如果等于0，表示不需要修改银行限额
   */
  int amount() default 1000;


  /**
   * 支付通道鉴权时的短信验证码，默认为123456
   */
  String authCode() default "123456";


  /**
   * 禁用指定的支付通道,banCodes和banOtherCodes是互斥的,如果同时设置了banCodes和banOtherCodes，只有banCodes生效，默认为PayPlatformNameEnum.ALL_IN_PAY
   */
  PayPlatformEnum[] banCodes() default {PayPlatformEnum.ALL_IN_PAY};


  /**
   * 禁用除指定支付通道外的通道,banCodes和banOtherCodes是互斥的,如果同时设置了banCodes和banOtherCodes，只有banCodes生效
   */
  PayPlatformEnum[] banOtherCodes() default {};


  /**
   * 是否删除Redis缓存，在支付之前
   */
  boolean delRedisCacheBeforePay() default true;


  /**
   * 是否删除Redis缓存，在支付之后
   */
  boolean delRedisCacheAfterPay() default false;


}
