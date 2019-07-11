package com.woodpecker.framework.bind.impl;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.framework.bind.BindRequestVerifyService;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import org.springframework.stereotype.Service;
import org.testng.Assert;

/**
 * 类描述:〈绑卡接口请求结果校验Service，只有校验请求绑卡和确认绑卡接口返回内容的功能，不会去校验绑卡后的数据库数据〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class BindRequestVerifyServiceImpl implements BindRequestVerifyService {


  /**
   * 方法功能描述: 校验请求绑卡结果
   *
   * @param httpResponse HttpResponse
   * @return void
   */
  @Override
  public void verifyRequestBindCardResult(HttpResponse httpResponse) {
    JSONObject jsonObject = JsonUtil.parseObject(httpResponse.getContent());
    String code = jsonObject.getString("code");
    Assert.assertEquals(code, "0000", "校验请求绑卡返回结果的code");
  }


  /**
   * 方法功能描述: 校验确认绑卡结果
   *
   * @param httpResponse HttpResponse
   * @return void
   */
  @Override
  public void verifySureBindCardResult(HttpResponse httpResponse) {
    JSONObject jsonObject = JsonUtil.parseObject(httpResponse.getContent());
    String code = jsonObject.getString("code");
    Assert.assertEquals(code, "0000", "校验确认绑卡返回结果的code");
  }


}
