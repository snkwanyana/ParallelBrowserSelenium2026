package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class LoginPage {

    WebDriver driver;

    By username_id = By.id("login-email");
    WebElement passwordField;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    public void enterUsername(String username){
        driver.findElement(username_id).sendKeys(username);
    }

    public void enterPassword(String password){
        passwordField = driver.findElement(with(By.tagName("input")).below(username_id));
        passwordField.sendKeys(password);
    }

    public void clickLoginButton(){
        WebElement loginBtn = driver.findElement(with(By.tagName("button")).below(passwordField));
        loginBtn.click();
    }
}
