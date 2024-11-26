package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PrintBarcodeLabelPage extends BasePage {
    Actions action = new Actions(getDriver());
    @FindBy(xpath = "//label[normalize-space()='Add Product']")
    private WebElement addProduct;

    @FindBy(xpath = "//label[normalize-space()='Style *']")
    private WebElement style;

    @FindBy(xpath = "//span[normalize-space()='Print:']")
    private WebElement print;

    @FindBy(xpath = "(//div[@class='form-group'])[6]/div/input")
    private List<WebElement> lstPrint;

    @FindBy(xpath = "//input[@id='add_item']")
    private WebElement inputAddItem;

    @FindBy(xpath = "(//div[@id='s2id_style'])[1]")
    private WebElement styleBox;

    @FindBy(xpath = "(//input[@id='s2id_autogen1_search'])[1]")
    private WebElement styleBoxInput;

    @FindBy(xpath = "//div[@class='icheckbox_square-blue checked']/input")
    private List<WebElement> lstPrintChecked;

    @FindBy(xpath = "//div[@class='icheckbox_square-blue']/input")
    private List<WebElement> lstPrintUnChecked;

    @FindBy(xpath = "//div[@id='barcode-con']//button[1]")
    private WebElement printButton;

    @FindBy(xpath = "//input[@name='print']")
    private WebElement updateButton;

    //barcode created
    @FindBy(xpath = "//span[@class='product_image']//img")
    private WebElement imageProduct;

    @FindBy(xpath = "//img[@alt='FFR02']")
    private WebElement imageBarcode;

    @FindBy(xpath = "//div[@class='item style18']/span")
    private List<WebElement> lstBarcodeItems;

    public PrintBarcodeLabelPage(WebDriver d) {
        super(d);
    }

    public boolean IsPrintBarcodeLabelDisplayProperty(List<String> lstPrintCheck) {
        boolean flag = true;
        for (int i = 0, j = 0; i < lstPrint.size(); i++, j++) {
            WebElement texti = getDriver().findElement(By.xpath("//label[@for='" + lstPrint.get(i).getAttribute("name") + "']"));
            if (!texti.getText().toUpperCase().equals(lstPrintCheck.get(j).toUpperCase())) {
                flag = false;
            }
            System.out.println(texti.getText() + " is verified!");
        }
        return flag && addProduct.isDisplayed() && style.isDisplayed() && print.isDisplayed();
    }

    public void CreatePrintBarcodeLabel(String name, String style, List<String> printlstName) throws InterruptedException {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(inputAddItem));
        inputAddItem.sendKeys(name);
        Thread.sleep(1000);
        styleBox.click();
        Thread.sleep(1000);
        styleBoxInput.sendKeys(style);
        action.sendKeys(Keys.ENTER).perform();

        //Check with all checkedPrint is presented on Exel data list
        for (int i = 0; i < lstPrintChecked.size(); i++) {
            WebElement texti = getDriver().findElement(By.xpath("//label[@for='" + lstPrintChecked.get(i).getAttribute("name") + "']"));
            boolean checki = false;
            for (int j = 0; j < printlstName.size(); j++) {
                if ((printlstName.get(i).toUpperCase().equals(texti.getText().toUpperCase()))) {
                    break;
                } else checki = true;
            }
            if (checki) {
                texti.click();
            }
        }

        //Check with all UncheckedPrint is presented on Exel data list
        for (int i = 0; i < printlstName.size(); i++) {
            for (int j = 0; j < lstPrintUnChecked.size(); j++) {
                WebElement textj = getDriver().findElement(By.xpath("//label[@for='" + lstPrintUnChecked.get(j).getAttribute("name") + "']"));
                if (printlstName.get(i).toUpperCase().equals(textj.getText().toUpperCase())) {
                    textj.click();
                    ;
                }
            }
        }

        updateButton.click();
    }

    public boolean IsPrintBarcodeCreateSuccessful(List<String> lstBarcodeInforCheck) {
        boolean flag = true;
        for (int i = 1, j = 0; i < lstBarcodeItems.size() - 1; i++, j++) {
            if (!(lstBarcodeItems.get(i).getText().toUpperCase().equals(lstBarcodeInforCheck.get(j).toUpperCase()))) {
                flag = false;
            }
            System.out.println(lstBarcodeItems.get(i).getText() + " is verified!");
        }
        return flag && lstBarcodeItems.get(0).isDisplayed() && lstBarcodeItems.get(lstBarcodeItems.size() - 1).isDisplayed();
    }

}
