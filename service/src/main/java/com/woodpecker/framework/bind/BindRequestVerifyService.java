package com.woodpecker.framework.bind;

import com.xujinjian.HttpClient.HttpResponse;

/**
 * 接口描述:〈绑卡接口请求结果校验Service，只有校验请求绑卡和确认绑卡接口返回内容的功能，不会去校验绑卡后的数据库数据〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface BindRequestVerifyService {


  /**
   * 方法功能描述: 校验请求绑卡结果
   *
   * @param httpResponse HttpResponse
   * @return void
   */
  void verifyRequestBindCardResult(HttpResponse httpResponse);


  /**
   * 方法功能描述: 校验确认绑卡结果
   *
   * @param httpResponse HttpResponse
   * @return void
   */
  void verifySureBindCardResult(HttpResponse httpResponse);


}
