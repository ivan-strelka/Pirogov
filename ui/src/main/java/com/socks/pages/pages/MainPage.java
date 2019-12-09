package com.socks.pages.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {


    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();

    }

    public void loginAs(String username, String password) {
        $("#login > a").click();
        $("#username-modal").setValue(username);
        $("#password-modal").setValue(password);
        $(By.xpath("//button[contains(text(),'Log in')]")).click();

    }




}
