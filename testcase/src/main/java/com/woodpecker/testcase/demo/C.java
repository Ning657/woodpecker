package com.woodpecker.testcase.demo;

import com.woodpecker.entity.payment.PayPlatformEntity;
import com.woodpecker.testcase.payment.repayment.RepaymentTestCase;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 类描述:〈〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public class C extends RepaymentTestCase {

  @BeforeSuite
  public void beforeSutie() {
    System.out.println("C BeforeSuite");
  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("C BeforeTest");
  }

  @BeforeClass
  public void beforeClass() {
    System.out.println("C BeforeClass");
  }

  @BeforeMethod
  public void beforeMethod() {
    System.out.println("C BeforeMethod");
  }

  @Test
  public void test1() {
    System.out.println("C Test1");
    List<PayPlatformEntity> list = null;
    try {
      String[] codes = {"2", "10", "15", "48"};
      list = super.banPayPlatformByCode(codes);
    } finally {
      if (null != list) {
        super.recoverPayPlatform(list);
      }
    }
  }

  @Test
  public void test2() {
    System.out.println("C Test2");
    List<PayPlatformEntity> list = null;
    try {
      String[] codes = {"60", "61"};
      //String[] codes = {"2", "10", "15", "48"};
      list = super.banOtherPayPlatformByCode(codes);
    } finally {
      if (null != list) {
        super.recoverPayPlatform(list);
      }
    }
  }

  @AfterSuite
  public void afterSutie() {
    System.out.println("C AfterSuite");
  }

  @AfterTest
  public void afterTest() {
    System.out.println("C AfterTest");
  }

  @AfterClass
  public void afterClass() {
    System.out.println("C AfterClass");
  }

  @AfterMethod
  public void afterMethod() {
    System.out.println("C AfterMethod");
  }

}
