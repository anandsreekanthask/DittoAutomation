package ui.pages;

import managers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LandingPage {

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

    public LandingPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(xpath = "//*[contains(text(),'Optima Secure')]")
    private WebElement OptimaSecure;

    @FindBy(xpath = "//*[contains(text(),'Optima Super Secure')]")
    private WebElement OptimaSuperSecure;

    @FindBy()
    private List<WebElement> healthPlans;


    public void clickOptimaSecure() {
        wait.until(ExpectedConditions.elementToBeClickable(OptimaSecure));
        OptimaSecure.click();
    }

    public void clickOptimaSuperSecure() {
        wait.until(ExpectedConditions.elementToBeClickable(OptimaSuperSecure));
        OptimaSuperSecure.click();
    }

    public void identifyPlan(String plan) {
        switch (plan) {
            case "Optima Secure":
                clickOptimaSecure();
                break;
            case "Optima Super Secure":
                clickOptimaSuperSecure();
                break;
            default:
                throw new RuntimeException("Plan not available");
        }
    }


}
