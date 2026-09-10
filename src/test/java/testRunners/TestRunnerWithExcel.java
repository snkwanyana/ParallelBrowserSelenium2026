package testRunners;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.ExcelReader;
import testData.ReadFromDatabase;
import utils.Base;

public class TestRunnerWithExcel extends Base {


    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return ExcelReader.getLoginDataFromExcel(System.getProperty("user.dir") + "/src/test/java/testData/data.xlsx", "LoginDetails");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        homePage.verifyHomePageIsLoaded();
        homePage.clickLoginButton();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        dashboardPage.validateLoginScreen();
    }
}
