package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PrintBarcodeLabelPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TC6_VerifyPrintBarcodeLabel extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC5_VerifyEditProduct(String url, String username, String password, String lst) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigatetoPrintBarcode();

        PrintBarcodeLabelPage printBarcodeLabelPage = new PrintBarcodeLabelPage(getDriver());
        List<String> lstPrintCheck = new ArrayList<>(Arrays.asList(lst.split(",")));
        Assert.assertTrue(printBarcodeLabelPage.IsPrintBarcodeLabelDisplayProperty(lstPrintCheck));
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC6_VerifyPrintBarcodeLabel", 1, 4);
    }
}
