package com.jaga.test;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() {
        if(Boolean.getBoolean("selenium.grid.enabled")) {
            this.driver = getRemoteDriver();
        }else {
            this.driver = getLocalDriver();
        }
        driver.manage().window().maximize();
    }

    public WebDriver getLocalDriver() {
        return new ChromeDriver();
    }

    public WebDriver getRemoteDriver() {
        Capabilities capabilities;
        if(System.getProperty("browser").equalsIgnoreCase("chrome")) {
            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }
        try {
            return new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

}
