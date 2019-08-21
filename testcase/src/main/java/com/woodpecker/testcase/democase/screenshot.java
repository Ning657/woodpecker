package com.woodpecker.testcase.democase;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Ning
 * @descripition:将当前浏览器的窗口截图
 * @creat 2019-08-21
 */

public class screenshot {
    WebDriver driver;

    //启动chrome驱动
    @BeforeClass
    public void ready(){
        System.setProperty("webdriver.chrome.driver","/Users/ning/Documents/woodpecker/testcase/src/main/resources/driver/chromedriver");
        driver=new ChromeDriver();
    }

    @Test
    public void screenWindow(){
        //访问百度首页
        driver.get("www.baidu.com");

        //截图并保存
        File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtil.copyFile(scrFile, new File("/Users/ning/Downloads/脚本/screen"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }



}