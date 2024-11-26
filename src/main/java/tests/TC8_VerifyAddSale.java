package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddSalePage;
import pages.DashboardPage;
import pages.LoginPage;

public class TC8_VerifyAddSale extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC8_VerifyAddSale(String url, String username, String password, String customerName, String productName, String salestatus, String paymentstatus, String addSaleMessageVerify) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateToSalesPage();

        AddSalePage addSalePage = new AddSalePage(getDriver());
        addSalePage.addSale(customerName, productName, salestatus, paymentstatus);

        Assert.assertTrue(addSalePage.IsAddSaleSuccess(addSaleMessageVerify));
        System.out.println(addSaleMessageVerify);
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC8_VerifyAddSale", 1, 8);
    }
}
