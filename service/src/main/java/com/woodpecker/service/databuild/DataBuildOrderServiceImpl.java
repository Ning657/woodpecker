package com.woodpecker.service.databuild;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.commons.util.TestCaseUtil;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈databuild订单相关功能service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Component
@AutoProxy
public class DataBuildOrderServiceImpl implements DataBuildOrderService {

  @AutoHttpRequest
  private HttpApi httpApi;

  @Autowired
  private Host host;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 造订单接口
   */
  private final String addUrl = "/order/add";

  /**
   * 生成还款订单接口
   */
  private final String createUrl = "/order/createRepaymentOrder";

  /**
   * 删除订单接口
   */
  private final String deleteUrl = "/order/delete";

  private final String msg = "成功";


  /**
   * 方法功能描述: 请求造订单接口
   *
   * @param param 造订单接口的参数
   * @return java.util.List<String>
   */
  private List<String> create(Map<String, String> param) {
    String url = host.getDatabuildHost() + addUrl;
    logger.debug("造订单接口[{}]参数为[{}]", url, param);
    HttpResponse httpResponse = httpApi.post(url, param);
    String result = httpResponse.getContent();
    int statusCode = httpResponse.getStatus().getStatusCode();
    logger.debug("造订单接口返回的内容为[{}]", result);
    TestCaseUtil.checkStatusCodeIsOK(statusCode, "校验造订单接口的响应状态码是否为200");
    //提取出orderId
    List<String> orderIds = new ArrayList<>();
    Pattern pattern = Pattern.compile("[1-9]\\d{4,11}");
    Matcher matcher = pattern.matcher(result);
    while (matcher.find()) {
      String orderId = matcher.group();
      logger.debug("orderId=[{}]", orderId);
      orderIds.add(orderId);
    }
    logger.debug("共提取出{}个orderId", orderIds.size());
    logger.debug("提取出的orderIds为{}", orderIds);
    return orderIds;
  }


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
  @Override
  public List<String> create(String userId, BigDecimal applyAmount, RiskLevelEnum riskLevelEnum,
      LoanPeriodEnum loanPeriodEnum, AppIdEnum appIdEnum, ProductCodeEnum productCodeEnum,
      Integer orderNum) {
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", userId);
    data.put("applyAmount", applyAmount.stripTrailingZeros().toPlainString());
    data.put("riskLevel", riskLevelEnum.value);
    data.put("loanPeriod", loanPeriodEnum.value);
    data.put("status", StatusEnum.newOrder.status);
    data.put("appId", appIdEnum.value);
    data.put("productCode", productCodeEnum.value);
    data.put("orderNum", orderNum.toString());
    return create(data);
  }

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
  @Override
  public List<String> create(String userId, BigDecimal applyAmount, RiskLevelEnum riskLevelEnum,
      LoanPeriodEnum loanPeriodEnum, AppIdEnum appIdEnum, ProductCodeEnum productCodeEnum,
      CleanPlatformIdEnum cleanPlatformIdEnum, Integer orderNum) {
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", userId);
    data.put("applyAmount", applyAmount.stripTrailingZeros().toPlainString());
    data.put("riskLevel", riskLevelEnum.value);
    data.put("loanPeriod", loanPeriodEnum.value);
    data.put("status", StatusEnum.waitDistribute.status);
    data.put("appId", appIdEnum.value);
    data.put("productCode", productCodeEnum.value);
    data.put("cleanPlatformId", cleanPlatformIdEnum.value);
    data.put("orderNum", orderNum.toString());
    return create(data);
  }


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
  @Override
  public List<String> create(String userId, BigDecimal applyAmount, RiskLevelEnum riskLevelEnum,
      LoanPeriodEnum loanPeriodEnum, AppIdEnum appIdEnum, ProductCodeEnum productCodeEnum,
      PlatformIdEnum platformIdEnum, Integer orderNum) {
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", userId);
    data.put("applyAmount", applyAmount.stripTrailingZeros().toPlainString());
    data.put("riskLevel", riskLevelEnum.value);
    data.put("loanPeriod", loanPeriodEnum.value);
    data.put("status", StatusEnum.waitPush.status);
    data.put("appId", appIdEnum.value);
    data.put("productCode", productCodeEnum.value);
    data.put("platformId", platformIdEnum.value);
    data.put("orderNum", orderNum.toString());
    return create(data);
  }


