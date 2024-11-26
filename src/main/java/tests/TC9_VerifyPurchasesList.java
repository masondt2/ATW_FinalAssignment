package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ListPurchasePage;
import pages.LoginPage;

public class TC9_VerifyPurchasesList extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC9_VerifyPurchasesList(String url, String username, String password, String dateTimeFormatVerify, String orderType, String numOfDisplay) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigatetoListPurchasePage();

        ListPurchasePage listPurchasePage = new ListPurchasePage(getDriver());
        Thread.sleep(2000);
        Assert.assertTrue(listPurchasePage.verifyTypeOfDatePurchases(dateTimeFormatVerify), "Format Date is Wrong");
        System.out.println("Format datetime is verified!");
        Assert.assertTrue(listPurchasePage.VerifyTypeOfSortReferencePurchases(orderType), "Order Type is Wrong");
        System.out.println("Reference No order by " + orderType + " is verified!");
        Assert.assertTrue(listPurchasePage.verifyListPurchasesDisplay(numOfDisplay));
        System.out.println("========================================================");
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC9_VerifyPurchasesList", 1, 6);
    }
}
