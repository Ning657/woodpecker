package com.woodpecker.service.payment.cache;

import com.woodpecker.commons.util.RedisUtil;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈清除Redis缓存〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  RedisUtil redisUtil;


  /**
   * 方法功能描述: 删除缓存
   *
   * @param key key
   * @return void
   */
  @Override
  public void deleteCache(String... key) {
    for (String k : key) {
      logger.debug("删除缓存[{}]", k);
    }
    redisUtil.delete(key);
  }


  /**
   * 方法功能描述: 删除缓存
   *
   * @param key key
   * @return void
   */
  @Override
  public void delete(String key) {
    logger.debug("删除缓存[{}]", key);
    redisUtil.delete(key);
  }


  /**
   * 方法功能描述: 获取缓存
   *
   * @param key key
   * @return java.lang.Object
   */
  @Override
  public Object getCache(String key) {
    Object cache = redisUtil.get(key);
    logger.debug("获取到缓存key=[{}]->[{}]", key, cache);
    return cache;
  }


  /**
   * 方法功能描述: 获取key
   *
   * @param pattern key模糊匹配
   * @return java.util.Set<java.lang.String>
   */
  @Override
  public Set<String> getKeys(String pattern) {
    return redisUtil.keys(pattern);
  }


  /**
   * 方法功能描述: 判断缓存是否存在
   *
   * @param key key
   * @return boolean
   */
  @Override
  public boolean hasCache(String key) {
    return redisUtil.hasKey(key);
  }


}
