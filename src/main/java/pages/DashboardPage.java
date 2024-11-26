package pages;

import core.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {
    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement messageDashboard;
    @FindBy(xpath = "//h2[normalize-space()='Overview Chart']")
    private WebElement overviewChart;
    @FindBy(xpath = "//h2[normalize-space()='Quick Links']")
    private WebElement quickLink;
    @FindBy(xpath = "//h2[normalize-space()='Latest Five']")
    private WebElement latestFive;
    @FindBy(xpath = "(//div[@class='col-sm-6'])[1]")
    private WebElement bestSeller1;
    @FindBy(xpath = "(//div[@class='col-sm-6'])[2]")
    private WebElement bestSeller2;
    @FindBy(xpath = "//span[normalize-space()='Products']")
    private WebElement productMenu;
    @FindBy(xpath = "//span[normalize-space()='List Products']")
    private WebElement listProcduct;
    @FindBy(xpath = "//span[normalize-space()='Print Barcode/Label']")
    private WebElement printBarcodeLabelMenu;

    @FindBy(xpath = "//span[normalize-space()='Sales']")
    private WebElement salesMenu;

    @FindBy(xpath = "//span[normalize-space()='Add Sale']")
    private WebElement addSaleSubMenu;

    @FindBy(xpath = "//span[normalize-space()='Purchases']")
    private WebElement purchaseMenu;

    @FindBy(xpath = "//span[normalize-space()='List Purchases']")
    private WebElement listpurchaseSubmenu;

    @FindBy(xpath = "//li[@id='purchases_add']")
    private WebElement addPurchaseSubmenu;

    public DashboardPage(WebDriver d) {
        super(d);
    }

    public boolean IsLoginSuccessful(String messageVerify) throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(messageDashboard));
        //JavascriptExecutor js = (JavascriptExecutor) getDriver();
        //js.executeScript("arguments[0].scrollIntoView(true);", quickLink);
        System.out.println(messageDashboard.getText());
        return messageDashboard.isDisplayed() && messageDashboard.getText().contains(messageVerify)
                && overviewChart.isDisplayed() && quickLink.isDisplayed() && latestFive.isDisplayed()
                && bestSeller1.isDisplayed() && bestSeller2.isDisplayed();
    }

    public void navigateToListProduct() throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(productMenu));
        Thread.sleep(2000);
        productMenu.click();
        Thread.sleep(2000);
        listProcduct.click();
    }

    public void navigatetoPrintBarcode() throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(productMenu));
        Thread.sleep(2000);
        productMenu.click();
        Thread.sleep(2000);
        printBarcodeLabelMenu.click();
    }

    public void navigateToSalesPage() throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(salesMenu));
        Thread.sleep(1000);
        salesMenu.click();
        Thread.sleep(2000);
        getWebDriverWait().until(ExpectedConditions.visibilityOf(addSaleSubMenu));
        addSaleSubMenu.click();
    }

    public void navigatetoListPurchasePage() throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(purchaseMenu));
        Thread.sleep(1000);
        purchaseMenu.click();
        Thread.sleep(1000);
        listpurchaseSubmenu.click();
    }

    public void navigatetoAddPurchasePage() throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(purchaseMenu));
        Thread.sleep(2000);
        purchaseMenu.click();
        Thread.sleep(2000);
        addPurchaseSubmenu.click();
    }
}
