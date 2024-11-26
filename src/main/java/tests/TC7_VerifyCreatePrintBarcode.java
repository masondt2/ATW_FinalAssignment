package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PrintBarcodeLabelPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TC7_VerifyCreatePrintBarcode extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC7_VerifyCreatePrintBarcode(String url, String username, String password, String itemName, String styleName, String lstPrint, String BarcodeVerify) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigatetoPrintBarcode();

        PrintBarcodeLabelPage printBarcodeLabelPage = new PrintBarcodeLabelPage(getDriver());
        List<String> lstPrintCheck = new ArrayList<>(Arrays.asList(lstPrint.split(",")));
        printBarcodeLabelPage.CreatePrintBarcodeLabel(itemName, styleName, lstPrintCheck);

        List<String> lstBarcodeVerify = new ArrayList<>(Arrays.asList(BarcodeVerify.split(";")));
        printBarcodeLabelPage.IsPrintBarcodeCreateSuccessful(lstBarcodeVerify);
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC7_VerifyCreatePrintBarcode", 1, 7);
    }
}
