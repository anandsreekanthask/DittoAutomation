package stepdef;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import ui.pages.*;
import utils.ConfigReader;
import utils.NumberFormatter;

import java.util.List;
import java.util.Map;

public class UIDefinition {

    private final ConfigReader configReader;
    private LandingPage landingPage;
    private PolicyPage policyPage;
    private MembersPage membersPage;
    private PlanPage planPage;
    private PremiumPage premiumPage;
    private NumberFormatter numberFormatter;

    private static final Logger logger = LogManager.getLogger(UIDefinition.class);

    public UIDefinition(ConfigReader configReader) {

        this.configReader = configReader;
    }

    @Given("I launch the application")
    public void iLaunchTheApplication() {
        String url = configReader.getBaseURL();
        logger.info("Launching URL : "+url);
        DriverManager.getDriver().get(url);
    }


    @And("I select {string}")
    public void iSelect(String plan) {
        landingPage = new LandingPage();
        logger.info("Selecting plan : "+plan);
        landingPage.identifyPlan(plan);
    }

    @And("I proceed to click Next {int} times to navigate from Policy Info Page")
    public void iProceedToClickNextTimesToNavigateFromPolicyInfoPage(int count) throws InterruptedException {
        policyPage = new PolicyPage();
        while (count > 0){
            policyPage.clickNext();
            Thread.sleep(1000);
            --count;
        }
        policyPage.clickContinue();
    }

    @And("I fill {string} and {string}")
    public void iFillSelfAndSpouse(String selfGender, String spouseGender)  {
        membersPage = new MembersPage();
        logger.info("Selecting self gender as "+selfGender+" and spouse gender as "+spouseGender);
        membersPage.identifySelfGender(selfGender);
        membersPage.identifySpouseGender(spouseGender);
    }

    @And("I add {int} son\\(s) and {int} daughter\\(s) and proceed to Plan page")
    public void iAddSonSAndDaughterSAndProceedToPlanPage(int sons, int daughters) {
        membersPage = new MembersPage();
        logger.info("Adding "+sons+" son(s) and "+daughters+" daughters");
        membersPage.addSons(sons);
        membersPage.addDaughters(daughters);
        membersPage.clickNext();
    }

    @And("I enter {string}, {string}, {string}, {string} and {string}")
    public void iEnterAnd(String selfAge, String spouseAge, String sonAge, String daughterAge, String pinCode) {
        planPage = new PlanPage();
        logger.info("Passing self age as "+selfAge);
        planPage.enterSelfAge(selfAge);
        logger.info("Passing spouse age as "+spouseAge);
        planPage.enterSpouseAge(spouseAge);
        logger.info("Passing son age as "+sonAge);
        planPage.enterSonAge(sonAge);
        logger.info("Passing daughter age as "+daughterAge);
        planPage.enterDaughterAge(daughterAge);
        logger.info("Passing pincode age as "+pinCode);
        planPage.enterProposerPincode(pinCode);
    }

    @And("I select {string} as the cover amount")
    public void iSelectLAsTheCoverAmount(String coverAmount) throws InterruptedException{
        int recommendedCoverAmount,expectedCoverAmount;
        expectedCoverAmount = Integer.parseInt(coverAmount.replaceAll("[^0-9]", ""));
        logger.info("Captured expected cover : "+expectedCoverAmount);
        planPage = new PlanPage();
        if(coverAmount.contains("Cr")){
            do{
                planPage.increaseCoverAmount();
                recommendedCoverAmount = Integer.parseInt(planPage.getCoverAmount());
            } while (recommendedCoverAmount != expectedCoverAmount);
        }
        recommendedCoverAmount = Integer.parseInt(planPage.getCoverAmount());
        if(expectedCoverAmount == recommendedCoverAmount)
            Assert.assertTrue(true);
        else if(expectedCoverAmount > recommendedCoverAmount)
        {
            do{
                planPage.increaseCoverAmount();
                recommendedCoverAmount = Integer.parseInt(planPage.getCoverAmount());
            }while ((expectedCoverAmount > recommendedCoverAmount) && (expectedCoverAmount != recommendedCoverAmount));
        }
        else
        {
            do{
                planPage.decreaseCoverAmount();
                recommendedCoverAmount = Integer.parseInt(planPage.getCoverAmount());
            }while ((expectedCoverAmount < recommendedCoverAmount) && (expectedCoverAmount != recommendedCoverAmount));
        }
    }

    @And("I proceed to calculate the premium")
    public void iProceedToCalculateThePremium() {
        planPage = new PlanPage();
        planPage.calculatePremium();
    }

    @When("I add the following riders")
    public void iAddTheFollowingRiders(DataTable table) throws InterruptedException {
        premiumPage = new PremiumPage();
        List<Map<String,String>> data = table.asMaps(String.class,String.class);
        Map<String,String> riderMap = data.getFirst();

        for(Map.Entry<String,String> entry : riderMap.entrySet()){
            String riderName = entry.getKey();
            boolean isSelected = Boolean.parseBoolean(entry.getValue());
                if(isSelected){
                    premiumPage.selectRider(riderName);
                    Thread.sleep(3500);
                }
        }
    }

    @Then("I capture the total premium and validate the premium is calculated correctly for the riders")
    public void iCaptureTheTotalPremiumAndValidateThePremiumIsCalculatedCorrectlyForTheRiders(DataTable table) {
        premiumPage = new PremiumPage();
        numberFormatter = new NumberFormatter();
        float sum = Float.parseFloat(numberFormatter.getCleansedPremium(premiumPage.getBasePremium()));
        List<Map<String,String>> data = table.asMaps(String.class,String.class);
        Map<String,String> riderMap = data.getFirst();
        logger.info("Captured amount for Base Premium : "+premiumPage.getBasePremium());
        for(Map.Entry<String,String> entry : riderMap.entrySet()){
            String riderName = entry.getKey();
            boolean isSelected = Boolean.parseBoolean(entry.getValue());
            if(isSelected){
                logger.info("Captured amount for "+riderName+" = "+premiumPage.getRiderPremium(riderName));
                sum += Float.parseFloat(numberFormatter.getCleansedPremium(premiumPage.getRiderPremium(riderName)));
            }
        }
        logger.info("Captured amount for Total Premium : "+premiumPage.getTotalPremium());
        logger.info("Final Actual Sum : "+numberFormatter.getCleansedPremium(premiumPage.getTotalPremium()));
        logger.info("Final Calculated Sum : "+sum);
        Assert.assertEquals(Float.parseFloat(numberFormatter.getCleansedPremium(premiumPage.getTotalPremium())),sum,
                "Assertion error while validating premium");

    }
}
