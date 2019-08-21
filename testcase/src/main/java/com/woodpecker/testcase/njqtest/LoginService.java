package com.woodpecker.testcase.njqtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Ning
 * @descripition:
 * @creat 2019-08-16
 */

public class LoginService {

    WebDriver webDriver;

    public LoginService(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     *
     * @param userName
     * @param password
     */
    public void login(String userName,String password){
        LoanClearingPO loanClearingPO = PageFactory.initElements(webDriver,LoanClearingPO.class);

        // 输入用户名
        loanClearingPO.inputUserName(userName);

        // 输入密码
        loanClearingPO.inputPassword(password);

        // 点击登录
        loanClearingPO.clickLoginBtn();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}