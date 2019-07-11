package com.woodpecker.service.pub.order;

import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import com.woodpecker.service.pub.DataAnalysisService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈订单Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private LoanOrderDao loanOrderDao;

  @Autowired
  private SinglePremiumScheduleDao singlePremiumScheduleDao;

  @Autowired
  private RepaymentScheduleDao repaymentScheduleDao;

  @Autowired
  private BankAccountDao bankAccountDao;

  @Autowired
  private DataAnalysisService dataAnalysisService;


  /**
   * 方法功能描述: 将订单t_loan_order表置为已结清状态
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  @Override
  public void clearLoanOrder(int loanOrderId) {
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId);
    if (null == loanOrderEntity) {
      logger.error("t_loan_order表不存在id=[{}]的记录", loanOrderId);
    } else {
      if (loanOrderEntity.getStatus().intValue() != 7) {
        loanOrderEntity.setStatus((byte) 7);
        loanOrderDao.saveAndFlush(loanOrderEntity);
      }
    }
  }


  /**
   * 方法功能描述: 将趸交计划qs_single_premium_schedule表置为已还清
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  @Override
  public void clearSinglePremiumSchedule(int loanOrderId) {
    SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findByLoanOrderId(loanOrderId);
    if (null == singlePremiumScheduleEntity) {
      logger.error("qs_single_premium_schedule表不存在loanOrderId=[{}]的记录", loanOrderId);
    } else {
      if (singlePremiumScheduleEntity.getCleared().intValue() != 1) {
        singlePremiumScheduleEntity.setCleared((byte) 1);
        singlePremiumScheduleEntity.setRepayAt(new Date());
        singlePremiumScheduleEntity.setStatus((byte) 11);
        singlePremiumScheduleEntity
            .setRepaidGratuity(singlePremiumScheduleEntity.getRepaidGratuity());
        singlePremiumScheduleEntity.setRepaidAmount(singlePremiumScheduleEntity.getRepaidAmount());
        singlePremiumScheduleDao.saveAndFlush(singlePremiumScheduleEntity);
      }
    }
  }


  /**
   * 方法功能描述: 将趸交计划qs_single_premium_schedule表置为未还
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  @Override
  public void setSinglePremiumScheduleNeedRepayment(int loanOrderId) {
    //读取趸交计划
    SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findByLoanOrderId(loanOrderId);
    if (null == singlePremiumScheduleEntity) {
      logger.error("qs_single_premium_schedule表不存在loanOrderId=[{}]的记录", loanOrderId);
    } else {
      //把趸交计划改为「未还」
      singlePremiumScheduleEntity.setCleared((byte) 0);
      singlePremiumScheduleEntity.setRepayAt(null);
      singlePremiumScheduleEntity.setRepaidGratuity(new BigDecimal(0));
      singlePremiumScheduleEntity.setRepaidAmount(new BigDecimal(0));
      singlePremiumScheduleEntity.setStatus((byte) 1);
      singlePremiumScheduleDao.save(singlePremiumScheduleEntity);
    }
  }


  /**
   * 方法功能描述: 将还款计划的某一期置为已还清
   *
   * @param loanOrderId loanOrderId
   * @param stage 第几期
   * @return void
   */
  @Override
  public void clearRepaymentSchedule(int loanOrderId, byte stage) {
    RepaymentScheduleEntity repaymentScheduleEntity = repaymentScheduleDao
        .findByLoanOrderIdAndStage(loanOrderId, stage);
    if (null == repaymentScheduleEntity) {
      logger.error("t_repayment_schedule表不存在loanOrderId=[{}] & stage=[{}]的记录", loanOrderId, stage);
    } else {
      if (repaymentScheduleEntity.getIsClear().intValue() == 0) {
        repaymentScheduleEntity.setRepayAt(new Date());
        repaymentScheduleEntity.setIsClear((byte) 1);
        repaymentScheduleEntity.setStatus((byte) 11);
        repaymentScheduleDao.saveAndFlush(repaymentScheduleEntity);
      }
    }
  }


  /**
   * 方法功能描述: 将订单置为已结清(t_loan_order、qs_single_premium_schedule、t_repayment_schedule表都会结清)
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  @Override
  public void clearLoanOrderSchedule(int loanOrderId) {
    //将趸交计划置为已还清
    clearSinglePremiumSchedule(loanOrderId);
    //将还款计划的所有期都置为已还清
    List<RepaymentScheduleEntity> repaymentSchedules = repaymentScheduleDao
        .findByLoanOrderId(loanOrderId);
    if (null == repaymentSchedules || repaymentSchedules.size() < 1) {
      logger.error("t_repayment_schedule表不存在loanOrderId=[{}]的记录", loanOrderId);
    } else {
      int size = repaymentSchedules.size();
      for (int i = 1; i <= size; i++) {
        //一期期的置为已还清
        clearRepaymentSchedule(loanOrderId, (byte) i);
      }
    }
    //将订单t_loan_order表置为已结清状态
    clearLoanOrder(loanOrderId);
  }


  /**
   * 方法功能描述: 获取订单总金额(趸交金额+每期还款计划的应还金额)
   *
   * @param loanOrderId loanOrderId
   * @return java.math.BigDecimal
   */
  @Override
  public BigDecimal getRepaymentSumAmount(int loanOrderId) {
    BigDecimal amount = new BigDecimal(0);

    //获取出趸交计划需要还款的金额
    SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findByLoanOrderId(loanOrderId);
    if (null == singlePremiumScheduleEntity) {
      logger.error("qs_single_premium_schedule表不存在loanOrderId=[{}]的记录", loanOrderId);
    } else {
      BigDecimal singlePremiumAmount = singlePremiumScheduleEntity.getAmount();
      //加上趸交金额
      amount = amount.add(singlePremiumAmount);
    }

    List<RepaymentScheduleEntity> repaymentScheduleEntities = repaymentScheduleDao
        .findByLoanOrderId(loanOrderId);
    if (null == repaymentScheduleEntities || repaymentScheduleEntities.size() < 1) {
      logger.error("t_repayment_schedule表不存在loanOrderId=[{}]的记录", loanOrderId);
    } else {
      //获取出还款计划需要还款的总金额
      for (RepaymentScheduleEntity repaymentScheduleEntity : repaymentScheduleEntities) {
        //获取出当期应还金额
        BigDecimal repaymentAmount = repaymentScheduleEntity.getAmount();
        //加上当期金额
        amount = amount.add(repaymentAmount);
      }
    }

    return amount;
  }


  /**
   * 方法功能描述: 获取t_loan_order所对应的银行卡卡号
   *
   * @param loanOrderId loanOrderId
   * @return java.lang.String
   */
  @Override
  public String getCardNoByLoanOrderId(int loanOrderId) {
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId);
    if (null == loanOrderEntity) {
      logger.error("t_loan_order表不存在id=[{}]的记录", loanOrderId);
    } else {
      int bankAccountId = loanOrderEntity.getBankAccountId().intValue();
      BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId);
      if (null == bankAccountEntity) {
        logger.error("t_bank_account表不存在id=[{}]的记录", bankAccountId);
      } else {
        String account = bankAccountEntity.getAccount();
        return dataAnalysisService.aesDecrypt(account);
      }
    }
    return "";
  }


  /**
   * 方法功能描述: 获取t_loan_order所对应的银行卡的bankId
   *
   * @param loanOrderId loanOrderId
   * @return int
   */
  @Override
  public int getBankIdByLoanOrderId(int loanOrderId) {
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId);
    if (null == loanOrderEntity) {
      logger.error("t_loan_order表不存在id=[{}]的记录", loanOrderId);
    } else {
      int bankAccountId = loanOrderEntity.getBankAccountId().intValue();
      BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId);
      if (null == bankAccountEntity) {
        logger.error("t_bank_account表不存在id=[{}]的记录", bankAccountId);
      } else {
        return bankAccountEntity.getBankId();
      }
    }
    return 0;
  }


}
