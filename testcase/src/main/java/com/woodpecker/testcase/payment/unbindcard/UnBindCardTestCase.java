package com.woodpecker.testcase.payment.unbindcard;

import com.sword.autotest.framework.assertion.Validate;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.payment.AccountEntity;
import com.woodpecker.entity.payment.CardInfoEntity;
import com.woodpecker.entity.payment.IntermediateEntity;
import com.woodpecker.entity.payment.TransactionEntity;
import com.woodpecker.service.payment.trade.UnBindCardService;
import com.woodpecker.testcase.payment.bindcard.BindCardTestCase;
import com.xujinjian.Commons.Lang.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * 类描述:〈解绑卡TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class UnBindCardTestCase extends BindCardTestCase {

  @Autowired
  protected UnBindCardService unBindCardService;


  /**
   * 方法功能描述: 校验解绑卡后的数据库数据变化是否正确
   *
   * @param bankAccountId bankAccountId
   * @return void
   */
  protected void checkUnBindCardTables(Integer bankAccountId) {
    //校验t_bank_account表的status = 2
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId);
    Assert.assertNotNull(bankAccountEntity, "校验t_bank_account表记录存在");
    Validate.isEquals(2, bankAccountEntity.getStatus(), "校验t_bank_account表的Status字段");
    //校验t_tp_account表的status = 2
    AccountEntity accountEntity = accountDao.findByAccountNoLast(bankAccountEntity.getAccount());
    Assert.assertNotNull(accountEntity, "校验t_tp_account表记录存在");
    Validate.isEquals(2, accountEntity.getStatus(), "校验t_tp_account表的status字段");
    //校验t_card_info表是否有插入解绑记录
    List<CardInfoEntity> cardInfoEntitys = cardInfoDao.findByCardNo(bankAccountEntity.getAccount());
    Assert.assertNotNull(cardInfoEntitys, "校验t_card_info表记录存在");
    boolean flag = false;
    for (CardInfoEntity cardInfoEntity : cardInfoEntitys) {
      if (StringUtil.equals(cardInfoEntity.getAccName(), bankAccountEntity.getName())) {
        if (StringUtil.equals(cardInfoEntity.getMobile(), bankAccountEntity.getMobile())) {
          if (StringUtil.equals(cardInfoEntity.getRetDesc(), "【解绑卡】解绑卡成功")) {
            if (cardInfoEntity.getStatus() == 1) {
              if (cardInfoEntity.getChannel() == -1) {
                flag = true;
              }
            }
          }
        }
      }
    }
    Validate.isTrue(flag, "校验t_card_info表有插入解绑记录");
    flag = false;
    //校验t_tp_intermediate表是否有插入解绑记录
    List<IntermediateEntity> intermediateEntities = intermediateDao
        .find(Long.parseLong(userId), bankAccountEntity.getCertificateNo(),
            bankAccountEntity.getAccount(), (byte) -1, (byte) 2, 3);
    Assert.assertNotNull(intermediateEntities, "校验t_tp_intermediate表有插入解绑记录");
    Validate.isTrue(intermediateEntities.size() > 0, "校验t_tp_intermediate表有插入解绑记录");
    //校验t_tp_transaction表是否有插入解绑记录
    List<TransactionEntity> transactionEntitys = transactionDao
        .find(userId, bankAccountEntity.getCertificateNo(), bankAccountEntity.getMobile(),
            bankAccountEntity.getName(), (byte) -1, bankAccountEntity.getAccount(), "2",
            "unBindCard", 3);
    Assert.assertNotNull(transactionEntitys, "校验t_tp_transaction表有插入解绑记录");
    Validate.isTrue(transactionEntitys.size() > 0, "校验t_tp_transaction表有插入解绑记录");
  }


  /**
   * 方法功能描述: 校验解绑卡接口失败的返回值
   *
   * @param code 接口返回的json的code
   * @param message 接口返回的json的message
   * @param data 接口返回的json的data
   * @return void
   */
  protected void validateUnBindCardResponseFail(String code, String message, boolean data) {
    Validate.isEquals(code, "9999", "校验code是否正确");
    Validate.isTrue(StringUtil.contains(message, "无法解绑"), "校验message是否正确");
    Validate.isFalse(data, "校验data是否正确");
  }


}
