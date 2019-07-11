package com.woodpecker.framework.mq.verify;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

/**
 * 接口描述:〈MQ消息查询功能Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface MqQueryService {


  /**
   * 方法功能描述: 查询指定时间段(默认当前10分钟内)，发送出去的MQ集合
   *
   * @param topic Topic
   * @return java.util.List<com.alibaba.fastjson.JSONObject>
   */
  List<JSONObject> queryMessageByTopic(String topic);


  /**
   * 方法功能描述: 查询指定时间段(离当前时间，前后X分钟内)，发送出去的MQ集合
   *
   * @param min 离当前时间，前后X分钟
   * @param topic Topic
   * @return java.util.List<com.alibaba.fastjson.JSONObject>
   */
  List<JSONObject> queryMessageByTopic(int min, String topic);


  /**
   * 方法功能描述: 查询指定时间段，发送出去的MQ集合
   *
   * @param begin 时间区间-起点，时间戳
   * @param end 时间区间-终点，时间戳
   * @param topic Topic
   * @return java.util.List<com.alibaba.fastjson.JSONObject>
   */
  List<JSONObject> queryMessageByTopic(long begin, long end, String topic);


  /**
   * 方法功能描述: 查询指定MQ的具体消息内容
   *
   * @param msgId MQ消息ID
   * @param topic Topic
   * @return com.alibaba.fastjson.JSONObject
   */
  JSONObject queryMessageByMsgId(String msgId, String topic);


}
