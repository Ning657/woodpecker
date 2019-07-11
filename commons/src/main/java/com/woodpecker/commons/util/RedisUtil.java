package com.woodpecker.commons.util;

import com.xujinjian.Commons.Collections.CollectionUtil;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 类描述:〈Redis工具类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
public class RedisUtil {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  private Logger logger = LoggerFactory.getLogger(this.getClass());


  /**
   * 方法功能描述: 判断缓存是否存在
   *
   * @param key Redis缓存的Key
   * @return boolean
   */
  public boolean hasKey(String key) {
    try {
      return redisTemplate.hasKey(key);
    } catch (Throwable e) {
      logger.error(e.getMessage(), e);
      return false;
    }
  }


  /**
   * 方法功能描述: 模糊获取出key
   *
   * @param pattern pattern
   * @return java.util.Set
   */
  public Set<String> keys(String pattern) {
    Set<String> set = redisTemplate.keys(pattern);
    //
    if (CollectionUtil.isNotEmpty(set)) {
      return set;
    }
    return new HashSet<>();
  }


  /**
   * 方法功能描述: 删除缓存
   *
   * @param key Redis缓存的Key
   * @return void
   */
  public void delete(String... key) {
    if (key != null && key.length > 0) {
      if (key.length == 1) {
        redisTemplate.delete(key[0]);
      } else {
        //noinspection unchecked
        redisTemplate.delete(CollectionUtils.arrayToList(key));
      }
    }
  }


  /**
   * 方法功能描述: 获取缓存
   *
   * @param key Redis缓存的Key
   * @return java.lang.Object
   */
  public Object get(String key) {
    return key == null ? null : redisTemplate.opsForValue().get(key);
  }


  /**
   * 方法功能描述: 将缓存放入Redis
   *
   * @param key Redis缓存的Key
   * @param value Redis缓存的值
   * @return void
   */
  public void set(String key, Object value) {
    if (null != key) {
      redisTemplate.opsForValue().set(key, value);
    }
  }


  /**
   * 方法功能描述: 将缓存放入Redis
   *
   * @param key Redis缓存的Key
   * @param value Redis缓存的值
   * @return void
   */
  public void put(String key, Object value) {
    set(key, value);
  }


  /**
   * 方法功能描述: 将缓存放入Redis
   *
   * @param key Redis缓存的Key
   * @param value Redis缓存的值
   * @param time 缓存的有效时间(单位：秒)
   * @return void
   */
  public void set(String key, Object value, long time) {
    if (time > 0) {
      if (null != key) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
      }
    } else {
      set(key, value);
    }
  }


  /**
   * 方法功能描述: 将缓存放入Redis
   *
   * @param key Redis缓存的Key
   * @param value Redis缓存的值
   * @param time 缓存的有效时间(单位：秒)
   * @return void
   */
  public void put(String key, Object value, long time) {
    set(key, value, time);
  }


}
