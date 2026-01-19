package com.jaga.test;

import com.jaga.util.ConfigUtil;
import com.jaga.util.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;

public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    protected WebDriver driver;

    @BeforeSuite
    public void setupConfig() {
        ConfigUtil.initialize();
    }

    @BeforeTest
    public void setDriver() {
        this.driver = Boolean.parseBoolean(ConfigUtil.get(Constants.SELENIUM_GRID_ENABLED))
                ? getRemoteDriver() : getLocalDriver();
        driver.manage().window().maximize();
    }

    public WebDriver getLocalDriver() {
        return new ChromeDriver();
    }

    public WebDriver getRemoteDriver() {
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(ConfigUtil.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = ConfigUtil.get(Constants.SELENIUM_GRID_URL_FORMAT);
        String hubHost = ConfigUtil.get(Constants.SELENIUM_GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("Grid URL: {}", url);
        try {
            return new RemoteWebDriver(URI.create(url).toURL(), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

}
