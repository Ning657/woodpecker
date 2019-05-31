package com.woodpecker.testcase.payment;

import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.dao.loandb.TradeOrderDao;
import com.woodpecker.dao.payment.TransactionDao;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.entity.loandb.RepaymentScheduleEntity;
import com.woodpecker.entity.loandb.SinglePremiumScheduleEntity;
import com.woodpecker.service.config.Account;
import com.woodpecker.service.databuild.DataBuildOrderService;
import com.woodpecker.service.payment.trade.RepaymentFactory;
import com.woodpecker.service.pub.DataAnalysisService;
import com.woodpecker.service.superdiamond.SuperdiamondService;
import com.woodpecker.testcase.TestCase;
import com.xujinjian.Commons.Lang.ThreadUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.Assert;

/**
 * 类描述:〈支付TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class PaymentTestCase extends TestCase {

  @Value("${testcase.userId}")
  protected String userId;

  @Autowired
  protected Account account;

  @Autowired
  protected SuperdiamondService superdiamondService;

  @Autowired
  protected DataAnalysisService dataAnalysisService;

  @Autowired
  protected RepaymentScheduleDao repaymentScheduleDao;

  @Autowired
  protected SinglePremiumScheduleDao singlePremiumScheduleDao;

  @Autowired
  protected LoanOrderDao loanOrderDao;

  @Autowired
  protected BankAccountDao bankAccountDao;

  @Autowired
  protected RepaymentFactory repaymentFactory;

  @Autowired
  protected DataBuildOrderService dataBuildOrderService;

  @Autowired
  protected TradeOrderDao tradeOrderDao;

  @Autowired
  protected TransactionDao transactionDao;

  /**
   * 常规的等待时间，2秒
   */
  protected int sleepTime = 2;


  /**
   * 方法功能描述: mock支付渠道
   *
   * @param payChannel 支付渠道
   * @return void
   */
  public void mockChannel(String payChannel) {
    String userName = account.getSdUserCode();
    String password = account.getSdPassword();
    Assert.assertNotNull(userName, "校验登录Superdiamond的账号是否为空");
    Assert.assertNotNull(password, "校验登录Superdiamond的密码是否为空");
    //打开Superdiamond登录页面
    superdiamondService.openSuperdiamond();
    //登录superdiamond
    boolean loginResult = superdiamondService.login(userName, password);
    Assert.assertTrue(loginResult, "校验登录Superdiamond是否成功");
    //搜索tp-payment-transaction项目的配置
    String projectName = "tp-payment-transaction";
    boolean isExistProject = superdiamondService.search(projectName);
    Assert.assertTrue(isExistProject, "校验是否搜索到项目" + projectName);
    //点击Profiles --> development
    String profiles = "development";
    boolean isProjectPage = superdiamondService.gotoProject(projectName, profiles);
    Assert.assertTrue(isProjectPage, "校验是否进入到tp-payment-transaction项目的development配置");
    //选择Module --> MOCK
    String moduleName = "MOCK";
    superdiamondService.selectModule(moduleName);
    //新增配置，mock指定支付渠道
    String configKey = "transaction.tran.mock.platforms";
    String configValue = "\"" + payChannel + "\"";
    boolean append = true;
    boolean deleteLastChar = true;
    String separator = ",";
    boolean updateSuccess = superdiamondService
        .addConfig(configKey, configValue, append, deleteLastChar, separator);
    Assert.assertTrue(updateSuccess, "校验指定用户的支付渠道为指定渠道是否成功");
    //修改配置中心后，暂停几秒，等待配置生效
    ThreadUtil.sleep(sleepTime);
  }


  /**
   * 方法功能描述: 获取订单总金额(趸交金额+每期还款计划的应还金额)
   *
   * @param loanOrderId loanOrderId
   * @return java.math.BigDecimal
   */
  public BigDecimal getRepaymentSumAmount(Integer loanOrderId) {
    BigDecimal amount = new BigDecimal(0);
    //获取出趸交计划需要还款的金额
    SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findByLoanOrderId(loanOrderId);
    BigDecimal singlePremiumAmount = singlePremiumScheduleEntity.getAmount();
    //加上趸交金额
    amount = amount.add(singlePremiumAmount);
    //获取出还款计划需要还款的总金额
    List<RepaymentScheduleEntity> repaymentScheduleEntities = repaymentScheduleDao
        .findByLoanOrderId(loanOrderId);
    for (RepaymentScheduleEntity repaymentScheduleEntity : repaymentScheduleEntities) {
      //获取出当期应还金额
      BigDecimal repaymentAmount = repaymentScheduleEntity.getAmount();
      //加上当期金额
      amount = amount.add(repaymentAmount);
    }
    return amount;
  }


  /**
   * 方法功能描述: 将订单置为已结清
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  public void clearLoanOrderSchedule(Integer loanOrderId) {
    //将趸交计划置为已还清
    clearSinglePremiumSchedule(loanOrderId);
    //将还款计划的所有期都置为已还清
    int size = repaymentScheduleDao.findByLoanOrderId(loanOrderId).size();
    for (int i = 1; i <= size; i++) {
      //一期期的置为已还清
      clearRepaymentSchedule(loanOrderId, (byte) i);
    }
    //将订单t_loan_order表置为已结清状态
    clearLoanOrder(loanOrderId);
  }


  /**
   * 方法功能描述: 将还款计划的某一期置为已还清
   *
   * @param loanOrderId loanOrderId
   * @param stage 期数
   * @return void
   */
  public void clearRepaymentSchedule(Integer loanOrderId, Byte stage) {
    RepaymentScheduleEntity repaymentScheduleEntity = repaymentScheduleDao
        .findByLoanOrderIdAndStage(loanOrderId, stage);
    if (repaymentScheduleEntity.getIsClear().intValue() == 0) {
      repaymentScheduleEntity.setRepayAt(new Date());
      repaymentScheduleEntity.setIsClear((byte) 1);
      repaymentScheduleEntity.setStatus((byte) 11);
      repaymentScheduleDao.saveAndFlush(repaymentScheduleEntity);
    }
  }


  /**
   * 方法功能描述: 将趸交计划置为已还清
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  public void clearSinglePremiumSchedule(Integer loanOrderId) {
    SinglePremiumScheduleEntity singlePremiumScheduleEntity = singlePremiumScheduleDao
        .findByLoanOrderId(loanOrderId);
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


  /**
   * 方法功能描述: 将订单t_loan_order表置为已结清状态
   *
   * @param loanOrderId loanOrderId
   * @return void
   */
  public void clearLoanOrder(Integer loanOrderId) {
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId);
    if (loanOrderEntity.getStatus().intValue() != 7) {
      loanOrderEntity.setStatus((byte) 7);
      loanOrderDao.saveAndFlush(loanOrderEntity);
    }
  }


}
