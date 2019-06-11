package com.woodpecker.service.pub;

import com.alibaba.fastjson.JSONObject;
import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈数据解析Service，包含加解密功能〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@AutoProxy
public class DataAnalysisServiceMainImpl implements DataAnalysisService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @AutoHttpRequest
  private HttpApi httpApi;

  @Autowired
  private Host host;

  /**
   * 加密接口
   */
  private final String encryptUrl = "/crypt/encrypt";

  /**
   * 解密接口
   */
  private final String decryptUrl = "/crypt/decrypt";


  /**
   * 方法功能描述: AES数据加密
   *
   * @param data 需要AES加密的数据
   * @return java.lang.String
   */
  @Override
  public String aesEncrypt(String data) {
    Map<String, String> param = new HashMap<>();
    param.put("content", data);
    param.put("algorithm", "AES");
    String url = host.getDatabuildHost() + encryptUrl;
    logger.debug("AES加密url=[{}]，参数param=[{}]", url, param);
    HttpResponse httpResponse = httpApi.get(url, param);
    String content = httpResponse.getContent();
    logger.debug("AES加密后的content=[{}]，状态码code=[{}]", content,
        httpResponse.getStatus().getStatusCode());
    JSONObject jsonObject = JsonUtil.parseObject(content);
    return jsonObject.getString("data");
  }


  /**
   * 方法功能描述: AES数据解密
   *
   * @param data 需要AES解密的数据
   * @return java.lang.String
   */
  @Override
  public String aesDecrypt(String data) {
    Map<String, String> param = new HashMap<>();
    param.put("content", data);
    param.put("algorithm", "AES");
    String url = host.getDatabuildHost() + decryptUrl;
    logger.debug("AES解密url=[{}]，参数param=[{}]", url, param);
    HttpResponse httpResponse = httpApi.get(url, param);
    String content = httpResponse.getContent();
    logger.debug("AES解密后的content=[{}]，状态码code=[{}]", content,
        httpResponse.getStatus().getStatusCode());
    JSONObject jsonObject = JsonUtil.parseObject(content);
    return jsonObject.getString("data");
  }


  public HttpApi getHttpApi() {
    return httpApi;
  }

  public Host getHost() {
    return host;
  }

  public String getEncryptUrl() {
    return encryptUrl;
  }

  public String getDecryptUrl() {
    return decryptUrl;
  }
}
