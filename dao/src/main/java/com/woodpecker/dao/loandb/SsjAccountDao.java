package com.woodpecker.dao.loandb;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.loandb.SsjAccountEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈qs_ssj_account表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface SsjAccountDao extends JpaRepository<SsjAccountEntity, Long> {

  List<SsjAccountEntity> findByUserId(Integer userId);

  SsjAccountEntity findByUserIdAndMobileAndIdCardNoAndCardNo(Integer userId, String mobile,
      String idCardNo, String cardNo);

}
