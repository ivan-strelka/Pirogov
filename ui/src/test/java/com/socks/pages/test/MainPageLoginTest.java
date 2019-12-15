package com.socks.pages.test;

import com.codeborne.selenide.Condition;
import com.socks.pages.api.conditions.Conditions;
import com.socks.pages.api.payload.PayLoadUserRegistration;
import com.socks.pages.api.sevices.UserApiServices;
import com.socks.pages.pages.LoggedUserPage;
import com.socks.pages.pages.MainPage;
import com.socks.pages.utils.BaseUITest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

@Slf4j
public class MainPageLoginTest extends BaseUITest {

    private UserApiServices userApiServices = new UserApiServices();

    @Test
    public void canNotLoginWithWrongCreds() {
        MainPage.open().loginAs("Lora", "123");
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.customerLogin().shouldHave(Condition.text("Customer login"));
        loggedUserPage.alert().shouldHave(Condition.text("Invalid login credentials."));
        loggedUserPage.loginField().shouldBe(Condition.not(Condition.empty));
        loggedUserPage.passwordField().shouldBe(Condition.not(Condition.empty));

    }

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
        MainPage.open().loginAs(payLoadUser.getUsername(), payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.customerLogin().shouldHave(Condition.text("Customer login"));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        log.info("ASSERT shouldHave Condition text Logout");
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
        log.info("ASSERT shouldHave Condition text Logged in as");
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
        log.info("ASSERT shouldHave Condition text We love socks!");
        loggedUserPage.existContent().should(Condition.exist);
        log.info("ASSERT shouldHave Condition exist");
        loggedUserPage.logoutBtn().click();

    }

    @Test
    public void canLoginParticularUser() {
        MainPage.open().loginAs("tenamu", "123");
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.customerLogin().shouldHave(Condition.text("Customer login"));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as Dakota Pate"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
        // loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);
        loggedUserPage.logoutBtn().click();

    }


    @Test
    public void canLoginWithoutCreds() {
        MainPage.open().loginAs("", "");
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.customerLogin().shouldHave(Condition.text("Customer login"));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
        // loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);
        loggedUserPage.logoutBtn().click();
    }

    @Test
    public void canNotLoginWithGap() {
        MainPage.open().loginAs("    ", "         ");
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alert().shouldHave(Condition.text("Invalid login credentials."));
        loggedUserPage.loginField().shouldBe(Condition.not(Condition.empty));
        loggedUserPage.loginField().shouldBe(Condition.not(Condition.empty));
        loggedUserPage.passwordField().shouldBe(Condition.not(Condition.empty));
        loggedUserPage.customerLogin().shouldHave(Condition.text("Customer login"));
    }


}
