package com.woodpecker.service.payment.cache;

import java.util.Set;

/**
 * 接口描述:〈清除Redis缓存〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface RedisCacheService {


  /**
   * 方法功能描述: 删除缓存
   *
   * @param key key
   * @return void
   */
  void deleteCache(String... key);

  /**
   * 方法功能描述: 删除缓存
   *
   * @param key key
   * @return void
   */
  void delete(String key);

  /**
   * 方法功能描述: 获取缓存
   *
   * @param key key
   * @return java.lang.Object
   */
  Object getCache(String key);


  /**
   * 方法功能描述: 获取key
   *
   * @param pattern key模糊匹配
   * @return java.util.Set<java.lang.String>
   */
  Set<String> getKeys(String pattern);


  /**
   * 方法功能描述: 判断缓存是否存在
   *
   * @param key key
   * @return boolean
   */
  boolean hasCache(String key);


}
