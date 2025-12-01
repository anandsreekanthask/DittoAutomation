package ui.pages;

import managers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PolicyPage {

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

    public PolicyPage(){
        PageFactory.initElements(DriverManager.getDriver(),this);
    }

    @FindBy(xpath = "//*[contains(text(),'Next')]")
    private WebElement Next;

    @FindBy(xpath = "//*[contains(text(),'Continue')]")
    private WebElement Continue;



    public void clickNext(){
        wait.until(ExpectedConditions.elementToBeClickable(Next));
        Next.click();
    }
    public void clickContinue(){
        wait.until(ExpectedConditions.elementToBeClickable(Continue));
        Continue.click();
    }


}
