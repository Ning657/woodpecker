package com.woodpecker.service.pub;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.commons.util.TestCaseUtil;
import com.woodpecker.service.HttpApi;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.HttpClient.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈数据解析Service，包含加解密等功能〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
@AutoProxy
public class DataAnalysisServiceImpl implements DataAnalysisService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @AutoHttpRequest
  private HttpApi httpApi;

  private final String host = "http://basisdata-others.api.test.91gfd.cn";


  /**
   * 方法功能描述: 发送加解密请求
   *
   * @param param 参数
   * @return java.lang.String
   */
  private String sendRequest(HashMap<String, String> param) {
    String url = host + "/crypt";
    logger.debug("加解密的url为[{}]，参数为[{}]", url, param);
    HttpResponse httpResponse = httpApi.get(url, param);
    String str = httpResponse.getContent();
    int code = httpResponse.getStatus().getStatusCode();
    if (code != 200) {
      logger.error("访问加解密接口失败，返回的状态码为[];内容为[{}]", code, str);
      TestCaseUtil.checkStatusCodeIsOK(code, "校验加解密接口的响应状态码是否为200");
    }
    List<String> list = new ArrayList<>();
    Pattern pattern = Pattern.compile("\"(.*?)\"");
    Matcher matcher = pattern.matcher(str);
    while (matcher.find()) {
      list.add(matcher.group());
    }
    if (list.size() > 0) {
      if (list.size() != 1) {
        logger.error("提取到多个结果，请检查报文内容[{}]", str);
        return "";
      } else {
        String result = StringUtil.stripToEmpty(list.get(0).replaceAll("\"", ""));
        if (StringUtil.isEmpty(result)) {
          logger.error("对加解密结果去除空格和单引号后的结果是空的result=[{}]", result);
          return "";
        } else {
          return result;
        }
      }
    } else {
      logger.error("提取报文失败，请检查报文内容[{}]", str);
      return "";
    }
  }


  /**
   * 方法功能描述: AES数据加密
   *
   * @param data 需要AES加密的数据
   * @return java.lang.String
   */
  @Override
  public String aesEncrypt(String data) {
    logger.debug("将数据[{}]进行AES加密", data);
    HashMap<String, String> param = new HashMap<>();
    param.put("type", "encrypt");
    param.put("data", data);
    String result = sendRequest(param);
    logger.debug("数据[{}]加密后的密文为[{}]", data, result);
    return result;
  }


  /**
   * 方法功能描述: AES数据解密
   *
   * @param data 需要AES解密的数据
   * @return java.lang.String
   */
  @Override
  public String aesDecrypt(String data) {
    logger.debug("将数据[{}]进行AES解密", data);
    HashMap<String, String> param = new HashMap<>();
    param.put("type", "decrypt");
    param.put("data", data);
    String result = sendRequest(param);
    logger.debug("数据[{}]解密后的明文为[{}]", data, result);
    return result;
  }


  public String getHost() {
    return host;
  }

}
