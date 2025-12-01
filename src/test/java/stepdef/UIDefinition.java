package stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import managers.DriverManager;
import org.testng.Assert;
import ui.pages.LandingPage;
import ui.pages.MembersPage;
import ui.pages.PlanPage;
import ui.pages.PolicyPage;
import utils.ConfigReader;

public class UIDefinition {

    private final ConfigReader configReader;
    private LandingPage landingPage;
    private PolicyPage policyPage;
    private MembersPage membersPage;
    private PlanPage planPage;

    public UIDefinition(ConfigReader configReader) {

        this.configReader = configReader;
    }

    @Given("I launch the application")
    public void iLaunchTheApplication() {
        String url = configReader.getBaseURL();
        DriverManager.getDriver().get(url);
    }


    @And("I select {string}")
    public void iSelect(String plan) {
        landingPage = new LandingPage();
        landingPage.identifyPlan(plan);
    }

    @And("I proceed to click Next {int} times to navigate from Policy Info Page")
    public void iProceedToClickNextTimesToNavigateFromPolicyInfoPage(int count) {
        policyPage = new PolicyPage();
        while (count > 0){
            policyPage.clickNext();
            --count;
        }
        policyPage.clickContinue();
    }

    @And("I fill {string} and {string}")
    public void iFillSelfAndSpouse(String selfGender, String spouseGender)  {
        membersPage = new MembersPage();
        membersPage.identifySelfGender(selfGender);
        membersPage.identifySpouseGender(spouseGender);
    }

    @And("I add {int} son\\(s) and {int} daughter\\(s) and proceed to Plan page")
    public void iAddSonSAndDaughterSAndProceedToPlanPage(int sons, int daughters) {
        membersPage = new MembersPage();
        membersPage.addSons(sons);
        membersPage.addDaughters(daughters);
        membersPage.clickNext();
    }

    @And("I enter {string}, {string}, {string}, {string} and {string}")
    public void iEnterAnd(String selfAge, String spouseAge, String sonAge, String daughterAge, String pinCode) {
        planPage = new PlanPage();
        planPage.enterSelfAge(selfAge);
        planPage.enterSpouseAge(spouseAge);
        planPage.enterSonAge(sonAge);
        planPage.enterDaughterAge(daughterAge);
        planPage.enterProposerPincode(pinCode);
    }

    @And("I select {int}L as the cover amount")
    public void iSelectLAsTheCoverAmount(int expectedCoverAmount) throws InterruptedException{
        int recommendedCoverAmount;
        planPage = new PlanPage();
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

        Thread.sleep(7500);
    }
}
