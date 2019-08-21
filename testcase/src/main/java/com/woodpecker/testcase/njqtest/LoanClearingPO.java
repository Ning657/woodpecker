package com.woodpecker.testcase.njqtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Ning
 * @descripition:
 * @creat 2019-08-16
 */

public class LoanClearingPO {

    WebDriver webDriver;

    public LoanClearingPO(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    @FindBy(id = "username")
    private WebElement userNameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;


    /**
     * 输入用户名
     * @param userName
     */
    public void inputUserName(String userName)  {
        userNameInput.clear();
        sleep(1);
        this.userNameInput.sendKeys(userName);
    }

    /**
     * 输入密码
     * @param password
     */

    public void inputPassword(String password){
        passwordInput.clear();
        sleep(1);
        this.passwordInput.sendKeys(password);
    }
    /**
     * 点击登录
     * @param
     */

    public void clickLoginBtn(){
        this.loginBtn.click();
    }


    private void sleep(int s){
        try {
            Thread.sleep(1000 * s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}