package com.woodpecker.testcase.demo;

import com.woodpecker.testcase.TestCase;
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
public class B extends TestCase {

  @BeforeSuite
  public void beforeSutie() {
    System.out.println("B BeforeSuite");
  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("B BeforeTest");
  }

  @BeforeClass
  public void beforeClass() {
    System.out.println("B BeforeClass");
  }

  @BeforeMethod
  public void beforeMethod() {
    System.out.println("B BeforeMethod");
  }

  @Test
  public void test() {
    System.out.println("B Test");
  }

  @AfterSuite
  public void afterSutie() {
    System.out.println("B AfterSuite");
  }

  @AfterTest
  public void afterTest() {
    System.out.println("B AfterTest");
  }

  @AfterClass
  public void afterClass() {
    System.out.println("B AfterClass");
  }

  @AfterMethod
  public void afterMethod() {
    System.out.println("B AfterMethod");
  }

}
