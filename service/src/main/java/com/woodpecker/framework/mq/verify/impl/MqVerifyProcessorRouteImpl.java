package com.woodpecker.framework.mq.verify.impl;

import com.woodpecker.framework.mq.verify.MqVerifyProcessor;
import com.woodpecker.framework.mq.verify.MqVerifyProcessorRoute;
import com.woodpecker.framework.mq.verify.MqVerifyService;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈MQ校验实现类路由〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class MqVerifyProcessorRouteImpl implements MqVerifyProcessorRoute {

  Logger log = LoggerFactory.getLogger(this.getClass());

  /**
   * MqVerifyProcessor实现类集合
   */
  @Autowired
  protected List<MqVerifyProcessor> mqVerifyProcessors;


  /**
   * 方法功能描述: 路由相应的MqVerifyProcessor实现类
   *
   * @param scheduleType ScheduleTypeEnum
   * @return com.woodpecker.framework.mq.verify.MqVerifyProcessor
   */
  @Override
  public MqVerifyProcessor route(ScheduleTypeEnum scheduleType) {
    log.debug("开始路由ScheduleTypeEnum=[{}({})]的MQ校验服务", scheduleType.name(), scheduleType.getDesc());
    MqVerifyProcessor mqVerifyProcessorImpl = null;
    if (null != mqVerifyProcessors) {
      log.debug("共注册了[{}]个MqVerifyProcessor MQ校验服务", mqVerifyProcessors.size());
      //
      for (MqVerifyProcessor mqVerifyProcessor : mqVerifyProcessors) {
        MqVerifyService mqVerifyService = mqVerifyProcessor.getClass()
            .getAnnotation(MqVerifyService.class);
        if (null != mqVerifyService) {
          //当前类有MqVerifyService注解
          //获取出计划类型枚举
          ScheduleTypeEnum type = mqVerifyService.scheduleType();
          //判断计划类型枚举是否相同
          if (scheduleType.equals(type)) {
            //此MqVerifyProcessor实现类，是相应的MQ校验实现类
            //路由到相应的MQ校验实现类
            mqVerifyProcessorImpl = mqVerifyProcessor;
            break;
          }
        }
      }
    }
    if (null != mqVerifyProcessorImpl) {
      log.debug("MQ校验路由结果：[{}]", mqVerifyProcessorImpl.getClass().getName());
    } else {
      log.debug("MQ校验路由结果：[null]");
    }
    return mqVerifyProcessorImpl;
  }


}
