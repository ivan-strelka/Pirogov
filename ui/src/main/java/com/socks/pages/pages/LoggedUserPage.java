package com.socks.pages.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class LoggedUserPage {

    public SelenideElement logoutBtn() {
        return $("#logout > a");
    }

    public SelenideElement userName() {
        return $("#howdy > a");
    }

    public SelenideElement weLoveSockElement() {
        return $(By.xpath("//a[contains(text(),'We love socks!')]"));
    }

    public SelenideElement existContent() {
        return $(By.xpath("//div[@id='content']"));
    }

    public SelenideElement alert() {
        return $("#login-message > div");
    }

    public SelenideElement loginField() {
        return $("#username-modal");
    }

    public SelenideElement passwordField() {
        return $("#password-modal");
    }

    public SelenideElement customerLogin() {
        return $("#Login");
    }

    public SelenideElement alertRegistration (){
        return $("#registration-message > div");
    }

    public SelenideElement titleRigister (){
        return $(By.xpath("//h4[contains(text(),'Register')]"));
    }


}
