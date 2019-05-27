package com.woodpecker.service.payment.trade;

import com.xujinjian.HttpClient.HttpResponse;

/**
 * 接口描述:〈绑卡相关Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface BindCardService {


  /**
   * 方法功能描述: 请求绑卡
   *
   * @param platform platform
   * @param bankId bankId
   * @param idCardNo 身份证
   * @param userName 用户名
   * @param mobile 手机号
   * @param cardNo 卡号
   * @param trustProjectCode 信托计划
   * @param platformUserId 用户在资金渠道的开户id
   * @param returnUrl url
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse requestBindCard(String platform, String bankId, String idCardNo, String userName,
      String mobile, String cardNo, String trustProjectCode, String platformUserId,
      String returnUrl);


  /**
   * 方法功能描述: 确认绑卡
   *
   * @param mobile 手机号
   * @param bankId bankId
   * @param userId userId
   * @param code 短信验证码
   * @param idCardNo 身份证
   * @param orderNo 请求绑卡接口返回的data
   * @param cardNo 卡号
   * @param oriOrderId 原始订单ID
   * @param trustProjectCode 信托计划
   * @param platformUserId 用户在资金渠道的开户id
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse sureBindCard(String mobile, String bankId, String userId, String code,
      String idCardNo, String orderNo, String cardNo, String oriOrderId, String trustProjectCode,
      String platformUserId);


}
