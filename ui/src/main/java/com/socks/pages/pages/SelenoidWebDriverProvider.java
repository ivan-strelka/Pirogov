package com.socks.pages.pages;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidWebDriverProvider implements WebDriverProvider {

    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("78.0");
        browser.setCapability("enableVNC", true);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub/").toURL(),
                    browser
            );
            driver.manage().window().setSize(new Dimension(1200, 700));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
