package com.woodpecker.service.pub.user;

import com.woodpecker.dao.loandb.CustInfoDao;
import com.woodpecker.entity.loandb.CustInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈User-Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private CustInfoDao custInfoDao;


  /**
   * 方法功能描述: 获取CustInfoEntity
   *
   * @param userId userId
   * @return com.woodpecker.entity.loandb.CustInfoEntity
   */
  @Override
  public CustInfoEntity getCustInfo(int userId) {
    CustInfoEntity custInfoEntity = custInfoDao.findById(userId).get();
    return custInfoEntity;
  }


}
