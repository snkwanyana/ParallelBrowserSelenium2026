package testRunners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testData.ReadFromDatabase;
import utils.Base;
import utils.BrowserFactory;

public class TestRunner extends Base {

    @BeforeClass
    public void setupData(){
        ReadFromDatabase.dbConnection();
    }

    @Test
    public void loginTest() throws InterruptedException {
        homePage.verifyHomePageIsLoaded();
        homePage.clickLoginButton();
        loginPage.enterUsername(ReadFromDatabase.getUsername);
        loginPage.enterPassword(ReadFromDatabase.getPassword);
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
