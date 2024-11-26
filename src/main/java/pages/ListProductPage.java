package pages;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

public class ListProductPage extends BasePage {

    Actions action = new Actions(getDriver());

    @FindBy(xpath = "(//table[@id='PRData']/thead/tr/th)")
    private List<WebElement> tableTitleColum;

    @FindBy(xpath = "//input[@class='input-xs']")
    private WebElement searchBox;

    @FindBy(xpath = "((//table[@id='PRData']/tbody)/tr[1]/td)[4]")
    private WebElement firstNameProductFound;

    @FindBy(xpath = "//img[@id='pr-image']")
    private WebElement imageFound;

    @FindBy(xpath = "//img[@alt='FFR03']")
    private WebElement barcode;

    @FindBy(xpath = "//img[@alt='https://sma.tec.sh/admin/products/view/3']")
    private WebElement qrcode;

    @FindBy(xpath = "//div[@class='panel-heading']")
    private WebElement productDetails;

    @FindBy(xpath = "(//table[@class='table table-borderless table-striped dfTable table-right-left']/tbody/tr)")
    private List<WebElement> tableLstDetailProduct;

    @FindBy(xpath = "//a[@class='tip btn btn-warning tip']")
    private WebElement editButton;

    public ListProductPage(WebDriver d) {
        super(d);
    }

    public boolean IsDisplayedTableTitleColumProperty(List<String> lstVerfy) {
        boolean flag = true;
        for (int i = 1, j = 0; i < tableTitleColum.size(); i++, j++) {
            if (!tableTitleColum.get(i).getText().equals(lstVerfy.get(j))) {
                flag = false;
            }
            System.out.println(tableTitleColum.get(i).getText() + " is displayed");
        }
        return flag;
    }

    public void searchProduct(String productName) {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.sendKeys(productName);
        action.sendKeys(Keys.ENTER).perform();

    }

    public void navigateToProductDetailPage(String productName) throws InterruptedException {
        searchProduct(productName);
        Thread.sleep(3000);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(firstNameProductFound));
        firstNameProductFound.click();
        Thread.sleep(2000);

        //move to window:
        Set<String> lsWindow = getDriver().getWindowHandles();
        for (String ls : lsWindow) {
            getDriver().switchTo().window(ls);
            if (getDriver().getPageSource().contains("Print")) {
                break;
            }
        }
        System.out.println("moved to Product detail page");
        Thread.sleep(2000);
    }

    public boolean IsDetailProductDisplayProperty(List<String> lstInfor) {
        boolean flag = true;
        for (int i = 2, j = 0; i < tableLstDetailProduct.size(); i++, j++) {
            if (!tableLstDetailProduct.get(i).getText().contains(lstInfor.get(j))) {
                flag = false;
            }
            System.out.println(tableLstDetailProduct.get(i).getText() + " is verified!");
        }
        return flag && imageFound.isDisplayed() && barcode.isDisplayed() && qrcode.isDisplayed() && productDetails.isDisplayed();
    }

    public void navigatetoEditPage(String productName) throws InterruptedException {
        searchProduct(productName);
        Thread.sleep(3000);

        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(firstNameProductFound));
        firstNameProductFound.click();
        Thread.sleep(2000);

        getWebDriverWait().until(ExpectedConditions.visibilityOf(editButton));
        editButton.click();
        Thread.sleep(2000);
    }
}
