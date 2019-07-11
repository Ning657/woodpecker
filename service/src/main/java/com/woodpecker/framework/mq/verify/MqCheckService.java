package com.woodpecker.framework.mq.verify;

/**
 * 接口描述:〈MQ消息检查Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface MqCheckService {


  /**
   * 方法功能描述: 校验MQ消息
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @param scheduleType ScheduleTypeEnum
   * @return boolean
   */
  boolean verify(String payNo, String id, ScheduleTypeEnum scheduleType);


}
