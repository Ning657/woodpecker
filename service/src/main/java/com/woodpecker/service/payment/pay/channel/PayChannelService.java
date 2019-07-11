package com.woodpecker.service.payment.pay.channel;

import com.woodpecker.framework.pay.PayChannelCodeEnum;

/**
 * 接口描述:〈rt_pay_channel表service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface PayChannelService {


  /**
   * 方法功能描述: 转换成PayChannelCodeEnum枚举类
   *
   * @param code code
   * @return com.woodpecker.framework.pay.PayChannelCodeEnum
   */
  PayChannelCodeEnum toPayChannelCodeEnum(String code);


}
