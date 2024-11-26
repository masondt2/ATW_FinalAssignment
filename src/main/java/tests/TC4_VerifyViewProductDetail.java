package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.ListProductPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TC4_VerifyViewProductDetail extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC4_VerifyViewProductDetail(String url, String username, String password, String searchProductName, String lst) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateToListProduct();

        Thread.sleep(2000);
        ListProductPage listProductPage = new ListProductPage(getDriver());

        Thread.sleep(2000);
        listProductPage.navigateToProductDetailPage(searchProductName);

        List<String> lstDetailProductVerify = new ArrayList<>(Arrays.asList(lst.split(",")));
        Assert.assertTrue(listProductPage.IsDetailProductDisplayProperty(lstDetailProductVerify));

    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC4_VerifyViewProductDetail", 1, 5);
    }
}
