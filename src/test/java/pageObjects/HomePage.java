package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    By mainLoginButton_xpath = By.xpath("//div[@class='nav-user-section']");

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void verifyHomePageIsLoaded(){
        driver.findElement(mainLoginButton_xpath).isDisplayed();
    }

    public void clickLoginButton(){
        driver.findElement(mainLoginButton_xpath).click();
    }
}
