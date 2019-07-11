package com.woodpecker.framework.bind;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Service;

/**
 * 注解描述:〈标记为：绑卡服务〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BindService {


  /**
   * 绑卡渠道
   */
  BindChannelEnum bindChannel();


}
