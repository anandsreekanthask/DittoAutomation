package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.ConfigReader;

/**
 * Class is responsible for handling the initial setup and final tear down for the execution
 */
public class Hooks {

    private final ConfigReader configReader;

    public Hooks(ConfigReader configReader) {
        this.configReader = configReader;
    }

    /**
     * Responsible for setting up the browser
     */
    @Before
    public void setUp() {
        String browser = configReader.getBrowser("browser");
        System.out.println("Execution started with " + browser + " browser");
        DriverManager.initializeDriver(browser);
    }

    /**
     * Responsible for taking screenshot after evey feature step
     *
     * @param scenario for the extent report to get context of scenario to capture screenshot
     */
    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        try {
            Thread.sleep(1500);
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    /**
     * Responsible for terminating browser session
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed())
            takeScreenshotAfterStep(scenario);
        System.out.println("Tear down initiated");
        DriverManager.quitDriver();
    }

}
