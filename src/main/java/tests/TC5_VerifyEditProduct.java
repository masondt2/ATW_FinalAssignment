package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EditProductPage;
import pages.ListProductPage;
import pages.LoginPage;

public class TC5_VerifyEditProduct extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC5_VerifyEditProduct(String url, String username, String password, String searchName, String barcode, String taxMethodName, String checkMessage) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateToListProduct();

        ListProductPage listProductPage = new ListProductPage(getDriver());
        listProductPage.navigatetoEditPage(searchName);

        EditProductPage editProductPage = new EditProductPage(getDriver());
        Assert.assertTrue(editProductPage.IsEditProductDisplayProperty());
        System.out.println("Edit Product Display is verified!");

        editProductPage.editProductDetails(barcode, taxMethodName, checkMessage);
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC5_VerifyEditProduct", 1, 7);
    }
}
