package pages;

import core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddSalePage extends BasePage {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    Actions action = new Actions(getDriver());
    @FindBy(xpath = "//div[@class='box-header']")
    private WebElement addSaleHeader;
    @FindBy(xpath = "(//a[@class='select2-choice select2-default'])[1]")
    private WebElement customerSelect;
    @FindBy(xpath = "(//input[@id='s2id_autogen12_search'])[1]")
    private WebElement customerSelectInput;
    @FindBy(xpath = "//input[@id='add_item']")
    private WebElement addProductInput;
    @FindBy(xpath = "//div[@id='s2id_slpayment_status']")
    private WebElement paymentStatusBox;
    @FindBy(xpath = "//span[@id='select2-chosen-5']")
    private WebElement saleStatusSelect;
    @FindBy(xpath = "//span[@id='select2-chosen-6']")
    private WebElement paymentStatusSelect;
    @FindBy(id = "add_sale")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement addSaleMessageElement;

    public AddSalePage(WebDriver d) {
        super(d);
    }

    public void addSale(String customer, String addProductName, String saleStatus, String paymentStatus) throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(addSaleHeader));
        Thread.sleep(1000);
        customerSelect.click();
        Thread.sleep(2000);
        customerSelectInput.sendKeys(customer);
        Thread.sleep(2000);
        action.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);
        addProductInput.sendKeys(addProductName);
        Thread.sleep(2000);
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        saleStatusSelect.click();
        Thread.sleep(1000);
        int indexsaleStatus = 0;
        switch (saleStatus) {
            case "Completed":
                indexsaleStatus = 1;
                break;
            case "Pending":
                indexsaleStatus = 2;
                break;
        }

        int indexPaymentStatus = 0;
        switch (paymentStatus) {
            case "Pending":
                indexPaymentStatus = 1;
                break;
            case "Due":
                indexPaymentStatus = 2;
                break;
            case "Partial":
                indexPaymentStatus = 3;
                break;
            case "Paid":
                indexPaymentStatus = 4;
                break;
        }
        ////ul[@id='select2-results-5']/li
        WebElement saleStatusResult = getDriver().findElement(By.xpath("(//ul[@id='select2-results-5']/li)[" + indexsaleStatus + "]"));
        saleStatusResult.click();

        ////ul[@id='select2-results-6']/li
        paymentStatusBox.click();
        WebElement paymentStatusResult = getDriver().findElement(By.xpath("(//ul[@id='select2-results-6']/li/div)[" + indexPaymentStatus + "]"));
        paymentStatusResult.click();

        submitButton.click();
    }

    public boolean IsAddSaleSuccess(String addSaleMessageVerify) {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(addSaleMessageElement));
        System.out.println(addSaleMessageElement.getText());
        return addSaleMessageElement.getText().contains(addSaleMessageVerify);
    }
}
