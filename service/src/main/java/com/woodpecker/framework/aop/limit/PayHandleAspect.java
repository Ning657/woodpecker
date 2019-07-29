package com.woodpecker.framework.aop.limit;

import com.sword.autotest.framework.exception.TestCaseException;
import com.woodpecker.dao.loandb.RepaymentScheduleDao;
import com.woodpecker.dao.loandb.SinglePremiumScheduleDao;
import com.woodpecker.dao.payment.PayPlatformDao;
import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.framework.aop.annotation.PayHandle;
import com.woodpecker.framework.aop.annotation.RepaymentAmount;
import com.woodpecker.framework.aop.annotation.RouterVersion;
import com.woodpecker.framework.aop.annotation.ScheduleId;
import com.woodpecker.framework.bind.AuthService;
import com.woodpecker.framework.bind.BindCardEnum;
import com.woodpecker.framework.mq.verify.ScheduleTypeEnum;
import com.woodpecker.framework.pay.PayPlatformEnum;
import com.woodpecker.service.payment.bind.check.VerifyBindData;
import com.woodpecker.service.payment.cache.RedisCacheFactory;
import com.xujinjian.Commons.Lang.ThreadUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 类描述:〈还款前后操作切面，修改银行日限额、禁用支付通道、支付通道鉴权〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
@Aspect
@Component
public class PayHandleAspect {

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private BankDayLimitService bankDayLimitService;

  @Autowired
  private PayPlatformDao payPlatformDao;

  @Autowired
  @Qualifier("payPlatformUtil")
  private PayPlatformService payPlatformService;

  @Autowired
  private AuthService authService;

  @Autowired
  private RepaymentScheduleDao repaymentScheduleDao;

  @Autowired
  private SinglePremiumScheduleDao singlePremiumScheduleDao;

  @Autowired
  private VerifyBindData verifyBindData;

  @Autowired
  private RedisCacheFactory redisCacheFactory;


  @Pointcut("@annotation(com.woodpecker.framework.aop.annotation.PayHandle)")
  public void pointcut() {

  }


