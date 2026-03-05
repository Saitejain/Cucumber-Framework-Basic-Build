package appHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.utils.ConfigReader;

import io.cucumber.java.After;
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
    public void launchBrowser() {
        driverFactory = new DriverFactory();
        driver = driverFactory.initDriver(prop);
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && driver != null) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", screenshotName);
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
