package com.woodpecker.framework.mq.verify.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.framework.mq.verify.AbstractMqVerifyProcessor;
import com.woodpecker.framework.mq.verify.Constants;
import com.woodpecker.framework.mq.verify.ConsumerGroupEnum;
import com.woodpecker.framework.mq.verify.MqQueryService;
import com.woodpecker.framework.mq.verify.MqVerifyService;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.framework.mq.verify.TopicEnum;
import com.xujinjian.Commons.Lang.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈校验MQ消息内容，不会校验id，只会校验payNo和userId〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@MqVerifyService(scheduleType = ScheduleTypeEnum.NONE)
public class NoneMqVerifyProcessor extends AbstractMqVerifyProcessor {

  @Autowired
  protected MqQueryService mqQueryService;

  @Autowired
  protected LoanOrderDao loanOrderDao;

  protected String topic = TopicEnum.RECHARGE.getValue();

  protected String consumerGroup = ConsumerGroupEnum.ACCOUNTING.getValue();


  /**
   * 方法功能描述: 校验MQ消息
   *
   * @param payNo payNo
   * @param id loanOrderId/还款计划Id/趸交计划Id
   * @return boolean
   */
  @Override
  public boolean doVerify(String payNo, String id) {
    //是否找到相应的MQ消息标记位
    boolean isExist = false;

    String userId = null;
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(Integer.parseInt(id));
    if (null == loanOrderEntity) {
      logger.error("没有找到id=[{}]的t_loan_order记录", id);
      return false;
    }
    userId = String.valueOf(loanOrderEntity.getUserId());

    //先查询发出去的MQ列表，此列表包含MQ的id，但不包含MQ的消息内容
    List<JSONObject> jsonObjectList = mqQueryService.queryMessageByTopic(super.time, this.topic);
    for (JSONObject json : jsonObjectList) {
      //取出MQ消息的ID
      String msgId = json.getString(Constants.MSGID);
      //根据MQ的ID查询MQ具体的消息内容
      JSONObject msgJsonObject = mqQueryService.queryMessageByMsgId(msgId, this.topic);
      JSONArray array = msgJsonObject.getJSONArray(Constants.MESSAGETRACKLIST);
      if (array.size() > 0) {
        JSONObject jsonObject = array.getJSONObject(0);
        String consumerGroup = jsonObject.getString(Constants.CONSUMERGROUP);
        boolean flag = StringUtil.equalsIgnoreCase(this.consumerGroup, consumerGroup);
        //判断是否为accounting消费的MQ消息
        if (flag) {
          //是accounting消费的MQ消息
          JSONObject messageView = msgJsonObject.getJSONObject(Constants.MESSAGEVIEW);
          JSONObject messageBody = messageView.getJSONObject(Constants.MESSAGEBODY);
          //
          String actPayNo = messageBody.getString(Constants.PAYNO);
          String actUserId = messageBody.getString(Constants.USERID);
          //判断这条MQ消息是否是我们找的MQ
          if (StringUtil.equals(actPayNo, payNo) && StringUtil.equals(actUserId, userId)) {
            logger.debug("找到发出去的MQ=[{}]", msgJsonObject);
            //匹配到了相应的MQ消息
            isExist = true;
            break;
          }
        }
      }
    }
    //返回
    return isExist;
  }


  public MqQueryService getMqQueryService() {
    return mqQueryService;
  }


  public void setMqQueryService(MqQueryService mqQueryService) {
    this.mqQueryService = mqQueryService;
  }


  public LoanOrderDao getLoanOrderDao() {
    return loanOrderDao;
  }


  public void setLoanOrderDao(LoanOrderDao loanOrderDao) {
    this.loanOrderDao = loanOrderDao;
  }


}
