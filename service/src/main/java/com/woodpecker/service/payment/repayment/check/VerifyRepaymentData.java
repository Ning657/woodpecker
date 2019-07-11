package com.woodpecker.service.payment.repayment.check;

import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.dao.loandb.TradeOrderDao;
import com.woodpecker.dao.payment.TransactionDao;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.xujinjian.HttpClient.HttpResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口描述:〈还款后的数据校验〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class VerifyRepaymentData {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private RepaymentCheckPointService repaymentCheckPointService;

  @Autowired
  private TradeOrderDao tradeOrderDao;

  @Autowired
  private TransactionDao transactionDao;

  @Autowired
  private SinglePremiumScheduleDao singlePremiumScheduleDao;

  @Autowired
  private RepaymentScheduleDao repaymentScheduleDao;


  /**
   * 方法功能描述: 还款后校验还款接口返回内容是否正确
   *
   * @param httpResponse HttpResponse
   * @return void
   */
  public void verify(HttpResponse httpResponse) {
    repaymentCheckPointService.checkHttpResponse(httpResponse);
  }


  /**
   * 方法功能描述: 校验还款计划所有期数，主要用于催收充值将订单整笔还清后的校验
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  public void verifyRepaymentSchedules(int loanOrderId) {
    //根据loanOrderId查找t_repayment_schedule表数据
    List<RepaymentScheduleEntity> repaymentSchedules = repaymentScheduleDao
        .findByLoanOrderId(loanOrderId);
    //
    for (RepaymentScheduleEntity repaymentScheduleEntity : repaymentSchedules) {
      //校验t_repayment_schedule表数据
      repaymentCheckPointService.checkRepaymentScheduleTable(repaymentScheduleEntity);
    }
  }


  /**
   * 方法功能描述: 还款后校验t_tp_trade_order、t_tp_transaction、t_repayment_schedule、qs_single_premium_schedule表数据
   *
   * @param tradeNo tradeNo
   * @param payNo payNo
   * @param payWay payWay
   * @param payPlatform payPlatform
   * @param channel channel
   * @param scheduleType scheduleType
   * @param scheduleId scheduleId
   * @return void
   */
  public void verify(String tradeNo, String payNo, byte payWay, byte payPlatform, String channel,
      ScheduleTypeEnum scheduleType, int scheduleId) {
    //校验t_tp_trade_order表数据
    repaymentCheckPointService
        .checkTradeOrderTable(tradeOrderDao.findByTradeNo(tradeNo), payWay, payPlatform, channel);
    //校验t_tp_transaction表数据
    repaymentCheckPointService
        .checkTransactionTable(transactionDao.findByOrderNo(payNo), payPlatform);
    //
    switch (scheduleType) {
      case PREMIUM:
        //校验qs_single_premium_schedule表数据
        repaymentCheckPointService
            .checkSinglePremiumScheduleTable(singlePremiumScheduleDao.findById(scheduleId));
        break;
      case REPAYMENT:
        //校验t_repayment_schedule表数据
        repaymentCheckPointService
            .checkRepaymentScheduleTable(repaymentScheduleDao.findById(scheduleId));
        break;
      default:
        logger.error("还款后的数据库校验不支持[{}({})]", scheduleType.name(), scheduleType.getDesc());
        break;
    }
  }


}
