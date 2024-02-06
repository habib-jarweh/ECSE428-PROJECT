Feature: Add and Update Dietary Restrictions in SmartEats

  As a user of SmartEats,
  I want to add and update my dietary restrictions,
  So that I can receive meal recommendations that adhere to my dietary needs.

  Scenario Outline: Successfully Adding a New Dietary Restriction
    Given I am logged into my SmartEats account
    When I navigate to the Dietary Restrictions section
    And I add a new dietary restriction "<restriction>"
    Then the new dietary restriction should be saved to my profile
    And I should see a confirmation message

    Examples:
      | restriction         |
      | Gluten-Free         |
      | Nut Allergy         |
      | Vegan               |
      | Dairy-Free          |
      | Low-Carb            |

  Scenario Outline: Successfully Updating an Existing Dietary Restriction
    Given I am logged into my SmartEats account
    And I have previously added a dietary restriction "<existing_restriction>"
    When I update the restriction to "<new_restriction>"
    Then the dietary restriction should be updated in my profile
    And I should see a confirmation message

    Examples:
      | existing_restriction | new_restriction   |
      | Vegetarian           | Vegan             |
      | No Seafood           | Pescatarian       |

  Scenario: Adding a Duplicate Dietary Restriction
    Given I am logged into my SmartEats account
    And I have already added a dietary restriction "Vegan"
    When I attempt to add the same dietary restriction "Vegan" again
    Then I should see an error message indicating the restriction already exists
    And the duplicate restriction should not be added to my profile

  Scenario: Adding an Invalid Dietary Restriction
    Given I am logged into my SmartEats account
    When I attempt to add an invalid dietary restriction "Ultra-Fast Food"
    Then I should see an error message indicating the restriction is not recognized
    And the invalid restriction should not be added to my profile
