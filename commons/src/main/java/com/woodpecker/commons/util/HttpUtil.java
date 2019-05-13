package com.woodpecker.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/**
 * 类描述:〈Http工具类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class HttpUtil {

  /**
   * 编码
   */
  public static String encoding = "UTF-8";


  /**
   * 方法功能描述: 对HTTP请求参数进行URL编码
   *
   * @param map 需要URL编码的请求参数
   * @return java.util.Map
   */
  public static String urlEncode(Map<String, String> map) {
    return urlEncode(map, encoding);
  }


  /**
   * 方法功能描述: 对HTTP请求参数进行URL编码
   *
   * @param map 需要URL编码的请求参数
   * @param encoding 编码
   * @return java.util.Map
   */
  public static String urlEncode(Map<String, String> map, String encoding) {
    List<NameValuePair> list = new ArrayList<>();
    map.entrySet().forEach(p -> list.add(new BasicNameValuePair(p.getKey(), p.getValue())));
    return URLEncodedUtils.format(list, encoding);
  }


  /**
   * 方法功能描述: Map转换
   *
   * @param map Map
   * @return java.util.Map
   */
  public static Map<String, String> convert(Map<String, Object> map) {
    Map<String, String> result = new HashMap<>();
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      String val = String.valueOf(value);
      result.put(key, val);
    }
    return result;
  }


  public static String getEncoding() {
    return encoding;
  }

  public static void setEncoding(String encoding) {
    HttpUtil.encoding = encoding;
  }

}
