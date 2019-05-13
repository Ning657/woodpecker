package com.woodpecker.service.databuild;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 类描述:〈databuild订单相关功能service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface DataBuildOrderService {


  /**
   * 方法功能描述: 造订单(新订单)
   *
   * @param userId 借款人Id
   * @param applyAmount 申请金额
   * @param riskLevelEnum 风险等级
   * @param loanPeriodEnum 期数
   * @param appIdEnum appName
   * @param productCodeEnum ProdCode
   * @param orderNum 创建数量
   * @return java.util.List<String>
   */
  List<String> create(String userId, BigDecimal applyAmount, RiskLevelEnum riskLevelEnum,
      LoanPeriodEnum loanPeriodEnum, AppIdEnum appIdEnum, ProductCodeEnum productCodeEnum,
      Integer orderNum);


  /**
   * 方法功能描述: 造订单(已存证待分发)
   *
   * @param userId 借款人Id
   * @param applyAmount 申请金额
   * @param riskLevelEnum 风险等级
   * @param loanPeriodEnum 期数
   * @param appIdEnum appName
   * @param productCodeEnum ProdCode
   * @param cleanPlatformIdEnum 需要洗数的渠道
   * @param orderNum 创建数量
   * @return java.util.List<String>
   */
  List<String> create(String userId, BigDecimal applyAmount, RiskLevelEnum riskLevelEnum,
      LoanPeriodEnum loanPeriodEnum, AppIdEnum appIdEnum, ProductCodeEnum productCodeEnum,
      CleanPlatformIdEnum cleanPlatformIdEnum, Integer orderNum);


  /**
   * 方法功能描述: 造订单(已签约待推送)
   *
   * @param userId 借款人Id
   * @param applyAmount 申请金额
   * @param riskLevelEnum 风险等级
   * @param loanPeriodEnum 期数
   * @param appIdEnum appName
   * @param productCodeEnum ProdCode
   * @param platformIdEnum 资金渠道
   * @param orderNum 创建数量
   * @return java.util.List<String>
   */
  List<String> create(String userId, BigDecimal applyAmount, RiskLevelEnum riskLevelEnum,
      LoanPeriodEnum loanPeriodEnum, AppIdEnum appIdEnum, ProductCodeEnum productCodeEnum,
      PlatformIdEnum platformIdEnum, Integer orderNum);


  /**
   * 方法功能描述: 造订单(还款订单)
   *
   * @param userId 借款人Id
   * @param platformIdEnum 资金渠道
   * @param version 版本
   * @return java.util.Map
   */
  Map<String, String> create(String userId, PlatformIdEnum platformIdEnum, String version);


  /**
   * 方法功能描述: 删除订单
   *
   * @param orderIds 原始订单Id(多个用英文,间隔)
   * @return boolean
   */
  boolean delete(String orderIds);


  /**
   * 方法功能描述: 删除订单
   *
   * @param userId 借款人Id(多个用英文,间隔)
   * @param platformIdEnum 资金渠道
   * @return boolean
   */
  boolean delete(String userId, PlatformIdEnum platformIdEnum);

}
