Feature: Add a Meal
  As a SmartEats user
  I want to add a predefined meal from the application to my menu/options
  So that I can easily incorporate recommended meals into my dietary plan

  Scenario: Add a Meal to User's Menu (Normal Flow)
    Given the user is logged into the SmartEats application
    When the user navigates to the "Add a Meal" section
    And the user selects a predefined meal from the available options
    And the user adds the selected meal to their menu/options
    Then the meal should be successfully added to the user's meal options
    And the nutritional information should be updated based on the added meal

  Scenario: Add a Meal that is Already in User's Menu (Error Flow)
    Given the user is logged into the SmartEats application
    And the user has previously added meals to their menu
    When the user navigates to the "Add a Meal" section
    And the user tries to add a meal that is already in their menu
    Then the system should display an error message indicating the meal is already in the menu
    And the meal should not be duplicated in the user's menu

  Scenario: Add a Nonexistent Meal (Error Flow)
    Given the user is logged into the SmartEats application
    When the user navigates to the "Add a Meal" section
    And the user attempts to add a meal that does not exist in the application
    Then the system should display an error message indicating the meal is not found
    And the meal should not be added to the user's menu

