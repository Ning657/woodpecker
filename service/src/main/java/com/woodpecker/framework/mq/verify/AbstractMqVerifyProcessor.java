package com.woodpecker.framework.mq.verify;

import com.xujinjian.Commons.Lang.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类描述:〈MQ消息校验执行器，所有具体的MQ校验实现类都需要继承此类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public abstract class AbstractMqVerifyProcessor implements MqVerifyProcessor {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 查询MQ前后2分钟内-时间
   */
  protected int time = 2;


  /**
   * 方法功能描述: 执行MQ消息校验
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @return boolean
   */
  @Override
  public boolean handle(String payNo, String id) {
    //校验参数合法性
    if (StringUtil.isEmpty(payNo)) {
      logger.error("参数payNo=[{}]非法!", payNo);
      return false;
    }
    //MQ是否找到标记位
    boolean mqExist = false;
    //执行具体的MQ消息校验，抛出钩子供真正的校验MQ消息服务调用
    try {
      mqExist = doVerify(payNo, id);
    } catch (Throwable e) {
      logger.error("查找发送出去的MQ消息时，出现异常，payNo=[{}];id=[{}]", payNo, id);
      logger.error(e.getMessage(), e);
    }
    if (!mqExist) {
      logger.error("没有找到发送出去的MQ消息，payNo=[{}];id=[{}]", payNo, id);
    }
    return mqExist;
  }


  /**
   * 方法功能描述: 校验MQ消息
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @return boolean
   */
  public abstract boolean doVerify(String payNo, String id);


}
