package com.woodpecker.service.pub.user;

import com.woodpecker.entity.loandb.CustInfoEntity;

/**
 * 接口描述:〈User-Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface UserService {


  /**
   * 方法功能描述: 获取CustInfoEntity
   *
   * @param userId userId
   * @return com.woodpecker.entity.loandb.CustInfoEntity
   */
  CustInfoEntity getCustInfo(int userId);


}
