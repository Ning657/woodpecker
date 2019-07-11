package com.woodpecker.framework.pay.impl;

import com.sword.autotest.framework.annotation.http.AutoHttpRequest;
import com.sword.autotest.framework.annotation.http.AutoProxy;
import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.framework.pay.AbstractPayProcessor;
import com.woodpecker.framework.pay.PayService;
import com.woodpecker.framework.pay.RepayTypeEnum;
import com.woodpecker.service.HttpApi;
import com.woodpecker.service.config.Host;
import com.woodpecker.service.pub.DataAnalysisService;
import com.xujinjian.HttpClient.HttpResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类描述:〈还款方式：催收充值〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@AutoProxy
@PayService(repayType = RepayTypeEnum.COLLECTION_DEPOSIT, desc = "催收充值")
public class CollectionDepositProxyPayProcessor extends AbstractPayProcessor {

  @AutoHttpRequest
  protected HttpApi httpApi;

  @Autowired
  protected Host host;

  @Autowired
  protected LoanOrderDao loanOrderDao;

  @Autowired
  protected BankAccountDao bankAccountDao;

  @Autowired
  protected DataAnalysisService dataAnalysisService;

  /**
   * 接口url
   */
  protected final String url = "/repaynew/chargerepay";


  /**
   * 方法功能描述: 支付
   *
   * @param id 还款计划id/趸交计划id/loanOrderId
   * @param amount 还款金额，只有催收充值场景下，才有效，其他场景会忽略此参数
   * @return com.xujinjian.HttpClient.HttpResponse
   */
  @Override
  protected HttpResponse doPay(long id, BigDecimal amount) {
    //查询t_loan_order表
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById((int) id);
    if (null == loanOrderEntity) {
      logger.error("t_loan_order表不存在id=[{}]的记录", id);
      return null;
    }
    long bankAccountId = loanOrderEntity.getBankAccountId();
    //查询t_bank_account表
    BankAccountEntity bankAccountEntity = bankAccountDao.findById((int) bankAccountId);
    if (null == bankAccountEntity) {
      logger.error("t_bank_account表不存在id=[{}]的记录", bankAccountId);
      return null;
    }
    String account = bankAccountEntity.getAccount();
    //解密account
    String cardNo = dataAnalysisService.aesDecrypt(account);
    logger.debug("开始还款->{}", loanOrderEntity.toString());
    //组装还款接口参数
    HashMap<String, String> data = new HashMap<>();
    data.put("loanOrderId", String.valueOf(id));
    data.put("accountNo", cardNo);
    data.put("refId", String.valueOf(System.currentTimeMillis()));
    data.put("refType", "BORROWER_CHARGE");
    data.put("source", RepayTypeEnum.COLLECTION_DEPOSIT.name());
    data.put("amount", amount.stripTrailingZeros().toPlainString());
    data.put("auditUid", super.appId);
    data.put("memo", "tyurty");
    data.put("returnUrl", null);
    //组装url
    String url = host.getPaymentTradeHost() + this.url;
    logger.debug("还款方式：催收充值;[{}]接口参数为[{}]", url, data);
    //发送还款请求
    return httpApi.get(url, data);
  }


}
