package utils;

import org.openqa.selenium.WebDriver;
import pageObjects.ContactUsPage;
import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class Base {

    WebDriver driver = BrowserFactory.getDriver("chrome", "https://ndosisimplifiedautomation.vercel.app/");
    public HomePage homePage = new HomePage(driver);
    public LoginPage loginPage = new LoginPage(driver);
    public DashboardPage dashboardPage = new DashboardPage(driver);
    public ContactUsPage contactUsPage = new ContactUsPage(driver);

}
