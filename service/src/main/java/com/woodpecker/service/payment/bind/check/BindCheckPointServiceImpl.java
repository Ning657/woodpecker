package com.woodpecker.service.payment.bind.check;

import com.sword.autotest.framework.assertion.Validate;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.payment.AccountEntity;
import com.woodpecker.framework.bind.BindChannelEnum;
import com.xujinjian.Json.JsonUtil;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.testng.Assert;

/**
 * 类描述:〈绑卡后校验点〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class BindCheckPointServiceImpl implements BindCheckPointService {

  @Value("${testcase.userId}")
  private String userId;


  /**
   * 方法功能描述: 校验t_bank_account表数据
   *
   * @param bankAccountEntity BankAccountEntity
   * @param bindChannels BindChannelEnum数组
   * @return void
   */
  @Override
  public void checkBankAccountTable(BankAccountEntity bankAccountEntity,
      BindChannelEnum[] bindChannels) {
    Assert.assertNotNull(bankAccountEntity, "校验t_bank_account表存在此绑卡记录");
    //检查UserId字段
    Validate.isEquals(userId, String.valueOf(bankAccountEntity.getUserId()),
        "校验t_bank_account表中的UserId字段");
    //
    for (BindChannelEnum bindChannel : bindChannels) {
      //检查BindId字段
      String bindId = bankAccountEntity.getBindId();
      boolean isExistBindId = JsonUtil.parseObject(bindId).containsKey(bindChannel.getCode());
      Validate
          .isTrue(isExistBindId, "校验t_bank_account表中的BindId字段包含[" + bindChannel.getCode() + "]");
      //检查Channel字段
      String channel = "\"" + bankAccountEntity.getChannel() + "\"";
      boolean isExistChannel = channel.contains(bindChannel.getCode());
      Validate
          .isTrue(isExistChannel, "校验t_bank_account表中的Channel字段包含[" + bindChannel.getCode() + "]");
    }
    //检查Status字段
    byte status = bankAccountEntity.getStatus();
    Validate.isEquals(1, status, "校验t_bank_account表中的Status字段");
  }


  /**
   * 方法功能描述: 校验t_tp_account表数据
   *
   * @param accountEntity AccountEntity
   * @param bindChannels BindChannelEnum数组
   * @return void
   */
  @Override
  public void checkAccountTable(AccountEntity accountEntity, BindChannelEnum[] bindChannels) {
    Assert.assertNotNull(accountEntity, "校验t_tp_account表存在此绑卡记录");
    //检查status字段
    byte status = accountEntity.getStatus();
    Validate.isEquals(1, status, "校验t_tp_account表中的status字段");
    //
    for (BindChannelEnum bindChannel : bindChannels) {
      //检查channel字段
      String channel = accountEntity.getChannel();
      List<String> channels = Arrays.asList(channel.split(","));
      boolean isExistChannel = channels.contains(bindChannel.getCode());
      Validate
          .isTrue(isExistChannel, "校验t_tp_account表中的channel字段包含[" + bindChannel.getCode() + "]");
    }
  }


}
