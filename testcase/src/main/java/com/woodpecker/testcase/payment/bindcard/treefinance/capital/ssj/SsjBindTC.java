package com.woodpecker.testcase.payment.bindcard.treefinance.capital.ssj;

import com.woodpecker.entity.loandb.CustInfoEntity;
import com.woodpecker.framework.bind.BindChannelEnum;
import com.woodpecker.framework.bind.dto.BindCardDto;
import com.woodpecker.testcase.payment.bindcard.BindTestCase;
import com.xujinjian.Commons.Math.MathUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 类描述:〈资金方鉴权：随手记〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class SsjBindTC extends BindTestCase {

  BindChannelEnum bindChannel = BindChannelEnum.SSJPAY;


  @BeforeClass
  public void ready() {
    //mock
    superdiamond.mockChannel(bindChannel.getValue());
  }


  @Test(description = "随手记鉴权", timeOut = 60000)
  public void auth() {
    String cardNoPrefix = "6216611300008";
    //生成6位随机数，充当银行卡卡号的后6位
    int cardNoSuffix = MathUtil.getRandomBySize(6);
    //这样就组成了一个随机的银行卡卡号，避免重复
    String cardNo = cardNoPrefix + cardNoSuffix;
    CustInfoEntity custInfoEntity = userService.getCustInfo(Integer.parseInt(userId));
    Assert.assertNotNull(custInfoEntity, "校验custInfoEntity存在");
    //解密省份证
    String idCardNo = custInfoEntity.getIdCardNo();
    idCardNo = dataAnalysisService.aesDecrypt(idCardNo);
    //BindCardDto
    BindCardDto bindCardDto = new BindCardDto();
    bindCardDto.setDeductPlatform(bindChannel.getValue());
    bindCardDto.setBankId("10");
    bindCardDto.setIdCardNo(idCardNo);
    bindCardDto.setUserName(custInfoEntity.getUserName());
    bindCardDto.setMobile("18758585888");
    bindCardDto.setCardNo(cardNo);
    bindCardDto.setUserId(userId);
    bindCardDto.setCode("123456");
    //绑卡
    int bankAccountId = authService.bindCard(bindCardDto, new BindChannelEnum[]{bindChannel});
    //校验
    verifyBindData.verify(bankAccountId, new BindChannelEnum[]{bindChannel});
  }


}
