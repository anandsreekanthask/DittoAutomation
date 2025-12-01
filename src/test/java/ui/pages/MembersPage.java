package ui.pages;

import managers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MembersPage {

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

    public MembersPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(xpath = "//*[contains(text(),'Next step')]")
    private WebElement NextStep;

    //Self Locators
    @FindBy(xpath = "//*[text()='Self']/following-sibling::div[1]/div[contains(text(),'Male')]")
    private WebElement selfMale;

    @FindBy(xpath = "//*[text()='Self']/following-sibling::div[1]/div[contains(text(),'Female')]")
    private WebElement selfFemale;

    //Spouse Locators
    @FindBy(xpath = "//*[text()='Spouse']/following-sibling::div[1]/div[contains(text(),'Male')]")
    private WebElement spouseMale;

    @FindBy(xpath = "//*[text()='Spouse']/following-sibling::div[1]/div[contains(text(),'Female')]")
    private WebElement spouseFemale;

    //Son(s) locators
    @FindBy(xpath = "//*[text()='Son']/following-sibling::div[1]/button[2]")
    private WebElement addSon;

    //Daughter(s) locators
    @FindBy(xpath = "//*[text()='Daughter']/following-sibling::div[1]/button[2]")
    private WebElement addDaughter;

    public void maleInsured() {
        wait.until(ExpectedConditions.elementToBeClickable(selfMale));
        selfMale.click();
    }

    public void femaleInsured() {
        wait.until(ExpectedConditions.elementToBeClickable(selfFemale));
        selfFemale.click();
    }

    public void maleSpouse() {
        wait.until(ExpectedConditions.elementToBeClickable(spouseMale));
        spouseMale.click();
    }

    public void femaleSpouse() {
        wait.until(ExpectedConditions.elementToBeClickable(spouseFemale));
        spouseFemale.click();
    }

    public void addSon() {
        wait.until(ExpectedConditions.elementToBeClickable(addSon));
        addSon.click();
    }

    public void addDaughter() {
        wait.until(ExpectedConditions.elementToBeClickable(addDaughter));
        addDaughter.click();
    }

    public void clickNext(){
        wait.until(ExpectedConditions.elementToBeClickable(NextStep));
        NextStep.click();
    }


    public void identifySelfGender(String selfGender) {
        switch (selfGender) {
            case "Male":
                maleInsured();
                break;
            case "Female":
                femaleInsured();
                break;
            default:
                throw new RuntimeException("Specified Gender not available for self");
        }
    }

    public void identifySpouseGender(String spouseGender){
        switch(spouseGender){
            case "Male":
                maleSpouse();
                break;
            case "Female":
                femaleSpouse();
                break;
            default:
                throw new RuntimeException("Specified Gender not available for spouse");
        }
    }

    public void addSons(int count){
        while (count > 0){
            addSon();
            --count;
        }
    }

    public void addDaughters(int count){
        while (count > 0){
            addDaughter();
            --count;
        }
    }


}
