package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.WangXinAccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈qs_wangxin_account表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface WangXinAccountDao extends JpaRepository<WangXinAccountEntity, Long> {

  List<WangXinAccountEntity> findByUserId(Integer userId);

  WangXinAccountEntity findByUserIdAndMobileAndIdCardNoAndBankAccountId(Integer userId,
      String mobile, String idCardNo, Integer bankAccountId);

  List<WangXinAccountEntity> findByOpenId(String openId);

}
