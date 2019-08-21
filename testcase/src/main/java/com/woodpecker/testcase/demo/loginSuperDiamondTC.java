package com.woodpecker.testcase.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Ning
 * @descripition:
 * @creat 2019-07-31
 */

public class loginSuperDiamondTC {

    WebDriver driver;

    @BeforeClass
    public void ready(){
        System.setProperty("webdriver.chrome.driver","/Users/ning/Documents/woodpecker/testcase/src/main/resources/driver/chromedriver");
        driver = new ChromeDriver();

    }

    @Test
    public void test(){
        System.out.println("test");
        driver.get("http://superdiamond.wangxin.test.91gfd.cn/superdiamond/");

        WebElement userNameElement=driver.findElement(By.name("userCode"));
        userNameElement.sendKeys("admin");

        WebElement passwordElement=driver.findElement(By.name("password"));
        passwordElement.sendKeys("000000");

        WebElement loginBtn=driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
    }
    @Test
    public  void test2(){
        System.out.print("222");
    }

    @AfterClass
    public void close(){
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.quit();

    }

}