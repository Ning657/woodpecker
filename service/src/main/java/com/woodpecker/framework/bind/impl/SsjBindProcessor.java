package com.woodpecker.framework.bind.impl;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.framework.bind.AbstractBindProcessor;
import com.woodpecker.framework.bind.BindCardService;
import com.woodpecker.framework.bind.BindChannelEnum;
import com.woodpecker.framework.bind.BindRequestVerifyService;
import com.woodpecker.framework.bind.BindService;
import com.woodpecker.framework.bind.dto.BindCardDto;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈绑卡通道：随手记〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@BindService(bindChannel = BindChannelEnum.SSJPAY)
public class SsjBindProcessor extends AbstractBindProcessor {

  @Autowired
  private BindCardService bindCardService;

  @Autowired
  private BindRequestVerifyService bindRequestVerifyService;

  private String deductPlatform = BindChannelEnum.SSJPAY.getValue();


  /**
   * 方法功能描述: 绑卡
   *
   * @param bindCardDto BindCardDto
   * @return int
   */
  @Override
  protected int doBind(BindCardDto bindCardDto) {
    //强制设置为绑SSJPAY，因为这个类就是绑SSJPAY的
    bindCardDto.setDeductPlatform(deductPlatform);

    //请求绑卡
    HttpResponse requestResponse = bindCardService.requestBindCard(bindCardDto);

    //校验请求绑卡结果
    bindRequestVerifyService.verifyRequestBindCardResult(requestResponse);

    //解析
    String content = requestResponse.getContent();
    logger.debug("请求绑卡返回的内容为[{}]", content);
    JSONObject jsonObject = JsonUtil.parseObject(content);
    //获取出确认绑卡时，需要的orderNo参数
    String orderNo = jsonObject.getString(super.data);
    //set进BindCardDto
    bindCardDto.setOrderNo(orderNo);

    //确认绑卡
    HttpResponse sureResponse = bindCardService.sureBindCard(bindCardDto);

    //校验确认绑卡结果
    bindRequestVerifyService.verifySureBindCardResult(sureResponse);

    //解析
    String result = sureResponse.getContent();
    logger.debug("确认绑卡返回的内容为[{}]", result);
    JSONObject json = JsonUtil.parseObject(result);
    //获取出data，data的值，就是t_bank_account表的id
    String bankAccountIdStr = json.getString(super.data);

    //将bankAccountId返回
    int bankAccountId = Integer.parseInt(bankAccountIdStr);
    return bankAccountId;
  }


}