  @Around("pointcut()")
  public Object updateBankDayLimit(ProceedingJoinPoint joinPoint) throws Throwable {
    //是否需要恢复银行原来的限额标记
    boolean needRestore = false;
    int bankId = 0;
    int dayAmountLimit = 0;
    String payChannelCode = null;
    String version = null;
    //禁用指定的支付通道
    PayPlatformEnum[] banCodes = null;
    //禁用除指定支付通道外的通道
    PayPlatformEnum[] banOtherCodes = null;
    //被禁用的通道，后面需要恢复
    List<PayPlatformEntity> list = null;

    //获取被拦截的方法上的UpdateBankDayLimit注解
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    PayHandle payHandle = method.getAnnotation(PayHandle.class);
    log.debug("拦截到方法[{}],PayHandle == null ? [{}]", joinPoint.getSignature().getName(),
        payHandle == null);
    //
    if (null != payHandle) {
      //获取出ScheduleTypeEnum
      ScheduleTypeEnum scheduleType = payHandle.scheduleType();
      //获取出payChannelCode
      payChannelCode = payHandle.payPlatform().getPayChannelCode();
      //获取出id
      int id = getId(joinPoint);
      //获取出amount
      int amount = payHandle.amount();
      //获取出version
      version = getVersion(joinPoint);
      //获取出banCodes
      banCodes = payHandle.banCodes();
      //获取出banOtherCodes
      banOtherCodes = payHandle.banOtherCodes();

      //鉴权
      bindCard(id, payHandle);

      //判断是否需要修改银行限额
      int editAmount = payHandle.amount();
      log.debug("amount=[{}]", editAmount);
      if (editAmount != 0) {
        //有设置需要修改银行限额
        log.debug("有设置需要修改银行限额，当还款金额大于银行限额时，则会在原限额基础上，增加amount=[{}]", editAmount);
        //本次的还款金额
        BigDecimal repaymentAmount = null;
        //判断scheduleType，如果scheduleType=REPAYMENT/PREMIUM，则取计划的金额，否则，就取调用还款接口时，传的金额
        switch (scheduleType) {
          case REPAYMENT:
            //获取还款计划/趸交计划的金额
            repaymentAmount = bankDayLimitService.getAmount(scheduleType, id);
            break;
          case PREMIUM:
            //获取还款计划/趸交计划的金额
            repaymentAmount = bankDayLimitService.getAmount(scheduleType, id);
            break;
          default:
            //获取还款接口传的还款金额
            repaymentAmount = getRepaymentAmount(joinPoint);
            break;
        }
        if (null == repaymentAmount) {
          repaymentAmount = BigDecimal.ZERO;
          log.error("修改银行限额时，没有获取到还款金额，使用默认值:[{}]",
              repaymentAmount.stripTrailingZeros().toPlainString());
        }
        //获取出bankId
        bankId = bankDayLimitService.getBankId(scheduleType, id);
        //获取出银行日限额
        dayAmountLimit = bankDayLimitService
            .getDayAmountLimit(String.valueOf(bankId), payChannelCode, version);

        //判断当前还款金额是否超过限额
        if (repaymentAmount.compareTo(new BigDecimal(dayAmountLimit)) > 0) {
          log.debug("计划类型=[{}]的还款金额[{}]超过了银行[{}]的日限额[{}]", scheduleType.name(),
              repaymentAmount.stripTrailingZeros().toPlainString(), bankId, dayAmountLimit);
          //要修改银行日限额
          //计算新的银行限额
          int newDayAmountLimit = repaymentAmount.add(new BigDecimal(amount)).intValue();
          log.debug("将银行[{}]原来的日限额[{}]修改为[{}]", bankId, dayAmountLimit, newDayAmountLimit);

          //修改银行限额
          bankDayLimitService
              .setDayAmountLimit(String.valueOf(bankId), payChannelCode, newDayAmountLimit,
                  version);

          //需要恢复银行原来的限额
          needRestore = true;

        } else {
          log.debug("未超过银行限额");
        }
      }

      //判断是否需要禁用指定的支付通道
      if (banCodes.length >= 1) {
        log.debug("有设置需要禁用的通道");
        //有设置需要禁用的通道
        List<String> codes = new ArrayList<>(banCodes.length);
        for (PayPlatformEnum banCode : banCodes) {
          codes.add(banCode.getCode());
        }
        log.debug("需要禁用支付通道[{}]", codes);
        //先查询原来的支付通道状态，因为后面测完之后，需要还原的
        List<PayPlatformEntity> payPlatformEntityList = payPlatformDao.findByVersion(version);
        log.debug("禁用支付通道[{}]前的payPlatformEntityList=[{}]", codes, payPlatformEntityList);
        //禁用指定的支付通道
        //ban
        list = payPlatformService
            .banPayPlatformByCode(payPlatformEntityList,
                codes.toArray(new String[banCodes.length]));
        log.debug("成功禁用支付通道[{}]", codes);
        //再次查询一下
        List<PayPlatformEntity> payPlatformEntityListAfterBan = payPlatformDao
            .findByVersion(version);
        log.debug("禁用支付通道[{}]后的payPlatformEntityListAfterBan=[{}]", codes,
            payPlatformEntityListAfterBan);
      } else {
        //banCodes和banOtherCodes是互斥的
        //如果同时设置了banCodes和banOtherCodes，只有banCodes生效
        //判断是否需要禁用除指定的支付通道外的通道
        if (banOtherCodes.length >= 1) {
          log.debug("有设置需要禁用的通道");
          //有设置需要禁用的通道
          List<String> codes = new ArrayList<>(banOtherCodes.length);
          for (PayPlatformEnum banOtherCode : banOtherCodes) {
            codes.add(banOtherCode.getCode());
          }
          log.debug("需要禁用除[{}]外的支付通道", codes);
          //先查询原来的支付通道状态，因为后面测完之后，需要还原的
          List<PayPlatformEntity> payPlatformEntityList = payPlatformDao.findByVersion(version);
          log.debug("禁用除[{}]外的支付通道，前的payPlatformEntityList=[{}]", payPlatformEntityList);
          //禁用除指定的支付通道外的通道
          //ban
          list = payPlatformService.banOtherPayPlatformByCode(payPlatformEntityList,
              codes.toArray(new String[banOtherCodes.length]));
          log.debug("成功禁用除[{}]外的支付通道", codes);
          //再次查询一下
          List<PayPlatformEntity> payPlatformEntityListAfterBan = payPlatformDao
              .findByVersion(version);
          log.debug("禁用除[{}]外的支付通道，后的payPlatformEntityListAfterBan=[{}]",
              payPlatformEntityListAfterBan);
        } else {
          log.debug("没有设置需要禁用的通道");
        }
      }

      //判断是否需要删除Redis缓存
      boolean delRedisCacheBeforePay = payHandle.delRedisCacheBeforePay();
      log.debug("delRedisCacheBeforePay=[{}]", delRedisCacheBeforePay);
      if (delRedisCacheBeforePay) {
        log.debug("在支付前，先删除Redis缓存");
        //删除Redis缓存
        delRedisCache();
      } else {
        log.debug("在支付前，不需要删除Redis缓存");
      }
    }

    //执行目标方法
    //有返回参数 则需返回值
    Object result = null;
    try {
      //执行目标方法
      result = joinPoint.proceed();
    } finally {
      try {
        //判断是否需要恢复银行原来的限额
        if (needRestore) {
          log.debug("恢复银行[{}]原来的日限额[{}]", bankId, dayAmountLimit);
          //恢复日限额
          bankDayLimitService
              .setDayAmountLimit(String.valueOf(bankId), payChannelCode, dayAmountLimit, version);
          //恢复了日限额后，重新置为false
          needRestore = false;
        }
      } finally {
        //判断是否有修改过的支付通道
        if (null != list) {
          log.debug("恢复被禁用/启用的支付通道list=[{}]", list);
          //还原被修改的支付通道状态
          payPlatformService.recoverPayPlatform(list);
          //还原后，再次查询一下
          List<PayPlatformEntity> payPlatformEntityList = payPlatformDao.findByVersion(version);
          log.debug("恢复支付通道后的payPlatformEntityList=[{}]", payPlatformEntityList);
        }
      }
      //
      if (null != payHandle) {
        //判断是否需要删除Redis缓存
        boolean delRedisCacheAfterPay = payHandle.delRedisCacheAfterPay();
        log.debug("delRedisCacheAfterPay=[{}]", delRedisCacheAfterPay);
        if (delRedisCacheAfterPay) {
          log.debug("在支付后，删除Redis缓存");
          //删除Redis缓存
          delRedisCache();
        } else {
          log.debug("在支付后，不需要删除Redis缓存");
        }
      }
    }

    return result;
  }


