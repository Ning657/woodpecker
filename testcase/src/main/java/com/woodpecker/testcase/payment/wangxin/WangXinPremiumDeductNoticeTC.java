package com.woodpecker.testcase.payment.wangxin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sword.autotest.framework.assertion.Validate;
import com.woodpecker.commons.annotation.Issue;
import com.woodpecker.commons.util.HttpUtil;
import com.woodpecker.commons.util.SignUtils;
import com.woodpecker.commons.util.TrippleDesUtil;
import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.loandb.CustInfoDao;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.dao.loandb.OrderThirdpartyRefDao;
import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.service.config.Host;
import com.woodpecker.service.databuild.DataBuildOrderService;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.service.pub.DataAnalysisService;
import com.woodpecker.service.pub.MQService;
import com.woodpecker.testcase.payment.PaymentTestCase;
import com.xujinjian.Commons.Lang.DateUtil;
import com.xujinjian.Commons.Lang.StringUtil;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.Json.JsonUtil;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * 类描述:〈网信趸交扣款通知(提测2019.04.23) 〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Issue(value = "PAYMENT-362")
public class WangXinPremiumDeductNoticeTC extends PaymentTestCase {

  private static final String THREEDESKEY;
  private static String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ4JEu7dw8vmk/GP2ky8HmvnnUxaWEbIoJpDk3wo8v85cfknAAEeg4DZ8BMt6i0FJbm6LK5j31Jmsz+wKrJU65f/hDVp4bTg14TXpzmZnA0OYh6FAMAoIWHP+qoNGkrmPbvda1VzqXmMEh/2cGAPMOYjyj1jnGkVfRmLOjIetUNVAgMBAAECgYBg9upR+2mOKvytI3lqyaXUyTjfRzOQwrqfzESq0qk95e5DVCWRijpfjdjau7pKQzVBfrS5j3fPpEnpEtsvriEanAsVh4vrou2x5NoM0KKPccXrhWrI2DL5jjH03NFtPqkQZBggLK1BargNKm2Kk6Ael9/Br6/uVou2PVLB8G/1ZQJBAMsC5SyS6GGQ/jU+6CkaEbxtCbDCAT+32zSR5HWP3AkVW25Y2zgegF8oeG8SQsltKEOPQpSE51HkcWb6JaNA50cCQQDHSOqDSnzSii01Tnqc9Kcy1GGurMf+Kts6eI9oqBgDPvvHodJvJxVqwmzfNpxWuggR9m3PIEeXT6rNc1J8csaDAkEArMTUALEtrwrULMBSl9gly/QH0TPqGC4CEaUvhpVdryVL4IciWYorudAZXn1t9To+j/B1uyu4KUt+7yJnsZ3ktQJALpKn288IpNuN/Ss+FHhIquRff8b5Zjj1yxBp03eVRQzvIpR67Wcg5jZR40iL+Oi5AhmpSAKvOHlx3VnaKSNZqQJBAK5n4gbpiXsRI+8l8mRHa9I0Yn9Wx5EAtriEy4dDNoQQtEo6F4KE8hku0yh0dNxpfwoJFhhDgo9ffgi/CW56yNY=";
  //private static String RSA_PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMOKEyL7OF9TlspjVDM4JaNEQ/OuxSjhkJwTuGFX/nUXhCkTpkd2CXdAy57CMvDkfcioVP7bq/LXs1RXpfVJC15qhYmjpfLMRSi7fwKzMWpFnmoThKDIPvS8/Xu8dCS+lBc3CH9uYcDQFMCDndJ5D2H1Tc6pREyNATDVFP5WD+71AgMBAAECgYAFcfXgdoB2XxyG3Ec++eRKbJ87zPUek1F0lzP+OfYTCqmafzqVKNtQn9RLwnqqrKI4ET/0rOdX5tvOkHZFo1gWp4EBgzFJ5mQmT3vZidMBFXHG2iCvAit3BPqiOdGjYHtBTnnCUF1TThUEWBS5Np23kxTnrgdy+FF8CAtGxb+srQJBAO/ch4inP98d2Zp0MoKhOI7eQnJDesbifeftKABWRQ2xPfb2FDAX0duvZ9RApwA5DTBFmqg+jQYDXcsUeiiQVHsCQQDQsh+s6GHklwJPc1ByIqczVV1eRMbHpGXv555v940xbb/mAcF+lbpLdfqwDktoJ4XkzpWA7yoHI15lsrCkG4dPAkEAlkjQSpiv9jWXr6R6OUqWSz2K2FjbRl2GkZgP5hYncerJbkDEaVWjUUfXgC958zPLxaD2w89dQJU/YQxVdbDonQJBAKf65/IWLk1/mzV2PQdRi0GPcZLiSxoA4qgix+2Z1YU2sKKjQSrxu7znnru9FcclIOnVupLIbwzF1EKJfRLqsZMCQQDZqg9mB2y5biKWaprxsX71d7cmpoh+8B9C6oTkTzVFmjaiaL5WDTPrvda9z1w0y2dtiWOyRUArx3mqOWI9Q/bc";
  private static String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCeCRLu3cPL5pPxj9pMvB5r551MWlhGyKCaQ5N8KPL/OXH5JwABHoOA2fATLeotBSW5uiyuY99SZrM/sCqyVOuX/4Q1aeG04NeE16c5mZwNDmIehQDAKCFhz/qqDRpK5j273WtVc6l5jBIf9nBgDzDmI8o9Y5xpFX0ZizoyHrVDVQIDAQAB";
  //private static String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDihMi+zhfU5bKY1QzOCWjREPzrsUo4ZCcE7hhV/51F4QpE6ZHdgl3QMuewjLw5H3IqFT+26vy17NUV6X1SQteaoWJo6XyzEUou38CszFqRZ5qE4SgyD70vP17vHQkvpQXNwh/bmHA0BTAg53SeQ9h9U3OqURMjQEw1RT+Vg/u9QIDAQAB";
  private static TrippleDesUtil trippleDesUtil;

