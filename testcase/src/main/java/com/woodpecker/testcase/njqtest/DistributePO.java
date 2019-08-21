package com.woodpecker.testcase.njqtest;

import com.xujinjian.Commons.Lang.ThreadUtil;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * @author Ning
 * @descripition:订单路由中待分发tab页
 * @creat 2019-08-02
 */

public class DistributePO {
    WebDriver driver;

    public DistributePO(WebDriver driver) {
        this.driver = driver;
    }

    //订单路由
    @FindBy(xpath = "//span[text()='订单路由']")
    WebElement routeEle;

    //订单分发
    @FindBy(xpath = "//div[text()='待分发']")
    WebElement distriEle;

    //订单输入框
    @FindBy(id = "orderId")
    WebElement orderEle;

    //筛选按钮
    @FindBy(xpath = "//span/button[span='筛 选']")
    WebElement filterEle;

    //订单勾选框
    @FindBy(xpath = "//table/thead/tr/th[1]/span/div/span[1]/div/label/span/input[1]")
    WebElement checkOrderEle;

    //分发选渠道按钮
    @FindBy(xpath = "//span[text()='分发选渠道']")
    WebElement distriChannelEle;

    //渠道选择框
    @FindBy(xpath = "//span[text()='投资渠道']/following-sibling::div[1]/div")
    WebElement selectChannelEle;

    //光大渠道
    @FindBy(xpath = "//li[@text()='光大信托']")
    WebElement GDChannelEle;



    /**
     * 点击订单路由
     * @param
     */
    public void clickRoute(){
        routeEle.click();
        sleep(1);
    }

    /**
     * 点击待分发
     * @param
     */
    public void clickDistri(){
        distriEle.click();
        sleep(1);
    }

    /**
     * 输入订单号
     * @param order
     */
    public void inputOrder(String order){
        orderEle.sendKeys(order);
    }

    /**
     * 点击筛选
     * @param
     */
    public void clickfilterBtn(){
//        filterEle.sendKeys(Keys.SPACE);
        filterEle.click();
        sleep(3);
    }

    /**
     * 勾选订单
     * @param
     */
    public void clickSelect(){
//        checkOrderEle.click();
        checkOrderEle.sendKeys(Keys.ENTER);
    }

    /**
     * 点击分发渠道按钮
     * @param
     */
    public void clickChannelBtn(){
        distriChannelEle.click();
        sleep(1);
    }

    /**
     * 点击渠道选择框
     * @param
     */
    public void clickSelectChannelBtn(){
        selectChannelEle.click();
        sleep(1);
    }

    /**
     * 点击光大渠道
     * @param
     */
    public void channelBtn(){
        GDChannelEle.click();
        sleep(1);
    }


    private void sleep(int s){
        try {
            Thread.sleep(1000 * s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}

