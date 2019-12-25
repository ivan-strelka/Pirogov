package com.socks.pages.test;

import com.codeborne.selenide.Condition;
import com.socks.pages.api.payload.PayLoadUserRegistration;
import com.socks.pages.pages.LoggedUserPage;
import com.socks.pages.pages.MainPage;
import com.socks.pages.utils.BaseUITest;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class MainPageRegistrationTest extends BaseUITest {


    @Test
    public void canRegWithRandomValidParam() {
        MainPage.open().registerAsValidRandomParam();
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("Registration and login successful."));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
       // loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);
        loggedUserPage.logoutBtn().click();

    }

    @Test
    public void canRegWithValidParam() {
        //given
        PayLoadUserRegistration payLoadUserRegistration = new PayLoadUserRegistration();
        payLoadUserRegistration.setUsername(RandomStringUtils.randomAlphanumeric(8));
        payLoadUserRegistration.setFirstname(RandomStringUtils.randomAlphabetic(7));
        payLoadUserRegistration.setLastname(RandomStringUtils.randomAlphanumeric(7));
        payLoadUserRegistration.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUserRegistration.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUserRegistration.getUsername(),
                payLoadUserRegistration.getFirstname(),
                payLoadUserRegistration.getLastname(),
                payLoadUserRegistration.getEmail(),
                payLoadUserRegistration.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("Registration and login successful."));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as " + payLoadUserRegistration.getFirstname() + " " + payLoadUserRegistration.getLastname()));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
        //loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);
        loggedUserPage.logoutBtn().click();

    }


    @Test
    public void canNotRegWithOnlyPassword() {
        //given
        PayLoadUserRegistration payLoadUserRegistration = new PayLoadUserRegistration();
        payLoadUserRegistration.setPassword(RandomStringUtils.randomAlphanumeric(5));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUserRegistration.getUsername(),
                payLoadUserRegistration.getFirstname(),
                payLoadUserRegistration.getLastname(),
                payLoadUserRegistration.getEmail(),
                payLoadUserRegistration.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
        //loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
      //  loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canNotRegWithOnlyEmail() {
        //given
        PayLoadUserRegistration payLoadUserRegistration = new PayLoadUserRegistration();
        payLoadUserRegistration.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUserRegistration.getUsername(),
                payLoadUserRegistration.getFirstname(),
                payLoadUserRegistration.getLastname(),
                payLoadUserRegistration.getEmail(),
                payLoadUserRegistration.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
       // loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
       // loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));
    }

    @Test
    public void canNotRegWithEmailAndPassword() {
        //given
        PayLoadUserRegistration payLoadUserRegistration = new PayLoadUserRegistration();
        payLoadUserRegistration.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");
        payLoadUserRegistration.setPassword(RandomStringUtils.randomAlphanumeric(8));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUserRegistration.getUsername(),
                payLoadUserRegistration.getFirstname(),
                payLoadUserRegistration.getLastname(),
                payLoadUserRegistration.getEmail(),
                payLoadUserRegistration.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
       // loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
      //  loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canNotRegWithEmailAndPasswordAndLastName() {
        //given
        PayLoadUserRegistration payLoadUserRegistration = new PayLoadUserRegistration();
        payLoadUserRegistration.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");
        payLoadUserRegistration.setPassword(RandomStringUtils.randomAlphanumeric(8));
        payLoadUserRegistration.setLastname(RandomStringUtils.randomAlphabetic(7));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUserRegistration.getUsername(),
                payLoadUserRegistration.getFirstname(),
                payLoadUserRegistration.getLastname(),
                payLoadUserRegistration.getEmail(),
                payLoadUserRegistration.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
       // loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
       // loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canNotRegWithoutUSername() {
        //given
        PayLoadUserRegistration payLoadUserRegistration = new PayLoadUserRegistration();
        payLoadUserRegistration.setEmail(RandomStringUtils.randomAlphabetic(12) + "@gmail.com");
        payLoadUserRegistration.setPassword(RandomStringUtils.randomAlphanumeric(8));
        payLoadUserRegistration.setLastname(RandomStringUtils.randomAlphabetic(7));
        payLoadUserRegistration.setFirstname(RandomStringUtils.randomAlphanumeric(8));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUserRegistration.getUsername(),
                payLoadUserRegistration.getFirstname(),
                payLoadUserRegistration.getLastname(),
                payLoadUserRegistration.getEmail(),
                payLoadUserRegistration.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("There was a problem with your registration: Internal Server Error"));
      //  loggedUserPage.alertRegistration().should(Condition.visible);
        loggedUserPage.alertRegistration().shouldHave(Condition.exist);
       // loggedUserPage.titleRigister().shouldHave(Condition.visible);
        loggedUserPage.titleRigister().shouldHave(Condition.text("Register"));

    }

    @Test
    public void canRegWithOnlyUsername() {
        //given
        PayLoadUserRegistration payLoadUserRegistration = new PayLoadUserRegistration();
        payLoadUserRegistration.setUsername(RandomStringUtils.randomAlphanumeric(8));

        //when
        MainPage.open().registerAsParticularParam(
                payLoadUserRegistration.getUsername(),
                payLoadUserRegistration.getFirstname(),
                payLoadUserRegistration.getLastname(),
                payLoadUserRegistration.getEmail(),
                payLoadUserRegistration.getPassword());

        //then
        LoggedUserPage loggedUserPage = new LoggedUserPage();
        loggedUserPage.alertRegistration().shouldHave(Condition.text("Registration and login successful."));
        loggedUserPage.logoutBtn().shouldHave(Condition.text("Logout"));
        loggedUserPage.userName().shouldHave(Condition.text("Logged in as"));
        loggedUserPage.weLoveSockElement().shouldHave(Condition.text("We love socks!"));
       // loggedUserPage.weLoveSockElement().shouldHave(Condition.visible);
        loggedUserPage.existContent().should(Condition.exist);
        loggedUserPage.logoutBtn().click();

    }


}
