package com.woodpecker.framework.bind;

import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.framework.bind.dto.BindCardDto;

/**
 * 接口描述:〈鉴权Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface AuthService {


  /**
   * 方法功能描述: 绑卡
   *
   * @param bindCardDto BindCardDto
   * @param bindCardEnums BindCardEnum数组
   * @return int
   */
  int bindCard(BindCardDto bindCardDto, BindCardEnum[] bindCardEnums);


  /**
   * 方法功能描述: 鉴权
   *
   * @param loanOrderId loanOrderId
   * @param bindCardEnums BindCardEnum数组
   * @param code 短信验证码
   * @return int
   */
  int auth(int loanOrderId, BindCardEnum[] bindCardEnums, String code);


  /**
   * 方法功能描述: 判断是否已鉴权
   *
   * @param bankAccountEntity BankAccountEntity
   * @param bindCardEnum BindCardEnum
   * @return boolean
   */
  boolean isAuth(BankAccountEntity bankAccountEntity, BindCardEnum bindCardEnum);


}
