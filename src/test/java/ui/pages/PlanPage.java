package ui.pages;

import managers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.NumberFormatter;

import java.time.Duration;

public class PlanPage {

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

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

    @FindBy(xpath = "//*[contains(@class,'Slider-root')]")
    private WebElement Slider;

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
        System.out.println("Captured Recommended cover : "+recommendedCover);
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




}
