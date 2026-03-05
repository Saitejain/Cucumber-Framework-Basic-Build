package appHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.qa.factory.DriverFactory;
import com.utils.ConfigReader;
import com.utils.ExtentReportManager;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser(Scenario scenario) {
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(prop);
        ExtentReportManager.createScenarioTest(scenario.getName());
        ExtentReportManager.getScenarioTest().log(Status.INFO, "Browser launched: " + prop.getProperty("browser", "chrome"));
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", screenshotName);
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            ExtentReportManager.getScenarioTest().addScreenCaptureFromBase64String(base64Screenshot, screenshotName);
            ExtentReportManager.getScenarioTest().log(Status.FAIL, scenario.getName() + " failed");
        } else {
            ExtentReportManager.getScenarioTest().log(Status.PASS, scenario.getName() + " passed");
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void flushReport() {
        ExtentReportManager.flushReport();
    }
}
