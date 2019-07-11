package com.woodpecker.framework.aop.annotation;

import com.woodpecker.framework.bind.BindChannelEnum;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.framework.pay.PayChannelCodeEnum;
import com.woodpecker.framework.pay.PayPlatformNameEnum;
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
   * rt_pay_channel_bank表的pay_channel_code字段值，默认为JD
   */
  PayChannelCodeEnum payChannelCode() default PayChannelCodeEnum.JD;


  /**
   * 计划类型（还款计划/趸交计划），默认为还款计划
   */
  ScheduleTypeEnum scheduleType() default ScheduleTypeEnum.REPAYMENT;


  /**
   * 增加的金额，就是在原先限额的金额基础上，再增加这个金额，默认为1000
   */
  int amount() default 1000;


  /**
   * 禁用指定的支付通道,banCodes和banOtherCodes是互斥的,如果同时设置了banCodes和banOtherCodes，只有banCodes生效，默认为PayPlatformNameEnum.ALL_IN_PAY
   */
  PayPlatformNameEnum[] banCodes() default {PayPlatformNameEnum.ALL_IN_PAY};


  /**
   * 禁用除指定支付通道外的通道,banCodes和banOtherCodes是互斥的,如果同时设置了banCodes和banOtherCodes，只有banCodes生效
   */
  PayPlatformNameEnum[] banOtherCodes() default {};


  /**
   * 绑卡/鉴权通道，如果没鉴权的话，就会去鉴权，默认为JDAGREEMENTPAY
   */
  BindChannelEnum[] bindChannels() default {BindChannelEnum.JDAGREEMENTPAY};


  /**
   * 支付通道鉴权时的短信验证码，默认为123456
   */
  String verificationCode() default "123456";


  /**
   * 是否删除Redis缓存，在支付之前
   */
  boolean delRedisCacheBeforePay() default true;


  /**
   * 是否删除Redis缓存，在支付之后
   */
  boolean delRedisCacheAfterPay() default false;


}