  /**
   * 方法功能描述: 造订单(生成还款订单)
   *
   * @param userId 借款人Id
   * @param platformIdEnum 资金渠道
   * @param version 版本
   * @return java.util.Map
   */
  @Override
  public Map<String, String> create(String userId, PlatformIdEnum platformIdEnum, String version) {
    HashMap<String, String> map = new HashMap<>();
    HashMap<String, String> data = new HashMap<>();
    data.put("userId", userId);
    data.put("platFormId", platformIdEnum.value);
    data.put("version", version);
    String url = host.getDatabuildHost() + createUrl;
    logger.debug("生成还款订单的接口[{}]参数为[{}]", url, data);
    HttpResponse httpResponse = httpApi.post(url, data);
    String result = httpResponse.getContent();
    int statusCode = httpResponse.getStatus().getStatusCode();
    logger.debug("生成还款订单接口返回的内容为[{}]", result);
    TestCaseUtil.checkStatusCodeIsOK(statusCode, "校验生成还款订单接口的响应状态码是否为200");
    //提取出orderId和loanorderId
    List<String> ids = new ArrayList<>();
    Pattern pattern = Pattern.compile("[1-9]\\d{4,11}");
    Matcher matcher = pattern.matcher(result);
    while (matcher.find()) {
      String id = matcher.group();
      logger.debug("id=[{}]", id);
      ids.add(id);
    }
    logger.debug("共提取出{}个id", ids.size());
    logger.debug("提取出的ids为{}", ids);
    if (ids.size() == 2) {
      map.put("orderId", ids.get(0));
      map.put("loanorderId", ids.get(1));
      logger.debug("生成还款订单的orderId和loanorderId为[{}]", map);
    } else {
      logger.error("提取生成的还款订单的orderId和loanorderId出现错误，请检查，接口返回内容为[{}]", result);
    }
    return map;
  }


  /**
   * 方法功能描述: 删除订单
   *
   * @param param 参数
   * @return boolean
   */
  private boolean del(String param) {
    logger.debug("删除订单订单的接口参数为[{}]", param);
    HttpResponse httpResponse = httpApi.post(host.getDatabuildHost() + deleteUrl, param);
    String result = httpResponse.getContent();
    logger.debug("删除订单接口返回的内容为[{}]", result);
    TestCaseUtil
        .checkStatusCodeIsOK(httpResponse.getStatus().getStatusCode(), "校验删除订单接口的响应状态码是否为200");
    Map map = JsonUtil.toMap(result);
    String str = (String) map.get("msg");
    if (str.contains(msg)) {
      logger.debug("删除成功");
      return true;
    } else {
      logger.debug("删除失败，请检查，接口返回的内容为[{}]", result);
      return false;
    }
  }


  /**
   * 方法功能描述: 删除订单
   *
   * @param orderIds 原始订单Id(多个用英文,间隔)
   * @return boolean
   */
  @Override
  public boolean delete(String orderIds) {
    String param = "delBy=byOrderId&orderIds=" + orderIds + "&userIds=&platformId=";
    return del(param);
  }


  /**
   * 方法功能描述: 删除订单
   *
   * @param userId 借款人Id(多个用英文,间隔)
   * @param platformIdEnum 资金渠道
   * @return boolean
   */
  @Override
  public boolean delete(String userId, PlatformIdEnum platformIdEnum) {
    String param =
        "delBy=byPlatAndUserId&orderIds=&userIds=" + userId + "&platformId=" + platformIdEnum.value;
    return del(param);
  }


  public String getAddUrl() {
    return addUrl;
  }

  public String getCreateUrl() {
    return createUrl;
  }

  public String getDeleteUrl() {
    return deleteUrl;
  }

  public String getMsg() {
    return msg;
  }

}