  private MethodSignature getMethodSignature(ProceedingJoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    return signature;
  }


  private Annotation[][] getParameterAnnotations(ProceedingJoinPoint joinPoint) {
    MethodSignature signature = getMethodSignature(joinPoint);
    Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
    return parameterAnnotations;
  }


  /**
   * 方法功能描述: 获取拦截到的方法的还款金额参数金额
   *
   * @param joinPoint ProceedingJoinPoint
   * @return java.math.BigDecimal
   */
  private BigDecimal getRepaymentAmount(ProceedingJoinPoint joinPoint) {
    BigDecimal amount = null;
    boolean flag = false;

    Annotation[][] parameterAnnotations = getParameterAnnotations(joinPoint);
    for (int i = 0; i < parameterAnnotations.length; i++) {
      Annotation[] annotations = parameterAnnotations[i];
      for (Annotation annotation : annotations) {
        if (annotation instanceof RepaymentAmount) {
          flag = true;

          Object[] args = joinPoint.getArgs();
          Object param = args[i];
          //
          if (param instanceof Integer) {
            amount = new BigDecimal((Integer) param);
          } else if (param instanceof Long) {
            amount = new BigDecimal((Long) param);
          } else if (param instanceof Double) {
            amount = new BigDecimal((Double) param);
          } else if (param instanceof BigDecimal) {
            amount = (BigDecimal) param;
          } else {
            log.error(
                "被RepaymentAmount注解的参数类型不正确，还款金额只能是Integer/Long/Double/BigDecimal类型，参数下标为:[{}]", i);
            throw new TestCaseException("被RepaymentAmount注解的参数类型不正确");
          }
          break;
        }
      }
    }

    if (!flag) {
      log.error("目标方法的所有参数，均没有被RepaymentAmount标记，请添加RepaymentAmount注解在「还款金额」参数上");
      throw new TestCaseException("目标方法的所有参数，均没有被RepaymentAmount标记");
    }
    //return
    return amount;
  }


  /**
   * 方法功能描述: 获取还款计划ID/趸交ID/LoanOrderID
   *
   * @param joinPoint ProceedingJoinPoint
   * @return int
   */
  private int getId(ProceedingJoinPoint joinPoint) {
    int id = -1;
    boolean flag = false;

    Annotation[][] parameterAnnotations = getParameterAnnotations(joinPoint);
    for (int i = 0; i < parameterAnnotations.length; i++) {
      Annotation[] annotations = parameterAnnotations[i];
      for (Annotation annotation : annotations) {
        if (annotation instanceof ScheduleId) {
          flag = true;

          Object[] args = joinPoint.getArgs();
          Object param = args[i];
          //
          if (param instanceof Integer) {
            id = (Integer) param;
          } else if (param instanceof String) {
            id = Integer.parseInt((String) param);
          } else if (param instanceof Long) {
            id = ((Long) param).intValue();
          } else {
            log.error(
                "被ScheduleId注解的参数类型不正确，还款计划ID/趸交ID/LoanOrderID只能是int/String/long类型，参数下标为:[{}]", i);
            throw new TestCaseException("被ScheduleId注解的参数类型不正确");
          }
          break;
        }
      }
    }

    if (!flag) {
      log.error("目标方法的所有参数，均没有被ScheduleId标记，请添加相应的ScheduleId注解在「还款计划ID/趸交ID/LoanOrderID」");
      throw new TestCaseException("目标方法的所有参数，均没有被ScheduleId标记");
    }

    return id;
  }


