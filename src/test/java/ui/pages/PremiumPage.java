package ui.pages;

import managers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PremiumPage {

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

    public PremiumPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @FindBy(xpath = "//*[contains(text(),'Unlimited Restoration')]/../preceding-sibling::div[1]")
    private WebElement selectUnlimitedRestoration;

    @FindBy(xpath = "//*[contains(text(),'Optima Well-being')]/../preceding-sibling::div[1]")
    private WebElement selectOptimaWellBeing;

    @FindBy(name = "ABCD")
    private WebElement selectABCD;

    @FindBy(xpath = "//*[contains(text(),'Select the members')]/../following-sibling::div[1]//input[@type='checkbox']")
    private List<WebElement> memberCheckBox;

    @FindBy(xpath = "//*[contains(text(),'Confirm')]")
    private WebElement confirm;

    @FindBy(xpath = "//*[contains(text(),'Base Premium')]/following-sibling::span[1]")
    private WebElement basePremium;

    @FindBy(xpath = "//*[contains(text(),'Unlimited Restoration')]/../../../following-sibling::div[1]/div/span")
    private WebElement unlimitedRestorationPremium;

    @FindBy(xpath = "//*[contains(text(),'Optima Well-being')]/../../../following-sibling::div[1]/div/span")
    private WebElement optimaWellBeingPremium;

    @FindBy(xpath = "//*[contains(text(),'ABCD')]/../../../following-sibling::div[1]/div/span")
    private WebElement abcdPremium;

    @FindBy(xpath = "//*[contains(text(),'Total Premium')]/following-sibling::span[1]")
    private WebElement totalPremium;

    public void clickConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(confirm));
        confirm.click();
    }

    public String getBasePremium() {
        wait.until(ExpectedConditions.elementToBeClickable(basePremium));
        return basePremium.getText();
    }

    public String getUnlimitedRestorationPremium() {
        wait.until(ExpectedConditions.elementToBeClickable(unlimitedRestorationPremium));
        return unlimitedRestorationPremium.getText();
    }

    public String getOptimaWellBeingPremium() {
        wait.until(ExpectedConditions.elementToBeClickable(optimaWellBeingPremium));
        return optimaWellBeingPremium.getText();
    }

    public String getAbcdPremium() {
        wait.until(ExpectedConditions.elementToBeClickable(abcdPremium));
        return abcdPremium.getText();
    }

    public String getTotalPremium() {
        wait.until(ExpectedConditions.elementToBeClickable(totalPremium));
        return totalPremium.getText();
    }


    public void selectRider(String riderName) {
        switch (riderName.toLowerCase()) {

            case "unlimited restoration":
                wait.until(ExpectedConditions.elementToBeClickable(selectUnlimitedRestoration));
                selectUnlimitedRestoration.click();
                break;

            case "optima wellbeing":
                wait.until(ExpectedConditions.elementToBeClickable(selectOptimaWellBeing));
                selectOptimaWellBeing.click();
                break;

            case "abcd":
                wait.until(ExpectedConditions.elementToBeClickable(selectABCD));
                selectABCD.click();
                wait.until(ExpectedConditions.visibilityOfAllElements(memberCheckBox));
                for (WebElement item : memberCheckBox) {
                    wait.until(ExpectedConditions.elementToBeClickable(item));
                    item.click();
                }
                clickConfirm();
                break;
            default:
                throw new RuntimeException("Rider not available");

        }
    }

    public String getRiderPremium(String riderName) {
        switch (riderName.toLowerCase()) {

            case "unlimited restoration":
                return getUnlimitedRestorationPremium();
            case "optima wellbeing":
                return getOptimaWellBeingPremium();
            case "abcd":
                return getAbcdPremium();
            default:
                throw new RuntimeException("Rider not available");

        }
    }


}
