package com.socks.pages.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoggedUserPage {
    public SelenideElement logoutBtn(){
        return $("#logout > a");
    }

    public SelenideElement userName() {
        return $("#howdy > a");
    }
}