  static {
    THREEDESKEY = "ThisIsTrippleDESKeyHahaha";
    //THREEDESKEY = "ThisIsTrippleDESKeyDashu";
    try {
      trippleDesUtil = new TrippleDesUtil(THREEDESKEY);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private Integer orderId;

  private Integer loanOrderId;

  @Autowired
  private DataAnalysisService dataAnalysisService;

  @Autowired
  private DataBuildOrderService dataBuildOrderService;

  @Autowired
  private OrderThirdpartyRefDao orderThirdpartyRefDao;

  @Autowired
  private SinglePremiumScheduleDao singlePremiumScheduleDao;

  @Autowired
  private LoanOrderDao loanOrderDao;

  @Autowired
  private BankAccountDao bankAccountDao;

  @Autowired
  private CustInfoDao custInfoDao;

  @Autowired
  private Host host;

  @Autowired
  private MQService mqService;


  @Test(enabled = false, description = "通知支付趸交扣款成功")
  public void testSuccess() {
    Map<String, String> result = dataBuildOrderService.create(userId, PlatformIdEnum.WX, "1");
    orderId = Integer.valueOf(result.get("orderId"));
    loanOrderId = Integer.valueOf(result.get("loanorderId"));

//    orderId = 54889138;
//    loanOrderId = loanOrderDao.findByOrderId(orderId).getId();

    String outOrderNo = "" + DateUtil.getTime();
    // 2、成功；3、失败
    String orderStatus = "2";
    String busNumber = orderThirdpartyRefDao.findByOrderId(orderId).getBusNumber();
    BigDecimal paidAmount = singlePremiumScheduleDao.findByLoanOrderId(loanOrderId).getAmount();
    Long bankAccountId = loanOrderDao.findById(loanOrderId).getBankAccountId();
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId.intValue());
    String account = bankAccountEntity.getAccount();
    String userName = bankAccountEntity.getName();
    String certificateNo = bankAccountEntity.getCertificateNo();
    String mobile = bankAccountEntity.getMobile();
    String cardNo = dataAnalysisService.aesDecrypt(account);
    String userCardId = dataAnalysisService.aesDecrypt(certificateNo);
    mobile = dataAnalysisService.aesDecrypt(mobile);
    BigDecimal platformFee = loanOrderDao.findById(loanOrderId).getPlatFee();
    BigDecimal amount = singlePremiumScheduleDao.findByLoanOrderId(loanOrderId).getAmount();
    BigDecimal merchantFee = amount.subtract(platformFee);

    Map<String, Object> bizExtDataMap = new HashMap<>();
    bizExtDataMap.put("approveNumber", busNumber);
    bizExtDataMap.put("platformFee", platformFee.stripTrailingZeros().toPlainString());
    bizExtDataMap.put("merchantFee", merchantFee.stripTrailingZeros().toPlainString());
    String bizExtData = JSON.toJSONString(bizExtDataMap);
    log.debug("bizExtData=[{}]", bizExtData);

    Map<String, String> param = new HashMap<>();
    param.put("merchant", "1234567890");
    param.put("version", "0.0.1");
    param.put("outOrderNo", outOrderNo);
    param.put("orderStatus", orderStatus);
    param.put("paidAmount", paidAmount.stripTrailingZeros().toPlainString());
    param.put("cardNo", cardNo);
    param.put("userCardId", userCardId);
    param.put("userName", userName);
    param.put("mobile", mobile);
    param.put("bankId", "1");
    param.put("startTime", DateUtil.format(new Date(), DateUtil.FORMAT_LONG));
    param.put("occurTime", DateUtil.format(new Date(), DateUtil.FORMAT_LONG));
    param.put("bizExtData", bizExtData);
    //param.put("remark", "aaa");

    log.info("param=[{}]", param);
    //
    execute(param);

    //MQ check
    boolean flag = false;
    String topic = "recharge";
    List<JSONObject> jsonObjectList = mqService.queryMessageByTopic(2, topic);
    for (JSONObject json : jsonObjectList) {
      String msgId = json.getString("msgId");
      JSONObject msgJsonObject = mqService.queryMessageByMsgId(msgId, topic);
      JSONArray array = msgJsonObject.getJSONArray("messageTrackList");
      if (array.size() > 0) {
        JSONObject j = array.getJSONObject(0);
        String consumerGroup = j.getString("consumerGroup");
        boolean f = StringUtil.equalsIgnoreCase("accounting", consumerGroup);
        if (f) {
          JSONObject s = msgJsonObject.getJSONObject("messageView");
          JSONObject messageBody = s.getJSONObject("messageBody");
          String scheduleId = messageBody.getString("scheduleId");
          Integer userId = messageBody.getInteger("userId");
          String acc = messageBody.getString("account");
          acc = dataAnalysisService.aesDecrypt(acc);
          String id = singlePremiumScheduleDao.findByLoanOrderId(loanOrderId).getId().toString();
          if (StringUtil.equals(scheduleId, id) && StringUtil
              .equals(this.userId, String.valueOf(userId)) && StringUtil.equals(acc, cardNo)) {
            log.info("找到发出去的MQ=[{}]", msgJsonObject);
            flag = true;
            break;
          }
        }
      }
    }
    Validate.isTrue(flag, "校验是否往accounting发送MQ");
  }


  @Test(enabled = true, description = "通知支付趸交扣款失败")
  public void testFail() {
//    Map<String, String> result = dataBuildOrderService.create(userId, PlatformIdEnum.WX, "1");
//    orderId = Integer.valueOf(result.get("orderId"));
//    loanOrderId = Integer.valueOf(result.get("loanorderId"));

//    log.info("orderId=[{}]", orderId);
    orderId = 54889246;
    loanOrderId = loanOrderDao.findByOrderId(orderId).get(0).getId();

    String outOrderNo = "" + DateUtil.getTime();
    // 2、成功；3、失败
    String orderStatus = "3";
    String busNumber = orderThirdpartyRefDao.findByOrderId(orderId).getBusNumber();
    BigDecimal paidAmount = singlePremiumScheduleDao.findByLoanOrderId(loanOrderId).getAmount();
    Long bankAccountId = loanOrderDao.findById(loanOrderId).getBankAccountId();
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId.intValue());
    String account = bankAccountEntity.getAccount();
    String userName = bankAccountEntity.getName();
    String certificateNo = bankAccountEntity.getCertificateNo();
    String mobile = bankAccountEntity.getMobile();
    String cardNo = dataAnalysisService.aesDecrypt(account);
    String userCardId = dataAnalysisService.aesDecrypt(certificateNo);
    mobile = dataAnalysisService.aesDecrypt(mobile);
    BigDecimal platformFee = loanOrderDao.findById(loanOrderId).getPlatFee();
    BigDecimal amount = singlePremiumScheduleDao.findByLoanOrderId(loanOrderId).getAmount();
    BigDecimal merchantFee = amount.subtract(platformFee);

    Map<String, Object> bizExtDataMap = new HashMap<>();
    bizExtDataMap.put("approveNumber", busNumber);
    bizExtDataMap.put("platformFee", platformFee.stripTrailingZeros().toPlainString());
    bizExtDataMap.put("merchantFee", merchantFee.stripTrailingZeros().toPlainString());
    String bizExtData = JSON.toJSONString(bizExtDataMap);
    log.debug("bizExtData=[{}]", bizExtData);

    Map<String, String> param = new HashMap<>();
    param.put("merchant", "1234567890");
    param.put("version", "0.0.1");
    param.put("outOrderNo", outOrderNo);
    param.put("orderStatus", orderStatus);
    param.put("paidAmount", paidAmount.stripTrailingZeros().toPlainString());
    param.put("cardNo", cardNo);
    param.put("userCardId", userCardId);
    param.put("userName", userName);
    param.put("mobile", mobile);
    param.put("bankId", "1");
    param.put("startTime", DateUtil.format(new Date(), DateUtil.FORMAT_LONG));
    //param.put("occurTime", DateUtil.format(new Date(), DateUtil.FORMAT_LONG));
    param.put("bizExtData", bizExtData);
    param.put("remark", "aaa");

    log.debug("param=[{}]", param);
    //
    execute(param);

    //MQ check
    boolean flag = false;
    String topic = "single_premium_call_back_topic";
    List<JSONObject> jsonObjectList = mqService.queryMessageByTopic(2, topic);
    for (JSONObject json : jsonObjectList) {
      String msgId = json.getString("msgId");
      JSONObject msgJsonObject = mqService.queryMessageByMsgId(msgId, topic);
      JSONArray array = msgJsonObject.getJSONArray("messageTrackList");
      if (array.size() > 0) {
        JSONObject j = array.getJSONObject(0);
        String consumerGroup = j.getString("consumerGroup");
        boolean f = StringUtil.equalsIgnoreCase("clearing-kit", consumerGroup);
        if (f) {
          JSONObject s = msgJsonObject.getJSONObject("messageView");
          JSONObject messageBody = s.getJSONObject("messageBody");
          String scheduleId = messageBody.getString("scheduleId");
          String id = singlePremiumScheduleDao.findByLoanOrderId(loanOrderId).getId().toString();
          if (StringUtil.equals(scheduleId, id)) {
            log.info("找到发出去的MQ=[{}]", msgJsonObject);
            flag = true;
            break;
          }
        }
      }
    }
    Validate.isTrue(flag, "校验是否往clearing-kit发送MQ");
  }


