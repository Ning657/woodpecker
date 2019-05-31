package com.woodpecker.testcase.payment.unbindcard.jd;

import com.alibaba.fastjson.JSONObject;
import com.sword.autotest.framework.assertion.Validate;
import com.woodpecker.entity.loandb.CustInfoEntity;
import com.woodpecker.service.databuild.AppIdEnum;
import com.woodpecker.service.databuild.CleanPlatformIdEnum;
import com.woodpecker.service.databuild.LoanPeriodEnum;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.service.databuild.ProductCodeEnum;
import com.woodpecker.service.databuild.RiskLevelEnum;
import com.woodpecker.testcase.payment.unbindcard.UnBindCardTestCase;
import com.xujinjian.Commons.Math.MathUtil;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 类描述:〈京东解绑卡TC，不允许解绑：卡关联 未结清订单 放款卡or还款卡〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class JdUnBindCardTC extends UnBindCardTestCase {

  protected String deductPlatform = "JDAGREEMENTPAY";

  protected String channel = "40";


  @BeforeClass
  public void ready() {
    //mock JDAGREEMENTPAY
    super.mockChannel(deductPlatform);
  }


  @BeforeMethod
  public void deleteOrder() {
    //删除订单
    super.dataBuildOrderService.delete(userId, PlatformIdEnum.ALL);
  }


  /**
   * 方法功能描述: 绑卡
   *
   * @return java.lang.String
   */
  private String[] bindCard() {
    String cardNoPrefix = "62166113000";
    //生成6位随机数，充当银行卡卡号的后8位
    int cardNoSuffix = MathUtil.getRandomBySize(8);
    //这样就组成了一个随机的银行卡卡号，避免重复
    String cardNo = cardNoPrefix + cardNoSuffix;
    String bankId = "10";
    String code = "123456";
    String mobile = "18758575466";
    CustInfoEntity custInfoEntity = custInfoDao.findById(Integer.parseInt(userId));
    String userName = custInfoEntity.getUserName();
    String idCardNo = custInfoEntity.getIdCardNo();
    idCardNo = dataAnalysisService.aesDecrypt(idCardNo);
    //先请求绑卡
    HttpResponse httpResponse = bindCardService
        .requestBindCard(deductPlatform, bankId, idCardNo, userName, mobile, cardNo, "", "\"\"",
            "");
    //获取出data
    String content = httpResponse.getContent();
    logger.debug("请求绑卡返回的内容为[{}]", content);
    JSONObject json = JsonUtil.parseObject(content);
    //校验
    super.validateBindCardResponse(json.getString("code"), json.getString("message"));
    String data = json.getString("data");
    //确认绑卡
    httpResponse = bindCardService
        .sureBindCard(mobile, bankId, userId, code, idCardNo, data, cardNo, "", "", "\"\"");
    //获取出data，这个data就是t_bank_account表的id
    content = httpResponse.getContent();
    logger.debug("确认绑卡返回的内容为[{}]", content);
    json = JsonUtil.parseObject(content);
    //校验
    super.validateBindCardResponse(json.getString("code"), json.getString("message"));
    //保存bankAccountId，后续要校验
    String bankAccountId = json.getString("data");
    //校验数据库表字段
    String bindId = "\"" + this.channel + "\":\"" + data + "\"";
    String channel = "\"" + this.channel + "\"";
    super.checkBindCardTables(data, bankAccountId, bindId, channel);
    //返回
    return new String[]{cardNo, bankAccountId};
  }


  /**
   * 方法功能描述: 绑卡
   *
   * @param cardNo 银行卡卡号
   * @return java.lang.String
   */
  private String[] bindCard(String cardNo) {
    String bankId = "10";
    String code = "123456";
    String mobile = "18758575466";
    CustInfoEntity custInfoEntity = custInfoDao.findById(Integer.parseInt(userId));
    String userName = custInfoEntity.getUserName();
    String idCardNo = custInfoEntity.getIdCardNo();
    idCardNo = dataAnalysisService.aesDecrypt(idCardNo);
    //先请求绑卡
    HttpResponse httpResponse = bindCardService
        .requestBindCard(deductPlatform, bankId, idCardNo, userName, mobile, cardNo, "", "\"\"",
            "");
    //获取出data
    String content = httpResponse.getContent();
    logger.debug("请求绑卡返回的内容为[{}]", content);
    JSONObject json = JsonUtil.parseObject(content);
    //校验
    super.validateBindCardResponse(json.getString("code"), json.getString("message"));
    String data = json.getString("data");
    //确认绑卡
    httpResponse = bindCardService
        .sureBindCard(mobile, bankId, userId, code, idCardNo, data, cardNo, "", "", "\"\"");
    //获取出data，这个data就是t_bank_account表的id
    content = httpResponse.getContent();
    logger.debug("确认绑卡返回的内容为[{}]", content);
    json = JsonUtil.parseObject(content);
    //校验
    super.validateBindCardResponse(json.getString("code"), json.getString("message"));
    //保存bankAccountId，后续要校验
    String bankAccountId = json.getString("data");
    //校验数据库表字段
    String bindId = "\"" + this.channel + "\":\"" + data + "\"";
    String channel = "\"" + this.channel + "\"";
    super.checkBindCardTables(data, bankAccountId, bindId, channel);
    //返回
    return new String[]{cardNo, bankAccountId};
  }


  @Test(description = "解绑新绑定的卡")
  public void test1() {
    //绑卡
    String[] result = bindCard();
    String cardNo = result[0];
    String bankAccountId = result[1];
    //解绑刚才绑的卡
    HttpResponse httpResponse = unBindCardService.unBindCard(userId, cardNo);
    String content = httpResponse.getContent();
    log.debug("解绑卡接口返回的内容为[{}]", content);
    //转成json
    JSONObject json = JsonUtil.parseObject(content);
    String code = json.getString("code");
    String message = json.getString("message");
    boolean data = json.getBooleanValue("data");
    //校验解绑卡请求是否发送成功
    super.validateBindCardResponse(code, message);
    //校验data是否为true
    Validate.isTrue(data, "校验解绑卡请求返回的data是否为true");
    //解绑卡后的校验点：
    //校验t_bank_account表的status = 2
    //校验t_tp_account表的status = 2
    //校验t_card_info表是否有插入解绑记录
    //校验t_tp_intermediate表是否有插入解绑记录
    //校验t_tp_transaction表是否有插入解绑记录
    super.checkUnBindCardTables(Integer.parseInt(bankAccountId));
  }


  @Test(description = "解绑未结清订单的放款卡")
  public void test2() {
    //绑卡
    String[] result = bindCard();
    String cardNo = result[0];
    String bankAccountId = result[1];
    //造未结清的订单 - 造已存证待分发的订单
    List<String> orders = dataBuildOrderService
        .create(userId, new BigDecimal(3000), RiskLevelEnum.A, LoanPeriodEnum.Six, AppIdEnum.GFD,
            ProductCodeEnum.CQD, CleanPlatformIdEnum.SSJ, 1);
    String orderId = orders.get(0);
    //更新t_order_original表的BankAccountId字段
    super
        .updateOrderOriginalBankAccountId(Integer.parseInt(orderId), Long.parseLong(bankAccountId));
    //解绑此卡
    HttpResponse httpResponse = unBindCardService.unBindCard(userId, cardNo);
    String content = httpResponse.getContent();
    log.debug("解绑卡接口返回的内容为[{}]", content);
    //转成json
    JSONObject json = JsonUtil.parseObject(content);
    String code = json.getString("code");
    String message = json.getString("message");
    boolean data = json.getBooleanValue("data");
    //校验解绑卡请求是否发送成功
    super.validateUnBindCardResponseFail(code, message, data);
  }


  @Test(description = "解绑未结清订单的还款卡")
  public void test3() {
    //绑卡
    String[] result = bindCard();
    String cardNo = result[0];
    String bankAccountId = result[1];
    //再绑一张卡
    String[] result2 = bindCard();
    String cardNo2 = result2[0];
    String bankAccountId2 = result2[1];
    //造未结清的订单 - 生成还款订单
    Map<String, String> ids = dataBuildOrderService.create(userId, PlatformIdEnum.SSJ, "2");
    String orderId = ids.get("orderId");
    String loanorderId = ids.get("loanorderId");
    //更新t_order_original表的BankAccountId字段
    super
        .updateOrderOriginalBankAccountId(Integer.parseInt(orderId), Long.parseLong(bankAccountId));
    //更新t_loan_order表的BankAccountId字段
    super.updateLoanOrderBankAccountId(Integer.parseInt(loanorderId),
        Long.parseLong(bankAccountId2));
    //解绑第一次绑的卡
    HttpResponse httpResponse = unBindCardService.unBindCard(userId, cardNo2);
    String content = httpResponse.getContent();
    log.debug("解绑卡接口返回的内容为[{}]", content);
    //转成json
    JSONObject json = JsonUtil.parseObject(content);
    String code = json.getString("code");
    String message = json.getString("message");
    boolean data = json.getBooleanValue("data");
    //校验解绑卡请求是否发送成功
    super.validateUnBindCardResponseFail(code, message, data);
  }


  @Test(description = "解绑已结清订单的放款卡")
  public void test4() {
    //绑卡
    String[] result = bindCard();
    String cardNo = result[0];
    String bankAccountId = result[1];
    //造未结清的订单 - 生成还款订单
    Map<String, String> ids = dataBuildOrderService.create(userId, PlatformIdEnum.SSJ, "2");
    String orderId = ids.get("orderId");
    String loanorderId = ids.get("loanorderId");
    //更新t_order_original表的BankAccountId字段
    super
        .updateOrderOriginalBankAccountId(Integer.parseInt(orderId), Long.parseLong(bankAccountId));
    //更新t_loan_order表的BankAccountId字段
    super
        .updateLoanOrderBankAccountId(Integer.parseInt(loanorderId), Long.parseLong(bankAccountId));
    //还清这笔订单
    super.clearLoanOrderSchedule(Integer.parseInt(loanorderId));
    //解绑此卡
    HttpResponse httpResponse = unBindCardService.unBindCard(userId, cardNo);
    String content = httpResponse.getContent();
    log.debug("解绑卡接口返回的内容为[{}]", content);
    //转成json
    JSONObject json = JsonUtil.parseObject(content);
    String code = json.getString("code");
    String message = json.getString("message");
    boolean data = json.getBooleanValue("data");
    //校验解绑卡请求是否发送成功
    super.validateBindCardResponse(code, message);
    //校验data是否为true
    Validate.isTrue(data, "校验解绑卡请求返回的data是否为true");
    //解绑卡后的校验点：
    //校验t_bank_account表的status = 2
    //校验t_tp_account表的status = 2
    //校验t_card_info表是否有插入解绑记录
    //校验t_tp_intermediate表是否有插入解绑记录
    //校验t_tp_transaction表是否有插入解绑记录
    super.checkUnBindCardTables(Integer.parseInt(bankAccountId));
  }


  @Test(description = "解绑已结清订单的还款卡")
  public void test5() {
    //绑卡
    String[] result = bindCard();
    String cardNo = result[0];
    String bankAccountId = result[1];
    //再绑一次卡
    String[] result2 = bindCard();
    String cardNo2 = result2[0];
    String bankAccountId2 = result2[1];
    //造未结清的订单 - 生成还款订单
    Map<String, String> ids = dataBuildOrderService.create(userId, PlatformIdEnum.SSJ, "2");
    String orderId = ids.get("orderId");
    String loanorderId = ids.get("loanorderId");
    //更新t_order_original表的BankAccountId字段
    super
        .updateOrderOriginalBankAccountId(Integer.parseInt(orderId), Long.parseLong(bankAccountId));
    //更新t_loan_order表的BankAccountId字段
    super
        .updateLoanOrderBankAccountId(Integer.parseInt(loanorderId),
            Long.parseLong(bankAccountId2));
    //还清这笔订单
    super.clearLoanOrderSchedule(Integer.parseInt(loanorderId));
    //解绑此卡
    HttpResponse httpResponse = unBindCardService.unBindCard(userId, cardNo2);
    String content = httpResponse.getContent();
    log.debug("解绑卡接口返回的内容为[{}]", content);
    //转成json
    JSONObject json = JsonUtil.parseObject(content);
    String code = json.getString("code");
    String message = json.getString("message");
    boolean data = json.getBooleanValue("data");
    //校验解绑卡请求是否发送成功
    super.validateBindCardResponse(code, message);
    //校验data是否为true
    Validate.isTrue(data, "校验解绑卡请求返回的data是否为true");
    //解绑卡后的校验点：
    //校验t_bank_account表的status = 2
    //校验t_tp_account表的status = 2
    //校验t_card_info表是否有插入解绑记录
    //校验t_tp_intermediate表是否有插入解绑记录
    //校验t_tp_transaction表是否有插入解绑记录
    super.checkUnBindCardTables(Integer.parseInt(bankAccountId2));
  }


  @Test(description = "绑已解绑的卡")
  public void test6() {
    //绑卡
    String[] result = bindCard();
    String cardNo = result[0];
    String bankAccountId = result[1];
    //解绑刚才绑的卡
    HttpResponse httpResponse = unBindCardService.unBindCard(userId, cardNo);
    String content = httpResponse.getContent();
    log.debug("解绑卡接口返回的内容为[{}]", content);
    //转成json
    JSONObject json = JsonUtil.parseObject(content);
    String code = json.getString("code");
    String message = json.getString("message");
    boolean data = json.getBooleanValue("data");
    //校验解绑卡请求是否发送成功
    super.validateBindCardResponse(code, message);
    //校验data是否为true
    Validate.isTrue(data, "校验解绑卡请求返回的data是否为true");
    //解绑卡后的校验点：
    //校验t_bank_account表的status = 2
    //校验t_tp_account表的status = 2
    //校验t_card_info表是否有插入解绑记录
    //校验t_tp_intermediate表是否有插入解绑记录
    //校验t_tp_transaction表是否有插入解绑记录
    super.checkUnBindCardTables(Integer.parseInt(bankAccountId));
    //再重新绑定解绑的这张卡
    bindCard(cardNo);
  }


}
