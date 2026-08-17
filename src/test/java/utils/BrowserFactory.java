package utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


public class BrowserFactory {

    public static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser, String url){
        if (browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
            options.addArguments("--incognito");
            driver.set(new ChromeDriver(options));

        }else if (browser.equalsIgnoreCase("firefox")){
            driver.set(new FirefoxDriver());

        }else if (browser.equalsIgnoreCase("Safari")){
            driver.set(new SafariDriver());

        }else {
            driver.set(new EdgeDriver());
            
        }

        driver.get().get(url);
//        driver.get().manage().window().setSize(new Dimension(1920,1080));
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static void quitDriver(){
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }
}