  private void execute(Map<String, String> param) {
    try {
      String original = SignUtils.sortParamsToSign(param);
      log.debug("original=[{}]", original);
      PrivateKey privateKey = null;
      try {
        privateKey = SignUtils.getPrivateKey(RSA_PRIVATE_KEY);
        param.put("sign", SignUtils.sign(original, privateKey));
      } catch (Exception e) {
        log.error("参数[{}]加签失败！", original);
        log.error(e.getMessage(), e);
      }
    } catch (UnsupportedEncodingException e) {
      log.error(e.getMessage(), e);
    }

    Set<String> ignorKeys = new HashSet<String>() {
      {
        add("merchant");
        add("version");
      }
    };

    Map<String, String> map = encrypt(param, ignorKeys);
    String data = HttpUtil.urlEncode(map);
    String url = host.getPaymentTradeHost() + "/notify/callPremium";
    url = url + "?" + data;

    log.debug("data=[{}]", data);
    log.info("url=[{}]", url);

    HttpResponse httpResponse = super.api.post(url);

    String content = httpResponse.getContent();
    log.info("content=[{}]", content);

    JSONObject jsonObject = JsonUtil.parseObject(content);
    Object dataObject = jsonObject.get("data");
    String dataa = trippleDesUtil.decrypt(String.valueOf(dataObject));
    Validate.isEquals("SUCCESS", dataa, "校验请求是否发送成功");
    log.info("data=[{}]", dataa);
  }

  //@AfterMethod
//  public void deleteOrder() {
//    boolean flag = dataBuildOrderService.delete(orderId.toString());
//    if (flag) {
//      log.info("订单[{}]删除成功!", orderId.toString());
//    } else {
//      log.info("订单[{}]删除失败!", orderId.toString());
//    }
//    Validate.isTrue(flag, "校验订单[" + orderId + "]是否删除成功");
//  }


  private static Map<String, String> encrypt(Map<String, String> map, Set<String> ignorKeys) {
    Map<String, String> result = new HashMap<>(map.size());

    for (Map.Entry<String, String> entry : map.entrySet()) {
      if (!ignorKeys.contains(entry.getKey())) {
        result.put(entry.getKey(), trippleDesUtil.encrypt(String.valueOf(entry.getValue())));
      } else {
        result.put(entry.getKey(), String.valueOf(entry.getValue()));
      }
    }
    return result;
  }


}
