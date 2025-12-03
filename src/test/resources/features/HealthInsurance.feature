@TC001

Feature: Health Insurance Validation

  Scenario Outline: Health Insurance Premium Validation
    Given I launch the application
    And I select "Optima Secure"
    And I proceed to click Next 3 times to navigate from Policy Info Page
    And I fill "<SelfGender>" and "<SpouseGender>"
    And I add 2 son(s) and 2 daughter(s) and proceed to Plan page
    And I enter "<SelfAge>", "<SpouseAge>" and "<ProposerPinCode>"
    And I enter children ages
      | type     | age |
      | daughter | 20  |
      | son      | 18  |
      | daughter | 10  |
      | son      | 12  |
    And I select "1 Cr" as the cover amount
    And I proceed to calculate the premium
    When I add the following riders
      | Unlimited Restoration | Optima Wellbeing | ABCD |
      | true                  | true             | true |
    Then I capture the total premium and validate the premium is calculated correctly for the riders
      | Unlimited Restoration | Optima Wellbeing | ABCD |
      | true                  | true             | true |

    Examples:

      | SelfGender | SpouseGender | SelfAge | SpouseAge | ProposerPinCode |
      | Male       | Female       | 45      | 40        | 560067          |