  /**
   * 方法功能描述: 获取支付Router的Version
   *
   * @param joinPoint ProceedingJoinPoint
   * @return java.lang.String
   */
  private String getVersion(ProceedingJoinPoint joinPoint) {
    String version = "test";
    boolean flag = false;

    Annotation[][] parameterAnnotations = getParameterAnnotations(joinPoint);
    for (int i = 0; i < parameterAnnotations.length; i++) {
      Annotation[] annotations = parameterAnnotations[i];
      for (Annotation annotation : annotations) {
        if (annotation instanceof RouterVersion) {
          flag = true;

          Object[] args = joinPoint.getArgs();
          Object param = args[i];
          //
          if (param instanceof String) {
            version = (String) param;
          } else if (param instanceof StringBuffer) {
            version = ((StringBuffer) param).toString();
          } else if (param instanceof StringBuilder) {
            version = ((StringBuilder) param).toString();
          } else {
            log.error(
                "被RouterVersion注解的参数类型不正确，RouterVersion只能是String/StringBuffer/StringBuilder类型，参数下标为:[{}];使用默认值:[{}]",
                i, version);
          }
          break;
        }
      }
    }

    if (!flag) {
      log.error("目标方法的所有参数，均没有被RouterVersion标记，请在相应的参数上添加RouterVersion注解;使用默认值:[{}]", version);
    }

    return version;
  }


  /**
   * 方法功能描述: 绑卡/鉴权
   *
   * @param id 还款计划ID/趸交ID/LoanOrderID
   * @param payHandle UpdateBankDayLimitAndPayChannel注解
   * @return void
   */
  private void bindCard(int id, PayHandle payHandle) {
    //是否需要绑卡
    PayPlatformEnum payPlatformEnum = payHandle.payPlatform();
    byte bindCard = payPlatformEnum.getBindCard();
    log.debug("支付通道[{} ({})]，是否需要绑卡[{}]", payPlatformEnum.getName(), payPlatformEnum.getCode(),
        bindCard);
    if (bindCard == 0) {
      log.debug("不需要绑卡");
      return;
    } else if (bindCard == 1) {
      log.debug("需要绑卡");
    } else {
      log.error("支付通道[{} ({})]，是否需要绑卡bindCard=[{}]值错误，只能是0或1", payPlatformEnum.getName(),
          payPlatformEnum.getCode(), bindCard);
      throw new TestCaseException("bindCard=" + bindCard + "值错误");
    }
    int loanOrderId;
    ScheduleTypeEnum scheduleType = payHandle.scheduleType();
    switch (scheduleType) {
      case LOAN_ORDER:
        loanOrderId = id;
        break;
      case PREMIUM:
        loanOrderId = singlePremiumScheduleDao.findById(id).get().getLoanOrderId();
        break;
      case REPAYMENT:
        loanOrderId = repaymentScheduleDao.findById(id).get().getLoanOrderId();
        break;
      default:
        loanOrderId = 0;
        break;
    }
    if (loanOrderId <= 0) {
      log.error("没有loanOrderId，无法鉴权，请检查！");
      return;
    }
    //
    BindCardEnum[] bindCardEnums = {payHandle.payPlatform().getBindCardEnum()};
    String authCode = payHandle.authCode();
    //鉴权
    int bankAccountId = authService.auth(loanOrderId, bindCardEnums, authCode);
    //绑卡后校验数据库数据
    verifyBindData.verify(bankAccountId, bindCardEnums);
  }


  /**
   * 方法功能描述: 删除Redis缓存
   *
   * @return void
   */
  private void delRedisCache() {
    int sleepTime = 2;
    //删除Redis缓存
    redisCacheFactory.delete();
    log.debug("删除Redis缓存后，暂停[{}]秒", sleepTime);
    ThreadUtil.sleep(sleepTime);
  }


}
