package com.woodpecker.framework.bind.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 类描述:〈绑卡接口参数dto〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class BindCardDto {

  private String deductPlatform;
  private String bankId;
  private String idCardNo;
  private String userName;
  private String mobile;
  private String cardNo;
  private String trustProjectCode = "";
  private String platformUserId = "";
  private String returnUrl = "";
  private String userId;
  private String code = "123456";
  private String orderNo;
  private String oriOrderId = "";


  /**
   * 方法功能描述: 转换成请求绑卡的参数Map
   *
   * @return java.util.Map
   */
  public Map<String, String> toRequestBindCardMap() {
    Map<String, String> param = new HashMap<>();
    param.put("deductPlatform", deductPlatform);
    param.put("bankId", bankId);
    param.put("idCardNo", idCardNo);
    param.put("userName", userName);
    param.put("mobile", mobile);
    param.put("cardNo", cardNo);
    param.put("trustProjectCode", trustProjectCode);
    param.put("platformUserId", platformUserId);
    param.put("returnUrl", returnUrl);
    return param;
  }


  /**
   * 方法功能描述: 转换成确认绑卡的参数Map
   *
   * @return java.util.Map
   */
  public Map<String, String> toSureBindCardMap() {
    Map<String, String> param = new HashMap<>();
    param.put("mobile", mobile);
    param.put("bankId", bankId);
    param.put("userId", userId);
    param.put("code", code);
    param.put("idCardNo", idCardNo);
    param.put("orderNo", orderNo);
    param.put("cardNo", cardNo);
    param.put("oriOrderId", oriOrderId);
    param.put("trustProjectCode", trustProjectCode);
    param.put("platformUserId", platformUserId);
    return param;
  }


  public String getDeductPlatform() {
    return deductPlatform;
  }

  public void setDeductPlatform(String deductPlatform) {
    this.deductPlatform = deductPlatform;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getCardNo() {
    return cardNo;
  }

  public void setCardNo(String cardNo) {
    this.cardNo = cardNo;
  }

  public String getTrustProjectCode() {
    return trustProjectCode;
  }

  public void setTrustProjectCode(String trustProjectCode) {
    this.trustProjectCode = trustProjectCode;
  }

  public String getPlatformUserId() {
    return platformUserId;
  }

  public void setPlatformUserId(String platformUserId) {
    this.platformUserId = platformUserId;
  }

  public String getReturnUrl() {
    return returnUrl;
  }

  public void setReturnUrl(String returnUrl) {
    this.returnUrl = returnUrl;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getOriOrderId() {
    return oriOrderId;
  }

  public void setOriOrderId(String oriOrderId) {
    this.oriOrderId = oriOrderId;
  }


  @Override
  public String toString() {
    return "BindCardDto{" +
        "deductPlatform='" + deductPlatform + '\'' +
        ", bankId='" + bankId + '\'' +
        ", idCardNo='" + idCardNo + '\'' +
        ", userName='" + userName + '\'' +
        ", mobile='" + mobile + '\'' +
        ", cardNo='" + cardNo + '\'' +
        ", trustProjectCode='" + trustProjectCode + '\'' +
        ", platformUserId='" + platformUserId + '\'' +
        ", returnUrl='" + returnUrl + '\'' +
        ", userId='" + userId + '\'' +
        ", code='" + code + '\'' +
        ", orderNo='" + orderNo + '\'' +
        ", oriOrderId='" + oriOrderId + '\'' +
        '}';
  }


}
