package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeScreenshots {

    // Directory for screenshots under the project: Reports/Screenshots
    // Using File.separator makes this path platform-independent (Windows vs Unix)
    private static final String screenshotDir = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Screenshots";

    // throws IOException so callers can handle file system failures (directory creation, copy errors)
    public static String takesSnapShot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationDirectory = new File(screenshotDir);
        // Create the screenshots directory if it does not exist. mkdirs() returns false on failure.
        if (!destinationDirectory.exists() && !destinationDirectory.mkdirs()) {
            // Fail fast with a clear message so the caller knows why the screenshot couldn't be written
            throw new IOException("Unable to create screenshot directory: " + destinationDirectory.getAbsolutePath());
        }

        // Save the screenshot with .png extension because Selenium's output is PNG by default
        File destination = new File(destinationDirectory, screenshotName + ".png");

        // Copy the temporary screenshot file produced by WebDriver to the destination path
        FileUtils.copyFile(src, destination);

        // Return the absolute file path for callers that may want to attach or log it
        return destination.getAbsolutePath();
    }
}
