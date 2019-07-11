package com.woodpecker.framework.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解描述:〈将参数标记为：还款计划ID/趸交ID/LoanOrderID〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ScheduleId {

}
