package com.woodpecker.service.payment.bind.check;

import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.payment.AccountDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.payment.AccountEntity;
import com.woodpecker.framework.bind.BindChannelEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口描述:〈绑卡后的数据校验〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class VerifyBindData {

  @Autowired
  private BindCheckPointService bindCheckPointService;

  @Autowired
  private BankAccountDao bankAccountDao;

  @Autowired
  private AccountDao accountDao;


  /**
   * 方法功能描述: 绑卡后校验t_bank_account和t_tp_account表数据
   *
   * @param bankAccountId bankAccountId
   * @param bindChannelEnums BindChannelEnum绑卡渠道
   * @return void
   */
  public void verify(int bankAccountId, BindChannelEnum[] bindChannelEnums) {
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId);
    //先校验t_bank_account表
    bindCheckPointService.checkBankAccountTable(bankAccountEntity, bindChannelEnums);
    //
    String accountNo = bankAccountEntity.getAccount();
    AccountEntity accountEntity = accountDao.findByAccountNoAndStatus(accountNo, (byte) 1);
    //校验t_tp_account表
    bindCheckPointService.checkAccountTable(accountEntity, bindChannelEnums);
  }


}
