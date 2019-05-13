package com.woodpecker.commons.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by wuminlang on 15/7/7.
 */
public class SignUtils {

  private static final String KEY_ALGORITHM = "RSA";
  private static String ENCODE = "UTF-8";
  private static final String SIGN_ALGORITHM = "SHA256withRSA";

  /**
   * 将所有参数值按升序排序
   */
  public static String sortParamsToSign(Map<String, String> params)
      throws UnsupportedEncodingException {
    // 按参数名字典排序
    List<String> valList = Arrays.asList(params.keySet().toArray(new String[params.size()]));
    Collections.sort(valList);

    StringBuilder sb = new StringBuilder();

    for (String k : valList) {

      // 跳过 不被签名参数
      if (k.equals("sign")) {
        continue;
      }
      sb.append(k).append("=").append(URLEncoder.encode(params.get(k), ENCODE)).append("&");
    }
    if (params.size() > 1) {
      sb.delete(sb.length() - 1, sb.length()); // 去掉最后一个字符
    }
    return sb.toString();
  }

  /**
   * https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Signature
   */
  public static String sign(String plain, PrivateKey pk) {
    try {
      Signature ex = Signature.getInstance(SIGN_ALGORITHM);
      ex.initSign(pk);
      ex.update(plain.getBytes(ENCODE));
      byte[] rex1 = ex.sign();
      String sign = new String(Base64.encode(rex1));
      return sign;
    } catch (Exception var5) {
      RuntimeException rex = new RuntimeException(var5.getMessage());
      rex.setStackTrace(var5.getStackTrace());
      throw rex;
    }
  }

  public static boolean check(String content, String sign, PublicKey pubKey)
      throws SignatureException {
    try {
      Signature signature = Signature.getInstance(SIGN_ALGORITHM);// MD5withRSA SHA1WithRSA
      // SHA256WithRSA
      signature.initVerify(pubKey);
      byte[] encodedKey = content.getBytes(ENCODE);
      signature.update(encodedKey);
      // signature.update(getContentBytes(content, charset));
      return signature.verify(Base64.decode(sign));
    } catch (Exception e) {
      throw new SignatureException("RSA验证签名[content = " + content + "; charset = " + ENCODE
          + "; signature = " + sign + "]发生异常!", e);
    }
  }

  public static RSAPrivateKey getPrivateKey(byte[] keyBytes) throws Exception {
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    return (RSAPrivateKey) keyFactory.generatePrivate(spec);
  }

  public static RSAPrivateKey getPrivateKey(String privateKey) throws Exception {
    byte[] keyBytes = Base64.decode(privateKey);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    return (RSAPrivateKey) keyFactory.generatePrivate(spec);
  }

  public static RSAPublicKey getPublicKey(String publicKey) throws Exception {
    byte[] keyBytes = Base64.decode(publicKey);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
    return (RSAPublicKey) keyFactory.generatePublic(spec);
  }
}
