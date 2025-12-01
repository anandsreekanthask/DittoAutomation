package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The ConfigReader class is responsible for reading the data from config.properties file
 * It supports environment-specific values based on the Maven -Denv parameter.
 */

public class ConfigReader {

    private static Properties properties;
    private final String ENV;

    public ConfigReader(){

        //Defaulting the environment URL to QA if not explicitly provided
        ENV = System.getProperty("env","QA").toLowerCase();
        properties = new Properties();

        try{

            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to read contents of config.properties file");
        }

    }

    /**
     * Method to capture the url from properties file
     * @return the url/endpoint for script execution
     */
    public String getBaseURL(){
        return properties.getProperty("url."+ENV);
    }

    /**
     * Method to capture particular property from configuration file
     * @param key used as the identifier for fetching configuration value
     * @return configuration value associated with property
     */
    public String getProperty(String key){
        return properties.getProperty(key);
    }

    /**
     * ethod to capture desired browser from configuration file
     * @param browser to be used for script execution
     * @return the browser name
     */
    public String getBrowser(String browser){
        return properties.getProperty(browser);
    }

}
