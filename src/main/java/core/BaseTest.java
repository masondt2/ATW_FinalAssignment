package core;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeTest
    public void BeforeTest(String browser) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        desiredCapabilities.setBrowserName(browser);
        driver = new RemoteWebDriver(desiredCapabilities);
    }

    @AfterTest
    public void AfterTest() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
