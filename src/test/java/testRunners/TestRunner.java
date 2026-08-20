package testRunners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.ReadFromDatabase;
import utils.Base;
import utils.BrowserFactory;

public class TestRunner extends Base {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return ReadFromDatabase.getLoginData();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) throws InterruptedException {
        homePage.verifyHomePageIsLoaded();
        homePage.clickLoginButton();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        dashboardPage.validateLoginScreen();
    }


    @Test
    public void contactListNameTest() {
        contactUsPage.clickContactUsMenu();
        contactUsPage.getContactMethodList();

    }

    @AfterMethod
    public void tearDown(){
        BrowserFactory.quitDriver();
    }
}
