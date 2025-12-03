package ui.pages;

import managers.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.NumberFormatter;

import java.time.Duration;

public class PlanPage {

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));
    private static final Logger logger = LogManager.getLogger(PlanPage.class);

    public PlanPage(){
        PageFactory.initElements(DriverManager.getDriver(),this);
    }

    @FindBy(name="Selfage")
    private WebElement SelfAge;

    @FindBy(name = "Spouseage")
    private WebElement SpouseAge;

    @FindBy(name = "Son0age")
    private WebElement FirstSonAge;

    @FindBy(name = "Daughter0age")
    private WebElement FirstDaughterAge;

    @FindBy(name = "pincode")
    private WebElement ProposerPincode;

    @FindBy(xpath = "//*[contains(text(),'Cover amount')]/parent::div/following-sibling::div[1]/button[2]")
    private WebElement increaseCoverAmount;

    @FindBy(xpath = "//*[contains(text(),'Cover amount')]/parent::div/following-sibling::div[1]/button[1]")
    private WebElement decreaseCoverAmount;

    @FindBy(xpath = "//*[contains(text(),'Cover amount')]/parent::div/following-sibling::div[1]/span")
    private WebElement getCoverAmount;

    @FindBy(xpath = "//*[contains(text(),'Pricing might change')]/following-sibling::form[1]//*[contains(text(),'Calculate Premium')]")
    private WebElement CalculatePremium;


    public void enterSelfAge(String age){
        wait.until(ExpectedConditions.elementToBeClickable(SelfAge));
        SelfAge.sendKeys(age);
    }

    public void enterSpouseAge(String age){
        wait.until(ExpectedConditions.elementToBeClickable(SpouseAge));
        SpouseAge.sendKeys(age);
    }

    public void enterSonAge(String age){
        wait.until(ExpectedConditions.elementToBeClickable(FirstSonAge));
        FirstSonAge.sendKeys(age);
    }

    public void enterDaughterAge(String age){
        wait.until(ExpectedConditions.elementToBeClickable(FirstDaughterAge));
        FirstDaughterAge.sendKeys(age);
    }

    public void enterProposerPincode(String age){
        wait.until(ExpectedConditions.elementToBeClickable(ProposerPincode));
        ProposerPincode.sendKeys(age);
    }

    public String getCoverAmount(){
        NumberFormatter nf = new NumberFormatter();
        wait.until(ExpectedConditions.elementToBeClickable(getCoverAmount));
        String recommendedCover = nf.getCleansedCover(getCoverAmount.getText());
        logger.info("Captured Recommended cover : "+recommendedCover);
        return recommendedCover;
    }

    public void increaseCoverAmount(){
        wait.until(ExpectedConditions.elementToBeClickable(increaseCoverAmount));
        increaseCoverAmount.click();
    }

    public void decreaseCoverAmount(){
        wait.until(ExpectedConditions.elementToBeClickable(decreaseCoverAmount));
        decreaseCoverAmount.click();
    }

    public void calculatePremium(){
        wait.until(ExpectedConditions.elementToBeClickable(CalculatePremium));
        CalculatePremium.click();
    }

    public void enterSonsAge(int index, String age){
        String name = "Son"+index+"age";
        WebElement sonInput = DriverManager.getDriver().findElement(By.name(name));
        sonInput.clear();
        sonInput.sendKeys(age);
    }
    public void enterDaughtersAge(int index, String age){
        String name = "Daughter"+index+"age";
        WebElement daughterInput = DriverManager.getDriver().findElement(By.name(name));
        daughterInput.clear();
        daughterInput.sendKeys(age);
    }


}
