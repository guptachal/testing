package com.w2a.testcases;

import com.w2a.pages.HomePage;

import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import org.testng.annotations.Test;

public class Login extends BaseTest {

    @Test
    public void loginTest(){
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();
       // ZohoAppPage appPage = new ZohoAppPage();
        homePage.gotoLogin();
        loginPage.login();
       // appPage.gotoAppPage();
    }
}
