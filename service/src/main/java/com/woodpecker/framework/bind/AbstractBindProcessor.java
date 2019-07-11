package com.woodpecker.framework.bind;

import com.woodpecker.framework.bind.dto.BindCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类描述:〈绑卡操作执行器，所有具体的绑卡实现类都需要继承此类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public abstract class AbstractBindProcessor implements BindProcessor {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  protected String data = "data";


  /**
   * 方法功能描述: 执行绑卡操作
   *
   * @param bindCardDto BindCardDto
   * @return int
   */
  @Override
  public int handle(BindCardDto bindCardDto) {
    if (null == bindCardDto) {
      logger.error("参数BindCardDto不能为空 bindCardDto=[null]");
      return 0;
    }
    //执行具体的绑卡操作，抛出钩子供真正的绑卡服务调用
    return doBind(bindCardDto);
  }


  /**
   * 方法功能描述: 绑卡
   *
   * @param bindCardDto BindCardDto
   * @return int
   */
  protected abstract int doBind(BindCardDto bindCardDto);


}
