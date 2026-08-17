package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.TakeScreenshots;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static utils.BrowserFactory.driver;

public class ExtendReportManager implements ITestListener {

    private static ExtentSparkReporter sparkReporter; //UI of the report
    private static ExtentReports extent; //Populates common information in the report
    private static ExtentTest test; //Creates test cases in the report

    @Override
    public void onStart(ITestContext context) {
        // Build the intended report file path under the project's Reports directory
        Path reportPath = Paths.get(System.getProperty("user.dir"), "Reports", "NdosiAutomationReport.html");
        try {
            // Ensure the Reports directory exists so the HTML report and relative screenshot links can be written
            Files.createDirectories(reportPath.getParent());
        } catch (IOException e) {
            // If directory creation fails, throw a clear runtime exception so test run fails fast with useful diagnostics
            throw new IllegalStateException("Unable to create report directory: " + reportPath.getParent(), e);
        }

        // Create an ExtentSparkReporter pointing at the report file path
        sparkReporter = new ExtentSparkReporter(reportPath.toString());
        sparkReporter.config().setDocumentTitle("Ndosi automation");
        sparkReporter.config().setReportName("Functional Test");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Execution machine", System.getProperty("user.name"));
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Test Environment", "QA");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName()); //Create a new entry in the report
        test.log(Status.PASS, "Test case "+result.getMethod().getMethodName()+" has passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test case "+result.getMethod().getMethodName()+" has failed");
        test.log(Status.FAIL, result.getThrowable());
        try {
            // Build the screenshot filename expected under Reports/Screenshots
            String screenshotName = result.getMethod().getMethodName() + ".png";
            // Capture and save the screenshot file to Reports/Screenshots via the utility
            TakeScreenshots.takesSnapShot(driver.get(), result.getMethod().getMethodName());
            // Use a relative path (Screenshots/...) in the report so the browser can resolve it when opening the HTML
            test.addScreenCaptureFromPath(
                    Paths.get("Screenshots", screenshotName).toString().replace("\\", "/"),
                    result.getMethod().getMethodName()
            );
        } catch (IOException e) {
            throw new IllegalStateException("Unable to attach success screenshot for " + result.getMethod().getMethodName(), e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test case "+result.getMethod().getMethodName()+" has skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
