package com.woodpecker.service.pub;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.sword.autotest.framework.assertion.Validate;
import com.woodpecker.commons.util.TestCaseUtil;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.Commons.Lang.DateUtil;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈MQ相关功能Service，包括检查MQ是否发送成功等功能〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
@AutoProxy
public class MQServiceImpl implements MQService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @AutoHttpRequest
  private HttpApi httpApi;

  @Autowired
  private Host host;


  /**
   * 方法功能描述: 查询指定时间段(离当前时间，前后3分钟内)，发送出去的MQ集合
   *
   * @param topic Topic
   * @return java.util.List<com.alibaba.fastjson.JSONObject>
   */
  @Override
  public List<JSONObject> queryMessageByTopic(String topic) {
    return queryMessageByTopic(3, topic);
  }


  /**
   * 方法功能描述: 查询指定时间段(离当前时间，前后X分钟内)，发送出去的MQ集合
   *
   * @param min 离当前时间，前后X分钟
   * @param topic Topic
   * @return java.util.List<com.alibaba.fastjson.JSONObject>
   */
  @Override
  public List<JSONObject> queryMessageByTopic(int min, String topic) {
    long nowTime = DateUtil.getTime();
    long times = min * 60 * 1000;
    long begin = nowTime - times;
    long end = nowTime + times;
    return queryMessageByTopic(begin, end, topic);
  }


  /**
   * 方法功能描述: 查询指定时间段，发送出去的MQ集合
   *
   * @param begin 时间区间-起点，时间戳
   * @param end 时间区间-终点，时间戳
   * @param topic Topic
   * @return java.util.List<com.alibaba.fastjson.JSONObject>
   */
  @Override
  public List<JSONObject> queryMessageByTopic(long begin, long end, String topic) {
    List<JSONObject> list = new ArrayList<>();

    Map<String, String> param = new HashMap<>();
    param.put("begin", String.valueOf(begin));
    param.put("end", String.valueOf(end));
    param.put("topic", topic);
    String url = host.getMqHost() + "/message/queryMessageByTopic.query";
    logger.debug("MQ查询接口[{}]的参数为[{}]", url, param);
    HttpResponse httpResponse = this.httpApi.get(url, param);
    int statusCode = httpResponse.getStatus().getStatusCode();
    String content = httpResponse.getContent();
    logger.debug("MQ查询接口返回的内容为[{}]", content);
    TestCaseUtil.checkStatusCodeIsOK(statusCode, "校验MQ查询接口的响应状态码是否为200");
    JSONObject jsonObject = JsonUtil.parseObject(content);
    //logger.debug("jsonObject=[{}]", jsonObject.toJSONString());
    Object errMsg = jsonObject.get("errMsg");
    if (null != errMsg) {
      if (!StringUtil.equalsIgnoreCase("null", String.valueOf(errMsg))) {
        Validate.isTrue(false, "校验查询MQ消息是否成功");
      }
    }
    //logger.debug("containsKey 'data' ? {}", String.valueOf(jsonObject.containsKey("data")));
    JSONArray data = jsonObject.getJSONArray("data");
    logger.debug("data=[{}]", data.toJSONString());

    int size = data.size();
    logger.debug("data部分数据的size=[{}]", size);
    for (int i = 0; i < size; i++) {
      JSONObject d = data.getJSONObject(i);
      logger.debug("data部分数据的index=[{}]的内容为[{}]", i, d.toJSONString());
      list.add(d);
    }
    logger.debug("list=[{}]", list);
    return list;
  }


  /**
   * 方法功能描述: 查询指定MQ的具体消息内容
   *
   * @param msgId MQ消息ID
   * @param topic Topic
   * @return com.alibaba.fastjson.JSONObject
   */
  @Override
  public JSONObject queryMessageByMsgId(String msgId, String topic) {
    Map<String, String> param = new HashMap<>();
    param.put("msgId", msgId);
    param.put("topic", topic);
    String url = host.getMqHost() + "/message/viewMessage.query";
    logger.debug("MQ查询接口[{}]的参数为[{}]", url, param);
    HttpResponse httpResponse = this.httpApi.get(url, param);
    int statusCode = httpResponse.getStatus().getStatusCode();
    String content = httpResponse.getContent();
    logger.debug("MQ查询接口返回的内容为[{}]", content);
    TestCaseUtil.checkStatusCodeIsOK(statusCode, "校验MQ查询接口的响应状态码是否为200");
    JSONObject jsonObject = JsonUtil.parseObject(content);
    Object errMsg = jsonObject.get("errMsg");
    if (null != errMsg) {
      if (!StringUtil.equalsIgnoreCase("null", String.valueOf(errMsg))) {
        Validate.isTrue(false, "校验查询MQ消息是否成功");
      }
    }
    JSONObject data = jsonObject.getJSONObject("data");
    logger.debug("data=[{}]", data.toJSONString());
    return data;
  }


}
