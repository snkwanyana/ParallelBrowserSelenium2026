package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import pageObjects.ContactUsPage;
import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class Base {

    public WebDriver driver;
    public HomePage homePage;
    public LoginPage loginPage;
    public DashboardPage dashboardPage;
    public ContactUsPage contactUsPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser){
        this.driver = BrowserFactory.getDriver(browser, ReadFromProperty.getRequiredProperty("baseUrl"));
        this.homePage = new HomePage(driver);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.contactUsPage = new ContactUsPage(driver);
    }
}
