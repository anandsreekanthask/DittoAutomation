package stepdef;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import managers.DriverManager;
import utils.ConfigReader;

/**
 * Class is responsible for handling the initial setup and final tear down for the execution
 */
public class Hooks {

private final ConfigReader configReader;

    public Hooks(ConfigReader configReader){
        this.configReader = configReader;
    }

    /**
     * Responsible for setting up the browser
     */
    @Before
    public void setUp(){
        String browser = configReader.getBrowser("browser");
        System.out.println("Execution started with "+browser+" browser");
        DriverManager.initializeDriver(browser);
    }

    /**
     * Responsible for terminating browser session
     */
    @After
    public void tearDown(){
        System.out.println("Tear down initiated");
        DriverManager.quitDriver();
    }

}
