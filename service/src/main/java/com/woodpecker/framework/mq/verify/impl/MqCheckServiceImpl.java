package com.woodpecker.framework.mq.verify.impl;

import com.woodpecker.framework.mq.verify.MqCheckService;
import com.woodpecker.framework.mq.verify.MqVerifyProcessor;
import com.woodpecker.framework.mq.verify.MqVerifyProcessorRoute;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈MQ消息检查Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class MqCheckServiceImpl implements MqCheckService {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected MqVerifyProcessorRoute mqVerifyProcessorRoute;


  /**
   * 方法功能描述: 校验MQ消息
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @param scheduleType ScheduleTypeEnum
   * @return boolean
   */
  @Override
  public boolean verify(String payNo, String id, ScheduleTypeEnum scheduleType) {
    //路由MQ校验实现类
    MqVerifyProcessor mqVerifyProcessor = mqVerifyProcessorRoute.route(scheduleType);
    if (null == mqVerifyProcessor) {
      return false;
    }
    //校验MQ消息
    return mqVerifyProcessor.handle(payNo, id);
  }


}
