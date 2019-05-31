package com.woodpecker.service.payment.trade;

import com.xujinjian.HttpClient.HttpResponse;

/**
 * 类描述:〈解绑卡相关Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface UnBindCardService {


  /**
   * 方法功能描述: 解绑卡
   *
   * @param userId userId
   * @param accountNo 银行卡卡号
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse unBindCard(String userId, String accountNo);


}
