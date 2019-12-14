package com.socks.pages.pages;

import com.codeborne.selenide.Selenide;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {


    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();

    }

    public void loginAs(String username, String password) {
        $("#login").click();
        $("#username-modal").setValue(username);
        $("#password-modal").setValue(password);
        $(By.xpath("//button[contains(text(),'Log in')]")).click();

    }

    public void registerAsParticularParam(String Username, String First_name, String Last_name, String Email, String Password ) {
        $(By.xpath("//a[contains(text(),'Register')]")).click();
        $("#register-username-modal").setValue(Username);
        $("#register-first-modal").setValue(First_name);
        $("#register-last-modal").setValue(Last_name);
        $("#register-email-modal").setValue(Email);
        $("#register-password-modal").setValue(Password);
        $(By.xpath("//button[contains(text(),'Register')]")).click();

    }

    public void registerAsValidRandomParam() {
        $("#register > a").click();
        $("#register-username-modal").setValue(RandomStringUtils.randomAlphabetic(8));
        $("#register-first-modal").setValue(RandomStringUtils.randomAlphabetic(10));
        $("#register-last-modal").setValue(RandomStringUtils.randomAlphabetic(5));
        $("#register-email-modal").setValue(RandomStringUtils.randomAlphabetic(8) + "gmail.com");
        $("#register-password-modal").setValue(RandomStringUtils.randomAlphabetic(7));
        $(By.xpath("//button[contains(text(),'Register')]")).click();

    }



}
