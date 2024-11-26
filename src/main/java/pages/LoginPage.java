package pages;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement username;
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement password;
    @FindBy(xpath = "//button[@class='btn btn-success pull-right']")
    private WebElement loginButton;
    @FindBy(xpath = "//span[@class='checkbox-text pull-left']")
    private WebElement rememberme;

    public LoginPage(WebDriver d) {
        super(d);
    }

    public void navigateToLoginPage(String url) {
        getDriver().get(url);
    }

    public boolean IsLoginPageDisplay() {
        return username.isDisplayed() && password.isDisplayed() && loginButton.isDisplayed() && rememberme.isDisplayed();
    }

    public void login(String u, String p) {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(username));
        username.clear();
        username.sendKeys(u);
        password.clear();
        password.sendKeys(p);
        loginButton.click();
    }
}
