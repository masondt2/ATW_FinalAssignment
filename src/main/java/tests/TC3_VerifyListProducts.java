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

public class TC3_VerifyListProducts extends BaseTest {
    @Test(dataProvider = "data")
    public void TestTC3_VerifyListProducts(String url, String username, String password, String lst) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        loginPage.login(username, password);

        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.navigateToListProduct();

        ListProductPage listProductPage = new ListProductPage(getDriver());
        List<String> checkLst = new ArrayList<>(Arrays.asList(lst.split(",")));
        Assert.assertTrue(listProductPage.IsDisplayedTableTitleColumProperty(checkLst));
    }

    @DataProvider(name = "data")
    public Object[][] getTestData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC3_VerifyListProducts", 1, 4);
    }

}
