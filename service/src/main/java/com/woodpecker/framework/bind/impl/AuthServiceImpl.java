package com.woodpecker.framework.bind.impl;

import com.woodpecker.dao.loandb.BankAccountDao;
import com.woodpecker.dao.loandb.LoanOrderDao;
import com.woodpecker.dao.payment.AccountDao;
import com.woodpecker.entity.loandb.BankAccountEntity;
import com.woodpecker.entity.loandb.LoanOrderEntity;
import com.woodpecker.framework.bind.AuthService;
import com.woodpecker.framework.bind.BindCardEnum;
import com.woodpecker.framework.bind.BindProcessor;
import com.woodpecker.framework.bind.BindProcessorRoute;
import com.woodpecker.framework.bind.dto.BindCardDto;
import com.woodpecker.service.pub.DataAnalysisService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类描述:〈绑卡/鉴权Service〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  protected BindProcessorRoute bindProcessorRoute;

  @Autowired
  protected LoanOrderDao loanOrderDao;

  @Autowired
  protected BankAccountDao bankAccountDao;

  @Autowired
  protected AccountDao accountDao;

  @Autowired
  protected DataAnalysisService dataAnalysisService;


  /**
   * 方法功能描述: 绑卡
   *
   * @param bindCardDto BindCardDto
   * @param bindCardEnums BindCardEnum数组
   * @return int
   */
  @Override
  public int bindCard(BindCardDto bindCardDto, BindCardEnum[] bindCardEnums) {
    int bankAccountId = 0;
    //循环绑卡
    for (BindCardEnum bindCardEnum : bindCardEnums) {
      bindCardDto.setDeductPlatform(bindCardEnum.getPayPlatformName());
      //路由具体的绑卡实现类
      BindProcessor bindProcessor = bindProcessorRoute.route(bindCardEnum);
      if (null == bindProcessor) {
        logger.error("没有路由到相应的BindProcessor绑卡实现类");
        return bankAccountId;
      }
      //进行鉴权
      bankAccountId = bindProcessor.handle(bindCardDto);
    }
    return bankAccountId;
  }


  /**
   * 方法功能描述: 鉴权
   *
   * @param loanOrderId loanOrderId
   * @param bindCardEnums BindCardEnum数组
   * @param code 短信验证码
   * @return int
   */
  @Override
  public int auth(int loanOrderId, BindCardEnum[] bindCardEnums, String code) {
    int bankAccountId = 0;
    //获取BankAccountEntity
    BankAccountEntity bankAccountEntity = getBankAccountEntity(loanOrderId);
    if (null == bankAccountEntity) {
      logger.error("没有找到id=[{}]的t_loan_order表记录所对应的t_bank_account记录", loanOrderId);
      return bankAccountId;
    }
    //遍历需要绑卡的渠道
    for (BindCardEnum bindCardEnum : bindCardEnums) {
      //判断是否已鉴权
      boolean isAuth = isAuth(bankAccountEntity, bindCardEnum);
      if (!isAuth) {
        //未鉴权
        //路由具体的绑卡实现类
        BindProcessor bindProcessor = bindProcessorRoute.route(bindCardEnum);
        if (null == bindProcessor) {
          logger.error("没有路由到相应的BindProcessor绑卡实现类");
          logger.error("注意：由于没有鉴权，将会影响支付通道路由结果！");
          return bankAccountId;
        }
        //
        String bankId = "" + bankAccountEntity.getBankId();
        String idCardNo = "" + bankAccountEntity.getCertificateNo();
        String userName = "" + bankAccountEntity.getName();
        String mobile = "" + bankAccountEntity.getMobile();
        String cardNo = "" + bankAccountEntity.getAccount();
        //解密
        idCardNo = dataAnalysisService.aesDecrypt(idCardNo);
        mobile = dataAnalysisService.aesDecrypt(mobile);
        cardNo = dataAnalysisService.aesDecrypt(cardNo);
        //set BindCardDto
        BindCardDto bindCardDto = new BindCardDto();
        bindCardDto.setDeductPlatform(bindCardEnum.getPayPlatformName());
        bindCardDto.setBankId(bankId);
        bindCardDto.setIdCardNo(idCardNo);
        bindCardDto.setUserName(userName);
        bindCardDto.setMobile(mobile);
        bindCardDto.setCardNo(cardNo);
        bindCardDto.setCode(code);
        //进行鉴权
        int id = bindProcessor.handle(bindCardDto);
        if (id > 0) {
          bankAccountId = bankAccountEntity.getId();
          logger.debug("鉴权成功 bankAccountId=[{}];id=[{}];Channel[{}]", bankAccountId, id,
              bindCardEnum.getChannel());
        } else {
          logger.error("鉴权失败 id=[{}];Channel[{}]", id, bindCardEnum.getChannel());
          logger.error("注意：鉴权失败，将会影响支付通道路由结果！");
        }
      } else {
        //已鉴权
        bankAccountId = bankAccountEntity.getId();
        logger
            .debug("已鉴权 bankAccountId=[{}];Channel[{}]", bankAccountId, bindCardEnum.getChannel());
      }
    }
    //
    return bankAccountId;
  }


  /**
   * 方法功能描述: 判断是否已鉴权
   *
   * @param bankAccountEntity BankAccountEntity
   * @param bindCardEnum BindCardEnum
   * @return boolean
   */
  @Override
  public boolean isAuth(BankAccountEntity bankAccountEntity, BindCardEnum bindCardEnum) {
    //是否已鉴权标记
    boolean isAuth = false;
    //获取出t_bank_account表的Channel
    String channel = bankAccountEntity.getChannel();
    channel = channel.substring(1, channel.length() - 1).replaceAll("\"", "");
    List<String> channels = new ArrayList<>(Arrays.asList(channel.split(",")));
    //判断是否已鉴权，判断channel是否包含
    if (!channels.contains(String.valueOf(bindCardEnum.getChannel()))) {
      //未鉴权，接下去要进行鉴权操作
      logger.debug("channel=[{}],deductPlatform=[{}]未鉴权,bankAccountEntity=[{}]",
          bindCardEnum.getChannel(), bindCardEnum.getPayPlatformName(), bankAccountEntity);
    } else {
      //已鉴权
      logger.debug("channel=[{}],deductPlatform=[{}]已鉴权,bankAccountEntity=[{}]",
          bindCardEnum.getChannel(), bindCardEnum.getPayPlatformName(), bankAccountEntity);
      isAuth = true;
    }
    return isAuth;
  }


  private BankAccountEntity getBankAccountEntity(int loanOrderId) {
    //获取t_loan_order表的BankAccountId
    LoanOrderEntity loanOrderEntity = loanOrderDao.findById(loanOrderId).get();
    Long bankAccountId = loanOrderEntity.getBankAccountId();
    //判断是否已鉴权
    BankAccountEntity bankAccountEntity = bankAccountDao.findById(bankAccountId.intValue()).get();
    return bankAccountEntity;
  }


}
