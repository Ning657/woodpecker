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
public class A extends TestCase {

  @BeforeSuite
  public void beforeSutie() {
    System.out.println("A BeforeSuite");
  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("A BeforeTest");
  }

  @BeforeClass
  public void beforeClass() {
    System.out.println("A BeforeClass");
  }

  @BeforeMethod
  public void beforeMethod() {
    System.out.println("A BeforeMethod");
  }

  @Test
  public void test1() {
    System.out.println("A Test1");
  }

  @Test
  public void test2() {
    System.out.println("A Test2");
  }

  @AfterSuite
  public void afterSutie() {
    System.out.println("A AfterSuite");
  }

  @AfterTest
  public void afterTest() {
    System.out.println("A AfterTest");
  }

  @AfterClass
  public void afterClass() {
    System.out.println("A AfterClass");
  }

  @AfterMethod
  public void afterMethod() {
    System.out.println("A AfterMethod");
  }

}
