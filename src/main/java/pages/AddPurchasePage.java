package pages;

import core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddPurchasePage extends BasePage {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    Actions action = new Actions(getDriver());
    @FindBy(xpath = "//h2[normalize-space()='Add Purchase']")
    private WebElement addPurchaseTitle;

    @FindBy(xpath = "//span[@id='select2-chosen-8']")
    private WebElement supplierBox;

    @FindBy(xpath = "//input[@id='s2id_autogen8_search']")
    private WebElement supplierSearchInput;

    @FindBy(xpath = "//input[@id='add_item']")
    private WebElement addProductInput;

    @FindBy(xpath = "//div[@class='from-group']/input")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement alertMessage;

    public AddPurchasePage(WebDriver d) {
        super(d);
    }

    public void addPurchase(String supplierName, String productName) throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(addPurchaseTitle));
        js.executeScript("arguments[0].scrollIntoView(true);", addPurchaseTitle);
        Thread.sleep(2000);

        supplierBox.click();
        Thread.sleep(1000);
        supplierSearchInput.sendKeys(supplierName);
        Thread.sleep(1000);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);

        addProductInput.sendKeys(productName);
        Thread.sleep(1000);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
    }

    public boolean VerifyAddPurchaseSuccess(String messageAddSuccess) {
        return alertMessage.getText().contains(messageAddSuccess);
    }
}
