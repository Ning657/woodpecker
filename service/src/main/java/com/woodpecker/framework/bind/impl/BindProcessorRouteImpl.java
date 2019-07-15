package com.woodpecker.framework.bind.impl;

import com.woodpecker.framework.bind.BindCardEnum;
import com.woodpecker.framework.bind.BindProcessor;
import com.woodpecker.framework.bind.BindProcessorRoute;
import com.woodpecker.framework.bind.BindService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈绑卡路由〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class BindProcessorRouteImpl implements BindProcessorRoute {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * BindProcessor实现类集合
   */
  @Autowired
  protected List<BindProcessor> bindProcessors;


  /**
   * 方法功能描述: 路由相应的BindProcessor绑卡实现类
   *
   * @param bindCardEnum BindCardEnum
   * @return com.woodpecker.framework.bind.BindProcessor
   */
  @Override
  public BindProcessor route(BindCardEnum bindCardEnum) {
    logger
        .debug("开始路由BindCardEnum=[{}({})]的绑卡服务", bindCardEnum.getPayPlatformName(),
            bindCardEnum.getChannel());
    BindProcessor bindProcessorImpl = null;
    if (null != bindProcessors) {
      logger.debug("共注册了[{}]个BindProcessor服务", bindProcessors.size());
      //
      for (BindProcessor bindProcessor : bindProcessors) {
        BindService bindService = bindProcessor.getClass().getAnnotation(BindService.class);
        if (null != bindService) {
          //当前类有BindService注解
          //获取出绑卡渠道枚举
          BindCardEnum type = bindService.bindCardEnum();
          //判断绑卡渠道枚举是否相同
          if (bindCardEnum.equals(type)) {
            //此BindProcessor实现类，是相应的绑卡实现类
            //路由到相应的绑卡实现类
            bindProcessorImpl = bindProcessor;
            break;
          }
        }
      }
    }
    if (null != bindProcessorImpl) {
      logger.debug("绑卡路由结果：[{}]", bindProcessorImpl.getClass().getName());
    } else {
      logger.debug("绑卡路由结果：[null]");
    }
    return bindProcessorImpl;
  }


}
