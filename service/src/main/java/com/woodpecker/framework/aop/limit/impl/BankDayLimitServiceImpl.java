package com.woodpecker.framework.aop.limit.impl;

import com.sword.autotest.framework.exception.TestCaseException;
import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.dao.payment.PayChannelBankDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import com.woodpecker.entity.payment.PayChannelBankEntity;
import com.woodpecker.framework.aop.limit.BankDayLimitService;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈银行日限额Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class BankDayLimitServiceImpl implements BankDayLimitService {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected RepaymentScheduleDao repaymentScheduleDao;

  @Autowired
  protected SinglePremiumScheduleDao singlePremiumScheduleDao;

  @Autowired
  protected LoanOrderDao loanOrderDao;

  @Autowired
  protected BankAccountDao bankAccountDao;

  @Autowired
  protected PayChannelBankDao payChannelBankDao;


  /**
   * 方法功能描述: 获取BankId
   *
   * @param scheduleType ScheduleTypeEnum
   * @param id 还款计划ID/趸交计划ID
   * @return int
   */
  @Override
  public int getBankId(ScheduleTypeEnum scheduleType, int id) {
    int bankId = -1;
    int loanOrderId = getLoanOrderId(scheduleType, id);
    if (0 == loanOrderId) {
      throw new TestCaseException("没有获取到loanOrderId");
    }
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId).get();
    Long bankAccountId = loanOrderEntity.getBankAccountId();
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId.intValue()).get();
    bankId = bankAccountEntity.getBankId();
    return bankId;
  }


  /**
   * 方法功能描述: 获取LoanOrderId
   *
   * @param scheduleType ScheduleTypeEnum
   * @param id 还款计划ID/趸交计划ID/LoanOrderId
   * @return int
   */
  @Override
  public int getLoanOrderId(ScheduleTypeEnum scheduleType, int id) {
    int loanOrderId = 0;
    switch (scheduleType) {
      case REPAYMENT:
        RepaymentScheduleEntity repaymentScheduleEntity = repaymentScheduleDao.findById(id).get();
        loanOrderId = repaymentScheduleEntity.getLoanOrderId();
        break;
      case PREMIUM:
        SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
            .findById(id).get();
        loanOrderId = singlePremiumScheduleEntity.getLoanOrderId();
        break;
      case LOAN_ORDER:
        loanOrderId = id;
        break;
      default:
        logger.error("获取LoanOrderId时，计划类型错误，不支持[{}]", scheduleType.name());
        break;
    }
    return loanOrderId;
  }


  /**
   * 方法功能描述: 获取还款计划/趸交计划的金额
   *
   * @param scheduleType ScheduleTypeEnum
   * @param id 还款计划ID/趸交计划ID
   * @return java.math.BigDecimal
   */
  @Override
  public BigDecimal getAmount(ScheduleTypeEnum scheduleType, int id) {
    BigDecimal amount = null;
    switch (scheduleType) {
      case REPAYMENT:
        RepaymentScheduleEntity repaymentScheduleEntity = repaymentScheduleDao.findById(id).get();
        amount = repaymentScheduleEntity.getAmount();
        break;
      case PREMIUM:
        SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
            .findById(id).get();
        amount = singlePremiumScheduleEntity.getAmount();
        break;
      default:
        logger.error("只能获取还款计划/趸交计划的金额，不支持[{}]", scheduleType.name());
        amount = BigDecimal.ZERO;
        break;
    }
    return amount;
  }


  /**
   * 方法功能描述: 获取银行日限额
   *
   * @param bankId bankId
   * @param payChannelCode rt_pay_channel_bank表的pay_channel_code字段值
   * @param version version
   * @return int
   */
  @Override
  public int getDayAmountLimit(String bankId, String payChannelCode, String version) {
    PayChannelBankEntity payChannelBankEntity = payChannelBankDao
        .findByCodeAndPayChannelCodeAndVersion(bankId, payChannelCode, version);
    return payChannelBankEntity.getDayAmount();
  }


  /**
   * 方法功能描述: 设置银行日限额
   *
   * @param bankId bankId
   * @param payChannelCode rt_pay_channel_bank表的pay_channel_code字段值
   * @param amount 限额金额
   * @param version version
   * @return void
   */
  @Override
  public void setDayAmountLimit(String bankId, String payChannelCode, int amount, String version) {
    PayChannelBankEntity payChannelBankEntity = payChannelBankDao
        .findByCodeAndPayChannelCodeAndVersion(bankId, payChannelCode, version);
    payChannelBankEntity.setDayAmount(amount);
    payChannelBankDao.save(payChannelBankEntity);
  }


}
