package com.woodpecker.commons.config;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.sword.autotest.framework.exception.TestCaseException;
import com.xujinjian.Commons.Lang.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述:〈MQ生产者配置〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Configuration
public class MQProducerConfig {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
   */
  @Value("${rocketmq.producer.groupName:}")
  private String groupName;

  /**
   * mq的nameserver地址
   */
  @Value("${rocketmq.producer.namesrvAddr}")
  private String namesrvAddr;

  /**
   * 消息最大大小，默认4M
   */
  @Value("${rocketmq.producer.maxMessageSize:4194304}")
  private Integer maxMessageSize;

  /**
   * 消息发送超时时间，默认3秒
   */
  @Value("${rocketmq.producer.sendMsgTimeout:3000}")
  private Integer sendMsgTimeout;

  /**
   * 消息发送失败重试次数，默认2次
   */
  @Value("${rocketmq.producer.retryTimesWhenSendFailed:2}")
  private Integer retryTimesWhenSendFailed;


  @Bean
  public DefaultMQProducer getRocketMQProducer() {
    logger.debug(toString());
    DefaultMQProducer producer;
    if (StringUtil.isEmpty(groupName)) {
      producer = new DefaultMQProducer();
    } else {
      producer = new DefaultMQProducer(groupName);
    }
    producer.setNamesrvAddr(namesrvAddr);
    //如果需要同一个jvm中不同的producer往不同的mq集群发送消息，需要设置不同的instanceName
    //producer.setInstanceName(instanceName);
    if (4194304 != maxMessageSize) {
      producer.setMaxMessageSize(maxMessageSize);
    }
    if (3000 != sendMsgTimeout) {
      producer.setSendMsgTimeout(sendMsgTimeout);
    }
    if (2 != retryTimesWhenSendFailed) {
      producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
    }
    //
    try {
      producer.start();
      logger.info("MQProducer is start! groupName=[{}],namesrvAddr=[{}]", groupName, namesrvAddr);
    } catch (MQClientException e) {
      logger.error(e.getMessage(), e);
      throw new TestCaseException("create MQProducer fail!");
    }
    return producer;
  }


  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getNamesrvAddr() {
    return namesrvAddr;
  }

  public void setNamesrvAddr(String namesrvAddr) {
    this.namesrvAddr = namesrvAddr;
  }

  public Integer getMaxMessageSize() {
    return maxMessageSize;
  }

  public void setMaxMessageSize(Integer maxMessageSize) {
    this.maxMessageSize = maxMessageSize;
  }

  public Integer getSendMsgTimeout() {
    return sendMsgTimeout;
  }

  public void setSendMsgTimeout(Integer sendMsgTimeout) {
    this.sendMsgTimeout = sendMsgTimeout;
  }

  public Integer getRetryTimesWhenSendFailed() {
    return retryTimesWhenSendFailed;
  }

  public void setRetryTimesWhenSendFailed(Integer retryTimesWhenSendFailed) {
    this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
  }


  @Override
  public String toString() {
    return "MQProducerConfig{" +
        "groupName='" + groupName + '\'' +
        ", namesrvAddr='" + namesrvAddr + '\'' +
        ", maxMessageSize=" + maxMessageSize +
        ", sendMsgTimeout=" + sendMsgTimeout +
        ", retryTimesWhenSendFailed=" + retryTimesWhenSendFailed +
        '}';
  }


}
