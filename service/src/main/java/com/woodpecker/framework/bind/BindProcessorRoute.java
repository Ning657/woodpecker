package com.woodpecker.framework.bind;

/**
 * 接口描述:〈绑卡路由〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface BindProcessorRoute {


  /**
   * 方法功能描述: 路由相应的BindProcessor绑卡实现类
   *
   * @param bindCardEnum BindCardEnum
   * @return com.woodpecker.framework.bind.BindProcessor
   */
  BindProcessor route(BindCardEnum bindCardEnum);


}
