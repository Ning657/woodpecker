package com.woodpecker.testcase.payment.bindcard;

import com.sword.autotest.framework.assertion.Validate;
import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.loandb.CustInfoDao;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.dao.loandb.OrderOriginalDao;
import com.woodpecker.dao.payment.AccountDao;
import com.woodpecker.dao.payment.CardInfoDao;
import com.woodpecker.dao.payment.IntermediateDao;
import com.woodpecker.dao.payment.TransactionDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.entity.loandb.OrderOriginalEntity;
import com.woodpecker.entity.payment.AccountEntity;
import com.woodpecker.entity.payment.CardInfoEntity;
import com.woodpecker.service.databuild.DataBuildOrderService;
import com.woodpecker.service.payment.trade.BindCardService;
import com.woodpecker.service.pub.DataAnalysisService;
import com.woodpecker.testcase.payment.PaymentTestCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * 类描述:〈绑卡TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class BindCardTestCase extends PaymentTestCase {

  @Autowired
  protected DataBuildOrderService dataBuildOrderService;

  @Autowired
  protected BindCardService bindCardService;

  @Autowired
  protected CustInfoDao custInfoDao;

  @Autowired
  protected CardInfoDao cardInfoDao;

  @Autowired
  protected BankAccountDao bankAccountDao;

  @Autowired
  protected AccountDao accountDao;

  @Autowired
  protected TransactionDao transactionDao;

  @Autowired
  protected IntermediateDao intermediateDao;

  @Autowired
  protected DataAnalysisService dataAnalysisService;

  @Autowired
  protected OrderOriginalDao orderOriginalDao;

  @Autowired
  protected LoanOrderDao loanOrderDao;


  /**
   * 方法功能描述: 校验请求绑卡/确认绑卡接口的返回值
   *
   * @param code 接口返回的json的code
   * @param message 接口返回的json的message
   * @return void
   */
  protected void validateBindCardResponse(String code, String message) {
    Assert.assertEquals(code, "0000", "校验code是否正确");
    Assert.assertEquals(message, "请求成功", "校验message是否正确");
  }


  /**
   * 方法功能描述: 校验绑卡成功后的表数据t_bank_account、t_card_info、t_tp_account
   *
   * @param orderNo orderNo
   * @param bankAccountId bankAccountId
   * @param bindId bindId
   * @param channel channel
   * @return void
   */
  protected void checkBindCardTables(String orderNo, String bankAccountId, String bindId,
      String channel) {
    //校验t_bank_account表
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(Integer.parseInt(bankAccountId));
    Assert.assertNotNull(bankAccountEntity, "校验t_bank_account表是否存在此绑卡记录");
    Validate.isEquals(userId, String.valueOf(bankAccountEntity.getUserId()),
        "校验t_bank_account表的UserId字段是否正确");
    Validate
        .isTrue(bankAccountEntity.getBindId().contains(bindId), "校验t_bank_account表的BindId字段是否正确");
    Validate.isEquals(1, bankAccountEntity.getStatus(), "校验t_bank_account表的Status字段是否正确");
    Validate.isTrue(bankAccountEntity.getChannel().contains(channel),
        "校验t_bank_account表的Channel字段是否正确");
    //校验t_card_info表
    CardInfoEntity cardInfoEntity = cardInfoDao.findByOrderNo(orderNo);
    Assert.assertNotNull(cardInfoEntity, "校验t_card_info表是否存在此绑卡记录");
    Validate.isEquals(1, cardInfoEntity.getStatus(), "校验t_card_info表的STATUS字段是否正确");
    //校验t_tp_account表
    List<AccountEntity> accountEntitys = accountDao.findByAccountNo(bankAccountEntity.getAccount());
    Assert.assertNotNull(accountEntitys, "校验t_tp_account表是否存在此绑卡记录");
    Assert.assertTrue(accountEntitys.size() >= 1, "校验t_tp_account表是否存在此绑卡记录");
    Validate.isEquals(1, accountEntitys.get(accountEntitys.size() - 1).getStatus(),
        "校验t_tp_account表的status字段是否正确");
  }


  /**
   * 方法功能描述: 更新t_order_original表的BankAccountId
   *
   * @param orderId t_order_original表Id
   * @param bankAccountId bankAccountId
   * @return void
   */
  protected void updateOrderOriginalBankAccountId(Integer orderId, Long bankAccountId) {
    OrderOriginalEntity orderOriginalEntity = orderOriginalDao.findById(orderId);
    Assert.assertNotNull(orderOriginalEntity, "校验OrderOriginalEntity不为空");
    if (orderOriginalEntity.getBankAccountId().longValue() != bankAccountId.longValue()) {
      orderOriginalEntity.setBankAccountId(bankAccountId);
      //update
      orderOriginalDao.saveAndFlush(orderOriginalEntity);
    }
  }


  /**
   * 方法功能描述: 更新t_loan_order表的BankAccountId
   *
   * @param orderId t_loan_order表Id
   * @param bankAccountId bankAccountId
   * @return void
   */
  protected void updateLoanOrderBankAccountId(Integer loanOrderId, Long bankAccountId) {
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId);
    Assert.assertNotNull(loanOrderEntity, "校验LoanOrderEntity不为空");
    if (loanOrderEntity.getBankAccountId().longValue() != bankAccountId.longValue()) {
      loanOrderEntity.setBankAccountId(bankAccountId);
      //update
      loanOrderDao.saveAndFlush(loanOrderEntity);
    }
  }


}
