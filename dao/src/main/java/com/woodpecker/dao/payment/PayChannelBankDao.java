package com.woodpecker.dao.payment;

import com.sword.autotest.framework.annotation.Dao;
import com.woodpecker.entity.payment.PayChannelBankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 接口描述:〈rt_pay_channel_bank表dao〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Dao
public interface PayChannelBankDao extends JpaRepository<PayChannelBankEntity, Integer> {

  PayChannelBankEntity findById(Integer id);

  PayChannelBankEntity findByCodeAndPayChannelCodeAndVersion(String code, String payChannelCode,
      String version);

}
