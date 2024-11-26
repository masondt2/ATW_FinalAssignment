package pages;

import core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class EditProductPage extends BasePage {
    Actions actions = new Actions(getDriver());
    @FindBy(xpath = "//label[normalize-space()='Product Type *']")
    private WebElement productType;

    @FindBy(xpath = "//label[normalize-space()='Product Name *']")
    private WebElement productName;

    @FindBy(xpath = "//label[normalize-space()='Product Code *']")
    private WebElement productCode;

    @FindBy(xpath = "//label[normalize-space()='Slug *']")
    private WebElement slug;

    @FindBy(xpath = "//label[normalize-space()='Barcode Symbology *']")
    private WebElement barcodeSymbology;

    @FindBy(id = "select2-chosen-5")
    private WebElement productTypeDropdown;

    @FindBy(xpath = "(//span[@id='select2-chosen-6'])[1]")
    private WebElement barcodeBox;

    @FindBy(xpath = "(//input[@id='s2id_autogen6_search'])[1]")
    private WebElement barcodeInput;

    @FindBy(xpath = "//div[@id='s2id_tax_method']")
    private WebElement taxMethod;

    @FindBy(name = "edit_product")
    private WebElement editButton;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement alertMessage;

    public EditProductPage(WebDriver d) {
        super(d);
    }

    public boolean IsEditProductDisplayProperty() throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(productType));
        Thread.sleep(1000);
        return productType.isDisplayed() && productName.isDisplayed() && productCode.isDisplayed() && barcodeSymbology.isDisplayed() && slug.isDisplayed();
    }

    public void editProductDetails(String barcode, String taxMethodName, String checkMessage) throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(productTypeDropdown));

//        //edit product type
//        productTypeDropdown.click();
//        WebElement type = getDriver().findElement(By.xpath("//div[@class='select2-result-label' and text()='"+productType+"']"));
//        type.click();
//        Thread.sleep(1000);

        //edit barcode
        barcodeBox.click();
        barcodeInput.sendKeys(barcode);
        actions.sendKeys(Keys.ENTER).perform();
        Thread.sleep(2000);

        //Scroll to tax Method
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", taxMethod);
        Thread.sleep(2000);

        //edit Tax method
        taxMethod.click();
        WebElement taxChoose = getDriver().findElement(By.xpath("//div[@class='select2-result-label' and text()='" + taxMethodName + "']"));
        taxChoose.click();
        Thread.sleep(3000);

        js.executeScript("arguments[0].scrollIntoView(true);", editButton);
        Thread.sleep(3000);
        editButton.click();

        //Verify alert message
        getWebDriverWait().until(ExpectedConditions.visibilityOf(alertMessage));
        Assert.assertTrue(alertMessage.getText().contains(checkMessage));
        System.out.println(alertMessage.getText());
    }

}
