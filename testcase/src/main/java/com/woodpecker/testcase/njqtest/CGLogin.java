package com.woodpecker.testcase.njqtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;

/**
 * @author Ning
 * @descripition:新清算系统登录功能
 * @creat 2019-07-31
 */

public class CGLogin {

    WebDriver  driver;

    //启动chrome驱动
    @BeforeClass
    public void ready(){
        System.setProperty("webdriver.chrome.driver","/Users/ning/Documents/woodpecker/testcase/src/main/resources/driver/chromedriver");
        driver =new ChromeDriver();
    }


    //用户登录
    @Test
    public void login() throws InterruptedException {
        driver.get("http://clearing-gateway.test.91gfd.cn/user/login?from=%2Floan%2Fdashboard");

        // 输入用户名
        WebElement userNameEle= driver.findElement(By.id("username"));
        userNameEle.clear();
        userNameEle.sendKeys("ningjieqiong");

        // 输入密码
        WebElement passWordEle= driver.findElement(By.id("password"));
        passWordEle.clear();
        passWordEle.sendKeys("Dashu07011");

        // 点击登录
        //WebElement loginbtn= driver.findElement(By.xpath("//button[@type='submit']"));
        WebElement loginbtn= driver.findElement(By.xpath("//*[@span='app']/section/main/div/form/div[3]/div/div/span/button"));
        loginbtn.click();

        Thread.sleep(3000);

        // 判断是否登录成功
        WebElement isLoginEle= driver.findElement(By.xpath("//*[@id='app']/section/header/ul/li[8]/div/div/span[2]"));
        boolean isDisplayed=isLoginEle.isDisplayed() ;
        Assert.assertTrue(isDisplayed,"断言登录成功");


    }



}