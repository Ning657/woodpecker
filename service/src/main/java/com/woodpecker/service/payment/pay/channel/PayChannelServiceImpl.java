package com.woodpecker.service.payment.pay.channel;

import com.woodpecker.framework.pay.PayChannelCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈rt_pay_channel表service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class PayChannelServiceImpl implements PayChannelService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());


  /**
   * 方法功能描述: 转换成PayChannelCodeEnum枚举类
   *
   * @param code code
   * @return com.woodpecker.framework.pay.PayChannelCodeEnum
   */
  @Override
  public PayChannelCodeEnum toPayChannelCodeEnum(String code) {
    if (PayChannelCodeEnum.contain(code)) {
      return PayChannelCodeEnum.convert(code);
    } else {
      logger.error("不存在code=[{}]的[{}]枚举", code, PayChannelCodeEnum.class);
      return null;
    }
  }


}
