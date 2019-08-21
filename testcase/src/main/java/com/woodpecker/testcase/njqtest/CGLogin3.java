package com.woodpecker.testcase.njqtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Ning
 * @descripition:新清算系统登录功能
 * @creat 2019-07-31
 */

public class CGLogin3 {

    WebDriver  driver;

    //启动chrome驱动
    @BeforeClass
    public void ready(){
        System.setProperty("webdriver.chrome.driver","/Users/ning/Documents/woodpecker/testcase/src/main/resources/driver/chromedriver");
        driver =new ChromeDriver();
    }

    //用户登录
    private void login() throws InterruptedException {
        LoginService loginService = new LoginService(driver);
        driver.get("http://clearing-gateway.test.91gfd.cn/user/login?from=%2Floan%2Fdashboard");

        // 登录
        loginService.login("ningjieqiong","Dashu07011");

        Thread.sleep(3000);

        // 判断是否登录成功
        WebElement isLoginEle= driver.findElement(By.xpath("//span[text()='仪表盘']"));
        boolean isDisplayed=isLoginEle.isDisplayed() ;
        Assert.assertTrue(isDisplayed,"断言登录成功");


    }


    private void distribute() {
        DistributeService distributeService=new DistributeService(driver);

        //分发订单
        distributeService.distribute("54893038");
    }

    @Test
    public void test() throws InterruptedException{
        //登录
        login();
//        //分发
//        distribute();
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }




}