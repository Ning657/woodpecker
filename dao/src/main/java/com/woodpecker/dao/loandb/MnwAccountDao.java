package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.MnwAccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈qs_mnw_account表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface MnwAccountDao extends JpaRepository<MnwAccountEntity, Long> {

  List<MnwAccountEntity> findByUserId(Integer userId);

  MnwAccountEntity findByUserIdAndMobileAndIdCardNoAndCardNo(Integer userId, String mobile,
      String idCardNo, String cardNo);

  MnwAccountEntity findByUserNameAndMobileAndIdCardNoAndCardNo(String userName, String mobile,
      String idCardNo, String cardNo);
  
}
