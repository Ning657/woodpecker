package com.woodpecker.framework.bind;

import com.woodpecker.framework.bind.dto.BindCardDto;
import com.xujinjian.HttpClient.HttpResponse;

/**
 * 接口描述:〈绑卡Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface BindCardService {


  /**
   * 方法功能描述: 请求绑卡
   *
   * @param bindCardDto BindCardDto
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse requestBindCard(BindCardDto bindCardDto);


  /**
   * 方法功能描述: 确认绑卡
   *
   * @param bindCardDto BindCardDto
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  HttpResponse sureBindCard(BindCardDto bindCardDto);


}
