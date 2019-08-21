package com.woodpecker.testcase.njqtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * @author Ning
 * @descripition:分发渠道
 * @creat 2019-08-05
 */

public class DistributeService {
    WebDriver webDriver;

    public DistributeService(WebDriver driver){
        this.webDriver=driver;
    }

    //订单分发至光大
    public void distribute(String orderId){
        DistributePO distributePO = PageFactory.initElements(webDriver,DistributePO.class);

        //点击订单路由
        distributePO.clickRoute();

        //点击待分发tab页
        distributePO.clickDistri();

        //输入订单号
        distributePO.inputOrder(orderId);

        //点击筛选
        distributePO.clickfilterBtn();

        //勾选订单
        distributePO.clickSelect();

        //点击分发按钮
        distributePO.clickChannelBtn();

        //点击渠道选择框
        distributePO.clickSelectChannelBtn();

        //点击光大
        distributePO.channelBtn();

        //点击分发按钮
        distributePO.clickChannelBtn();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}