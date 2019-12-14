package com.socks.pages.test;

import com.codeborne.selenide.Condition;
import com.socks.pages.api.payload.PayLoadUserRegistration;
import com.socks.pages.pages.LoggedUserPage;
import com.socks.pages.pages.MainPage;
import com.socks.pages.utils.BaseUITest;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import com.socks.pages.test.MainPageRegistrationTest;
import com.socks.pages.api.payload.PayLoadUserRegistration;

public class MainPageRegistrationTest extends BaseUITest {


    @Test
    public void canRegWithRandomValidParam() {
        MainPage.open().registerAsValidRandomParam();
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("Registration and login successful."));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);

    }

    @Test
    public void canRegWithValidParam() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(8));
        payLoadUser.setFirstname(RandomStringUtils.randomAlphabetic(7));
        payLoadUser.setLastname(RandomStringUtils.randomAlphanumeric(7));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUser.getUsername(),
                payLoadUser.getFirstname(),
                payLoadUser.getLastname(),
                payLoadUser.getEmail(),
                payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("Registration and login successful."));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as " + payLoadUser.getFirstname() + " " + payLoadUser.getLastname()));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);

    }


    @Test
    public void canNotRegWithOnlyPassword() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUser.getUsername(),
                payLoadUser.getFirstname(),
                payLoadUser.getLastname(),
                payLoadUser.getEmail(),
                payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
        loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
        loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canNotRegWithOnlyEmail() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUser.getUsername(),
                payLoadUser.getFirstname(),
                payLoadUser.getLastname(),
                payLoadUser.getEmail(),
                payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
        loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
        loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));
    }

    @Test
    public void canNotRegWithEmailAndPassword() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(8));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUser.getUsername(),
                payLoadUser.getFirstname(),
                payLoadUser.getLastname(),
                payLoadUser.getEmail(),
                payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
        loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
        loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canNotRegWithEmailAndPasswordAndLastName() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(8));
        payLoadUser.setLastname(RandomStringUtils.randomAlphabetic(7));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUser.getUsername(),
                payLoadUser.getFirstname(),
                payLoadUser.getLastname(),
                payLoadUser.getEmail(),
                payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
        loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
        loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canNotRegWithoutUSername() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(8));
        payLoadUser.setLastname(RandomStringUtils.randomAlphabetic(7));
        payLoadUser.setFirstname(RandomStringUtils.randomAlphanumeric(8));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUser.getUsername(),
                payLoadUser.getFirstname(),
                payLoadUser.getLastname(),
                payLoadUser.getEmail(),
                payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
        loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
        loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canRegWithOnlyUsername() {
        //given
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(8));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUser.getUsername(),
                payLoadUser.getFirstname(),
                payLoadUser.getLastname(),
                payLoadUser.getEmail(),
                payLoadUser.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("Registration and login successful."));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);

    }


}
