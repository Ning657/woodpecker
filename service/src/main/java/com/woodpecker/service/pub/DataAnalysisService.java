package com.woodpecker.service.pub;

/**
 * 接口描述:〈数据解析Service，包含加解密等功能〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface DataAnalysisService {


  /**
   * 方法功能描述: AES数据加密
   *
   * @param data 需要AES加密的数据
   * @return java.lang.String
   */
  String aesEncrypt(String data);


  /**
   * 方法功能描述: AES数据解密
   *
   * @param data 需要AES解密的数据
   * @return java.lang.String
   */
  String aesDecrypt(String data);


}
