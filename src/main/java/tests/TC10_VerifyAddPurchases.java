package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddPurchasePage;
import pages.DashboardPage;
import pages.LoginPage;

public class TC10_VerifyAddPurchases extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC10_VerifyAddPurchases(String url, String username, String password, String supplierName, String productName, String messageAddSuccess) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigatetoAddPurchasePage();
        Thread.sleep(2000);

        AddPurchasePage addPurchasePage = new AddPurchasePage(getDriver());
        addPurchasePage.addPurchase(supplierName, productName);
        Assert.assertTrue(addPurchasePage.VerifyAddPurchaseSuccess(messageAddSuccess));
        System.out.println(messageAddSuccess);
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC10_VerifyAddPurchases", 1, 6);
    }
}
