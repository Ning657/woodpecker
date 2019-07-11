package com.woodpecker.service.payment.bind.check;

import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.payment.AccountEntity;
import com.woodpecker.framework.bind.BindChannelEnum;

/**
 * 接口描述:〈绑卡后校验点〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface BindCheckPointService {


  /**
   * 方法功能描述: 校验t_bank_account表数据
   *
   * @param bankAccountEntity BankAccountEntity
   * @param bindChannels BindChannelEnum数组
   * @return void
   */
  void checkBankAccountTable(BankAccountEntity bankAccountEntity, BindChannelEnum[] bindChannels);


  /**
   * 方法功能描述: 校验t_tp_account表数据
   *
   * @param accountEntity AccountEntity
   * @param bindChannels BindChannelEnum数组
   * @return void
   */
  void checkAccountTable(AccountEntity accountEntity, BindChannelEnum[] bindChannels);


}
