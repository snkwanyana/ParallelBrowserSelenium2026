package testRunners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.Base;
import utils.BrowserFactory;

public class TestRunner extends Base {

    @Test
    public void loginTest() throws InterruptedException {
        homePage.verifyHomePageIsLoaded();
        homePage.clickLoginButton();
        loginPage.enterUsername("nkwanyana@gmail.com");
        loginPage.enterPassword("#12345678");
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
