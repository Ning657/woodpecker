package com.woodpecker.testcase.payment.repayment;

import com.woodpecker.service.databuild.DataBuildOrderService;
import com.woodpecker.service.databuild.PlatformIdEnum;
import com.woodpecker.service.payment.cache.RedisCacheFactory;
import com.woodpecker.testcase.payment.PaymentTestCase;
import com.xujinjian.Commons.Lang.ThreadUtil;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

/**
 * 类描述:〈还款TestCase基类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class RepaymentTestCase extends PaymentTestCase {

  @Autowired
  protected DataBuildOrderService dataBuildOrderService;

  @Autowired
  protected RedisCacheFactory redisCacheFactory;

  /**
   * Redis删除后等待的时间，3秒
   */
  protected int redisSleepTime = 3;


  /**
   * 方法功能描述: 删除用户名下的订单
   *
   * @param platformIdEnum 资金渠道
   * @return void
   */
  public void deleteUserOrders(PlatformIdEnum platformIdEnum) {
    //删除用户名下的订单
    boolean deleteResult = dataBuildOrderService.delete(userId, platformIdEnum);
    Assert.assertTrue(deleteResult, "校验删除用户" + userId + "名下的订单是否成功deleteResult=" + deleteResult);
  }


  /**
   * 方法功能描述: 删除指定订单
   *
   * @param orderIds 原始订单Id(多个用英文,间隔)
   * @return void
   */
  public void deleteOrders(String orderIds) {
    //删除指定订单
    boolean deleteResult = dataBuildOrderService.delete(orderIds);
    Assert.assertTrue(deleteResult, "校验删除订单" + orderIds + "是否成功deleteResult=" + deleteResult);
  }


  /**
   * 方法功能描述: 生成还款订单
   *
   * @param platformIdEnum 资金渠道
   * @param version version
   * @return java.util.Map
   */
  public Map<String, String> createOrder(PlatformIdEnum platformIdEnum, String version) {
    //生成还款订单
    Map<String, String> map = dataBuildOrderService.create(userId, platformIdEnum, version);
    Assert.assertEquals(map.size(), 2, "校验是否生成用户" + userId + "的还款订单成功map=" + map.toString());
    return map;
  }


  /**
   * 方法功能描述: 清除会影响支付的Redis缓存
   *
   * @return void
   */
  public void cleanRedis() {
    redisCacheFactory.delete();
    //删除缓存后，暂停几秒，等待生效
    ThreadUtil.sleep(redisSleepTime);
  }


}
