@TC001

Feature: Health Insurance Validation

  Scenario Outline: Health Insurance Premium Validation
    Given I launch the application
    And I select "Optima Secure"
    And I proceed to click Next 3 times to navigate from Policy Info Page
    And I fill "<SelfGender>" and "<SpouseGender>"
    And I add 1 son(s) and 1 daughter(s) and proceed to Plan page
    And I enter "<SelfAge>", "<SpouseAge>", "<SonAge>", "<DaughterAge>" and "<ProposerPinCode>"
    And I select 10L as the cover amount
    #And I calculate the premium

    Examples:

      | SelfGender | SpouseGender | SelfAge | SpouseAge | SonAge | DaughterAge | ProposerPinCode |
      | Male       | Female       | 45      | 40        | 18     | 10          | 560067          |