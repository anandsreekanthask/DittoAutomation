package managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Manages the WebDriver instance using the Singleton pattern with ThreadLocal
 * This ensures thread safety, allowing parallel execution of scenarios.
 */

public class DriverManager {

    private final static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    //Declaring constructor as private to enforce Singleton pattern
    private DriverManager() {

    }

    /**
     * Initializes the WebDriver and sets it for the current thread using ThreadLocal.
     */
    public static void initializeDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "chrome":
                driver = new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        tlDriver.set(driver);

    }

    /**
     * Method to get WebDriver instance for current thread
     * @return Webdriver instance
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Quits the WebDriver and removes the instance from ThreadLocal.
     */
    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }

}
