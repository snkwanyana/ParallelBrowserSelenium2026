package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ContactUsPage {

    WebDriver driver;

    By contactusMenu_xpath = By.xpath("//button[@class='nav-item ']/span[contains(text(),'Contact Us')]");
    By contactMethods_xpath = By.tagName("h3");

    public ContactUsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void clickContactUsMenu(){
        driver.findElement(contactusMenu_xpath).click();
    }
    public void getContactMethodList(){
        ArrayList<String> contacts = new ArrayList<>();
        driver.navigate().refresh();
        List<WebElement> contactList = driver.findElements(contactMethods_xpath);
        for (WebElement contact: contactList){
            contacts.add(contact.getText());
        }

        System.out.println(contacts);
    }
}
