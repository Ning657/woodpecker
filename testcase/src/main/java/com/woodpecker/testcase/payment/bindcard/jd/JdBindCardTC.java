package com.woodpecker.testcase.payment.bindcard.jd;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.entity.loandb.CustInfoEntity;
import com.woodpecker.testcase.payment.bindcard.BindCardTestCase;
import com.xujinjian.Commons.Math.MathUtil;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 类描述:〈京东绑卡TC〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class JdBindCardTC extends BindCardTestCase {

  protected String deductPlatform = "JDAGREEMENTPAY";

  protected String channel = "40";


  @BeforeClass
  public void ready() {
    //mock JDAGREEMENTPAY
    super.mockChannel(deductPlatform);
  }


  @Test(description = "京东绑卡")
  public void bindCard() {
    String cardNoPrefix = "6216611300008";
    //生成6位随机数，充当银行卡卡号的后6位
    int cardNoSuffix = MathUtil.getRandomBySize(6);
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
  }


}
