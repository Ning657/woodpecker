package com.woodpecker.framework.mq.verify;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service;

/**
 * 注解描述:〈MQ消息校验服务〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MqVerifyService {


  /**
   * 计划类型
   */
  ScheduleTypeEnum scheduleType();


  /**
   * 说明
   */
  String desc() default "";


}
