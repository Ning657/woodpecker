package com.woodpecker.testcase.njqtest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Ning
 * @descripition:新清算系统登录功能
 * @creat 2019-07-31
 */

public class CGLogin2 {

    WebDriver  driver;

    //启动chrome驱动
//    @BeforeClass
    public void ready(){
        System.setProperty("webdriver.chrome.driver","/Users/ning/Documents/woodpecker/testcase/src/main/resources/driver/chromedriver");
        driver =new ChromeDriver();
    }


    private boolean login(){
        driver.get("http://clearing-gateway.test.91gfd.cn/user/login?from=%2Floan%2Fdashboard");
        LoginService loginService = new LoginService(driver);
        loginService.login("ningjieqiong","Dashu07011");
        try{
            // 判断是否登录成功
            WebElement isLoginEle= driver.findElement(By.xpath("//span[text()='仪表盘']"));
            boolean isDisplayed=isLoginEle.isDisplayed() ;
            if (isDisplayed){
                return true;
            }else {
                return false;
            }
            //Assert.assertTrue(isDisplayed,"断言登录成功");
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }

    }


    private void fenfa(){

    }

    //
//    @Test
    public void test() {
        //login
        boolean loginFlag=login();
        if (loginFlag){
            System.out.println("登录成功分发订单");

            //fenfa
            fenfa();


        }else {
            System.out.println("登陆失败");
        }

    }


//    @AfterClass
    public void quit(){
        driver.quit();
    }




}