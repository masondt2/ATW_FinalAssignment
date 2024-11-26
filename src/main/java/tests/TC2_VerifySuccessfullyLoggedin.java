package tests;

import core.BaseTest;
import core.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class TC2_VerifySuccessfullyLoggedin extends BaseTest {
    @Test(dataProvider = "data")
    public void Test_TC2_VerifySuccessfullyLoggedin(String url, String username, String password, String messageVerify) throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.navigateToLoginPage(url);
        Assert.assertTrue(loginPage.IsLoginPageDisplay());
        loginPage.login(username, password);
        Thread.sleep(2000);
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        Assert.assertTrue(dashboardPage.IsLoginSuccessful(messageVerify));
    }

    @DataProvider(name = "data")
    public Object[][] getData() {
        return ExcelUtils.getTableArray("src/main/resources/FinalAssignmentData.xlsx", "TC_2Verifysuccessfullyloggedin", 1, 4);
    }
}
