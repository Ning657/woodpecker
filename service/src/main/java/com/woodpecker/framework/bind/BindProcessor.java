package com.woodpecker.framework.bind;

import com.woodpecker.framework.bind.dto.BindCardDto;

/**
 * 接口描述:〈绑卡操作执行器〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface BindProcessor {


  /**
   * 方法功能描述: 执行绑卡操作
   *
   * @param bindCardDto BindCardDto
   * @return int
   */
  int handle(BindCardDto bindCardDto);


}
