package com.woodpecker.testcase.payment.bindcard.treefinance.tonglian;

import com.alibaba.fastjson.JSONObject;
import com.woodpecker.entity.loandb.CustInfoEntity;
import com.woodpecker.testcase.payment.bindcard.BindCardTestCase;
import com.xujinjian.Commons.Math.MathUtil;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 类描述:〈大树通联绑卡TC〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class TlBindCardTC extends BindCardTestCase {

  protected String deductPlatform = "ALL_IN_PAY";

  /**
   * 通联绑卡，不管居间方是大树还是景宁，都是60
   */
  protected String channel = "60";


  @BeforeClass
  public void ready() {
    //目前还没有通联绑卡的mock，所以先不去mock
    //mock ALL_IN_PAY
    //super.mockChannel(deductPlatform);
  }


  @Test(description = "通联绑卡")
  public void bindCard() {
    String cardNoPrefix = "6216611300008";
    //生成6位随机数，充当银行卡卡号的后6位
    int cardNoSuffix = MathUtil.getRandomBySize(6);
    //这样就组成了一个随机的银行卡卡号，避免重复
    String cardNo = cardNoPrefix + cardNoSuffix;
    String bankId = "10";
    String code = "111111";
    //随机生成一个手机号
    String mobile = MathUtil.getMobile();
    CustInfoEntity custInfoEntity = custInfoDao.findById(Integer.parseInt(userId));
    String userName = custInfoEntity.getUserName();
    String idCardNo = custInfoEntity.getIdCardNo();
    idCardNo = dataAnalysisService.aesDecrypt(idCardNo);//des解密
    //随机生成一个身份证号，不使用上面从t_cust_info表中取的身份证，由于通联未mock，所以这块暂时不用生成的
    //idCardNo = StringUtil.getIdCardNo();
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