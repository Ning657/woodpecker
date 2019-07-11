package com.woodpecker.framework.mq.verify;

/**
 * 接口描述:〈MQ校验实现类路由〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface MqVerifyProcessorRoute {


  /**
   * 方法功能描述: 路由相应的MqVerifyProcessor实现类
   *
   * @param scheduleType ScheduleTypeEnum
   * @return com.woodpecker.framework.mq.verify.MqVerifyProcessor
   */
  MqVerifyProcessor route(ScheduleTypeEnum scheduleType);


}
