package com.socks.pages.test;

import com.codeborne.selenide.Condition;
import com.socks.pages.api.conditions.Conditions;
import com.socks.pages.api.payload.PayLoadUserRegistration;
import com.socks.pages.api.sevices.UserApiServices;
import com.socks.pages.pages.LoggedUserPage;
import com.socks.pages.pages.MainPage;
import com.socks.pages.utils.BaseUITest;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class MainPageLoginTest extends BaseUITest {

    private UserApiServices userApiServices = new UserApiServices();

    @Test
    public void userCanLoginWithValidCred() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(8));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())));
        //when
        MainPage.open()
                .loginAs(payLoadUser.getUsername(), payLoadUser.getPassword());


        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();

        //loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));

    }


    //Dakota Paite
}
